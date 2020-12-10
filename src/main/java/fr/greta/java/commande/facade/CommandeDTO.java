package fr.greta.java.commande.facade;

import fr.greta.java.commandeItems.facade.CommandeItemsDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommandeDTO {

    private int id;
    private LocalDateTime startDatePrep;
    private LocalDateTime endDatePrep;
    private String name;
    private String password;
    private String phone;
    private String etatCommande;
    private String heureRecup;
    private double prixTotal;
    private List<CommandeItemsDTO> commandeItemsDTOList;

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

    public String getHeureRecup() {
        return heureRecup;
    }

    public void setHeureRecup (LocalDateTime endDatePrep) {

        this.heureRecup = formatDate(endDatePrep);

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

    public List<CommandeItemsDTO> getCommandeItemsDTOList() {
        return commandeItemsDTOList;
    }

    public void setCommandeItemsDTOList(List<CommandeItemsDTO> commandeItemsDTOList) {
        this.commandeItemsDTOList = commandeItemsDTOList;
    }


}
