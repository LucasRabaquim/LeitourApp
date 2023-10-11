package com.polarys.appleitour.view;

import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.viewmodel.LoginViewModel;

import org.json.JSONException;

public class LoginDummyActivity extends AppCompatActivity {

    private EditText edit_Username;
    private EditText edit_password;
    private Button btn_Login;
    private Button btn_register;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dummy);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        edit_Username = findViewById(R.id.etUsername);
        edit_password = findViewById(R.id.etPassword);
        btn_Login = findViewById(R.id.btnLogin);
        edit_Username.setText(viewModel.getEmail());
        edit_password.setText(viewModel.getPassword());

        btn_Login.setOnClickListener(v -> {
            String email = edit_Username.getText().toString();
            String password = edit_Username.getText().toString();
            User logginUser = new User(email, password);
            viewModel.setUser(logginUser);
            viewModel.setContext(this);
            String result;
            if(!verifyConectivity(this))
                return;
            try{
                viewModel.login();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(PlaceHolderActivity.class);
        });
    }
}