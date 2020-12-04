package fr.greta.java.commande.domain;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.user.domain.User;

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
        // heure
        return model;
    }


    public CommandeEntity toEntity(Commande model) {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(model.getId());
        entity.setUserID(model.getUser().getId());
        // rajouter l'heure
        return entity;
    }

}
