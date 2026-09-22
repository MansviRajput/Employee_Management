package com.Ts.Employee_Management.controller;

import com.Ts.Employee_Management.dto.ApiResponse;
import com.Ts.Employee_Management.dto.EmployeeRequest;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.dto.PageResponse;
import com.Ts.Employee_Management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        ApiResponse<EmployeeResponse> response = ApiResponse.<EmployeeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.createEmployee(employeeRequest))
                .build();
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<EmployeeResponse>>> getAllEmployee(
            @PageableDefault(size = 20) Pageable pageable) {

        PageResponse<EmployeeResponse> page = PageResponse.from(employeeService.getAllEmployee(pageable));

        ApiResponse<PageResponse<EmployeeResponse>> response = ApiResponse.<PageResponse<EmployeeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .data(page)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable UUID id){
        ApiResponse<EmployeeResponse> response = ApiResponse.<EmployeeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.getEmployeeById(id))
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployeeById(@PathVariable UUID id,@RequestBody EmployeeRequest employeeRequest){
        ApiResponse<EmployeeResponse> response = ApiResponse.<EmployeeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.updateEmployeeById(id,employeeRequest))
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployeeById(@PathVariable UUID id){
        ApiResponse<EmployeeResponse> response = ApiResponse.<EmployeeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(employeeService.deleteEmployeeById(id))
                .build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
