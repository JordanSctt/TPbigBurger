package fr.greta.java.commandeItems.facade;

import java.time.LocalDateTime;

public class CommandeItemsDTO {

    private String label;
    private Double price;
    private String startDateFormat;
    private String endDateFormat;
    private int quantity;
    private Double totalPrixLigne;

    public Double getTotalPrixLigne() {
        return totalPrixLigne;
    }

    public void setTotalPrixLigne(Double totalPrixLigne) {
        this.totalPrixLigne = totalPrixLigne;
    }

    public String getStartDateFormat() {
        return startDateFormat;
    }

    public void setStartDateFormat(String startDateFormat) {
        this.startDateFormat = startDateFormat;
    }

    public String getEndDateFormat() {
        return endDateFormat;
    }

    public void setEndDateFormat(String endDateFormat) {
        this.endDateFormat = endDateFormat;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
