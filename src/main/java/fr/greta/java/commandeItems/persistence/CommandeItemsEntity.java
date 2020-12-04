package fr.greta.java.commandeItems.persistence;

public class CommandeItemsEntity {

    private int burgerId;
    private int commandeId;
    private int quantity;



    public int getBurgerId() {
        return burgerId;
    }

    public void setBurgerId(int burgerId) {
        this.burgerId = burgerId;
    }

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
