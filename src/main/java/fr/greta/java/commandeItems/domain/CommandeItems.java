package fr.greta.java.commandeItems.domain;

import fr.greta.java.burger.domain.Burger;
import fr.greta.java.commande.domain.Commande;

public class CommandeItems {

    private Burger burger;
    private Commande commande;
    private int quantity;


    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
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
