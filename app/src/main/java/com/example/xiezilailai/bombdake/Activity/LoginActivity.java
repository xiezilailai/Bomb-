package com.example.xiezilailai.bombdake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 蝎子莱莱123 on 2016/1/20.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText userName,password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUser user=new MyUser();
                user.setUsername(userName.getText().toString());
                user.setPassword(password.getText().toString());
                user.login(LoginActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void init() {
        userName=(EditText)findViewById(R.id.login_user_name);
        password=(EditText)findViewById(R.id.login_password);
        login=(Button)findViewById(R.id.login);
    }
}
