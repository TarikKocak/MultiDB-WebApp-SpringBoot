package com.springboot.TestApp.repository.db2;

import com.springboot.TestApp.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db2ManagerRepository extends JpaRepository<Manager, Long> {
}
