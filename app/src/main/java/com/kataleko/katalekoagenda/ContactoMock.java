package com.kataleko.katalekoagenda;

import com.kataleko.katalekoagenda.models.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ContactoMock {
    private List<Contacto> contactoList;

    public ContactoMock() {
        setContactoList(new ArrayList<>());
        getContactoList().add(new Contacto(1,"Julião", "922660717"));
        getContactoList().add(new Contacto(2,"Isabel", "930891857"));
        getContactoList().add(new Contacto(3,"Pai Kata", "940476648"));
    }

    public List<Contacto> getContactoList() {
        return contactoList;
    }

    public void setContactoList(List<Contacto> contactoList) {
        this.contactoList = contactoList;
    }
    
    public Contacto getContactoById(int id) {
        for (int i = 0; i < contactoList.size(); i++) {
            if(contactoList.get(i).getId() == id) {
                return contactoList.get(i);
            }
        }

        return null;
    }
}
