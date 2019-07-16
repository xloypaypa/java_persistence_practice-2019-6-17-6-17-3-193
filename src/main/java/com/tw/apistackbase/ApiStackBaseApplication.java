package com.tw.apistackbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class ApiStackBaseApplication {

    public static void main(String[] args) {
        initCompanyDB();
        initEmployeeDB();

        SpringApplication.run(ApiStackBaseApplication.class, args);
    }

    private static void initEmployeeDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./h2/org", "sa", "");

            stmt = conn.createStatement();

            int result = stmt.executeUpdate("CREATE TABLE EMPLOYEE (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(255) NOT NULL," +
                    "age BIGINT NOT NULL, " +
                    "PRIMARY KEY (id)" +
                    ");");

            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initCompanyDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./h2/org", "sa", "");

            stmt = conn.createStatement();

            int result = stmt.executeUpdate("CREATE TABLE COMPANY (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ");");

            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
