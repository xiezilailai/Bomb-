package com.example.xiezilailai.bombdake;

import cn.bmob.v3.BmobObject;

/**
 * Created by 蝎子莱莱123 on 2016/1/20.
 */
public class Comment extends BmobObject {
    private String content;
    private MyUser user;
    private Post post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
