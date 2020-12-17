package fr.greta.java.commande.domain;

import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.facade.CommandeItemsDTO;
import fr.greta.java.livreur.domain.Livreur;
import fr.greta.java.produit.domain.ProduitType;
import fr.greta.java.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;


public class Commande {

    private Integer id;
    private User user;
    private LocalDateTime startDatePrep;
    private LocalDateTime endDatePrep;
    private CommandeEtat etatCommande;
    private LocalDateTime startDateLivraison;
    private LocalDateTime endDateLivraison;
    private LocalDateTime estimationEndDateLivraison;
    private CommandeTypeLivraison typeLivraison;
    private Livreur livreur;
    private List<CommandeItems> commandeItemsList;
    private double prixTotal;
    private Integer menu;
    private Integer reductionMenu;




    public void calculPrixTotalMenu(List<CommandeItems> commandeItemsList) {

        int nombreBurger = 0;
        int nombreBoisson = 0;
        int nombreDessert = 0;
        int menu = 0;
        int reduction = 2;


        for (CommandeItems commandesItems : commandeItemsList) {

            switch (commandesItems.getProduit().getProduitType()) {

                case BURGER:
                    nombreBurger +=  commandesItems.getQuantity();
                    break;
                case BOISSON:
                    nombreBoisson +=  commandesItems.getQuantity();
                    break;
                case DESSERT:
                    nombreDessert += commandesItems.getQuantity();
                    break;
            }



            this.prixTotal += commandesItems.getPrixTotalLigne();
        }
        while (nombreBurger > 0 && nombreBoisson > 0 && nombreDessert > 0) {

            menu = menu + 1;
            nombreBurger -=   1;
            nombreBoisson -=   1;
            nombreDessert -=  1;

        }

        this.menu = menu;
        this.reductionMenu = menu * reduction;


    }

    public CommandeEtat getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(CommandeEtat etatCommande) {
        this.etatCommande = etatCommande;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDateTime getStartDateLivraison() {
        return startDateLivraison;
    }

    public void setStartDateLivraison(LocalDateTime startDateLivraison) {
        this.startDateLivraison = startDateLivraison;
    }

    public LocalDateTime getEndDateLivraison() {
        return endDateLivraison;
    }

    public void setEndDateLivraison(LocalDateTime endDateLivraison) {
        this.endDateLivraison = endDateLivraison;
    }

    public CommandeTypeLivraison getTypeLivraison() {
        return typeLivraison;
    }

    public void setTypeLivraison(CommandeTypeLivraison typeLivraison) {
        this.typeLivraison = typeLivraison;
    }

    public LocalDateTime getEstimationEndDateLivraison() {
        return estimationEndDateLivraison;
    }

    public void setEstimationEndDateLivraison(LocalDateTime estimationEndDateLivraison) {
        this.estimationEndDateLivraison = estimationEndDateLivraison;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public List<CommandeItems> getCommandeItemsList() {
        return commandeItemsList;
    }

    public void setCommandeItemsList(List<CommandeItems> commandeItemsList) {
        this.commandeItemsList = commandeItemsList;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public Integer getReductionMenu() {
        return reductionMenu;
    }

    public void setReductionMenu(Integer reductionMenu) {
        this.reductionMenu = reductionMenu;
    }
}
