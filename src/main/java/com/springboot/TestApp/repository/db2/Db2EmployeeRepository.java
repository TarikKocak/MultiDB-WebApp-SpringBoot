package com.springboot.TestApp.repository.db2;

import com.springboot.TestApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db2EmployeeRepository extends JpaRepository<Employee,Long> {
}
