package fr.greta.java.burger.domain;

import fr.greta.java.generic.tools.StringTool;

public class Burger {

    private int id;
    private String label;
    private double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isValid() {

        return !StringTool.isNullOrEmpty(getLabel());

    }
}
