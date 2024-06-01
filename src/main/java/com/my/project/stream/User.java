package com.my.project.stream;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 有参、无参、get、set、toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private int age;
}
