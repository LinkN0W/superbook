package ru.team.superbook1.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    public static enum UserRole {
        ADMIN, USER;
        public static User.UserRole getById(String id){
            for(User.UserRole e : values()) {
                if(e.name().equalsIgnoreCase(id)) return e;
            }
            return USER;
        }
    }
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id ;


    @Column(name = "email")
    private String email;


    private String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;



    @ManyToMany
    @JoinTable(
            name = "form",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;


}
