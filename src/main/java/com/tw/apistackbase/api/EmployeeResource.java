package com.tw.apistackbase.api;

import com.tw.apistackbase.core.Employee;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @GetMapping(produces = {"application/json"})
    public List<Employee> list() {
        List<Employee> responds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./h2/org", "sa", "");

            stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE");

            result = stmt.executeQuery();
            while (result.next()) {
                responds.add(new Employee(result.getLong("id"),
                        result.getString("name"),
                        result.getInt("age")));
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
    public void add(@RequestBody Employee employee) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./h2/org", "sa", "");

            stmt = conn.createStatement();
            int update = stmt.executeUpdate("INSERT INTO EMPLOYEE VALUES " +
                    "(" + (list().size() + 1) + ",'" +
                    employee.getName() + "', " +
                    employee.getAge() +
                    ");");
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
