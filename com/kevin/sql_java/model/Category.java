package com.kevin.sql_java.model;

public class Category {
    // Attributs
    private int id;
    private String categoryName;

    // Constructeurs
    public Category(){

    }
    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    // Getters/Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
