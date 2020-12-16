package fr.greta.java.commande.domain;

import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.domain.Livreur;
import fr.greta.java.livreur.domain.LivreurService;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommandeWrapper {

    UserService service = new UserService();


    public List<Commande> fromEntities(List<CommandeEntity> entities) throws ServiceException {

        List<Commande> models = new ArrayList<>();
        for (CommandeEntity entity : entities) {
            models.add(fromEntity(entity));
        }
        return models;
    }

    public Commande fromEntity(CommandeEntity entity) throws ServiceException {
        Commande model = new Commande();
        model.setId(entity.getId());

        User user = new User();
        user.setId(entity.getUserID());
        model.setUser(user);

        model.setStartDatePrep(entity.getStartDatePrep().toLocalDateTime());
        model.setEndDatePrep(entity.getEndDatePrep().toLocalDateTime());
        model.setEtatCommande(CommandeEtat.valueOf(entity.getEtatCommande()));
        model.setTypeLivraison(CommandeTypeLivraison.valueOf(entity.getTypeLivraison()));
        if (entity.getEstimationLivraison() != null) {
            model.setEstimationEndDateLivraison(entity.getEstimationLivraison().toLocalDateTime());
        }
        if (entity.getStartDateLivraison() != null && entity.getEndDateLivraison() != null) {
            model.setStartDateLivraison(entity.getStartDateLivraison().toLocalDateTime());
            model.setEndDateLivraison(entity.getEndDateLivraison().toLocalDateTime());
        }
        if (entity.getLivreurID() != null) {
            Livreur livreur = new Livreur();
            livreur.setId(entity.getLivreurID());
        }
        return model;
    }


    public CommandeEntity toEntity(Commande model) {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(model.getId());
        if (model.getUser() != null) {
            entity.setUserID(model.getUser().getId());
        }
        if (model.getStartDatePrep() != null || model.getEndDatePrep() != null) {
            entity.setStartDatePrep(Timestamp.valueOf(model.getStartDatePrep()));
            entity.setEndDatePrep(Timestamp.valueOf(model.getEndDatePrep()));
        }
        if (model.getEtatCommande() != null) {
            entity.setEtatCommande(model.getEtatCommande().name());
        }
        if (model.getTypeLivraison() != null) {
            entity.setTypeLivraison(model.getTypeLivraison().name());
        }
        if (model.getEstimationEndDateLivraison() != null) {
            entity.setEstimationLivraison(Timestamp.valueOf(model.getEstimationEndDateLivraison()));
        }
        if (model.getStartDateLivraison() != null && model.getEndDateLivraison() != null) {
            entity.setStartDateLivraison(Timestamp.valueOf(model.getStartDateLivraison()));
            entity.setEndDateLivraison(Timestamp.valueOf(model.getEndDateLivraison()));
        }


        return entity;
    }
}
