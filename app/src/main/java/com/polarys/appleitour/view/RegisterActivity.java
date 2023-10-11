package com.polarys.appleitour.view;

import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.viewmodel.SignViewModel;

import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {

    private EditText edit_Username;
    private EditText edit_Email;
    private EditText edit_password;
    private Button btn_Login;
    private Button btn_register;
    private SignViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(SignViewModel.class);
        edit_Username = findViewById(R.id.etFullName);
        edit_Email = findViewById(R.id.etPassword);
        edit_password = findViewById(R.id.etPassword);
        btn_Login = findViewById(R.id.btnToLogin);
        btn_register = findViewById(R.id.btnRegister);
        ViewHelper viewHelper = new ViewHelper(this);
        btn_register.setOnClickListener(v -> {
            String name = viewHelper.getTextOfEdit(R.id.etUsername);
            if(viewHelper.isEmpty(name,"Senha"))
                return;
            String email = viewHelper.getTextOfEdit(R.id.etEmail);
            if(viewHelper.isEmpty(email,"Email"))
                return;
            String password = viewHelper.getTextOfEdit(R.id.etPassword);
            if(viewHelper.isEmpty(password,"Senha"))
                return;
            User user = new User(name,email, password);
            viewModel.setUser(user);
            viewModel.setContext(this);
            viewModel.setKeepLogged(false);
            if(!verifyConectivity(this))
                return;
            try{
                if(viewModel.register() == null)
                    return;
            } catch (JSONException e) {
                Log.d("Fudeo",e.toString());
                throw new RuntimeException(e);
            }
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(BookApiActivity.class);
        });

        btn_Login.setOnClickListener(v -> {
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(LoginDummyActivity.class);
        });
    }
}