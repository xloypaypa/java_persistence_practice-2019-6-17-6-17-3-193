//package com.tw.apistackbase.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tw.apistackbase.core.Company;
//import com.tw.apistackbase.core.CompanyRepository;
//import com.tw.apistackbase.core.Employee;
//import com.tw.apistackbase.core.EmployeeRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@JdbcTest
//public class CompanyResourceTest {
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    @Test
//    @DirtiesContext
//    public void should_can_update_employee_list() throws Exception {
//        Employee alice = new Employee("alice", 21);
//        this.employeeRepository.save(alice);
//        Employee bob = new Employee("bob", 22);
//        this.employeeRepository.save(bob);
//        Company company = new Company("acompany");
//        company.setEmployees(Arrays.asList(alice, bob));
//
//        MvcResult mvcResult = this.mockMvc.perform(post("/companies")
//                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(company)))
//                .andReturn();
//
//        assertEquals(200, mvcResult.getResponse().getStatus());
//    }
//
//    @Test
//    @DirtiesContext
//    public void should_get_employee_list_when_get_company_details() throws Exception {
//        Employee alice = new Employee("alice", 21);
//        Employee bob = new Employee("bob", 22);
//        Company company = new Company("acompany");
//        company.setEmployees(Arrays.asList(alice, bob));
//        this.companyRepository.save(company);
//
//        MvcResult mvcResult = this.mockMvc.perform(get("/companies")).andReturn();
//
//        assertEquals(200, mvcResult.getResponse().getStatus());
//        Company[] companies = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Company[].class);
//        assertEquals(1, companies.length);
//        List<Employee> employees = companies[0].getEmployees();
//        assertTrue(employees.stream().anyMatch(employee -> employee.getName().equals("bob")));
//        assertTrue(employees.stream().anyMatch(employee -> employee.getName().equals("alice")));
//    }
//}