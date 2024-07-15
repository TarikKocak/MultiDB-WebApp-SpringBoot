package com.springboot.TestApp.service;

import com.springboot.TestApp.db1.Db1EmployeeRepository;
import com.springboot.TestApp.db1.Db1ManagerRepository;
import com.springboot.TestApp.db2.Db2EmployeeRepository;
import com.springboot.TestApp.db2.Db2ManagerRepository;
import com.springboot.TestApp.model.Employee;
import com.springboot.TestApp.model.Manager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DatabaseService {

    private final Db1EmployeeRepository db1EmployeeRepository;

    private final Db2EmployeeRepository db2EmployeeRepository;

    private final Db1ManagerRepository db1ManagerRepository;

    private final Db2ManagerRepository db2ManagerRepository;

    public DatabaseService(Db1EmployeeRepository db1EmployeeRepository, Db2EmployeeRepository db2EmployeeRepository, Db1ManagerRepository db1ManagerRepository, Db2ManagerRepository db2ManagerRepository) {
        this.db1EmployeeRepository = db1EmployeeRepository;
        this.db2EmployeeRepository = db2EmployeeRepository;
        this.db1ManagerRepository = db1ManagerRepository;
        this.db2ManagerRepository = db2ManagerRepository;
    }

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
    @Transactional("db1TransactionManager")
    public void updateEmployeeInDb1(Employee employee) {
        db1EmployeeRepository.save(employee);
    }

    @Transactional("db2TransactionManager")
    public void updateEmployeeInDb2(Employee employee) {
        db2EmployeeRepository.save(employee);
    }

    @Transactional("db1TransactionManager")
    public void deleteEmployeeInDb1(Long id) {
        db1EmployeeRepository.deleteById(id);
    }

    @Transactional("db2TransactionManager")
    public void deleteEmployeeInDb2(Long id) {
        db2EmployeeRepository.deleteById(id);
    }

    // Manager operations

    @Transactional("db1TransactionManager")
    public void updateManagerInDb1(Manager manager) {
        db1ManagerRepository.save(manager);
    }

    @Transactional("db2TransactionManager")
    public void updateManagerInDb2(Manager manager) {
        db2ManagerRepository.save(manager);
    }

    @Transactional("db1TransactionManager")
    public void deleteManagerInDb1(Long id) {
        db1ManagerRepository.deleteById(id);
    }

    @Transactional("db2TransactionManager")
    public void deleteManagerInDb2(Long id) {
        db2ManagerRepository.deleteById(id);
    }

    public Employee getEmployeeFromDb1ById(Long id) {
        return db1EmployeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee getEmployeeFromDb2ById(Long id) {
        return db2EmployeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Manager getManagerFromDb1ById(Long id) {
        return db1ManagerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
    }

    public Manager getManagerFromDb2ById(Long id) {
        return db2ManagerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
    }



}
