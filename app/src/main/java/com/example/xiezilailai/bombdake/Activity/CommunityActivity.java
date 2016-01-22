package com.example.xiezilailai.bombdake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 蝎子莱莱123 on 2016/1/20.
 */
public class CommunityActivity extends AppCompatActivity{
    private List<Post>myList=new ArrayList<>();
    private ListView listView;
    private CommunityAdapter communityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_view);
        listView=(ListView)findViewById(R.id.listView);

        BmobQuery<Post>query=new BmobQuery<Post>();
        query.order("-updatedAt");
        query.findObjects(CommunityActivity.this, new FindListener<Post>() {
            @Override
            public void onSuccess(List<Post> list) {
                myList=list;
                communityAdapter = new CommunityAdapter(list, CommunityActivity.this);
                listView.setAdapter(communityAdapter);
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(CommunityActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent=new Intent();
                 intent.putExtra("id",myList.get(i).getObjectId());
                 intent.setClass(CommunityActivity.this, CommunityItemActivity.class);
                 startActivity(intent);
             }
         });
    }
}
