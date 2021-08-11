package com.delta_inductions.spider_task_3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("lg")
    @Expose
    private String URl;

    public String getURl() {
        return URl;
    }
}
