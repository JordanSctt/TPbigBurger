package fr.greta.java.livreur.persistence;


import fr.greta.java.livreur.domain.LivreurPresence;

public class LivreurEntity {

    private int id;
    private int commandeID;
    private String name;
    private LivreurPresence presence;

    //-----------------------------------------
    public LivreurEntity(int id, int commandeID, String name, LivreurPresence presence) {
        this.id = id;
        this.commandeID = commandeID;
        this.name = name;
        this.presence = presence;
    }

    public LivreurEntity() {
    }

    //-----------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommandeID() {
        return commandeID;
    }

    public void setCommandeID(int commandeID) {
        this.commandeID = commandeID;
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
