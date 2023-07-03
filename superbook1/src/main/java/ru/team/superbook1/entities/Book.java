package ru.team.superbook1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id ;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "amount_copies")
    private int amount;

    @ManyToMany(mappedBy = "books")
    private List<User> users;


}
