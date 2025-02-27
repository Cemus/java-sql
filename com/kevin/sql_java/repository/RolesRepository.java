package com.kevin.sql_java.repository;

import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RolesRepository {
    private static final Connection connection = Bdd.getConnection();
    public static Roles save(Roles addRole)  {
        Roles newRole = null;
        try {
            String sql = "INSERT INTO roles (roles_name) " +
                    "VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, addRole.getRolesName());
            int nbrRows = preparedStatement.executeUpdate();
            if (nbrRows > 0){
                newRole = new Roles();
                newRole.setRolesName(addRole.getRolesName());
                newRole.setId((addRole.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newRole;
    }
    public static boolean isExist(String rolesName)  {
        Roles getRole = null;
        try{
            String sql = "SELECT id, roles_name from roles WHERE roles_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,rolesName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getRole = new Roles();
                getRole.setId(resultSet.getInt("id"));
                getRole.setRolesName(resultSet.getString("roles_name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        boolean roleExists = false;
        if (getRole != null){
            roleExists = true;
            System.out.println("Le role existe déjà");
        }
        return roleExists;
    }
    public static Optional<Roles> findBy(String rolesName){
        Roles getRole = null;
        try{
            String sql ="SELECT id, roles_name FROM roles WHERE roles_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,rolesName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getRole = new Roles();
                getRole.setId(resultSet.getInt("id"));
                getRole.setRolesName(resultSet.getString("roles_name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(getRole);
    }
    public static List<Roles> findAll(){
        List<Roles> listRoles = new ArrayList<Roles>();
        try{
            String sql ="SELECT id, roles_name FROM roles";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Roles role = new Roles();
                role.setId(resultSet.getInt("id"));
                role.setRolesName(resultSet.getString("roles_name"));
                listRoles.add(role);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listRoles;
    }
}

