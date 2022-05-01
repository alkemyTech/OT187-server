package com.alkemy.ong.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "You must enter your name")
    @Column(name = "first_name")
    private String firstName;
    
    @NotBlank(message = "You must enter your last name")
    @Column(name = "last_name")
    private String lastName;
    
    @NotBlank(message = "You must enter an email")
    @Email(message = "Email is not valid")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "You must enter a password")
    private String password;
    @Column(nullable = false)
    private String photo;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private Role roleId;
    
    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDate creationDate;
    
    private Integer active = 1;
}
