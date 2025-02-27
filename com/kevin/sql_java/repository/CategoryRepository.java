package com.kevin.sql_java.repository;
import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepository {
    private static final Connection connection = Bdd.getConnection();
    public static Category save(Category addCategory)  {
        Category newCategory = null;
        try {
            String sql = "INSERT INTO category(category_name) " +
                    "VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, addCategory.getCategoryName());
            int nbrRows = preparedStatement.executeUpdate();
            if (nbrRows > 0){
                newCategory = new Category(addCategory.getCategoryName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newCategory;
    }
    public static boolean isExist(String category)  {
        Category getCategory = null;
        try{
            String sql = "SELECT id, category_name from category WHERE category_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getCategory = new Category();
                getCategory.setId(resultSet.getInt("id"));
                getCategory.setCategoryName(resultSet.getString("category_name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        boolean categoryExists = false;
        if (getCategory != null){
            categoryExists = true;
            System.out.println("La catégorie existe déjà");
        }
        return categoryExists;
    }
    public static Optional<Category> findBy(String categoryName){
        Category getCategory = null;
        try{
            String sql ="SELECT id, category_name FROM category WHERE category_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getCategory = new Category();
                getCategory.setId(resultSet.getInt("id"));
                getCategory.setCategoryName(resultSet.getString("category_name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(getCategory);
    }
    public static List<Category> findAll(){
        List<Category> listCategory = new ArrayList<Category>();
        try{
            String sql ="SELECT id, category_name FROM category";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("category_name"));
                listCategory.add(category);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listCategory;
    }
}
