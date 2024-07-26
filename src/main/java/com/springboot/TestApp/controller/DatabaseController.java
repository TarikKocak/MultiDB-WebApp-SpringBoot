package com.springboot.TestApp.controller;

import com.springboot.TestApp.model.Employee;
import com.springboot.TestApp.model.Manager;

import com.springboot.TestApp.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;



@Controller
public class DatabaseController {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseController.class);


    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

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
            //if employee table selected, fetch employee's data
            List<Employee> employees;
            if ("db1".equalsIgnoreCase(db)) {
                employees = databaseService.getEmployeesFromDb1();
            } else {
                employees = databaseService.getEmployeesFromDb2();
            }
            model.addAttribute("employees", employees);
            model.addAttribute("table", "employee");



        } else if ("manager".equalsIgnoreCase(table)) {
            //if manager table selected, fetch manager's data

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
    //Handling Insertion operation for each entity on database
    @GetMapping("/addEmployeeForm")
    public String showAddEmployeeForm(@RequestParam String db, Model model) {
        model.addAttribute("db", db);
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }
    @PostMapping("/addEmployee")
    public String addEmployee(@RequestParam String db,
                              @RequestParam String name,@RequestParam String email){
        Employee employee = new Employee(name,email);
        if("db1".equalsIgnoreCase(db)){
            databaseService.addEmployeeToDb1(employee);
        }else {
            databaseService.addEmployeeToDb2(employee);
        }
        String redirectUrl = UriComponentsBuilder.fromPath("/fetchData")
                .queryParam("db", db)
                .queryParam("table", "employee")
                .build()
                .toUriString();
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/addManagerForm")
    public String showAddManagerForm(@RequestParam String db, Model model) {
        model.addAttribute("db", db);
        model.addAttribute("manager", new Manager());
        return "addManager";
    }

    @PostMapping("/addManager")
    public String addManager(@RequestParam String db,
                             @RequestParam String name, @RequestParam String email) {
        Manager manager = new Manager(name, email);
        if ("db1".equalsIgnoreCase(db)) {
            databaseService.addManagerToDb1(manager);
        } else {
            databaseService.addManagerToDb2(manager);
        }
        String redirectUrl = UriComponentsBuilder.fromPath("/fetchData")
                .queryParam("db", db)
                .queryParam("table", "manager")
                .build()
                .toUriString();
        return "redirect:" + redirectUrl;
    }


    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long id, @RequestParam String db) {
        if ("db1".equalsIgnoreCase(db)) {
            databaseService.deleteEmployeeInDb1(id);
        } else {
            databaseService.deleteEmployeeInDb2(id);
        }
        String redirectUrl = UriComponentsBuilder.fromPath("/fetchData")
                .queryParam("db", db)
                .queryParam("table", "employee")
                .build()
                .toUriString();

        return "redirect:" + redirectUrl;
    }


    @PostMapping("/deleteManager")
    public String deleteManager(@RequestParam Long id, @RequestParam String db) {
        if ("db1".equalsIgnoreCase(db)) {
            databaseService.deleteManagerInDb1(id);
        } else {
            databaseService.deleteManagerInDb2(id);
        }
        String redirectUrl = UriComponentsBuilder.fromPath("/fetchData")
                .queryParam("db", db)
                .queryParam("table", "manager")
                .build()
                .toUriString();

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/updateEmployeeForm")
    public String showUpdateEmployeeForm(@RequestParam Long id, @RequestParam String db, Model model) {
        Employee employee;
        if ("db1".equalsIgnoreCase(db)) {
            employee = databaseService.getEmployeeFromDb1ById(id);
        } else {
            employee = databaseService.getEmployeeFromDb2ById(id);
        }
        model.addAttribute("employee", employee);
        model.addAttribute("db", db);
        return "updateEmployee";
    }
    @PostMapping("/updateEmployee")
    public String updateEmployee(@RequestParam Long id, @RequestParam String db,
                                 @RequestParam String name, @RequestParam String email) {
        Employee employee = new Employee(name,email);
        employee.setId(id);
        if ("db1".equalsIgnoreCase(db)) {
            databaseService.updateEmployeeInDb1(employee);
        } else {
            databaseService.updateEmployeeInDb2(employee);
        }
        String redirectUrl = UriComponentsBuilder.fromPath("/fetchData")
                .queryParam("db", db)
                .queryParam("table", "employee")
                .build()
                .toUriString();
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/updateManagerForm")
    public String showUpdateManagerForm(@RequestParam Long id, @RequestParam String db, Model model) {
        Manager manager;
        if ("db1".equalsIgnoreCase(db)) {
            manager = databaseService.getManagerFromDb1ById(id);
        } else {
            manager = databaseService.getManagerFromDb2ById(id);
        }
        model.addAttribute("manager", manager);
        model.addAttribute("db", db);
        return "updateManager";
    }

    @PostMapping("/updateManager")
    public String updateManager(@RequestParam Long id, @RequestParam String db,
                                @RequestParam String name, @RequestParam String email) {
        Manager manager = new Manager(name, email);
        manager.setId(id);
        if ("db1".equalsIgnoreCase(db)) {
            databaseService.updateManagerInDb1(manager);
        } else {
            databaseService.updateManagerInDb2(manager);
        }
        String redirectUrl = UriComponentsBuilder.fromPath("/fetchData")
                .queryParam("db", db)
                .queryParam("table", "manager")
                .build()
                .toUriString();

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/dynamicQueryForm")
    public String showDynamicQueryForm(@RequestParam String db, Model model) {
        model.addAttribute("db", db);
        return "dynamicQueryForm";
    }

    @PostMapping("/executeQuery")
    public String executeDynamicQuery(@RequestParam String db, @RequestParam String query, Model model) {
        try {
            List<Object[]> results = databaseService.executeQuery(db, query);
            model.addAttribute("results", results);
            model.addAttribute("db", db);
            model.addAttribute("query", query);

            if (results.isEmpty()) {
                model.addAttribute("message", "No data found for the typed query.");
            }

        } catch (Exception e) {
            logger.error("Error executing query: {}", query, e);
            model.addAttribute("error", "Error executing query: " + e.getMessage());
        }
        return "queryResult";
    }

}


