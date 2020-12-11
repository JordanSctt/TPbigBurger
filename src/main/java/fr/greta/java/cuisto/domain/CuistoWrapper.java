package fr.greta.java.cuisto.domain;

import fr.greta.java.cuisto.facade.CuistoDTO;
import fr.greta.java.cuisto.persistence.CuistoEntity;

import java.util.ArrayList;
import java.util.List;

public class CuistoWrapper {


    public List<Cuisto> fromEntities(List<CuistoEntity> entities) {

    List <Cuisto> models = new ArrayList<>();

    for (CuistoEntity entity : entities) {
        models.add(fromEntity(entity));
    }
        return models;
    }

    public List<CuistoDTO> toDTOS(List<Cuisto> models ) {

        List<CuistoDTO> dtos = new ArrayList<>();

        for (Cuisto model : models) {

            dtos.add(toDTO(model));
        }
        return dtos;
    }

    public Cuisto fromEntity(CuistoEntity entity) {

        Cuisto model = new Cuisto();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPresence(CuistoPresence.valueOf(entity.getPresence()));
        return model;

    }

    public CuistoEntity toEntity(Cuisto model) {
        CuistoEntity entity = new CuistoEntity();

        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setPresence(model.getPresence().name());

        return entity;
    }

    public CuistoDTO toDTO(Cuisto model) {

        CuistoDTO dto = new CuistoDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setPresence(model.getPresence().name());
        return dto;
    }


}
