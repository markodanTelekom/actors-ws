package com.onboarding.actors.actorsws.dao;

import com.onboarding.actors.actorsws.model.ActorEntity;

import java.util.List;

public interface ActorDAO {

    ActorEntity getActorById(Integer actorId);

    List<ActorEntity> getActorsByName(String fullName);

    List<ActorEntity> getActors();

    ActorEntity createActor(ActorEntity actorEntity);

    ActorEntity updateActor(ActorEntity actorEntity);

    void deleteActor(Integer actorId);
}
