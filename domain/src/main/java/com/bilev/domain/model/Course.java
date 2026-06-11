package com.bilev.domain.model;

public class Course {
    private final int id;
    private final String title;
    private final String text;
    private final String price;
    private final String rate;
    private final String startDate;
    private boolean hasLike;
    private final String publishDate;

    public Course(int id, String title, String text, String price, String rate,
                  String startDate, boolean hasLike, String publishDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.price = price;
        this.rate = rate;
        this.startDate = startDate;
        this.hasLike = hasLike;
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getPrice() {
        return price;
    }

    public String getRate() {
        return rate;
    }

    public String getStartDate() {
        return startDate;
    }

    public boolean isHasLike() {
        return hasLike;
    }

    public void setHasLike(boolean hasLike) {
        this.hasLike = hasLike;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
