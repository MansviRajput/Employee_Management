package com.Ts.Employee_Management.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private String empId;
    private String firstName;
    private String lastName;
    private String email;
    private String salary;
    private String joiningDate;
    private String employeeType;
    private Boolean isActive;
    private String  departmentId;
    private String departmentName;
}