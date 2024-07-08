package com.springboot.TestApp;

import com.springboot.TestApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/connectDb1")
    public String connectDb1(Model model) {
        List<Employee> employees = employeeService.getEmployeesFromDb1();
        model.addAttribute("employees", employees);
        model.addAttribute("db", "db1");
        return "employeeList";
    }

    @GetMapping("/connectDb2")
    public String connectDb2(Model model) {
        List<Employee> employees = employeeService.getEmployeesFromDb2();
        model.addAttribute("employees", employees);
        model.addAttribute("db", "db2");
        return "employeeList";
    }
}