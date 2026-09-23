package com.Ts.Employee_Management.controller;

import com.Ts.Employee_Management.dto.ApiResponse;
import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.dto.PageResponse;
import com.Ts.Employee_Management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        ApiResponse<EmployeeResponse> response = ApiResponse.<EmployeeResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.createEmployee(employeeRequest))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<EmployeeResponse>>> getAllEmployee(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        PageResponse<EmployeeResponse> pageResponse = PageResponse.from(employeeService.getAllEmployee(pageable));

        ApiResponse<PageResponse<EmployeeResponse>> response = ApiResponse.<PageResponse<EmployeeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .data(pageResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable String id) {
        ApiResponse<EmployeeResponse> response = ApiResponse.<EmployeeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.getEmployeeById(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> searchEmployee(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department) {
        List<EmployeeResponse> result;
        if (StringUtils.hasText(name)) {
            result = employeeService.searchEmployeeByName(name);
        } else if (StringUtils.hasText(department)) {
            result = employeeService.searchEmployeeByDepartmentName(department);
        } else {
            throw new IllegalArgumentException("Provide either name or department");
        }

        ApiResponse<List<EmployeeResponse>> response = ApiResponse.<List<EmployeeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployeeById(
            @PathVariable String id, @RequestBody EmployeeRequest employeeRequest) {
        ApiResponse<EmployeeResponse> response = ApiResponse.<EmployeeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.updateEmployeeById(id, employeeRequest))
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteEmployeeById(@PathVariable String id) {
        ApiResponse<Boolean> response = ApiResponse.<Boolean>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.deleteEmployeeById(id))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}