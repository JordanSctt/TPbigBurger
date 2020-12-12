package fr.greta.java.livreur.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.livreur.domain.LivreurPresence;

import java.time.LocalDateTime;

public class LivreurDTO {

    private int id;
    private String name;
    private String presence;
    private int commandeID;


    //-------------------------------------------


    public LivreurDTO(int id, String name, String presence, int commandeID) {
        this.id = id;
        this.name = name;
        this.presence = presence;
        this.commandeID = commandeID;
    }

    public LivreurDTO() {
    }

    //-------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public int getCommandeID() {
        return commandeID;
    }

    public void setCommandeID(int commandeID) {
        this.commandeID = commandeID;
    }
}
