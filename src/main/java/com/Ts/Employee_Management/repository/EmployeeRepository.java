package com.Ts.Employee_Management.repository;

import com.Ts.Employee_Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    boolean existsByEmail(String email);

    List<Employee> findAllByDepartment_DepId(String depId);

    List<Employee> searchEmployeeByFirstName(String name);

    List<Employee> searchEmployeeByDepartment_DepName(String department);
}
