package fr.greta.java.user.facade;

public class AdminDTO extends UserDTO{

    public AdminDTO(int id, String name, String password, String phone) {
        super(id, name, password, phone);
    }

    public AdminDTO() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
