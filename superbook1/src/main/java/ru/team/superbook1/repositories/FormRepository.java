package ru.team.superbook1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.entities.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormRepository extends CrudRepository<Form, UUID> {

    List<Form> findAllByUserId(UUID id);



}
