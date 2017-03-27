package com.alex.viewpagerandgridview;

import java.io.Serializable;

/**
 * Created by Alex on 2017/3/24.
 */

public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public ProductBean(String name, int url) {
        super();
        this.name = name;
        this.url = url;
    }

}
