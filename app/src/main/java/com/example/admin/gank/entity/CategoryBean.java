package com.example.admin.gank.entity;

/**
 * Created by admin on 2017/9/5.
 */

public class CategoryBean {

    /**
     * _id : 59ac06be421aa901c85e6006
     * createdAt : 2017-09-03T21:42:22.920Z
     * desc : 如何自己实现一个 EventBus
     * publishedAt : 2017-09-05T11:29:05.240Z
     * source : web
     * type : Android
     * url : https://github.com/Werb/EventBusKotlin/wiki/%E5%A6%82%E4%BD%95%E8%87%AA%E5%B7%B1%E5%AE%9E%E7%8E%B0%E4%B8%80%E4%B8%AA-EventBus
     * used : true
     * who : Werb
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean getUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }
}
