package com.springboot.TestApp.repository.db1;

import com.springboot.TestApp.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db1ManagerRepository extends JpaRepository<Manager, Long> {
}
