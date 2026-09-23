package com.Ts.Employee_Management.mapper;

import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;
import com.Ts.Employee_Management.entity.Employee;
import com.Ts.Employee_Management.utils.Helper;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequest employeeRequest) {
        return Employee.builder()
                .id(Helper.generateId())
                .
                .isActive(true)
                .isDeleted(false)
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .salary(employeeRequest.getSalary())
                .joiningDate(employeeRequest.getJoiningDate())
                .build();
    }

//    public static void updateEntity(Employee employee,EmployeeRequest employeeRequest,Department department){
//        employee.setFirstName(employeeRequest.getFirstName());
//        employee.setLastName(employeeRequest.getLastName());
//        employee.setEmail(employee.getEmail());
//        employee.setSalary(employeeRequest.getSalary());
//        employee.setJoiningDate(employeeRequest.getJoiningDate());
//        if(employeeRequest.getIsActive() != null){
//            employee.setIsActive(employeeRequest.getIsActive());
//        }
//        employee.setDepartment(department);
//    }

    public static EmployeeResponse toResponse(Employee employee) {
        return EmployeeResponse.builder()
                .empId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .salary(Helper.formatBigDecimal(employee.getSalary()))
                .joiningDate(employee.getJoiningDate() != null ?
                        Helper.formatDate(employee.getJoiningDate()) : null)
                .isActive(employee.getIsActive())
                .departmentId(employee.getDepartment().getId())
                .departmentName(employee.getDepartment().getDepName())
                .build();
    }
}
