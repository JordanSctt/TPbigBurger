package fr.greta.java.livreur.domain;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeEtat;

import java.util.ArrayList;
import java.util.List;


public class Livreur {

    private int id;
    private List<Commande> commandes;
    private String name;
    private LivreurPresence presence;
    private Commande commandeEnCours;
    private String password;

    //-----------------------------------------------


  

    public Livreur() {
    }

    //-----------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Commande> getCommandes() {
        if (this.commandes == null) {

            this.commandes = new ArrayList<>();

        }
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LivreurPresence getPresence() {
        return presence;
    }

    public void setPresence(LivreurPresence presence) {
        this.presence = presence;
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public void setCommandeEnCours(Commande commandeEnCours) {
        this.commandeEnCours = commandeEnCours;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

