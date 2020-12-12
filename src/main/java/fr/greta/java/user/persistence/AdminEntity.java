package fr.greta.java.user.persistence;

public class AdminEntity extends UserEntity {

    public AdminEntity(int id, String name, String password, String phone, String role, String adresse) {
        super(id, name, password, phone, role, adresse);
    }

    public AdminEntity() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
