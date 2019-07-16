package com.tw.apistackbase.api;

import com.tw.apistackbase.core.Company;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.tw.apistackbase.helper.DBHelper.closeConnection;
import static com.tw.apistackbase.helper.DBHelper.createConnection;

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
            conn = createConnection();

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
            closeConnection(conn);
        }
        return responds;
    }

    @PostMapping(produces = {"application/json"})
    public void add(@RequestBody Company company) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = createConnection();

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
            closeConnection(conn);
        }
    }
}
