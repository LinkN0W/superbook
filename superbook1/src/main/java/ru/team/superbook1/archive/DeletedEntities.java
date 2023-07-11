package ru.team.superbook1.archive;

import ru.team.superbook1.entities.Book;

import java.util.Date;
import java.util.UUID;

public interface DeletedEntities {


    abstract UUID  getId();
    abstract UUID getRemoverId();

    abstract Date getDateOfDelete();

    abstract void  setId(UUID id);
    abstract void setRemoverId(UUID removerId);

    abstract void setDateOfDelete(Date dateOfDelete);



}
