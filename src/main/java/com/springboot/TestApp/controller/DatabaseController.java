package com.springboot.TestApp.controller;

import com.springboot.TestApp.model.Employee;
import com.springboot.TestApp.model.Manager;

import com.springboot.TestApp.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DatabaseController {
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/selectTable")
    public String selectTable(@RequestParam String db, Model model) {
        model.addAttribute("db", db);
        return "selectTable";
    }

    @GetMapping("/fetchData")
    public String fetchData(@RequestParam String db, @RequestParam String table, Model model) {
        if ("employee".equalsIgnoreCase(table)) {
            List<Employee> employees;
            if ("db1".equalsIgnoreCase(db)) {
                employees = databaseService.getEmployeesFromDb1();
            } else {
                employees = databaseService.getEmployeesFromDb2();
            }
            model.addAttribute("employees", employees);
            model.addAttribute("table", "employee");
        } else if ("manager".equalsIgnoreCase(table)) {
            List<Manager> managers;
            if ("db1".equalsIgnoreCase(db)) {
                managers = databaseService.getManagersFromDb1();
            } else {
                managers = databaseService.getManagersFromDb2();
            }
            model.addAttribute("managers", managers);
            model.addAttribute("table", "manager");
        } else {
            // Handle unknown table
            System.out.println("error");
        }
        model.addAttribute("db", db);
        return "dataList";
    }
}
