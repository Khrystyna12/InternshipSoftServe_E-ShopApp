package com.internshipSoftServe.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    @Size(max = 255)
    private String text;

    private LocalDateTime dateCreation;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private User authorComments;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "comments")
    private List<Product> products;
}
