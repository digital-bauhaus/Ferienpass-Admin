package de.jonashackt.springbootvuejs.domain_refactored;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EssenLimitierung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long essen_id;
    private String name;
    private String information;

    public  EssenLimitierung(String name, String info) {
        this.name = name;
        this.information = info;
    }

    protected EssenLimitierung() {}

    @Override
    public String toString() {
        return String.format(
                "EssenLimitierung[id=%d, name='%s', information='%s']",
                essen_id, name, information);
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

    public long getId() {
        return essen_id;
    }

    public void setId(long id) {
        this.essen_id = id;
    }
}
