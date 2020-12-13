package fr.greta.java.commande.facade;

import fr.greta.java.commandeItems.facade.CommandeItemsDTO;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommandeDTO {

    private int id;
    private String startDatePrep;
    private String endDatePrep;
    private String name;
    private String password;
    private String phone;
    private String etatCommande;
    private String startDateLivraison;
    private String endDateLivraison;
    private double prixTotal;
    private List<CommandeItemsDTO> commandeItemsDTOList;
    private String typeLivraison;
    private String estimationLivraison;
    private String adresse;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
