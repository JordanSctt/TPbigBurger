package fr.greta.java.user.domain;

public class Admin extends User {

    public Admin (int id, String name, String password, String phone, String role) {
        super(id, name, password, phone, role);
    }

    public Admin() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
