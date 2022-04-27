package com.alkemy.ong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table(name = "slides")
@Builder
@AllArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE slides SET soft_delete = true WHERE id=?")
@NoArgsConstructor
@Where(clause = "soft_delete=false")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "imageUrl" , nullable = false)
    private String imageUrl;

    @Column(name = "text" , nullable = false)
    private String text;

    @Column(name = "order_number" , nullable = false)
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "organization")
    private Organization organizationId;

    @Column(name="soft_delete")
    private Boolean softDelete = Boolean.FALSE;

}
