package com.example.scribbler;

public class MyModel {
    String postTitle;
    String postShortDescription;
    String postImageUrl;
    String category;
    String dateAndTime;
    String likeCounts;

    MyModel(){

    }

    public MyModel(String postTitle, String postShortDescription, String postImageUrl, String dateAndTime, String likeCounts) {
        this.postTitle = postTitle;
        this.postShortDescription = postShortDescription;
        this.postImageUrl = postImageUrl;
        this.dateAndTime = dateAndTime;
        this.likeCounts = likeCounts;
    }


    public String getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(String likeCounts) {
        this.likeCounts = likeCounts;
    }


    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostShortDescription() {
        return postShortDescription;
    }

    public void setPostShortDescription(String postShortDescription) {
        this.postShortDescription = postShortDescription;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

}
