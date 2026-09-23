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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        String depName = departmentRequest.getDepName();
        if(departmentRepository.existsByDepNameIgnoreCase(depName)){
            throw new ConflictException("Department is already in use : " + depName);
        }
        Department department = DepartmentMapper.toEntity(departmentRequest);
        return DepartmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentResponse> getAllDepartment() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(String depId) {
        Department department = findDepartmentOrThrow(depId);
        return DepartmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartmentById(String depId, DepartmentRequest departmentRequest) {
        Department department = findDepartmentOrThrow(depId);
        String newName = departmentRequest.getDepName().trim();

        boolean nameChanged = !department.getDepName().equalsIgnoreCase(newName);
        if (nameChanged && departmentRepository.existsByDepNameIgnoreCase(newName)) {
            throw new ConflictException("Department already exists: " + newName);
        }

        department.setDepName(newName);
        department.setDescription(departmentRequest.getDescription());
        if (departmentRequest.getIsActive() != null) {
            department.setIsActive(departmentRequest.getIsActive());
        }

        return DepartmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    public Boolean deleteDepartmentById(String depId) {
        departmentRepository.delete(findDepartmentOrThrow(depId));
        return true;
    }

    @Override
    public List<EmployeeResponse> getDepartmentEmployee(String depId) {
        findDepartmentOrThrow(depId);
        return employeeRepository.findAllByDepartment_Id(depId).stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    @Override
    public Department getDepartmentEntityById(String id) {
        return findDepartmentOrThrow(id);
    }

    private Department findDepartmentOrThrow(String depId){
        return departmentRepository.findById(depId).orElseThrow(()-> new ResourceNotFoundException("Department not found : " + depId));
    }
}
