package com.onboarding.actors.actorsws.controller;

import com.onboarding.actors.actorsws.helper.Helper;
import com.onboarding.actors.actorsws.model.ActorDTO;
import com.onboarding.actors.actorsws.service.ActorService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    @Autowired
    Helper helper;

    private static final Logger logger = LogManager.getLogger(ActorController.class);

    @GetMapping(value = "/all")
    public ResponseEntity<List<ActorDTO>> getActors() {
        logger.info("Method getActors started! ");

        List<ActorDTO> actorDTOList = actorService.getActors();
        for (ActorDTO actorDTO: actorDTOList) {
            actorDTO.setHref(WebMvcLinkBuilder.linkTo(methodOn(ActorController.class).getActorById(actorDTO.getActorId())).toUriComponentsBuilder().toUriString());
        }
        logger.info("API response - getActors -> " + helper.convertObjectToString(actorDTOList));

        logger.info("Method getGenres finished! ");
        return new ResponseEntity<>(actorDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{actorId:[0-9]+}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable("actorId") Integer actorId) {
        logger.info("Method getActorById " + actorId + " started! ");

        ActorDTO actorDTO = actorService.getActorById(actorId);
        actorDTO.setHref(WebMvcLinkBuilder.linkTo(methodOn(ActorController.class).getActorById(actorDTO.getActorId())).toUriComponentsBuilder().toUriString());
        logger.info("API response - getActorById -> " + helper.convertObjectToString(actorDTO));

        logger.info("Method getActorById " + actorId + " finished! ");
        return new ResponseEntity<>(actorDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getActorByName(@RequestParam(value = "fullName", required = false, defaultValue = "") String fullName) {
        logger.info("Method getActorByName " + fullName + " started! ");

        List<ActorDTO> actorDTOList = actorService.getActorsByName(fullName);
        for (ActorDTO actorDTO: actorDTOList) {
            actorDTO.setHref(WebMvcLinkBuilder.linkTo(methodOn(ActorController.class).getActorById(actorDTO.getActorId())).toUriComponentsBuilder().toUriString());
        }
        logger.info("API response - getActorByName -> " + helper.convertObjectToString(actorDTOList));

        logger.info("Method getGenreByName by genreName " + fullName + " finished! ");
        return new ResponseEntity<>(actorDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@Valid @RequestBody ActorDTO actorDTO) {

        logger.info("Method createActor " + helper.convertObjectToString(actorDTO) + " started! ");

        ActorDTO savedActorDTO = actorService.createActor(actorDTO);
        savedActorDTO.setHref(WebMvcLinkBuilder.linkTo(methodOn(ActorController.class).getActorById(savedActorDTO.getActorId())).toUriComponentsBuilder().toUriString());
        logger.info("API response - createActor -> " + helper.convertObjectToString(savedActorDTO));

        logger.info("Method createActor finished! ");
        return new ResponseEntity<>(savedActorDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{actorId:[0-9]+}")
    public ResponseEntity<ActorDTO> updateActor(@Valid @RequestBody ActorDTO actorDTO, @PathVariable("actorId") Integer actorId) {

        logger.info("Method updateActor " + helper.convertObjectToString(actorDTO) + "for actorId" + actorId + " started! ");

        ActorDTO savedActorDTO = actorService.updateActor(actorDTO, actorId);
        savedActorDTO.setHref(WebMvcLinkBuilder.linkTo(methodOn(ActorController.class).getActorById(savedActorDTO.getActorId())).toUriComponentsBuilder().toUriString());
        logger.info("API response - updateActor -> " + helper.convertObjectToString(savedActorDTO));

        logger.info("Method updateActor finished! ");
        return new ResponseEntity<>(savedActorDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{actorId:[0-9]+}")
    public void deleteActor(@PathVariable("actorId") Integer actorId) {

        logger.info("Method deleteActor by id " + actorId + " started! ");

        actorService.deleteActor(actorId);

        logger.info("Method deleteActor by id " + actorId + " finished! ");
    }
}
