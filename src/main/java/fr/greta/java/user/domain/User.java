package fr.greta.java.user.domain;

import fr.greta.java.generic.tools.StringTool;

public class User {

    private int id;
    private String name;
    private String password;
    private String phone;
    private String role;
    private String adresse;

    public User(int id, String name, String password, String phone, String role, String adresse) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.adresse = adresse;
    }

    public User() {
    }
    //-------------------------------
    public boolean isAdmin() {
        return false;
    }
    //-------------------------------

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

    //-------------------------------------------------------
    public boolean nameIsValid() {
        return !StringTool.isNullOrEmpty(getName());
    }
    public boolean passwordIsValid() {
        return !StringTool.isNullOrEmpty(getPassword());
    }
    public boolean phoneIsValid() {
        return !StringTool.isNullOrEmpty(getPhone());
    }
    public boolean adressIsValid() {
        return !StringTool.isNullOrEmpty(getPhone());
    }
}
