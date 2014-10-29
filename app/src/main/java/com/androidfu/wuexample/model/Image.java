
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;

public class Image {

    @Expose
    private String url;
    @Expose
    private String title;
    @Expose
    private String link;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
