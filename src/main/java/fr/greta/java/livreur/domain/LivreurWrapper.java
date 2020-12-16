package fr.greta.java.livreur.domain;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commande.domain.CommandeWrapper;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.persistence.LivreurEntity;

import java.util.ArrayList;
import java.util.List;

public class LivreurWrapper {

    public List<Livreur> fromEntities(List<LivreurEntity> entities) throws ServiceException, RepositoryException {

        List<Livreur> models = new ArrayList<>();
        for (LivreurEntity entity : entities) {
            models.add(fromEntity(entity));
        }
        return models;
    }
    public List<LivreurEntity> toEntities(List<Livreur> models) {

        List<LivreurEntity> entities = new ArrayList<>();
        for (Livreur model : models) {
            entities.add(toEntity(model));
        }
        return entities;
    }


    public Livreur fromEntity(LivreurEntity entity) throws RepositoryException, ServiceException {
        Livreur model = new Livreur();
        model.setId(entity.getId());
        if (entity.getCommandeIdEnCours() != null) {
            Commande commandeEnCours = new Commande();
            commandeEnCours.setId(entity.getCommandeIdEnCours());
            model.setCommandeEnCours(commandeEnCours);
        }
        model.setName(entity.getName());
        model.setPresence(LivreurPresence.valueOf(entity.getPresence()));
        return model;
    }

    public LivreurEntity toEntity(Livreur model) {
        LivreurEntity entity = new LivreurEntity();
        entity.setId(model.getId());
        if (model.getCommandeEnCours() != null) {
            entity.setCommandeIdEnCours(model.getCommandeEnCours().getId());
        }
        entity.setName(model.getName());
        entity.setPresence(model.getPresence().name());
      

        return entity;
    }
}
