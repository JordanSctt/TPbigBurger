package fr.greta.java.produit.persistence;

import fr.greta.java.produit.domain.ProduitType;

public class ProduitEntity {

    private int id;
    private ProduitType produitType;
    private String label;
    private double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProduitType getProduitType() {
        return produitType;
    }

    public void setProduitType(ProduitType produitType) {
        this.produitType = produitType;
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
}
