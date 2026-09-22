package com.Ts.Employee_Management.serviceImpl;

import com.Ts.Employee_Management.dto.DepartmentRequest;
import com.Ts.Employee_Management.dto.DepartmentResponse;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;
import com.Ts.Employee_Management.exception.ConflictException;
import com.Ts.Employee_Management.exception.ResourceNotFoundException;
import com.Ts.Employee_Management.mapper.DepartmentMapper;
import com.Ts.Employee_Management.mapper.EmployeeMapper;
import com.Ts.Employee_Management.repository.DepartmentRepository;
import com.Ts.Employee_Management.repository.EmployeeRepository;
import com.Ts.Employee_Management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        String depName = departmentRequest.getDepName();
        if(departmentRepository.existsByDepNameIgnoreCase(depName)){
            throw new ConflictException("Department is already in use : " + depName);
        }
        Department department = DepartmentMapper.toEntity(departmentRequest);
        department.setDepName(depName);
        departmentRepository.save(department);
        return DepartmentMapper.toResponse(department);
    }

    @Override
    public List<DepartmentResponse> getAllDepartment() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(UUID depId) {
        Department department = findDepartmentOrThrow(depId);
        return DepartmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartmentById(UUID depId,DepartmentRequest departmentRequest) {
        Department department = findDepartmentOrThrow(depId);
        String name = departmentRequest.getDepName().trim();
        if(departmentRepository.existsByDepNameIgnoreCase(name)){
            throw new ConflictException("Department is aalready exists: " + name);
        }
        department.setDepName(departmentRequest.getDepName());
        department.setDescription(departmentRequest.getDescription());
        if (departmentRequest.getIsActive() != null) {
            department.setIsActive(departmentRequest.getIsActive());
        }
        return DepartmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse deleteDepartmentById(UUID depId) {
        departmentRepository.delete(findDepartmentOrThrow(depId));
        return null;
    }

    @Override
    public List<EmployeeResponse> getDepartmentEmployee(UUID depId) {
        Department department = findDepartmentOrThrow(depId);
        return employeeRepository.findAllByDepartment_DepId(depId).stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    private Department findDepartmentOrThrow(UUID depId){
        return departmentRepository.findById(depId).orElseThrow(()-> new ResourceNotFoundException("Department not found : " + depId));
    }
}
