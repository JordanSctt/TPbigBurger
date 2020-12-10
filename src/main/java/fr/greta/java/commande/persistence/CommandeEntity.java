package fr.greta.java.commande.persistence;

import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.user.domain.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class CommandeEntity {

    private int id;
    private int userID;
    private Timestamp startDatePrep;
    private Timestamp endDatePrep;
    private String etatCommande;

    public String getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartDatePrep() {
        return startDatePrep;
    }

    public void setStartDatePrep(Timestamp startDatePrep) {
        this.startDatePrep = startDatePrep;
    }

    public Timestamp getEndDatePrep() {
        return endDatePrep;
    }

    public void setEndDatePrep(Timestamp endDatePrep) {
        this.endDatePrep = endDatePrep;
    }

}
