package com.Ts.Employee_Management.dto;

import com.Ts.Employee_Management.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {

    @NotBlank(message = "department is required")
    @Size(max = 255)
    private String depName;

    @Size(max = 255)
    private String description;

    @Builder.Default
    private Boolean isActive =  true;

}
