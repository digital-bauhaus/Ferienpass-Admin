package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Limitation {

    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long limit_id;
    protected String name;
    protected String information;

    protected Limitation() {}

    public Limitation(String name, String information) {
        this.name = name;
        this.information = information;
    }

    @Override
    public String toString() {
        return String.format(
                "Limitation[limit_id=%d, name='%s', information='%s']",
                limit_id, name, information);
    }


    public long getLimit_id() {
        return limit_id;
    }

    public void setLimit_id(long limit_id) {
        this.limit_id = limit_id;
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
