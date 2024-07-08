package com.springboot.TestApp.db1;

import com.springboot.TestApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db1EmployeeRepository extends JpaRepository<Employee, Long> {
}
