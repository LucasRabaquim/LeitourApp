package com.polarys.appleitour.view;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.model.SharedSettings;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.viewmodel.LoginViewModel;

import org.json.JSONObject;

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
            String email = "lucas@gmail.com";//edit_Username.getText().toString();
            String password = "12345";//edit_Username.getText().toString();
            User logginUser = new User("lucas@gmail.com", "12345");
            viewModel.setContext(this);
            String result = viewModel.login(logginUser);
            if(result == "")
                return;
            this.finish();
            Intent intent = new Intent(this, PlaceHolderActivity.class);
            intent.putExtra("USER", result);
            startActivity(intent);
        });
    }
}