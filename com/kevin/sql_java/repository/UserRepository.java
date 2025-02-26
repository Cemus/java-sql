package com.kevin.sql_java.repository;

import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    /*
    * Attributs
     */

    private static final Connection connection = Bdd.getConnection();
    /*
    * Méthodes
    * */

    public static User save(User addUser){
        // Créer objet user
        User newUser = null;
        try {
            // Requête
            String sql = "INSERT INTO users(firstname, lastname, email, password) " +
                    "VALUES (?, ?, ?, ?)";
            // Préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Bind
            preparedStatement.setString(1, addUser.getFirstname());
            preparedStatement.setString(2, addUser.getLastname());
            preparedStatement.setString(3, addUser.getEmail());
            preparedStatement.setString(4, addUser.getPassword());
            // Exécution -|===>
            int nbrRows = preparedStatement.executeUpdate(); //Update sinon get pour la consultation => executeQuery();
            // Ça marche ou bien ?
            if (nbrRows > 0){
                newUser = new User(addUser.getFirstname(), addUser.getLastname(), addUser.getEmail(), addUser.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newUser;
    }
    public static boolean isExist(String email){
        User getUser = null;
        try{
            String sql ="SELECT id, firstname, lastname, email FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getUser = new User();
                getUser.setId(resultSet.getInt("id"));
                getUser.setFirstname(resultSet.getString("firstname"));
                getUser.setLastname(resultSet.getString("lastname"));
                getUser.setEmail(resultSet.getString("email"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        boolean emailExists = false;
        if (getUser != null){
            emailExists = true;
            System.out.println("Le mail existe déjà");
        }
        return emailExists;
    }
    public static User findByEmail(String email){
        User getUser = null;
        try{
            String sql ="SELECT id, firstname, lastname, email FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getUser = new User();
                getUser.setId(resultSet.getInt("id"));
                getUser.setFirstname(resultSet.getString("firstname"));
                getUser.setLastname(resultSet.getString("lastname"));
                getUser.setEmail(resultSet.getString("email"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(getUser);
        return getUser;
    }
}
