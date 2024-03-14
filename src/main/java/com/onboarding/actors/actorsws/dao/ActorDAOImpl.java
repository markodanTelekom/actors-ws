package com.onboarding.actors.actorsws.dao;

import com.onboarding.actors.actorsws.model.ActorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ActorDAOImpl implements ActorDAO{

    private EntityManager entityManager;

    @Autowired
    public ActorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ActorEntity getActorById(Integer theActorId) {
        return entityManager.find(ActorEntity.class, theActorId);
    }

    @Override
    public List<ActorEntity> getActorsByName(String theFullName) {

        TypedQuery<ActorEntity> query = entityManager.createQuery(
                "from ActorEntity where ActorEntity.actorId = :data", ActorEntity.class);
        query.setParameter("data", theFullName);

        List<ActorEntity> actors = query.getResultList();

        return actors;
    }

    @Override
    public List<ActorEntity> getActors() {
        TypedQuery<ActorEntity> query = entityManager.createQuery(
                "from ActorEntity", ActorEntity.class);

        List<ActorEntity> actors = query.getResultList();

        return actors;
    }

    @Override
    public ActorEntity createActor(ActorEntity theActorEntity) {
        entityManager.persist(theActorEntity);

        return theActorEntity;
    }

    @Override
    public ActorEntity updateActor(ActorEntity theActorEntity) {
        entityManager.merge(theActorEntity);

        return theActorEntity;
    }

    @Override
    public void deleteActor(Integer theActorId) {

        ActorEntity tempActorEntity = entityManager.find(ActorEntity.class, theActorId);

        entityManager.remove(tempActorEntity);
    }
}
