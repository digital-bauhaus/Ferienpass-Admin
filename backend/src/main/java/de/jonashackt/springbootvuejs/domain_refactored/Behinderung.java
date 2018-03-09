package de.jonashackt.springbootvuejs.domain_refactored;

import de.jonashackt.springbootvuejs.domain.DisabilityCode;

import javax.persistence.*;

@Entity
public class Behinderung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long behinderung_id;
    private String name;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="d_code")
    private BehinderungKodierung code;
    private boolean approved;
    private boolean id_available;
    private boolean token_available;
    private boolean needs_escort;

    public Behinderung(String name, BehinderungKodierung code, boolean approved, boolean id_available, boolean token_available, boolean needs_escort) {
        this.setName(name);
        this.setCode(code);
        this.setApproved(approved);
        this.setId_available(id_available);
        this.setToken_available(token_available);
        this.setNeeds_escort(needs_escort);
    }

    protected Behinderung() {}

    @Override
    public String toString() {
        return String.format(
                "Behinderung[id=%d, name='%s', code='%s', approved='%b', id_available='%b', token_available='%b', needs_escort='%b']",
                behinderung_id, name, code.toString(), approved, id_available, token_available, needs_escort);
    }

    public long getId() {
        return behinderung_id;
    }

    public void setId(long id) {
        this.behinderung_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BehinderungKodierung getCode() {
        return code;
    }

    public void setCode(BehinderungKodierung code) {
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
