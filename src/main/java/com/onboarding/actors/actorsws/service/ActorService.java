package com.onboarding.actors.actorsws.service;

import com.onboarding.actors.actorsws.dao.ActorDAO;
import com.onboarding.actors.actorsws.mapper.ObjectUtilMapper;
import com.onboarding.actors.actorsws.model.ActorDTO;
import com.onboarding.actors.actorsws.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    ActorDAO actorDAO;

    @Autowired
    ObjectUtilMapper mapper;

    public ActorDTO getActorById(Integer actorId) {

        return mapper.map(actorDAO.getActorById(actorId), ActorDTO.class);
    }

    public List<ActorDTO> getActorsByName(String fullName) {

        return mapper.mapList(actorDAO.getActorsByName(fullName), ActorDTO.class);
    }

    public List<ActorDTO> getActors() {

        return mapper.mapList(actorDAO.getActors(), ActorDTO.class);
    }

    public ActorDTO createActor(ActorDTO actorDTO) {

        ActorEntity actorEntity = mapper.map(actorDTO, ActorEntity.class);

        actorDAO.createActor(actorEntity);

        return mapper.map(actorDAO.getActorById(actorEntity.getActorId()), ActorDTO.class);
    }

    public ActorDTO updateActor(ActorEntity actorEntity, Integer actorId) {

        //ActorEntity actorEntity = mapper.map(actorDAO.getActorById(actorEntity.getActorId()), ActorEntity.class);

        actorDAO.updateActor(actorEntity);

        return mapper.map(actorDAO.getActorById(actorEntity.getActorId()), ActorDTO.class);
    }

    public void deleteActor(Integer actorId) {

        actorDAO.deleteActor(actorId);
    }
}
