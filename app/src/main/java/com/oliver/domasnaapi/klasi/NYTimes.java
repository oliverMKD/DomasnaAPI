package com.oliver.domasnaapi.klasi;

import java.io.Serializable;

/**
 * Created by Oliver on 1/20/2018.
 */

public class NYTimes implements Serializable {

    String section;
    String title;
    String url;
    String short_url;

    public NYTimes(String section, String title, String url, String short_url) {
        this.section = section;
        this.title = title;
        this.url = url;
        this.short_url = short_url;
    }

    public NYTimes() {
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }
}
