package fr.greta.java.cuisto.domain;

public class Cuisto {

    int id;
    String name;
    CuistoPresence presence;

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

    public CuistoPresence getPresence() {
        return presence;
    }

    public void setPresence(CuistoPresence presence) {
        this.presence = presence;
    }
}
