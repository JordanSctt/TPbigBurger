package fr.greta.java.commande.domain;

import fr.greta.java.user.domain.User;

import java.time.LocalDateTime;

public class Commande {

    private int id;
    private User user;
    private LocalDateTime startDatePrep;
    private LocalDateTime endDatePrep;
    private CommandeEtat etatCommande;




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
}
