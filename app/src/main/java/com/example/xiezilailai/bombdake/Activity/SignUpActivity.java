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
public class SignUpActivity extends AppCompatActivity{
    private Button sure;
    private EditText userName,secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_view);
        init();




        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUser user=new MyUser();
                user.setUsername(userName.getText().toString());
                user.setPassword(secret.getText().toString());

                user.signUp(SignUpActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(SignUpActivity.this,"success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(SignUpActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void init() {
        sure=(Button)findViewById(R.id.sure);
        userName=(EditText)findViewById(R.id.sign_up_user_name);
        secret=(EditText)findViewById(R.id.sign_up_secret);
    }
}
