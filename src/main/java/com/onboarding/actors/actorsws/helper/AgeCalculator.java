package com.onboarding.actors.actorsws.helper;

import com.onboarding.actors.actorsws.model.ActorDTO;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AgeCalculator {
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static void calculateAge(ActorDTO actorDTO) {
        LocalDate dateOfBirth = convertToLocalDateViaInstant(actorDTO.getDateOfBirth());
        LocalDate curDate = LocalDate.now();
        actorDTO.setAge(Period.between(dateOfBirth, curDate).getYears());
    }

    public static void calculateAges(List<ActorDTO> actorDTOs) {
        for(ActorDTO actorDTO: actorDTOs) {
            LocalDate dateOfBirth = convertToLocalDateViaInstant(actorDTO.getDateOfBirth());
            LocalDate curDate = LocalDate.now();
            actorDTO.setAge(Period.between(dateOfBirth, curDate).getYears());
        }
    }
}
