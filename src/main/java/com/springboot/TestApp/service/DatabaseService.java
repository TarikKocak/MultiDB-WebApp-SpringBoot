package com.springboot.TestApp.service;

import com.springboot.TestApp.db1.Db1EmployeeRepository;
import com.springboot.TestApp.db1.Db1ManagerRepository;
import com.springboot.TestApp.db2.Db2EmployeeRepository;
import com.springboot.TestApp.db2.Db2ManagerRepository;
import com.springboot.TestApp.model.Employee;
import com.springboot.TestApp.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private Db1EmployeeRepository db1EmployeeRepository;

    @Autowired
    private Db2EmployeeRepository db2EmployeeRepository;

    @Autowired
    private Db1ManagerRepository db1ManagerRepository;

    @Autowired
    private Db2ManagerRepository db2ManagerRepository;

    public List<Employee> getEmployeesFromDb1() {
        List<Employee> employees = db1EmployeeRepository.findAll();
        System.out.println("Employees from DB1: " + employees);  // Debugging line
        return employees;
    }

    public List<Employee> getEmployeesFromDb2() {
        List<Employee> employees = db2EmployeeRepository.findAll();
        System.out.println("Employees from DB2: " + employees);  // Debugging line
        return employees;
    }

    public List<Manager> getManagersFromDb1() {
        List<Manager> managers = db1ManagerRepository.findAll();
        System.out.println("Managers from DB1: " + managers);  // Debugging line
        return managers;
    }

    public List<Manager> getManagersFromDb2() {
        List<Manager> managers = db2ManagerRepository.findAll();
        System.out.println("Managers from DB2: " + managers);  // Debugging line
        return managers;
    }


}
