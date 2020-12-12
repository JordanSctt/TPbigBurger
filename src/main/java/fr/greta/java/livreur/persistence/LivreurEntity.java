package fr.greta.java.livreur.persistence;


import fr.greta.java.livreur.domain.LivreurPresence;

public class LivreurEntity {

    private int id;
    private Integer commandeID;
    private String name;
    private String presence;

    //-----------------------------------------


    public LivreurEntity(int id, Integer commandeID, String name, String presence) {
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

    public Integer getCommandeID() {
        return commandeID;
    }

    public void setCommandeID(Integer commandeID) {
        this.commandeID = commandeID;
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
}
