package com.example.xiezilailai.bombdake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 蝎子莱莱123 on 2016/1/21.
 */
public class CommentActivity extends AppCompatActivity {
    private EditText commentContent;
    private Button publish;
    private String postId;
    private Post thepost;
    private MyUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_view);

        commentContent=(EditText)findViewById(R.id.comment_content);
        publish=(Button)findViewById(R.id.comment_publish);

        Intent intent=getIntent();
        postId=intent.getStringExtra("id");

        BmobQuery<com.example.xiezilailai.bombdake.Post>query=new BmobQuery<>();
        query.getObject(CommentActivity.this, postId, new GetListener<Post>() {
            @Override
            public void onSuccess(Post post) {
                thepost = post;
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

         user=MyUser.getCurrentUser(CommentActivity.this,MyUser.class);


        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment=new Comment();
                comment.setContent(commentContent.getText().toString());
                comment.setPost(thepost);
                comment.setUser(user);

                comment.save(CommentActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(CommentActivity.this,"success",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            }
        });

    }
}
