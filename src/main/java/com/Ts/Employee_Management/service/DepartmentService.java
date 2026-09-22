package com.Ts.Employee_Management.service;

import com.Ts.Employee_Management.dto.DepartmentRequest;
import com.Ts.Employee_Management.dto.DepartmentResponse;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    List<DepartmentResponse> getAllDepartment();

    DepartmentResponse getDepartmentById(String depId);

    DepartmentResponse updateDepartmentById(String depId,DepartmentRequest departmentRequest);

    Boolean deleteDepartmentById(String depId);

    List<EmployeeResponse> getDepartmentEmployee(String depId);

    Department getDepartmentEntityById(String id);


}
