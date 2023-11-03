package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.verifyConectivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.LoginDummyActivity;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
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
        btn_register.setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.etUsername)).getText().toString();
            if(name.isEmpty()){
                Toast.makeText(this,"Preencha o campo email",Toast.LENGTH_SHORT).show();
                return;
            }
            String email = ((EditText) findViewById(R.id.etEmail)).getText().toString();
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
                String[] response = viewModel.register(new User(name,email,password));
                if(response[1] == null) {
                    Toast.makeText(this, response[0], Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedHelper settings = new SharedHelper(this);
                settings.SetKeepLogged(checkBox.isChecked());
                settings.SetToken(response[1]);
                User user = (User) JsonToObject(new User(),response[0]);
                settings.SetUser(user);
                Toast.makeText(this, "Seja bem-vindo(a): " + user.getNameUser(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(PlaceholderActivity.class);
        });

        btn_Login.setOnClickListener(v -> {
            IntentHelper intentHelper = new IntentHelper(this,IntentHelper.USER_SHARED);
            intentHelper.nextActivity(LoginDummyActivity.class);
        });
    }
    public static String getTextOfEdit(Activity activity, int id){
        EditText textView = activity.findViewById(id);
        try {
            return textView.getText().toString();
        }catch(Exception e){
            return "";
        }
    }

    public static boolean isEmpty(Activity activity, String data, String field){
        if(data == "")
            Toast.makeText(activity,"Preencha o campo "+field,Toast.LENGTH_SHORT).show();
        return (data == "");
    }
}