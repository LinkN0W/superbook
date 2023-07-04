package ru.team.superbook1.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.team.superbook1.entities.Book;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Book b set b.author = :author, b.amount = :amount,  b.description = :description, b.title = :title where b.id = :id")
    void updateBookById(@Param("id") UUID id,@Param("author") String author, @Param("amount") Integer amount,
                                  @Param("description") String description, @Param("title") String title);
}
