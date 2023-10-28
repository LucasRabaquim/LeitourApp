package com.polarys.appleitour.view;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.fragment.BookApiFragment;
import com.polarys.appleitour.viewmodel.SignViewModel;

import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {

    private EditText edit_Username;
    private EditText edit_Email;
    private EditText edit_password;
    private EditText edit_repeat_password;
    private CheckBox checkBox;
    private Button btn_Login;
    private Button btn_register;
    private SignViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(SignViewModel.class);
        checkBox = findViewById(R.id.cbKeepLogged);
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
            if(viewHelper.isEmpty(password,"Verificar senha"))
                return;
            String repeatPassword = viewHelper.getTextOfEdit(R.id.etRepeatPassword);
            if(viewHelper.isDifferent(repeatPassword,"Verificar senha",password,"senha"))
                return;

            User registeringUser = new User(name,email, password);
            viewModel.setUser(registeringUser);
            viewModel.setContext(this);
            viewModel.setKeepLogged(checkBox.isChecked());
            if(!verifyConectivity(this))
                return;
            try{
                String[] response = viewModel.register();
                if(response== null)
                    return;
                SharedHelper settings = new SharedHelper(this);
                settings.SetKeepLogged(checkBox.isChecked());
                settings.SetToken(response[1]);
                User user = (User) JsonToObject(User.class,response[0]);
                settings.SetUser(user);
                Toast.makeText(this, "Seja bem-vindo(a): " + user.getNameUser(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(BookApiFragment.class);
        });

        btn_Login.setOnClickListener(v -> {
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(LoginDummyActivity.class);
        });
    }
}