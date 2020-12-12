package fr.greta.java.user.facade;

public class AdminDTO extends UserDTO{

    public AdminDTO(int id, String name, String password, String phone, String role, String adresse) {
        super(id, name, password, phone, role, adresse);
    }

    public AdminDTO() {
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
