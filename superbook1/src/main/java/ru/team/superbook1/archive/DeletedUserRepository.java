package ru.team.superbook1.archive;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeletedUserRepository extends CrudRepository<DeletedUsers, UUID> {
}
