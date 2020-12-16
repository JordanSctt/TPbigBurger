package fr.greta.java.commande.persistence;


import java.sql.Timestamp;


public class CommandeEntity {

    private Integer id;
    private int userID;
    private Timestamp startDatePrep;
    private Timestamp endDatePrep;
    private String etatCommande;
    private Timestamp startDateLivraison;
    private Timestamp endDateLivraison;
    private String typeLivraison;
    private Timestamp estimationLivraison;
    private Integer livreurID;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Timestamp getStartDateLivraison() {
        return startDateLivraison;
    }

    public void setStartDateLivraison(Timestamp startDateLivraison) {
        this.startDateLivraison = startDateLivraison;
    }

    public Timestamp getEndDateLivraison() {
        return endDateLivraison;
    }

    public void setEndDateLivraison(Timestamp endDateLivraison) {
        this.endDateLivraison = endDateLivraison;
    }

    public String getTypeLivraison() {
        return typeLivraison;
    }

    public void setTypeLivraison(String typeLivraison) {
        this.typeLivraison = typeLivraison;
    }

    public Timestamp getEstimationLivraison() {
        return estimationLivraison;
    }

    public void setEstimationLivraison(Timestamp estimationLivraison) {
        this.estimationLivraison = estimationLivraison;
    }

    public Integer getLivreurID() {
        return livreurID;
    }

    public void setLivreurID(Integer livreurID) {
        this.livreurID = livreurID;
    }
}
