package com.tw.apistackbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.Statement;

import static com.tw.apistackbase.helper.DBHelper.closeConnection;
import static com.tw.apistackbase.helper.DBHelper.createConnection;

@SpringBootApplication
public class ApiStackBaseApplication {

    public static void main(String[] args) {
        initCompanyDB();
        initEmployeeDB();

        SpringApplication.run(ApiStackBaseApplication.class, args);
    }

    private static void initEmployeeDB() {
        Connection conn = null;
        try {
            conn = createConnection();

            Statement stmt = conn.createStatement();

            int result = stmt.executeUpdate("CREATE TABLE EMPLOYEE (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL," +
                    "age INTEGER NOT NULL, " +
                    "PRIMARY KEY (id)" +
                    ");");

            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    private static void initCompanyDB() {
        Connection conn = null;
        try {
            conn = createConnection();

            Statement stmt = conn.createStatement();

            int result = stmt.executeUpdate("CREATE TABLE COMPANY (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ");");

            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }
}
