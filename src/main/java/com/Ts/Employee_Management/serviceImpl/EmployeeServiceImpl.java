package com.Ts.Employee_Management.serviceImpl;

import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.entity.Department;
import com.Ts.Employee_Management.entity.Employee;
import com.Ts.Employee_Management.exception.ConflictException;
import com.Ts.Employee_Management.exception.ResourceNotFoundException;
import com.Ts.Employee_Management.mapper.EmployeeMapper;
import com.Ts.Employee_Management.repository.EmployeeRepository;
import com.Ts.Employee_Management.service.DepartmentService;
import com.Ts.Employee_Management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        String email = employeeRequest.getEmail();
        if(employeeRepository.existsByEmail(email)){
            throw new ConflictException("Email is already in use : " + email);
        }
        Department department =departmentService.getDepartmentEntityById(employeeRequest.getDepartmentId());

        Employee employee = EmployeeMapper.toEntity(employeeRequest);
        employee.setDepartment(department);

        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public Page<EmployeeResponse> getAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(EmployeeMapper::toResponse);
    }

    @Override
    public EmployeeResponse getEmployeeById(String empId) {
        return EmployeeMapper.toResponse(findEmployeeOrThrow(empId));
    }

    @Override
    public EmployeeResponse updateEmployeeById(String empId,EmployeeRequest employeeRequest) {

        Employee employee = findEmployeeOrThrow(empId);
        String email = employeeRequest.getEmail();
        if(employeeRepository.existsByEmail(email)){
            throw new ConflictException("Email is already in use : " + email);
        }
        Department department = departmentService.getDepartmentEntityById(employeeRequest.getDepartmentId());
        employee.setId(empId);
        employee.setUpdatedBy(employeeRequest.getFirstName());
        employee.setUpdatedAt();
    }

    @Override
    public Boolean deleteEmployeeById(String empId) {
        employeeRepository.delete(findEmployeeOrThrow(empId));
        return true;
    }

    @Override
    public List<EmployeeResponse> searchEmployeeByName(String name) {
        return employeeRepository.searchEmployeeByFirstName(name).stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

//    @Override
//    public List<EmployeeResponse> searchEmployeeByDepartmentName(String department) {
//        return employeeRepository.searchEmployeeByDepartment_DepName(department).stream()
//                .map(EmployeeMapper::toResponse)
//                .toList();
//    }


    private Employee findEmployeeOrThrow(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }



}
