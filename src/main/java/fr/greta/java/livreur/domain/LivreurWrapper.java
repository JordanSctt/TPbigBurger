package fr.greta.java.livreur.domain;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.livreur.persistence.LivreurEntity;

import java.util.ArrayList;
import java.util.List;

public class LivreurWrapper {


    public List<Livreur> fromEntities(List<LivreurEntity> entities) {

        List<Livreur> models = new ArrayList<>();
        for (LivreurEntity entity : entities) {
            models.add(fromEntity(entity));
        }
        return models;
    }

    public Livreur fromEntity(LivreurEntity entity) {
        Livreur model = new Livreur();
        Commande commande = new Commande();
        commande.setId(entity.getCommandeID());
        model.setId(entity.getId());
        model.setCommande(commande);
        model.setName(entity.getName());
        model.setPresence(LivreurPresence.valueOf(entity.getPresence().name()));

        return model;
    }

    public LivreurEntity toEntity(Livreur model) {
        LivreurEntity entity = new LivreurEntity();
        entity.setId(model.getId());
        entity.setCommandeID(model.getCommande().getId());
        entity.setName(model.getName());
        entity.setPresence(model.getPresence());

        return entity;
    }
}
