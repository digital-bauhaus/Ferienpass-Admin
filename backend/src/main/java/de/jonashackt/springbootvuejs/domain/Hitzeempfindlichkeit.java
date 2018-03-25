package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hitzeempfindlichkeit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hitze_id;
    private String name;
    private String information;

    public Hitzeempfindlichkeit(String name, String information) {
        this.name = name;
        this.information = information;
    }

    protected Hitzeempfindlichkeit() {}

    @Override
    public String toString() {
        return String.format(
                "Hitzeempfindlichkeit[id=%d, name='%s', information='%s']",
                hitze_id, name, information);
    }

    public long getHitze_id() {
        return hitze_id;
    }

    public void setHitze_id(long hitze_id) {
        this.hitze_id = hitze_id;
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
