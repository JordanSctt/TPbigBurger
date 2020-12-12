package fr.greta.java.user.domain;

public class Admin extends User {

    public Admin(int id, String name, String password, String phone, String role, String adresse) {
        super(id, name, password, phone, role, adresse);
    }

    public Admin() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
