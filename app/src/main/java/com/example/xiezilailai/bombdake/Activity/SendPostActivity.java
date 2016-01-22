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
public class SendPostActivity extends AppCompatActivity {
    private EditText title,content;
    private Button publish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_post_view);

        title=(EditText)findViewById(R.id.send_post_title);
        content=(EditText)findViewById(R.id.send_post_content);
        publish=(Button)findViewById(R.id.send_post_publish);

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUser user=MyUser.getCurrentUser(SendPostActivity.this,MyUser.class);
                Post post=new Post();
                post.setAuthor(user);
                post.setTitle(title.getText().toString());
                post.setContent(content.getText().toString());
                post.save(SendPostActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(SendPostActivity.this,"success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(SendPostActivity.this,s,Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
