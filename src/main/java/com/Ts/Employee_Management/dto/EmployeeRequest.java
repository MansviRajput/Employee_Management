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

    @Builder.Default
    private Boolean isActive = true;

    private String departmentId;
    private String employeeType;

    public void validate() {
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasText(firstName)) {
            errors.add("First name is required");
        }
        if (!StringUtils.hasText(lastName)) {
            errors.add("Last name is required");
        }
        if (!StringUtils.hasText(email)) {
            errors.add("Email is required");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            errors.add("Email must be valid");
        }
        if (salary == null) {
            errors.add("Salary is required");
        } else if (salary.compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Salary must be positive");
        }
        if (joiningDate != null && joiningDate.isAfter(LocalDate.now())) {
            errors.add("Joining date cannot be in the future");
        }
        if (!StringUtils.hasText(departmentId)) {
            errors.add("Department is required");
        }
        if (!StringUtils.hasText(employeeType)) {
            errors.add("Employee type is required");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}