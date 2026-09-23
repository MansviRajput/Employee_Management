package com.Ts.Employee_Management.dto;

import com.Ts.Employee_Management.entity.Employee;
import com.Ts.Employee_Management.exception.ValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {

    private String depName;

    private String description;

    private Boolean isActive =  true;

    public void validate(){
        if(!StringUtils.hasText(depName)){
            throw new ValidationException("Department name is required");
        }
        if(depName.length() > 255){
            throw new ValidationException("Department name must not exceed 255 characters");
        }
        if(!StringUtils.hasText(description)){
            throw new ValidationException("Department description is required");
        }
        if(description.length() > 255){
            throw new ValidationException("Department description must not exceed 255 characters");
        }
    }
}
