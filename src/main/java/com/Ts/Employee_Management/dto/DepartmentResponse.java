package com.Ts.Employee_Management.dto;

import com.Ts.Employee_Management.entity.Employee;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {

    private String depId;

    private String depName;

    private String description;

    private Boolean isActive;

}
