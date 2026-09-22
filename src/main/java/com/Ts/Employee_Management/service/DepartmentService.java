package com.Ts.Employee_Management.service;

import com.Ts.Employee_Management.dto.DepartmentRequest;
import com.Ts.Employee_Management.dto.DepartmentResponse;
import com.Ts.Employee_Management.dto.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    List<DepartmentResponse> getAllDepartment();

    DepartmentResponse getDepartmentById(UUID depId);

    DepartmentResponse updateDepartmentById(UUID depId,DepartmentRequest departmentRequest);

    DepartmentResponse deleteDepartmentById(UUID depId);

    List<EmployeeResponse> getDepartmentEmployee(UUID depId);


}
