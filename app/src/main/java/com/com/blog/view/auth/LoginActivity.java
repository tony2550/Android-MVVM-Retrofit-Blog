package com.com.blog.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.bean.LoginUser;
import com.com.blog.repository.dto.LoginDto;
import com.com.blog.view.InitActivity;
import com.com.blog.view.post.PostListActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity implements InitActivity {

    private static final String TAG = "LoginActivity";
    private LoginActivity mContext = LoginActivity.this;
    private TextInputEditText tfUsername, tfPassword;
    private MaterialButton btnLogin;
    private TextView tvLinkJoin;
    private LoginViewModel loginViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        initLr();
        initObserve();
        initSetting();
    }

    @Override
    public void init() {
        tfUsername = findViewById(R.id.tfUsername);
        tfPassword = findViewById(R.id.tfPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvLinkJoin = findViewById(R.id.tvLinkJoin);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    }

    @Override
    public void initObserve() {
        loginViewModel.getLoginUser().observe(this, loginUser -> {
            Intent intent = new Intent(
                    mContext,
                    PostListActivity.class
            );
            startActivity(intent);
        });
    }


    @Override
    public void initLr() {
        btnLogin.setOnClickListener(v -> {
            String username = tfUsername.getText().toString().trim(); // trim() 공백 지우기
            String password = tfPassword.getText().toString().trim();
            LoginDto loginDto = new LoginDto(username,password);
            loginViewModel.loginClick(loginDto);
        });
    }

    @Override
    public void initSetting() {

    }


}