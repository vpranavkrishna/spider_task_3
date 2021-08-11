package com.delta_inductions.spider_task_3.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Superhero {

    private Integer id;
    private String name;
    private String slug;
    @SerializedName("powerstats")
    @Expose
    private Powerstats powerstats;
    @SerializedName("appearance")
    @Expose
    private Appearance appearance;
    @SerializedName("biography")
    @Expose
    private Biography biography;
    @SerializedName("work")
    @Expose
    private Work work;
    @SerializedName("connections")
    @Expose
    private Connections connections;
    @SerializedName("images")
    @Expose
    private Image image;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public Biography getBiography() {
        return biography;
    }

    public Work getWork() {
        return work;
    }

    public Connections getConnections() {
        return connections;
    }

    public Image getImage() {
        return image;
    }
}
