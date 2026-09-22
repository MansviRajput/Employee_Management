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

public class Department extends AbstractPersistable {

    @Column(name = "department_name", nullable = false, unique = true)
    private String depName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

}
