package com.Ts.Employee_Management.mapper;

import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;
import com.Ts.Employee_Management.entity.Employee;
import com.Ts.Employee_Management.utils.Helper;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequest employeeRequest, Department department){
        return Employee.builder()
                .empId(Helper.generateId())
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .salary(employeeRequest.getSalary())
                .joiningDate(employeeRequest.getJoiningDate())
                .isActive(employeeRequest.getIsActive() != null ? employeeRequest.getIsActive() : true)
                .department(department)
                .build();
    }

    public static void updateEntity(Employee employee,EmployeeRequest employeeRequest,Department department){
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employee.getEmail());
        employee.setSalary(employeeRequest.getSalary());
        employee.setJoiningDate(employeeRequest.getJoiningDate());
        if(employeeRequest.getIsActive() != null){
            employee.setIsActive(employeeRequest.getIsActive());
        }
        employee.setDepartment(department);
    }

    public static EmployeeResponse toResponse(Employee employee){
        Department dep = employee.getDepartment();
        return EmployeeResponse.builder()
                .empId(employee.getEmpId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .joiningDate(employee.getJoiningDate())
                .isActive(employee.getIsActive())
                .departmentId(dep != null ? dep.getDepId() : null)
                .departmentName(dep != null ? dep.getDepName() : null)
                .build();
    }
}
