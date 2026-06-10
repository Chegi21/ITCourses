package com.bilev.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_courses")
public class FavoriteCourseEntity {

    @PrimaryKey
    private int id;
    private String title;
    private String text;
    private String price;
    private String rate;
    private String startDate;
    private String publishDate;

    public FavoriteCourseEntity(int id, String title, String text, String price,
                                 String rate, String startDate, String publishDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.price = price;
        this.rate = rate;
        this.startDate = startDate;
        this.publishDate = publishDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getRate() { return rate; }
    public void setRate(String rate) { this.rate = rate; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }
}
