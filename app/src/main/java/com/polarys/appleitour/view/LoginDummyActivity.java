package com.polarys.appleitour.view;

import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.viewmodel.SignViewModel;

import org.json.JSONException;

public class LoginDummyActivity extends AppCompatActivity {

    private EditText edit_Username;
    private EditText edit_password;
    private Button btn_Login;
    private Button btn_register;
    private SignViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dummy);
        viewModel = ViewModelProviders.of(this).get(SignViewModel.class);
        edit_Username = findViewById(R.id.etUsername);
        edit_password = findViewById(R.id.etPassword);
        btn_Login = findViewById(R.id.btnLogin);
        btn_register = findViewById(R.id.btnToRegister);
        ViewHelper viewHelper = new ViewHelper(this);
        btn_Login.setOnClickListener(v -> {
            String email = viewHelper.getTextOfEdit(R.id.etUsername);
            if(viewHelper.isEmpty(email,"Email"))
                return;
            String password = viewHelper.getTextOfEdit(R.id.etPassword);
            if(viewHelper.isEmpty(password,"Senha"))
                return;
            User logginUser = new User(email, password);
            viewModel.setUser(logginUser);
            viewModel.setContext(this);
            //TODO: keep logged Check box
            viewModel.setKeepLogged(false);
            if(!verifyConectivity(this))
                return;
            try{
                if(viewModel.login() == null)
                    return;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(BookApiActivity.class);
        });

        btn_register.setOnClickListener(v -> {
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(RegisterActivity.class);
        });
    }
}