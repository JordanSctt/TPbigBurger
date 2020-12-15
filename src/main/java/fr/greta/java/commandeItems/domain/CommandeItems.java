package fr.greta.java.commandeItems.domain;

import fr.greta.java.burger.domain.Produit;
import fr.greta.java.commande.domain.Commande;

public class CommandeItems {

    private Produit produit;
    private Commande commande;
    private int quantity;



    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

}
