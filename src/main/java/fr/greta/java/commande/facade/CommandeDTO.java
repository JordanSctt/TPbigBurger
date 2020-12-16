package fr.greta.java.commande.facade;

import fr.greta.java.burger.domain.ProduitType;
import fr.greta.java.commandeItems.facade.CommandeItemsDTO;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommandeDTO {

    private Integer id;
    private String startDatePrep;
    private String endDatePrep;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userAdresse;
    private String etatCommande;
    private String startDateLivraison;
    private String endDateLivraison;
    private double prixTotal;
    private List<CommandeItemsDTO> commandeItemsDTOList;
    private String typeLivraison;
    private String estimationLivraison;
    private String livreurName;
    private Integer menu;

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void calculPrixTotal(List<CommandeItemsDTO> commandeItemsDTOList) {

        for (CommandeItemsDTO commandesItems : commandeItemsDTOList) {

            this.prixTotal += commandesItems.getTotalPrixLigne();

        }


    }

    public void calculPrixTotalMenu(List<CommandeItemsDTO> commandeItemsDTOList) {

            int nombreBurger = 0;
            int nombreBoisson = 0;
            int nombreDessert = 0;
            int menu = 0;

        for (CommandeItemsDTO commandesItems : commandeItemsDTOList) {

          switch (commandesItems.getType()) {

              case "BURGER":
                  nombreBurger += 1;
                  break;
              case "BOISSON":
                  nombreBoisson += 1;
                  break;
              case "DESSERT":
                  nombreDessert += 1;
                  break;
          }

                  if (nombreBurger >= 1 && nombreBoisson >= 1 && nombreDessert >= 1) {

                      menu += 1;
                      nombreBurger -= 1;
                      if (nombreBurger == -1) {

                          nombreBurger = 0;
                      }
                      nombreBoisson -= 1;
                      if (nombreBoisson == -1) {

                          nombreBoisson = 0;
                      }
                      nombreDessert -= 1;
                      if (nombreDessert == -1) {

                          nombreDessert = 0;
                      }

                  }
                  this.prixTotal += commandesItems.getTotalPrixLigne();
          }

            this.menu = menu;

        }






    public String formatDate(LocalDateTime localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatDateTime = localDate.format(formatter);

        return formatDateTime;
    }


    public String getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDatePrep() {


        return startDatePrep;
    }

    public void setStartDatePrep(LocalDateTime startDatePrep) {


        this.startDatePrep = formatDate(startDatePrep);
    }

    public String getEndDatePrep() {


        return endDatePrep;
    }

    public void setEndDatePrep(LocalDateTime startDatePrep) {


        this.endDatePrep = formatDate(startDatePrep);
    }


    public String getStartDateLivraison() {
        return startDateLivraison;
    }

    public void setStartDateLivraison(LocalDateTime startDateLivraison) {

        this.startDateLivraison = formatDate(startDateLivraison);
    }

    public String getEndDateLivraison() {
        return endDateLivraison;
    }

    public void setEndDateLivraison(LocalDateTime endDateLivraison) {


        this.endDateLivraison = formatDate(endDateLivraison);
    }

    public List<CommandeItemsDTO> getCommandeItemsDTOList() {
        return commandeItemsDTOList;
    }

    public void setCommandeItemsDTOList(List<CommandeItemsDTO> commandeItemsDTOList) {
        this.commandeItemsDTOList = commandeItemsDTOList;
    }

    public String getTypeLivraison() {
        return typeLivraison;
    }

    public void setTypeLivraison(String typeLivraison) {
        this.typeLivraison = typeLivraison;
    }

    public String getEstimationLivraison() {
        return estimationLivraison;
    }

    public void setEstimationLivraison(LocalDateTime estimationLivraison) {
        this.estimationLivraison = formatDate(estimationLivraison);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAdresse() {
        return userAdresse;
    }

    public void setUserAdresse(String userAdresse) {
        this.userAdresse = userAdresse;
    }

    public String getLivreurName() {
        return livreurName;
    }

    public void setLivreurName(String livreurName) {
        this.livreurName = livreurName;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }
}