package com.alkemy.ong.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = -6460463158912279419L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    private String description;
    @Column(name = "create_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createAt;

    public Role(String name, String description, LocalDate createAt) {
        this.name = name;
        this.description = description;
        this.createAt = createAt;
    }
}
