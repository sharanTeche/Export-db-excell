package com.excel.repository;

import com.excel.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Employee,Integer> {
}
