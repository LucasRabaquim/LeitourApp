package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.viewmodel.SignViewModel;

import org.json.JSONException;

public class LoginDummyActivity extends AppCompatActivity {

    private EditText edit_Username;
    private EditText edit_password;
    private CheckBox checkBox;
    private Button btn_Login;
    private Button btn_register;
    private SignViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dummy);
        viewModel = new ViewModelProvider(this).get(SignViewModel.class);
        edit_Username = findViewById(R.id.etUsername);
        edit_password = findViewById(R.id.etPassword);
        checkBox = findViewById(R.id.cbKeepLogged);
        btn_Login = findViewById(R.id.btnLogin);
        btn_register = findViewById(R.id.btnToRegister);
        btn_Login.setOnClickListener(v -> {

            String email = ((EditText) findViewById(R.id.etUsername)).getText().toString();
            if(email.isEmpty()){
                Toast.makeText(this,"Preencha o campo email",Toast.LENGTH_SHORT).show();
                return;
            }
            String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
            if(password.isEmpty()){
                Toast.makeText(this,"Preencha o campo senha",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!verifyConectivity(this)) {
                Toast.makeText(this, "Verifique sua conexão", Toast.LENGTH_SHORT).show();
                return;
            }
            try{
                String[] response = viewModel.login(new User(email,password));
                if(response[1] == null) {
                    Toast.makeText(this, "Erro: " + response[0], Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedHelper settings = new SharedHelper(this);
                settings.SetKeepLogged(checkBox.isChecked());
                settings.SetToken(response[1]);
                User user = (User) JsonToObject(new User(), response[0]);
                settings.SetUser(user);
                Toast.makeText(this, "Seja bem-vindo(a): " + user.getNameUser(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);
            }
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(PlaceholderActivity.class);
        });

        btn_register.setOnClickListener(v -> {
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(RegisterActivity.class);
        });
    }
}