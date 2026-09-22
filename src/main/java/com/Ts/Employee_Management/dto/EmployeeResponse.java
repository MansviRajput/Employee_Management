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

    private UUID empId;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    private LocalDate joiningDate;
    private Boolean isActive;
    private UUID departmentId;
    private String departmentName;
}