package com.Ts.Employee_Management.service;

import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    Page<EmployeeResponse> getAllEmployee(Pageable pageable);

    EmployeeResponse getEmployeeById(String empId);

    EmployeeResponse updateEmployeeById(String empId,EmployeeRequest employeeRequest);

    Boolean deleteEmployeeById(String empId);

    List<EmployeeResponse> searchEmployeeByName(String name);

    List<EmployeeResponse> searchEmployeeByDepartmentName(String department);
}
