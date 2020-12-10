package fr.greta.java.user.facade;

public class AdminDTO extends UserDTO{

    public AdminDTO(int id, String name, String password, String phone, String role) {
        super(id, name, password, phone, role);
    }

    public AdminDTO() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
