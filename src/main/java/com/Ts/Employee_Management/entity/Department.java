package com.Ts.Employee_Management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "department")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "department_id",nullable = false,unique = true)
    private UUID depId;

    @Column(name = "department_name",nullable = false,unique = true)
    private String depName;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean isActive =  true;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

}
