package com.onboarding.actors.actorsws.controller;

import com.onboarding.actors.actorsws.helper.Helper;
import com.onboarding.actors.actorsws.model.ActorDTO;
import com.onboarding.actors.actorsws.model.ActorEntity;
import com.onboarding.actors.actorsws.service.ActorService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @Autowired
    Helper helper;

    private static final Logger logger = LogManager.getLogger(ActorController.class);

    @GetMapping("/actors")
    public ResponseEntity<List<ActorDTO>> getActors() {
        logger.info("Method getActors started! ");

        List<ActorDTO> actorDTOList = actorService.getActors();
        logger.info("API response - getActors -> " + helper.convertObjectToString(actorDTOList));

        logger.info("Method getGenres finished! ");
        return new ResponseEntity<>(actorDTOList, HttpStatus.OK);
    }

    @GetMapping("/actors/id/{actorId:[0-9]+}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable("actorId") Integer actorId) {
        logger.info("Method getActorById " + actorId + " started! ");

        ActorDTO actorDTO = actorService.getActorById(actorId);
        logger.info("API response - getActorById -> " + helper.convertObjectToString(actorDTO));

        logger.info("Method getActorById " + actorId + " finished! ");
        return new ResponseEntity<>(actorDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/actors/name/{fullName:[A-Za-z]+}")
    public ResponseEntity<List<ActorDTO>> getActorByName(@PathVariable("fullName") String fullName) {
        logger.info("Method getActorByName " + fullName + " started! ");

        List<ActorDTO> actorDTOList = actorService.getActorsByName(fullName);
        logger.info("API response - getActorByName -> " + helper.convertObjectToString(actorDTOList));

        logger.info("Method getGenreByName by genreName " + fullName + " finished! ");
        return new ResponseEntity<>(actorDTOList, HttpStatus.OK);
    }

    @PostMapping("/actors")
    public ResponseEntity<ActorDTO> createActor(@Valid @RequestBody ActorDTO actorDTO) {

        ActorDTO savedActorDTO = actorService.createActor(actorDTO);

        return new ResponseEntity<>(savedActorDTO, HttpStatus.OK);
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable Integer actorId) {
        actorService.deleteActor(actorId);
    }
}
