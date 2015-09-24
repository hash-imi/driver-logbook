package com.example.mohammad.bluetoothconnect;

/**
 * Created by mohammad on 15.07.15.
 */
public class DataProvider {
    private String prename;
    private String surname;
    private String email;
    private String mobile;
    private String register;

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public DataProvider(String prename, String surname, String email, String mobile, String register){
        this.prename = prename;
        this.surname = surname;
        this.email = email;
        this.mobile = mobile;
        this.register = register;

    }
}
