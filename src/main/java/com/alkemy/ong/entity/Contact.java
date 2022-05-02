package com.alkemy.ong.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "contacts")
@Data
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "You must enter your name")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "You must enter your phone number")
    private String phone;
    
    @NotBlank(message = "you must enter your email")
    @Column(nullable = false)
    @Email(message = "Email is not valid")
    private String email;
    
    private String message;
    
    private Integer active = 1;
}
