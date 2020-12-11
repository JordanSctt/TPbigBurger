package fr.greta.java.user.facade;

public class UserDTO {

    private int id;
    private String name;
    private String password;
    private String phone;


    public UserDTO(int id, String name, String password, String phone, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
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

}
