package com.excel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int employeeId;
    @Column(name = "FirstName")
    String firstName;
    @Column(name = "LastName")
    String lastName;
    @Column(name = "Email")
    String email;
    @Column(name = "Phone")
    String phone;
    @Column(name = "Salary")
    double salary;
    @Column(name = "Department")
    String department;
}
