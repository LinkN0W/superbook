package ru.team.superbook1.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id ;



}
