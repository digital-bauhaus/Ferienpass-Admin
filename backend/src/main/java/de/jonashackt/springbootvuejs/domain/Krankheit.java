package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Krankheit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long krankheit_id;
    private String name;
    private String information;
    private String medikamente;

    protected Krankheit() {}

    public Krankheit(String name, String information, String medikamente) {
        this.setName(name);
        this.setInformation(information);
        this.setMedikamente(medikamente);
    }

    @Override
    public String toString() {
        return String.format(
                "Krankheit[id=%d, Name='%s', Information='%s', Medikation='%s']",
                getId(), getName(), getInformation(), getMedikamente());
    }

    public long getId() {
        return krankheit_id;
    }

    public void setId(long id) {
        this.krankheit_id = id;
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

    public String getMedikamente() {
        return medikamente;
    }

    public void setMedikamente(String medikamente) {
        this.medikamente = medikamente;
    }
}
