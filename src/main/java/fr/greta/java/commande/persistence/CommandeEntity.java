package fr.greta.java.commande.persistence;

import fr.greta.java.user.domain.User;

import java.time.LocalDateTime;

public class CommandeEntity {

    private int id;
    private int userID;
    private LocalDateTime startDatePrep;
    private LocalDateTime endDatePrep;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartDatePrep() {
        return startDatePrep;
    }

    public void setStartDatePrep(LocalDateTime startDatePrep) {
        this.startDatePrep = startDatePrep;
    }

    public LocalDateTime getEndDatePrep() {
        return endDatePrep;
    }

    public void setEndDatePrep(LocalDateTime endDatePrep) {
        this.endDatePrep = endDatePrep;
    }
}
