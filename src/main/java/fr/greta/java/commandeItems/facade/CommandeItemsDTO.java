package fr.greta.java.commandeItems.facade;

import java.time.LocalDateTime;

public class CommandeItemsDTO {

    private String label;
    private String price;
    private LocalDateTime startDatePrep;
    private LocalDateTime endDatePrep;
    private int quantity;



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
