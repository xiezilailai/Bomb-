package com.example.xiezilailai.bombdake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 蝎子莱莱123 on 2016/1/20.
 */
public class InformationActivity extends AppCompatActivity{
    private EditText nickName,age,sex;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_view);

        init();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyUser newUser=new MyUser();
                newUser.setNick(nickName.getText().toString());
                newUser.setSex(sex.getText().toString().equals("男"));
                newUser.setAge(Integer.parseInt(age.getText().toString()));

                newUser.update(InformationActivity.this, MyUser.getCurrentUser(InformationActivity.this, MyUser.class).getObjectId(), new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(InformationActivity.this, "success", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(InformationActivity.this, s, Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });
    }

    private void init() {
        nickName=(EditText)findViewById(R.id.info_nick_name);
        age=(EditText)findViewById(R.id.info_age);
        sex=(EditText)findViewById(R.id.info_sex);

        MyUser user= BmobUser.getCurrentUser(InformationActivity.this, MyUser.class);
        if(user!=null){
            String currentUser=(String)BmobUser.getObjectByKey(InformationActivity.this,"nick");
            Integer currrentAge=(Integer)BmobUser.getObjectByKey(InformationActivity.this,"age");
            Boolean currentSex=(Boolean)BmobUser.getObjectByKey(InformationActivity.this,"sex");
            nickName.setText(currentUser);
            age.setText(currrentAge+"");
            if(currentSex==Boolean.TRUE){
                sex.setText("男");
            }else if(currentSex==Boolean.FALSE){
                sex.setText("女");
            }
        }else{
            Toast.makeText(InformationActivity.this,"请先登陆",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(InformationActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        save=(Button)findViewById(R.id.save);

    }
}
