package ru.team.superbook1.archive;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.team.superbook1.entities.Book;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeletedBookRepository extends CrudRepository<DeletedBooks, UUID> {



}
