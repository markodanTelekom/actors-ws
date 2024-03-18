package com.onboarding.actors.actorsws.service;

import com.onboarding.actors.actorsws.dao.ActorDAO;
import com.onboarding.actors.actorsws.exception.ResourceNotFoundException;
import com.onboarding.actors.actorsws.mapper.ObjectUtilMapper;
import com.onboarding.actors.actorsws.model.ActorDTO;
import com.onboarding.actors.actorsws.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import static com.onboarding.actors.actorsws.helper.AgeCalculator.calculateAge;
import static com.onboarding.actors.actorsws.helper.AgeCalculator.calculateAges;
import static com.onboarding.actors.actorsws.helper.ImageHelper.*;

@Service
public class ActorService {

    @Autowired
    ActorDAO actorDAO;

    @Autowired
    ObjectUtilMapper mapper;

    public ActorDTO getActorById(Integer actorId) {
        ActorEntity actorEntity = actorDAO.getActorById(actorId);
        if(actorEntity == null) {
            throw new ResourceNotFoundException("Actor with Id:" + actorId + " is not found.");
        }
        ActorDTO actorDTO = mapper.map(actorEntity, ActorDTO.class);

        String file = "src\\main\\resources\\static\\" + actorDTO.getImageName();
        String base64Image = encodeImageToBase64(file);
        actorDTO.setActorImage(base64Image);

        calculateAge(actorDTO);

        return actorDTO;
    }

    public List<ActorDTO> getActorsByName(String fullName) {

        List<ActorDTO> actorDTOs = mapper.mapList(actorDAO.getActorsByName(fullName), ActorDTO.class);

        for(ActorDTO actorDTO: actorDTOs) {
            String file = "src\\main\\resources\\static\\" + actorDTO.getImageName();
            String base64Image = encodeImageToBase64(file);
            actorDTO.setActorImage(base64Image);
        }

        calculateAges(actorDTOs);

        return actorDTOs;
    }

    public List<ActorDTO> getActors() {

        List<ActorDTO> actorDTOs = mapper.mapList(actorDAO.getActors(), ActorDTO.class);

        for(ActorDTO actorDTO: actorDTOs) {
            String file = "src\\main\\resources\\static\\" + actorDTO.getImageName();
            String base64Image = encodeImageToBase64(file);
            actorDTO.setActorImage(base64Image);
        }

        calculateAges(actorDTOs);

        return actorDTOs;
    }

    public ActorDTO createActor(ActorDTO actorDTO) {

        ActorEntity actorEntity = mapper.map(actorDTO, ActorEntity.class);

        if(actorDTO.getActorImage() !=  null) {
            String[] imageData = splittedImageData(actorDTO.getActorImage());
            String imageExtension = getImageExtension(imageData[0]);

            UUID uuid = UUID.randomUUID();
            String file = "src//main//resources//static//" + uuid + "." + imageExtension;

            saveBase64Image(imageData[1], file);
            actorEntity.setImageName(uuid + "." + imageExtension);
        }

        ActorEntity returnedActorEntity = actorDAO.createActor(actorEntity);
        ActorDTO returnedActorDTO = mapper.map(actorDAO.getActorById(returnedActorEntity.getActorId()), ActorDTO.class);

        returnedActorDTO.setActorImage(actorDTO.getActorImage());
        calculateAge(returnedActorDTO);

        return returnedActorDTO;
    }

    public ActorDTO updateActor(ActorDTO actorDTO, Integer actorId) {

        ActorEntity actorEntityOld = actorDAO.getActorById(actorId);
        if(actorEntityOld == null){
            throw new ResourceNotFoundException("Actor with Id:" + actorId + " is not found.");
        }
        actorDTO.setActorId(actorId);

        if(!Objects.equals(actorDTO.getActorImage(), "")){
            if(actorEntityOld.getImageName() == null){
                String[] imageData = splittedImageData(actorDTO.getActorImage());
                String imageExtension = getImageExtension(imageData[0]);
                UUID uuid = UUID.randomUUID();
                String file = "src//main//resources//static//" + uuid + "." + imageExtension;
                saveBase64Image(imageData[1], file);

                actorDTO.setImageName(uuid + "." + imageExtension);
            }
            else {
                String[] imageData = splittedImageData(actorDTO.getActorImage());
                String imageExtension = getImageExtension(imageData[0]);

                String oldFile = actorEntityOld.getImageName();
                String[] newFileSplit = oldFile.split("\\.");

                String newFile = "src\\main\\resources\\static\\" + newFileSplit[0] + "." + imageExtension;
                saveBase64Image(imageData[1], newFile);

                actorDTO.setImageName(newFileSplit[0] + "." + imageExtension);
            }
        }

        ActorEntity actorEntity = mapper.map(actorDTO, ActorEntity.class);
        ActorEntity returnedActorEntity = actorDAO.updateActor(actorEntity);
        ActorDTO returnedActorDTO = mapper.map(actorDAO.getActorById(returnedActorEntity.getActorId()), ActorDTO.class);
        returnedActorDTO.setActorImage(actorDTO.getActorImage());


        calculateAge(returnedActorDTO);

        return returnedActorDTO;
    }

    public void deleteActor(Integer actorId) {

        ActorEntity actorEntity= actorDAO.getActorById(actorId);
        if(actorEntity == null){
            throw new ResourceNotFoundException("Actor with Id:" + actorId + " is not found.");
        }

        String file = "src\\main\\resources\\static\\"+ actorEntity.getImageName();
        deleteFile(file);

        actorDAO.deleteActor(actorId);
    }
}
