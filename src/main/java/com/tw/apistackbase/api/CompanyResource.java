package com.tw.apistackbase.api;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.helper.DBHelper;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyResource {

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list() {
        List<Company> responds = new ArrayList<>();
        DBHelper dbHelper = null;
        try {
            dbHelper = new DBHelper();
            responds = dbHelper.list("SELECT * FROM COMPANY", resultSet ->
                    new Company(resultSet.getLong("id"), resultSet.getString("name")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dbHelper != null) {
                dbHelper.closeConnection();
            }
        }
        return responds;
    }

    @PostMapping(produces = {"application/json"})
    public void add(@RequestBody Company company) {
        Connection conn = null;
        try {
            conn = createConnection();

            String[] generatedColumns = {"id"};
            Statement stmt = conn.createStatement();
            int update = stmt.executeUpdate("INSERT INTO COMPANY VALUES (" +
                    "null ," +
                    "'" + company.getName() + "'" +
                    ");", generatedColumns);
            System.err.println(update);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }
}
