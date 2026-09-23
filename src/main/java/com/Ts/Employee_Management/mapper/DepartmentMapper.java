package com.Ts.Employee_Management.mapper;

import com.Ts.Employee_Management.dto.DepartmentRequest;
import com.Ts.Employee_Management.dto.DepartmentResponse;
import com.Ts.Employee_Management.entity.Department;
import com.Ts.Employee_Management.utils.Helper;

public class DepartmentMapper {

    public static Department toEntity(DepartmentRequest request) {
        return Department.builder()
                .id(Helper.generateId())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .isDeleted(false)
                .depName(request.getDepName())
                .description(request.getDescription())
                .build();
    }

    public static DepartmentResponse toResponse(Department department) {
        return DepartmentResponse.builder()
                .depId(department.getId())
                .depName(department.getDepName())
                .description(department.getDescription())
                .isActive(department.getIsActive())
                .build();
    }
}