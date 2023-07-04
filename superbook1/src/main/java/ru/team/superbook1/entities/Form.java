package ru.team.superbook1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "form")
@NoArgsConstructor
@Getter
@Setter
public class Form {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id ;

    @JsonIgnore
    @Column(name = "user_id")
    private UUID userId ;

    @Column(name = "book_id")
    private UUID bookId ;

    @Column(name = "date_of_taking")
    private Date dateOfTaking;


    @Column(name = "date_of_returning")
    private Date dateOfReturning;

    @Column(name = "penalties")
    private int penalties;

    public Form(UUID userId, UUID bookId, Date now, Date date, int penalties) {
        this.userId = userId;
        this.bookId = bookId;
        this.dateOfTaking = now;
        this.penalties = penalties;

    }
}
