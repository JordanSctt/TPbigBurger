package fr.greta.java.user.facade;

public class UserDTO {

    private int id;
    private String name;
    private String password;
    private String phone;
    private String role;
    private String adresse;

    public UserDTO(int id, String name, String password, String phone, String role, String adresse) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.adresse = adresse;
    }

    //-------------------------------
    public boolean isAdmin() {
        return false;
    }
    //-------------------------------

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
