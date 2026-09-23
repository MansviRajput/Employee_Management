package com.Ts.Employee_Management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "department")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE department SET is_deleted = true, is_active = false WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Department extends AbstractPersistable {

    @Column(name = "department_name", nullable = false, unique = true)
    private String depName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "department")
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();

}
