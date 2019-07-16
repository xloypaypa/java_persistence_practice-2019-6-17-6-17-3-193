package com.tw.apistackbase.helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private Connection conn;

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> list(String SQL, ObjectMapper<T> objectMapper) throws SQLException {
        List<T> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(SQL);

        ResultSet result = null;
        try {
            result = stmt.executeQuery();
            while (result.next()) {
                list.add(objectMapper.mapResultToObject(result));
            }
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    public DBHelper() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        this.conn = DriverManager.getConnection("jdbc:h2:./h2/org", "sa", "");
    }

    public interface ObjectMapper<T> {

        T mapResultToObject(ResultSet resultSet) throws SQLException;

    }

}
