package ru.team.superbook1.functional;

import ru.team.superbook1.entities.Form;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CountFunctional {
    List<Form> countUserPenaltiesForOnce( List<Form> forms, Date date);

    List<Form> countUserPenaltiesForPeriod( List<Form> forms, Date dateOfBegin, Date dateOfEnd);


}
