package com.kevin.sql_java.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Task {
    // Attributs
    private int id;
    private String title;
    private String content;
    private java.sql.Date createAt;
    private java.sql.Date endDate;
    private boolean status = false;
    private User user;
    private final List<Category> categories;

    // Constructeurs
    public Task(){
        this.categories = new ArrayList<>();
    }
    public Task(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
        this.categories = new ArrayList<>();
    }

    // Getters/Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public java.sql.Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public java.sql.Date getEndDate() {
        return endDate;
    }
    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Category> getCategories(){
        return this.categories;
    }
    // Méthodes
    public void addCategory(Category category){
        this.categories.add(category);
    }
    public void removeCategory(Category category){
        this.categories.remove(category);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", endDate=" + endDate +
                ", status=" + status +
                ", user=" + user +
                ", categories=" + categories +
                '}';
    }
}

