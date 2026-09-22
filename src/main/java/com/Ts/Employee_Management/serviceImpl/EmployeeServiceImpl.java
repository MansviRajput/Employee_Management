package com.Ts.Employee_Management.serviceImpl;

import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;
import com.Ts.Employee_Management.entity.Employee;
import com.Ts.Employee_Management.exception.ConflictException;
import com.Ts.Employee_Management.exception.ResourceNotFoundException;
import com.Ts.Employee_Management.mapper.EmployeeMapper;
import com.Ts.Employee_Management.repository.DepartmentRepository;
import com.Ts.Employee_Management.repository.EmployeeRepository;
import com.Ts.Employee_Management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        String email = employeeRequest.getEmail();
        if(employeeRepository.existsByEmail(email)){
            throw new ConflictException("Email is already in use : " + email);
        }
        Department department = findDepartmentOrThrow(employeeRequest.getDepartmentId());

        Employee employee = EmployeeMapper.toEntity(employeeRequest,department);
        employee.setEmail(email);

        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public Page<EmployeeResponse> getAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(EmployeeMapper::toResponse);
    }

    @Override
    public EmployeeResponse getEmployeeById(UUID empId) {
        return EmployeeMapper.toResponse(findEmployeeOrThrow(empId));
    }

    @Override
    public EmployeeResponse updateEmployeeById(UUID empId,EmployeeRequest employeeRequest) {

        Employee employee = findEmployeeOrThrow(empId);
        String email = employeeRequest.getEmail();
        if(employeeRepository.existsByEmail(email)){
            throw new ConflictException("Email is already in use : " + email);
        }
        Department department = findDepartmentOrThrow(employeeRequest.getDepartmentId());
        EmployeeMapper.updateEntity(employee,employeeRequest,department);
        employee.setEmail(email);
        return EmployeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse deleteEmployeeById(UUID empId) {
        employeeRepository.delete(findEmployeeOrThrow(empId));
        return null;
    }



    private Employee findEmployeeOrThrow(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }


    private Department findDepartmentOrThrow(UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id));
    }
}
