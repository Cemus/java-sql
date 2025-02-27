package com.kevin.sql_java.model;
public class Roles {
    // Attributs
    private int id;
    private String rolesName;

    // Constructeurs
    public Roles(){

    }
    public Roles(String rolesName){
        this.rolesName = rolesName;
    }

    //Getters/Setters
    public String getRolesName() {
        return rolesName;
    }
    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", rolesName='" + rolesName + '\'' +
                '}';
    }
}
