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
public class Disability {

    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long disability_id;
    private String name;
    @ManyToOne
    @JoinColumn(name="d_code")
    private DisabilityCode code;
    private boolean approved;
    private boolean id_available;
    private boolean token_available;
    private boolean needs_escort;

    public Disability(long disability_id, String name, DisabilityCode code, boolean approved, boolean id_available, boolean token_available, boolean needs_escort) {
        this.disability_id = disability_id;
        this.name = name;
        this.code = code;
        this.approved = approved;
        this.id_available = id_available;
        this.token_available = token_available;
        this.needs_escort = needs_escort;
    }

    protected Disability() {
    }


    public long getDisability_id() {
        return disability_id;
    }

    public void setDisability_id(long disability_id) {
        this.disability_id = disability_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DisabilityCode getCode() {
        return code;
    }

    public void setCode(DisabilityCode code) {
        this.code = code;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isId_available() {
        return id_available;
    }

    public void setId_available(boolean id_available) {
        this.id_available = id_available;
    }

    public boolean isToken_available() {
        return token_available;
    }

    public void setToken_available(boolean token_available) {
        this.token_available = token_available;
    }

    public boolean isNeeds_escort() {
        return needs_escort;
    }

    public void setNeeds_escort(boolean needs_escort) {
        this.needs_escort = needs_escort;
    }
}