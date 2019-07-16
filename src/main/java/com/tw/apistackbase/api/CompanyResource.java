package com.tw.apistackbase.api;

import com.tw.apistackbase.core.Company;
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
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./h2/org", "sa", "");

            stmt = conn.prepareStatement("SELECT * FROM COMPANY");

            result = stmt.executeQuery();
            while (result.next()) {
                responds.add(new Company(result.getLong("id"),
                        result.getString("name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return responds;
    }

    @PostMapping(produces = {"application/json"})
    public void add(@RequestBody Company company) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./h2/org", "sa", "");

            String[] generatedColumns = {"id"};
            stmt = conn.createStatement();
            int update = stmt.executeUpdate("INSERT INTO COMPANY VALUES (" +
                    "null ," +
                    "'" + company.getName() + "'" +
                    ");", generatedColumns);
            System.err.println(update);

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
