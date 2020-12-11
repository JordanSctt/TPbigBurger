package fr.greta.java.livreur.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.livreur.domain.LivreurPresence;

import java.time.LocalDateTime;

public class LivreurDTO {

    private int id;
    private String name;
    private LivreurPresence presence;
    private String startDatePrep;
    private String endDatePrep;
    private String etatCommande;
    private String startDateLivraison;
    private String endDateLivraison;


    //-------------------------------------------
    public LivreurDTO(int id, String name, LivreurPresence presence, String startDatePrep, String endDatePrep, String etatCommande, String startDateLivraison, String endDateLivraison) {
        this.id = id;
        this.name = name;
        this.presence = presence;
        this.startDatePrep = startDatePrep;
        this.endDatePrep = endDatePrep;
        this.etatCommande = etatCommande;
        this.startDateLivraison = startDateLivraison;
        this.endDateLivraison = endDateLivraison;
    }

    public LivreurDTO() {
    }

    //-------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LivreurPresence getPresence() {
        return presence;
    }

    public void setPresence(LivreurPresence presence) {
        this.presence = presence;
    }

    public String getStartDatePrep() {
        return startDatePrep;
    }

    public void setStartDatePrep(String startDatePrep) {
        this.startDatePrep = startDatePrep;
    }

    public String getEndDatePrep() {
        return endDatePrep;
    }

    public void setEndDatePrep(String endDatePrep) {
        this.endDatePrep = endDatePrep;
    }

    public String getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    public String getStartDateLivraison() {
        return startDateLivraison;
    }

    public void setStartDateLivraison(String startDateLivraison) {
        this.startDateLivraison = startDateLivraison;
    }

    public String getEndDateLivraison() {
        return endDateLivraison;
    }

    public void setEndDateLivraison(String endDateLivraison) {
        this.endDateLivraison = endDateLivraison;
    }
}
