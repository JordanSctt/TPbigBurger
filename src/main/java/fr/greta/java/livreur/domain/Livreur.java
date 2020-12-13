package fr.greta.java.livreur.domain;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeEtat;


public class Livreur {

    private int id;
    private Commande commande;
    private String name;
    private LivreurPresence presence;

    //-----------------------------------------------
    public Livreur(int id, Commande commande, String name, LivreurPresence presence) {
        this.id = id;
        this.commande = commande;
        this.name = name;
        this.presence = presence;
    }

    public Livreur() {
    }

    //-----------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
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

    }

