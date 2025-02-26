package com.kevin.sql_java.repository;

import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
