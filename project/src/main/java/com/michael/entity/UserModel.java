package com.michael.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserModel {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Enter valid email")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 5, message = "Password should be at least 5 characters")
    private String password;
    private Long age = 0L;
}
