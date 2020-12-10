package fr.greta.java.user.persistence;

public class AdminEntity extends UserEntity {

    public AdminEntity(int id, String name, String password, String phone, String role) {
        super(id, name, password, phone, role);
    }

    public AdminEntity() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
