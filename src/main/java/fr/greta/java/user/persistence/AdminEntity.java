package fr.greta.java.user.persistence;

public class AdminEntity extends UserEntity {

    public AdminEntity(int id, String name, String password, String phone) {
        super(id, name, password, phone);
    }

    public AdminEntity() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
