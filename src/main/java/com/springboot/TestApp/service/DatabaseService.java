package com.springboot.TestApp.service;

import com.springboot.TestApp.enums.Database;
import com.springboot.TestApp.repository.db1.Db1EmployeeRepository;
import com.springboot.TestApp.repository.db1.Db1ManagerRepository;
import com.springboot.TestApp.repository.db2.Db2EmployeeRepository;
import com.springboot.TestApp.repository.db2.Db2ManagerRepository;
import com.springboot.TestApp.model.Employee;
import com.springboot.TestApp.model.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DatabaseService {

    private final Db1EmployeeRepository db1EmployeeRepository;
    private final Db2EmployeeRepository db2EmployeeRepository;
    private final Db1ManagerRepository db1ManagerRepository;
    private final Db2ManagerRepository db2ManagerRepository;
    private final TransactionService transactionService;

    @PersistenceContext(unitName = "db1")
    private EntityManager db1EntityManager;

    @PersistenceContext(unitName = "db2")
    private EntityManager db2EntityManager;

    public DatabaseService(Db1EmployeeRepository db1EmployeeRepository, Db2EmployeeRepository db2EmployeeRepository, Db1ManagerRepository db1ManagerRepository, Db2ManagerRepository db2ManagerRepository, TransactionService transactionService) {
        this.db1EmployeeRepository = db1EmployeeRepository;
        this.db2EmployeeRepository = db2EmployeeRepository;
        this.db1ManagerRepository = db1ManagerRepository;
        this.db2ManagerRepository = db2ManagerRepository;
        this.transactionService = transactionService;
    }

    public List<Object[]> executeQuery(String db, String query) {
        Database database = Database.fromString(db);
        EntityManager entityManager = database.getEntityManager(db1EntityManager, db2EntityManager);
        return transactionService.executeDynamicQuery(entityManager, query);
    }



    //<---------------------------Getting All Entities--------------------------->
    @Transactional("db1TransactionManager")
    public List<Employee> getEmployeesFromDb1() {
        List<Employee> employees = db1EmployeeRepository.findAll();
        System.out.println("Employees from DB1: " + employees);  // Debugging line
        return employees;
    }

    @Transactional("db2TransactionManager")
    public List<Employee> getEmployeesFromDb2() {
        List<Employee> employees = db2EmployeeRepository.findAll();
        System.out.println("Employees from DB2: " + employees);  // Debugging line
        return employees;
    }

    @Transactional("db1TransactionManager")
    public List<Manager> getManagersFromDb1() {
        List<Manager> managers = db1ManagerRepository.findAll();
        System.out.println("Managers from DB1: " + managers);  // Debugging line
        return managers;
    }

    @Transactional("db2TransactionManager")
    public List<Manager> getManagersFromDb2() {
        List<Manager> managers = db2ManagerRepository.findAll();
        System.out.println("Managers from DB2: " + managers);  // Debugging line
        return managers;
    }


    //<---------------------------Adding Entities--------------------------->
    @Transactional("db1TransactionManager")
    public void addEmployeeToDb1(Employee employee) {
        db1EmployeeRepository.save(employee);
    }

    @Transactional("db2TransactionManager")
    public void addEmployeeToDb2(Employee employee) {
        db2EmployeeRepository.save(employee);
    }

    @Transactional("db1TransactionManager")
    public void addManagerToDb1(Manager manager) {
        db1ManagerRepository.save(manager);
    }

    @Transactional("db2TransactionManager")
    public void addManagerToDb2(Manager manager) {
        db2ManagerRepository.save(manager);
    }


    //<---------------------------Updating Entities--------------------------->
    @Transactional("db1TransactionManager")
    public void updateEmployeeInDb1(Employee employee, Long managerId) {
        Manager manager = db1ManagerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found"));
        employee.setManager(manager);
        db1EmployeeRepository.save(employee);
    }

    @Transactional("db2TransactionManager")
    public void updateEmployeeInDb2(Employee employee, Long managerId) {
        Manager manager = db2ManagerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found"));
        employee.setManager(manager);
        db2EmployeeRepository.save(employee);
    }

    @Transactional("db1TransactionManager")
    public void updateManagerInDb1(Manager manager) {
        db1ManagerRepository.save(manager);
    }

    @Transactional("db2TransactionManager")
    public void updateManagerInDb2(Manager manager) {
        db2ManagerRepository.save(manager);
    }


    //<---------------------------Deleting Entities--------------------------->
    @Transactional("db1TransactionManager")
    public void deleteEmployeeInDb1(Long id) {
        db1EmployeeRepository.deleteById(id);
    }

    @Transactional("db2TransactionManager")
    public void deleteEmployeeInDb2(Long id) {
        db2EmployeeRepository.deleteById(id);
    }

    @Transactional("db1TransactionManager")
    public void deleteManagerInDb1(Long id) {
        db1ManagerRepository.deleteById(id);
    }

    @Transactional("db2TransactionManager")
    public void deleteManagerInDb2(Long id) {
        db2ManagerRepository.deleteById(id);
    }


    //<---------------------------Getting selected Entity--------------------------->
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

    //<---------------------------HasManageranyEmployee?--------------------------->
    public boolean hasEmployees(Long managerId, String db) {
        if ("db1".equalsIgnoreCase(db)) {
            return db1EmployeeRepository.existsByManagerId(managerId);
        } else {
            return db2EmployeeRepository.existsByManagerId(managerId);
        }
    }

}
