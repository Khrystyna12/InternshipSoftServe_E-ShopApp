package com.internshipSoftServe.eshop.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @Size(max = 45)
    private String name;

    private String text;

    private LocalDateTime dateCreation;

    @ToString.Exclude
    @ManyToOne
    private Product product;

    @Override
    public String toString() {
        return "Articles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", dateCreation=" + dateCreation +
                ", product=" + product +
                '}';
    }
}
