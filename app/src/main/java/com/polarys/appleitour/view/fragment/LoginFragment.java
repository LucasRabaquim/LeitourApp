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
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
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
        edit_email = view.findViewById(R.id.edit_email);
        edit_password = view.findViewById(R.id.edit_password);
        checkBox = view.findViewById(R.id.cbKeepLogged);
        btn_Login = view.findViewById(R.id.btnLogin);
        link_register = view.findViewById(R.id.txt_register);
        user = viewModel.GetUser();
        if(user != null){ // Set data between Fragments
            edit_email.setText(user.GetEmail());
            edit_password.setText(user.GetPassword());
        }
        btn_Login.setOnClickListener(v -> {
            user = GetUserData();
            String result = viewModel.VerifyFields(user);
            if(result != viewModel.SUCCESS) { // Validate fields
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                return;
            }
            if(!verifyConectivity(getContext())) { // Verify Internet
                Toast.makeText(getContext(), "Verifique sua conexão", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] response = viewModel.login(user);
            if(response[1] == null) { // Verify success on login
                Toast.makeText(getContext(), "Erro: " + response[0], Toast.LENGTH_SHORT).show();
                return;
            }
            SharedHelper settings = new SharedHelper(getContext());
            settings.SetKeepLogged(checkBox.isChecked());
            settings.SetToken(response[1]);
            User user = (User) JsonToObject(new User(), response[0]);
            settings.SetUser(user);
            Toast.makeText(getContext(), "Seja bem-vindo(a): " + user.GetNameUser(), Toast.LENGTH_SHORT).show();
            getActivity().finish();
            IntentHelper intentHelper = new IntentHelper(getActivity(),IntentHelper.USER_SHARED);
            intentHelper.nextActivity(PlaceholderActivity.class);
        });

        link_register.setOnClickListener(v -> {
            viewModel.SetUser(GetUserData());
            ((SignActivity) getActivity()).loadFragment(new RegisterFragment(viewModel));
        });
    }
    private User GetUserData(){
        String email = edit_email.getText().toString();
        String password = edit_password.getText().toString();
        return new User(email,password);
    }
}