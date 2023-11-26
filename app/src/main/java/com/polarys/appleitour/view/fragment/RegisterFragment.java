package com.polarys.appleitour.view.fragment;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;

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

import androidx.fragment.app.Fragment;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
import com.polarys.appleitour.view.activity.SignActivity;
import com.polarys.appleitour.viewmodel.UserViewModel;

import org.json.JSONException;

public class RegisterFragment extends Fragment {

    private EditText edit_Username;
    private EditText edit_Email;
    private EditText edit_password;
    private EditText edit_repeat_password;
    private CheckBox checkBox;
    private TextView btn_Login;
    private Button btn_register;
    private UserViewModel viewModel;
    private User user;

    public RegisterFragment() {}
    public RegisterFragment(UserViewModel viewModel){this.viewModel = viewModel;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkBox = view.findViewById(R.id.cbKeepLogged);
        btn_Login = view.findViewById(R.id.txt_login);
        btn_register = view.findViewById(R.id.btnRegister);
        edit_Username = view.findViewById(R.id.edit_username);
        edit_Email = view.findViewById(R.id.edit_email);
        edit_password = view.findViewById(R.id.edit_password);
        edit_repeat_password = view.findViewById(R.id.edit_compare_password);
        user = viewModel.GetUser();

        if (user != null) { // Set data between Fragments
            edit_Username.setText(user.GetNameUser());
            edit_Email.setText(user.GetEmail());
            edit_password.setText(user.GetPassword());
        }

        btn_register.setOnClickListener(v -> {
            user = GetUserData();
            String passwordCompare = edit_repeat_password.getText().toString();
            String result = viewModel.VerifyFields(user, passwordCompare);
            if (result != UserViewModel.SUCCESS) { // Validate fields
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!verifyConectivity(getContext())) { // Verify Internet
                Toast.makeText(getContext(), "Verifique sua conexão", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] response = viewModel.register(user);
            if (response[1] == null) { // Verify success on register
                Toast.makeText(getContext(), response[0], Toast.LENGTH_SHORT).show();
                return;
            }
            SharedHelper settings = new SharedHelper(getContext());
            settings.SetKeepLogged(checkBox.isChecked());
            settings.SetToken(response[1]);
            User user = (User) JsonToObject(new User(), response[0]);
            settings.SetUser(user);
            ((SignActivity) getActivity()).loadFragment(new UploadImageFragment(user));
        });

        btn_Login.setOnClickListener(v -> {
            viewModel.SetUser(GetUserData());
            ((SignActivity) getActivity()).loadFragment(new LoginFragment(viewModel));
        });
    }

    private User GetUserData() {
        String username = edit_Username.getText().toString();
        String email = edit_Email.getText().toString();
        String password = edit_password.getText().toString();
        return new User(username, email, password);
    }
}