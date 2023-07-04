package ru.team.superbook1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.team.superbook1.entities.Book;
import ru.team.superbook1.entities.User;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String email;

    private String password;

    private String role;

    private List<Book> books;
}
