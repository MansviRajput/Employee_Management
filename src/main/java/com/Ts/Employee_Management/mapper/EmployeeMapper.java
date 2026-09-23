package com.Ts.Employee_Management.mapper;

import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;
import com.Ts.Employee_Management.entity.Employee;
import com.Ts.Employee_Management.enums.EmployeeType;
import com.Ts.Employee_Management.utils.Helper;

import java.util.Optional;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequest employeeRequest) {
        return Employee.builder()
                .id(Helper.generateId())
                .isActive(true)
                .isDeleted(false)
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .salary(employeeRequest.getSalary())
                .joiningDate(employeeRequest.getJoiningDate())
                .employeeType(EmployeeType.fromValue(employeeRequest.getEmployeeType()).getEmployeeType()) //return the code but if you use the name()n then it return the enum
                .build();
    }



    public static EmployeeResponse toResponse(Employee employee) {

        Optional<Department> department = Optional.ofNullable(employee.getDepartment());

        return EmployeeResponse.builder()
                .empId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .salary(Helper.formatBigDecimal(employee.getSalary()))
                .joiningDate(employee.getJoiningDate() != null ?
                        Helper.formatDate(employee.getJoiningDate()) : null)
                .employeeType(employee.getEmployeeType() != null
                        ? EmployeeType.fromValue(employee.getEmployeeType()).getEmployeeType()
                        : null)
                .isActive(employee.getIsActive())
                .departmentId(department.map(Department::getId).orElse(null))
                .departmentName(department.map(Department::getDepName).orElse(null))
                .build();
    }
}
