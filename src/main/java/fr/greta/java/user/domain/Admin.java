package fr.greta.java.user.domain;

public class Admin extends User {

    public Admin (int id, String name, String password, String phone) {
        super(id, name, password, phone);
    }

    public Admin() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
