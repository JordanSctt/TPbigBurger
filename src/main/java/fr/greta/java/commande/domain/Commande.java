package fr.greta.java.commande.domain;

import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public class Commande {

    private int id;
    private User user;
    private LocalDateTime startDatePrep;
    private LocalDateTime endDatePrep;
    private CommandeEtat etatCommande;
    private List<CommandeItems> commandesItems;


    public CommandeEtat getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(CommandeEtat etatCommande) {
        this.etatCommande = etatCommande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartDatePrep() {
        return startDatePrep;
    }

    public void setStartDatePrep(LocalDateTime startDatePrep) {
        this.startDatePrep = startDatePrep;
    }

    public LocalDateTime getEndDatePrep() {
        return endDatePrep;
    }

    public void setEndDatePrep(LocalDateTime endDatePrep) {
        this.endDatePrep = endDatePrep;
    }

    public List<CommandeItems> getCommandesItems() {
        return commandesItems;
    }

    public void setCommandesItems(List<CommandeItems> commandesItems) {
        this.commandesItems = commandesItems;
    }
}
