package com.example.xiezilailai.bombdake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 蝎子莱莱123 on 2016/1/21.
 */
public class CommentAdapter extends BaseAdapter {
    private List<Comment>list;
    private Context context;
    public CommentAdapter(List<Comment> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Comment getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view1=inflater.inflate(R.layout.comment_item_view, null);
        ViewHolder2 viewHolder= new ViewHolder2();
        viewHolder.user=(TextView)view1.findViewById(R.id.comment_item_user);
        viewHolder.content=(TextView)view1.findViewById(R.id.comment_item_content);
        viewHolder.user.setText(getItem(i).getUser().getNick());
        viewHolder.content.setText(getItem(i).getContent());

        return view1;

    }
    class ViewHolder2{
        TextView user;
        TextView content;
    }
}
