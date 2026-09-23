package com.Ts.Employee_Management.dto;

import com.Ts.Employee_Management.exception.ValidationException;
import lombok.*;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    private LocalDate joiningDate;

    private String departmentId;
    private String employeeType;

    public void validate() {
        if (!StringUtils.hasText(firstName)) {
            throw new ValidationException("First Name is Required");
        }
        if (!StringUtils.hasText(lastName)) {
            throw new ValidationException("Last Name is Required");
        }
        if (!StringUtils.hasText(email)) {
            throw new ValidationException("Email is Required");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Email is not valid");
        }
        if (salary == null) {
            throw new ValidationException("Salary is Required");
        } else if (salary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Salary should be positive");
        }
        if (joiningDate != null && joiningDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Joining Date is Required");
        }
        if (!StringUtils.hasText(departmentId)) {
            throw new ValidationException("Department Id is Required");
        }
        if (!StringUtils.hasText(employeeType)) {
            throw new ValidationException("Employee type is Required");
        }
    }
}