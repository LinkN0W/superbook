package ru.team.superbook1.functional;

import ru.team.superbook1.entities.Form;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FormFunctional implements CountFunctional{


    @Override
    public List<Form> countUserPenaltiesForOnce( List<Form> forms, Date date) {
        forms.stream().forEach(e -> {
                    int difference = 0;
                    if( e.getDateOfReturning() == null)
                        difference = (int) ((e.getTermOfReturning().getTime() - date.getTime()) / 86400000);
                    else
                        difference = (int) ((e.getTermOfReturning().getTime() - e.getDateOfReturning().getTime()) / 86400000);

                    if(difference < 0){
                        e.setDelay(Math.abs(difference));
                        e.setPenalties( 5*(Math.abs(difference)));
                    }
                    else{
                        e.setPenalties(0);
                        e.setDelay(0);
                    }
                }
        );

        forms.stream().forEach(e -> System.out.println(e.getPenalties()));

        return forms;
    }

    @Override
    public List<Form> countUserPenaltiesForPeriod(List<Form> forms, Date dateOfBegin, Date dateOfEnd) {
        forms.stream().forEach(e -> {
                    int difference = 0;

                    if( e.getDateOfTaking().getTime() < dateOfBegin.getTime()){
                        if(e.getDateOfReturning() == null || e.getDateOfReturning().getTime() > dateOfEnd.getTime()){
                            difference = (int) ((dateOfEnd.getTime() - dateOfBegin.getTime()) / 86400000);
                            System.out.println(difference);
                        }
                        else{
                            difference = (int) ((dateOfEnd.getTime() - e.getDateOfReturning().getTime()) / 86400000);
                        }
                    }
                    else{
                        if(e.getDateOfReturning() == null || e.getDateOfReturning().getTime() > dateOfEnd.getTime()){
                            difference = (int) ((dateOfEnd.getTime() - e.getDateOfTaking().getTime()) / 86400000);
                        }
                        else{
                            difference = (int) ((e.getDateOfReturning().getTime() - e.getDateOfTaking().getTime()) / 86400000);
                        }
                    }
                    difference +=1;

                        if(difference < 30){
                            e.setDelay(0);
                            e.setPenalties(0);
                        }
                        else if(difference == 30){
                            e.setDelay(Math.abs(difference) );
                            e.setPenalties(5 * ((Math.abs(difference))));
                        }
                        else {
                            e.setDelay(Math.abs(difference) - 30);
                            e.setPenalties(5 * ((Math.abs(difference)) - 30));
                        }


                    }


        );

        forms.stream().forEach(e -> System.out.println(e.getPenalties()));

        return forms;
    }
}
