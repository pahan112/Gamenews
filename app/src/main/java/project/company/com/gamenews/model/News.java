package project.company.com.gamenews.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pahan on 07.10.2017.
 */

public class News implements Serializable{
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("cover")
    private String cover;
    @SerializedName("link")
    private String link;
    @SerializedName("date")
    private int date;
    @SerializedName("top")
    private boolean top;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }
}
