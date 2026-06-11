package com.bilev.data.dto;

import com.google.gson.annotations.SerializedName;

public class CourseDto {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("text")
    private String text;

    @SerializedName("price")
    private String price;

    @SerializedName("rate")
    private String rate;

    @SerializedName("startDate")
    private String startDate;

    @SerializedName("hasLike")
    private boolean hasLike;

    @SerializedName("publishDate")
    private String publishDate;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getText() { return text; }
    public String getPrice() { return price; }
    public String getRate() { return rate; }
    public String getStartDate() { return startDate; }
    public boolean isHasLike() { return hasLike; }
    public String getPublishDate() { return publishDate; }
}
