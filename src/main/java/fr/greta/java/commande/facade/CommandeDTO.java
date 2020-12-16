package fr.greta.java.commande.facade;

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

    @Override
    public String toString() {
        return "CommandeDTO{" +
                "id=" + id +
                ", startDatePrep='" + startDatePrep + '\'' +
                ", endDatePrep='" + endDatePrep + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAdresse='" + userAdresse + '\'' +
                ", etatCommande='" + etatCommande + '\'' +
                ", startDateLivraison='" + startDateLivraison + '\'' +
                ", endDateLivraison='" + endDateLivraison + '\'' +
                ", prixTotal=" + prixTotal +
                ", commandeItemsDTOList=" + commandeItemsDTOList +
                ", typeLivraison='" + typeLivraison + '\'' +
                ", estimationLivraison='" + estimationLivraison + '\'' +
                ", livreurName='" + livreurName + '\'' +
                '}';
    }
}