package fr.greta.java.livreur.persistence;


import fr.greta.java.livreur.domain.LivreurPresence;

import java.util.ArrayList;
import java.util.List;

public class LivreurEntity {

    private int id;
    private Integer commandeIdEnCours;
    private String name;
    private String presence;
    

    public LivreurEntity() {
    }

    //-----------------------------------------
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

    public Integer getCommandeIdEnCours() {
        return commandeIdEnCours;
    }

    public void setCommandeIdEnCours(Integer commandeIdEnCours) {
        this.commandeIdEnCours = commandeIdEnCours;
    }
}
