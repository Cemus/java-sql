package com.kevin.sql_java.repository;

import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.Category;
import com.kevin.sql_java.model.Roles;
import com.kevin.sql_java.model.Task;
import com.kevin.sql_java.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    public static final Connection connection = Bdd.getConnection();
    public static Task save(Task addTask) {
        Task newTask = null;
        try {
            String sql = "INSERT INTO task( title, content, end_date, users_id) " +
                    "VALUES (?,?,?, (SELECT id from users WHERE firstname = ? AND lastname = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, addTask.getTitle());
            preparedStatement.setString(2, addTask.getContent());
            preparedStatement.setDate(3, addTask.getEndDate());
            preparedStatement.setString(4, addTask.getUser().getFirstname());
            preparedStatement.setString(5, addTask.getUser().getLastname());

            int nbrRows = preparedStatement.executeUpdate();
            if (nbrRows > 0){
                newTask = new Task(addTask.getTitle(),addTask.getContent(), addTask.getUser());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newTask;
    }
    public static boolean isExist(String taskTitle, Date taskDate) {
        Task getTask = null;
        try{
            String sql = "SELECT id from task WHERE title = ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskTitle);
            preparedStatement.setDate(2, taskDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getTask = new Task();
                getTask.setId(resultSet.getInt("id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        boolean taskExists = false;
        if (getTask != null){
            taskExists = true;
            System.out.println("La tâche existe déjà");
        }
        return taskExists;
    }
    public static Optional<Task> findBy(String taskTitle, Date taskDate) {
        Task getTask = null;
        try{
            String sql ="SELECT t.id AS tId, t.title, t.content, t.create_at, t.end_date, t.`status`, \n" +
                    "u.id AS uId, u.firstname, u.lastname, r.id AS rId, r.roles_name AS rName,\n" +
                    "group_concat(c.id) AS catId, " +
                    "group_concat(c.category_name) AS catName  " +
                    "FROM task_category AS tc " +
                    "INNER JOIN task AS t ON tc.task_id = t.id " +
                    "INNER JOIN category AS c ON tc.category_id = c.id " +
                    "INNER JOIN users AS u ON t.users_id = u.id " +
                    "INNER JOIN roles AS r ON u.roles_id = r.id " +
                    "WHERE t.title = ? AND t.create_at = ? " +
                    "GROUP BY t.id " +
                    "ORDER BY DESC LIMIT 1";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,taskTitle);
            preparedStatement.setDate(2,taskDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                getTask = new Task();
                getTask.setId(resultSet.getInt("id"));
                getTask.setTitle(resultSet.getString("title"));
                getTask.setContent(resultSet.getString("content"));
                getTask.setCreateAt(resultSet.getDate("create_at"));
                getTask.setEndDate(resultSet.getDate("end_date"));
                getTask.setStatus(resultSet.getBoolean("status"));

                Roles role = new Roles();
                role.setRolesName(resultSet.getString("rName"));
                role.setId(resultSet.getInt("rId"));

                User user = new User();
                user.setId(resultSet.getInt("uId"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setRoles(role);

                getTask.setUser(user);
                Category category = new Category(resultSet.getString("catName"));
                category.setId(resultSet.getInt("catId"));
                getTask.addCategory(category);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(getTask);
    }
    public static List<Task> findAll() {
        List<Task> listTask = new ArrayList<Task>();
        try{
            String sql ="SELECT t.id AS tId, t.title, t.content, t.create_at, t.end_date, t.`status`," +
                    "u.id AS uId, u.firstname, u.lastname, r.id AS rId, r.roles_name AS rName," +
                    "group_concat(c.id) AS catId," +
                    "group_concat(c.category_name) AS catName " +
                    "FROM task_category AS tc " +
                    "INNER JOIN task AS t ON tc.task_id = t.id " +
                    "INNER JOIN category AS c ON tc.category_id = c.id " +
                    "INNER JOIN users AS u ON t.users_id = u.id " +
                    "INNER JOIN roles AS r ON u.roles_id = r.id " +
                    "GROUP BY t.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("tId"));
                task.setTitle(resultSet.getString("title"));
                task.setContent(resultSet.getString("content"));
                task.setCreateAt(resultSet.getDate("create_at"));
                task.setEndDate(resultSet.getDate("end_date"));
                task.setStatus(resultSet.getBoolean("status"));

                Roles role = new Roles();
                role.setRolesName(resultSet.getString("rName"));
                role.setId(resultSet.getInt("rId"));

                User user = new User();
                user.setId(resultSet.getInt("uId"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setRoles(role);
                task.setUser(user);

                if (resultSet.getString("catId") != null && resultSet.getString("catName") != null){
                    String[] arrayCatId = resultSet.getString("catId").split(",");
                    String[] arrayCatName = resultSet.getString("catName").split(",");
                    for (int i = 0; i < arrayCatId.length; i++){
                        Category newCat = new Category();
                        newCat.setId(Integer.parseInt(arrayCatId[i]));
                        newCat.setCategoryName(arrayCatName[i]);
                        task.addCategory(newCat);
                    }
                }

                listTask.add(task);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listTask;
    }
}
