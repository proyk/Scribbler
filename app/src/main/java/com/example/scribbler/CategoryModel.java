package com.example.scribbler;

public class CategoryModel {
    private String categoryName;
    private String categoryDescription;

    CategoryModel(){

    }

    public CategoryModel(String categoryDescription, String categoryName) {
        this.categoryDescription = categoryDescription;
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String toString(){
        return this.getCategoryName() + "";
    }

}
