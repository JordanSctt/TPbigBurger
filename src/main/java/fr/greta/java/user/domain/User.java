package fr.greta.java.user.domain;

import fr.greta.java.generic.tools.StringTool;

public class User {

    private int id;
    private String name;
    private String password;
    private String phone;
    private String role;

    public User(int id ,String name, String password, String phone, String role) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
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
}
