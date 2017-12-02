package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import java.util.Date;

@Entity
public class Doctor {

    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long doctor_id;
    private String name;
    private String address;
    private String telephone;

    protected Doctor() {}

    public Doctor(String name, String street, int streetNr, String city, String postcode, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return String.format(
                "Doctor[doctor_id=%d, name='%s', address='%s', telephone='%s']",
                doctor_id, name, address, telephone);
    }


    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
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

    public void setAddress(String street) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
