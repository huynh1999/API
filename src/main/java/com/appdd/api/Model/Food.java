package com.appdd.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "food")
@Table(name = "food")
public class Food {
    @Id
    @Column(name = "idfood")
    private int idfood;
    @Column(name = "Title")
    private String title;
    @Column(name="Thumbnail")
    private String thumbnail;
    @Column(name = "Shortdescription")
    private String shortdescription;
    @Column(name = "Content")
    private String content;
    @Column(name = "Idkindfood")
    private int idkindfood;

    public int getIdfood() {
        return idfood;
    }

    public void setIdfood(int idfood) {
        this.idfood = idfood;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdkindfood() {
        return idkindfood;
    }

    public void setIdkindfood(int idkindfood) {
        this.idkindfood = idkindfood;
    }
}
