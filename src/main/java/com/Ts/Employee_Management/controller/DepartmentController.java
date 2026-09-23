package com.Ts.Employee_Management.controller;

import com.Ts.Employee_Management.dto.ApiResponse;
import com.Ts.Employee_Management.dto.DepartmentRequest;
import com.Ts.Employee_Management.dto.DepartmentResponse;
import com.Ts.Employee_Management.dto.EmployeeResponse;
import com.Ts.Employee_Management.service.DepartmentService;
import com.Ts.Employee_Management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> createDepartment(@Valid @RequestBody DepartmentRequest request){
        ApiResponse<DepartmentResponse> response = ApiResponse.<DepartmentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(departmentService.createDepartment(request))
                .build();

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartment(){
        ApiResponse<List<DepartmentResponse>> response = ApiResponse.<List<DepartmentResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(departmentService.getAllDepartment())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> getDepartmentById(@PathVariable String id){
        ApiResponse<DepartmentResponse> response = ApiResponse.<DepartmentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(departmentService.getDepartmentById(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> updateDepartmentById(@PathVariable String id,@RequestBody DepartmentRequest departmentRequest){
        ApiResponse<DepartmentResponse> response = ApiResponse.<DepartmentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(departmentService.updateDepartmentById(id,departmentRequest))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteDepartment(@PathVariable("id") String id) {
        ApiResponse<Boolean> response = ApiResponse.<Boolean>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(departmentService.deleteDepartmentById(id))
                .build();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/employee")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getDepartmentEmployee(@PathVariable String id){
        ApiResponse<List<EmployeeResponse>> response = ApiResponse.<List<EmployeeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .multiple(false)
                .data(departmentService.getDepartmentEmployee(id))
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
