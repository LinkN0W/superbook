package ru.team.superbook1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.team.superbook1.entities.User;

import java.util.Date;

@Getter
@Setter
public class PeriodDateDTO {

    public enum Period {

        MONTH,

        YEAR;

    }


    private Date date;

    @JsonProperty(value = "period")
    private Period period;

}
