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
public class Contact {

    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contact_id;
    private String name;
    private String address;
    private String telephone;

    protected Contact() {}

    public Contact(String name, String address, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return String.format(
                "Contact[contact_id=%d, name='%s', address='%s', telephone='%s']",
                contact_id, name, address, telephone);
    }


    public long getContact_id() {
        return contact_id;
    }

    public void setContact_id(long doctor_id) {
        this.contact_id = contact_id;
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
