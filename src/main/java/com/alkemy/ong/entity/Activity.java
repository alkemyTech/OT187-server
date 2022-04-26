package com.alkemy.ong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE activities SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    @NotNull
    private String image;

    @NotNull
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTimestamp;

    @NotNull
    @Column(columnDefinition = "INT default 0")
    private Integer deleted;
}
