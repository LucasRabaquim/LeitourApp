package com.polarys.appleitour.view.fragment;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
import com.polarys.appleitour.view.activity.SignActivity;
import com.polarys.appleitour.viewmodel.UserViewModel;
import org.json.JSONException;

public class LoginFragment extends Fragment {

    private EditText edit_email,edit_password;
    private CheckBox checkBox;
    private Button btn_Login;
    private TextView link_register;
    private UserViewModel viewModel;
    private User user;
    public LoginFragment(){}
    public LoginFragment(UserViewModel viewModel){this.viewModel = viewModel;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_login,container,false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = viewModel.GetUser();
        declareUi(view,user);
        View rootView = getActivity().getWindow().getDecorView().getRootView();
        UIHelper uiHelper = new UIHelper(getContext(),rootView);
        btn_Login.setOnClickListener(v -> {
            btn_Login.setClickable(false);
            user = getUserData();
            String result = viewModel.VerifyFields(user);
            if(result != viewModel.SUCCESS) { // Validate fields
                uiHelper.showSnackBar(result);
                btn_Login.setClickable(true);
                return;
            }
            if(!verifyConectivity(getContext())) { // Verify Internet
                uiHelper.showSnackBar(R.string.string_connection);
                btn_Login.setClickable(true);
                return;
            }
            String[] response = viewModel.login(user);
            if(response[1] == null) { // Verify success on login
                uiHelper.showSnackBar(response[0]);
                btn_Login.setClickable(true);
                return;
            }
            saveUser(response);
            uiHelper.showSnackBar(this.getResources().getString(R.string.string_welcome) + user.GetNameUser());
            getActivity().finish();
            IntentHelper intentHelper = new IntentHelper(getActivity(),IntentHelper.USER_SHARED);
            intentHelper.nextActivity(PlaceholderActivity.class);
            btn_Login.setClickable(true);
        });

        link_register.setOnClickListener(v -> { // Switch to Register screen
            viewModel.SetUser(getUserData());
            ((SignActivity) getActivity()).loadFragment(new RegisterFragment(viewModel));
        });
    }
    private void declareUi(View view, User user){
        edit_email = view.findViewById(R.id.edit_email);
        edit_password = view.findViewById(R.id.edit_password);
        checkBox = view.findViewById(R.id.cbKeepLogged);
        btn_Login = view.findViewById(R.id.btnLogin);
        link_register = view.findViewById(R.id.txt_register);
        if(user != null){ // Set data between Fragments
            edit_email.setText(user.GetEmail());
            edit_password.setText(user.GetPassword());
        }
    }
    private User getUserData(){ // Get Data from fields
        String email = edit_email.getText().toString().trim();
        String password = edit_password.getText().toString().trim();
        return new User(email,password);
    }
    private void saveUser(String[] response){
        SharedHelper settings = new SharedHelper(getContext());
        settings.SetKeepLogged(checkBox.isChecked()); // Save preference
        settings.SetToken(response[1]); // Save Token
        User user = (User) JsonToObject(new User(), response[0]);
        settings.SetUser(user); // Save User
    }
}