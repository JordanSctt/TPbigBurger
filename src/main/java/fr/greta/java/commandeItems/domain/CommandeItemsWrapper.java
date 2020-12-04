package fr.greta.java.commandeItems.domain;

import fr.greta.java.burger.domain.Burger;
import fr.greta.java.burger.persistence.BurgerEntity;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;

import java.util.ArrayList;
import java.util.List;

public class CommandeItemsWrapper {


    public List<CommandeItems> fromEntities(List<CommandeItemsEntity> entities) {

        List<CommandeItems> models = new ArrayList<>();
        for (CommandeItemsEntity entity : entities) {
            models.add(fromEntity(entity));
        }
        return models;
    }

    public CommandeItems fromEntity(CommandeItemsEntity entity) {
        Burger burger = new Burger();
        burger.setId(entity.getBurgerId());

        Commande commande = new Commande();
        commande.setId(entity.getCommandeId());

        CommandeItems model = new CommandeItems();
        model.setBurger(burger);

        model.setCommande(commande);
        model.setQuantity(entity.getQuantity());
        return model;
    }


    public CommandeItemsEntity toEntity(CommandeItems model) {
        CommandeItemsEntity entity = new CommandeItemsEntity();
        entity.setBurgerId(model.getBurger().getId());
        entity.setCommandeId(model.getCommande().getId());
        entity.setQuantity(model.getQuantity());
        return entity;
    }

}
