package com.example.scribbler;

public class PostAdd {
    public String postTitle;
    public String postShortDescription;
    public String postDescription;
    public String postImageUrl;
    public String category;
    public String dateAndTime;
    //public String likeCounts;



    public PostAdd()
    {

    }

    public PostAdd(String postTitle, String postShortDescription, String postDescription, String postImageUrl, String category, String curdateAndTime) {
        this.postTitle = postTitle;
        this.postShortDescription = postShortDescription;
        this.postDescription = postDescription;
        this.postImageUrl = postImageUrl;
        this.category = category;
        this.dateAndTime = curdateAndTime;
        //this.likeCounts = likeCounts;
    }


  /*  public String getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(String likeCounts) {
        this.likeCounts = likeCounts;
    }
*/

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostShortDescription(String postShortDescription) {
        this.postShortDescription = postShortDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getCategory() {
        return category;
    }




    public String getPostTitle() {
        return postTitle;
    }



    public String getPostShortDescription() {
        return postShortDescription;
    }


    public String getPostDescription() {
        return postDescription;
    }



    public String getPostImageUrl() {
        return postImageUrl;
    }


}
