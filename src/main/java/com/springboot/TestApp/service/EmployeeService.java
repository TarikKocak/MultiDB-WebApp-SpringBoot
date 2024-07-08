package com.springboot.TestApp;

import com.springboot.TestApp.db1.Db1EmployeeRepository;
import com.springboot.TestApp.db2.Db2EmployeeRepository;
import com.springboot.TestApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    @Autowired
    private Db1EmployeeRepository db1EmployeeRepository;

    @Autowired
    private Db2EmployeeRepository db2EmployeeRepository;

    public List<Employee> getEmployeesFromDb1() {
        return db1EmployeeRepository.findAll();
    }

    public List<Employee> getEmployeesFromDb2() {
        return db2EmployeeRepository.findAll();
    }
}