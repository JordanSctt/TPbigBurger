package fr.greta.java.user.domain;

import fr.greta.java.generic.tools.StringTool;

public class User {

    private int id;
    private String name;
    private String password;
    private String phone;


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
