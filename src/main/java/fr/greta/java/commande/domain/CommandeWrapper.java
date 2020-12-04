package fr.greta.java.commande.domain;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.user.domain.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandeWrapper {


    public List<Commande> fromEntities(List<CommandeEntity> entities) {

        List<Commande> models = new ArrayList<>();
        for (CommandeEntity entity : entities) {
            models.add(fromEntity(entity));
        }
        return models;
    }

    public Commande fromEntity(CommandeEntity entity) {
        Commande model = new Commande();
        User user = new User();
        user.setId(entity.getUserID());
        model.setId(entity.getId());
        model.setUser(user);
        model.setStartDatePrep(entity.getStartDatePrep().toLocalDateTime());
        model.setEndDatePrep(entity.getEndDatePrep().toLocalDateTime());
        model.setEtatCommande(CommandeEtat.valueOf(entity.getEtatCommande()));
        return model;
    }


    public CommandeEntity toEntity(Commande model) {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(model.getId());
        entity.setUserID(model.getUser().getId());
        entity.setStartDatePrep(Timestamp.valueOf(model.getStartDatePrep()));
        entity.setEndDatePrep(Timestamp.valueOf(model.getEndDatePrep()));
        entity.setEtatCommande(model.getEtatCommande().name());
        return entity;
    }

}
