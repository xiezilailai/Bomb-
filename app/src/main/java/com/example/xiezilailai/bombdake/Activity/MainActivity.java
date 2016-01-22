package com.example.xiezilailai.bombdake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
    private Button signUp,login,information,community,sendPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "88616cff6f37b340c5281c9563c248f6");

        signUp=(Button)findViewById(R.id.button1);
        login=(Button)findViewById(R.id.button2);
        information=(Button)findViewById(R.id.button3);
        community=(Button)findViewById(R.id.button4);
        sendPost=(Button)findViewById(R.id.button5);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,InformationActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CommunityActivity.class);
                startActivity(intent);
            }
        });
        sendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SendPostActivity.class);
                startActivity(intent);
            }
        });



    }
}
