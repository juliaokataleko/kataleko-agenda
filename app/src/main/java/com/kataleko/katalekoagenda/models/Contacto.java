package com.kataleko.katalekoagenda.models;

public class Contacto {
    private int id;
    private String name;
    private String address;
    private String phone1;
    private String phone2;
    private String email;

    public Contacto() {
        this.id = 0;
        this.address = "";
        this.name = "";
        this.phone1 = "";
        this.phone2 = "";
    }

    public Contacto(String name, String phone1) {
        this.id = 0;
        this.name = name;
        this.address = "";
        this.phone1 = phone1;
        this.phone2 = "";
        this.email = "";
    }

    public Contacto(int id, String name, String phone1) {
        this.id = id;
        this.name = name;
        this.address = "";
        this.phone1 = phone1;
        this.phone2 = "";
        this.email = "";
    }

    public Contacto(String name, String address, String phone1, String phone2, String email) {
        this.id = 0;
        this.name = name;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
    }

    public Contacto(int id, String name, String address, String phone1, String phone2, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
    }

    public Contacto(Contacto contacto) {
        this.id = contacto.id;
        this.name = contacto.name;
        this.address = contacto.address;
        this.phone1 = contacto.phone1;
        this.phone2 = contacto.phone2;
        this.email = contacto.email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
