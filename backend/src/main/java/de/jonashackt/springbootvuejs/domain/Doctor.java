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
    private String street;
    private int streetNr;
    private String city;
    private String postcode;
    private String telephone;

    protected Doctor() {}

    public Doctor(String name, String street, int streetNr, String city, String postcode, String telephone) {
        this.name = name;
        this.street = street;
        this.streetNr = streetNr;
        this.city = city;
        this.postcode = postcode;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return String.format(
                "Doctor[doctor_id=%d, name='%s', street='%s', streetNr='%d', city='%s', postcode='%s', telephone='%s']",
                doctor_id, name, street, streetNr, city, postcode, telephone);
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(int streetNr) {
        this.streetNr = streetNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
