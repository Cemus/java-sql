package com.kevin.sql_java.repository;

import com.kevin.sql_java.db.Bdd;
import com.kevin.sql_java.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static final Connection connection = Bdd.getConnection();
    public static void findAll() throws SQLException {

    }
}
