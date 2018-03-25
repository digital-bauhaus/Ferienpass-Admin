package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medikament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long medikament_id;
    private String name;
    private String information;

    public  Medikament(String name, String information) {
        this.name = name;
        this.information = information;
    }

    protected Medikament() {}

    @Override
    public String toString() {
        return String.format(
                "Medikament[id=%d, name='%s', information='%s']",
                medikament_id, name, information);
    }

    public long getMedikament_id() {
        return medikament_id;
    }

    public void setMedikament_id(long medikament_id) {
        this.medikament_id = medikament_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
