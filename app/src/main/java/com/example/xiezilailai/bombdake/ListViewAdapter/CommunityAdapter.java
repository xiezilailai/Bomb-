package com.example.xiezilailai.bombdake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 蝎子莱莱123 on 2016/1/20.
 */
public class CommunityAdapter extends BaseAdapter {
    private List<Post>list;
    private Context context;
    public CommunityAdapter(List<Post>list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view1=layoutInflater.inflate(R.layout.post_item_view, null);
        ViewHolder viewHolder=new ViewHolder();
        viewHolder.title=(TextView)view1.findViewById(R.id.post_item_title);
        viewHolder.content=(TextView)view1.findViewById(R.id.post_item_content);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.content.setText(list.get(i).getContent());
        return view1;
    }
    class ViewHolder{
        public TextView title;

        public TextView content;
    }
}
