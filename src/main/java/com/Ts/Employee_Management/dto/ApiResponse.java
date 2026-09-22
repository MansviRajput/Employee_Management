package com.Ts.Employee_Management.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private Integer statusCode;
    private String message;
    private Boolean multiple;
    private T data;

}
