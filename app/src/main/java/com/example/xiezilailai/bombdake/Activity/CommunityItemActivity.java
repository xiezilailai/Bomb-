package com.example.xiezilailai.bombdake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 蝎子莱莱123 on 2016/1/21.
 */
public class CommunityItemActivity extends AppCompatActivity {
    private TextView title,content;
    private Button dianzan,talk;
    private ListView listView;
    private String id;
    private Post thisPost=new Post();
    private int mark=0;
    private Boolean isDianZan=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_item_view);

        init();


        Intent intent=getIntent();
        id=intent.getStringExtra("id");

        //查询post详细信息
        BmobQuery<Post>query=new BmobQuery<Post>();
        query.getObject(CommunityItemActivity.this, id, new GetListener<Post>() {
            @Override
            public void onSuccess(Post post) {
                title.setText(post.getTitle());
                content.setText(post.getContent());
                thisPost = post;
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(CommunityItemActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
        //查询点赞人数（relation）
        BmobQuery<MyUser>userBmobQuery=new BmobQuery<>();
        Post post1=new Post();
        post1.setObjectId(id);
        userBmobQuery.addWhereRelatedTo("likes", new BmobPointer(post1));
        userBmobQuery.findObjects(CommunityItemActivity.this, new FindListener<MyUser>() {
            @Override
            public void onSuccess(List<MyUser> list) {
                mark=list.size();
                if(mycontains(list,MyUser.getCurrentUser(CommunityItemActivity.this, MyUser.class))) {
                    dianzan.setText("已点赞("+list.size()+")");
                    isDianZan=true;
                }
                    else {
                    dianzan.setText("未点赞("+list.size()+")");
                }

            }

            @Override
            public void onError(int i, String s) {

            }
        });





        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommunityItemActivity.this, CommentActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
        dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //该用户点赞，添加relation
                MyUser myUser=MyUser.getCurrentUser(CommunityItemActivity.this,MyUser.class);
                BmobRelation relation=new BmobRelation();
                relation.add(myUser);
                thisPost.setLikes(relation);
                thisPost.update(CommunityItemActivity.this, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        if(isDianZan){
                            Toast.makeText(CommunityItemActivity.this,"已点赞",Toast.LENGTH_SHORT).show();
                        }else{
                            dianzan.setText("已点赞("+(++mark)+")");
                            Toast.makeText(CommunityItemActivity.this,"success",Toast.LENGTH_SHORT).show();
                            isDianZan=true;
                        }

                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(CommunityItemActivity.this,s,Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });








    }

    @Override
    protected void onResume() {
        super.onResume();
        BmobQuery<Comment>commentBmobQuery=new BmobQuery<>();
        final Post post = new Post();
        post.setObjectId(id);
        commentBmobQuery.addWhereEqualTo("post", new BmobPointer(post));
        commentBmobQuery.include("user,post.author");
        commentBmobQuery.findObjects(CommunityItemActivity.this, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> list) {
                CommentAdapter adapter = new CommentAdapter(list, CommunityItemActivity.this);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    private boolean mycontains(List<MyUser>myUsers,MyUser currentUser) {
        for(MyUser i :myUsers){
            if(i.getObjectId().equals(currentUser.getObjectId())){
                return true;
            }
        }
        return false;
    }

    private void init() {
        title=(TextView)findViewById(R.id.community_item_title);
        content=(TextView)findViewById(R.id.community_item_content);
        dianzan=(Button)findViewById(R.id.community_item_zan);
        talk=(Button)findViewById(R.id.community_item_talk);
        listView=(ListView)findViewById(R.id.community_item_listview);
    }
}
