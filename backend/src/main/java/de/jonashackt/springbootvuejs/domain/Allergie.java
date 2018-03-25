package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Allergie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long allergie_id;
    private String name;
    private String zusatzInformation;

    public  Allergie(String name, String zusatzInformation) {
        this.name = name;
        this.zusatzInformation = zusatzInformation;
    }

    protected Allergie() {}

    @Override
    public String toString() {
        return String.format(
                "Allergie[id=%d, name='%s', information='%s']",
                allergie_id, name, zusatzInformation);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZusatzInformation() {
        return zusatzInformation;
    }

    public void setZusatzInformation(String zusatzInformation) {
        this.zusatzInformation = zusatzInformation;
    }

    public long getId() {
        return allergie_id;
    }

    public void setId(long id) {
        this.allergie_id = id;
    }
}
