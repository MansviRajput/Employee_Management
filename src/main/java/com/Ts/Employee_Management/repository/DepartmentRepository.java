package com.Ts.Employee_Management.repository;

import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    boolean existsByDepNameIgnoreCase(String depName);

}
