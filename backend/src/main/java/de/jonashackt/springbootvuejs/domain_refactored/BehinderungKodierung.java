package de.jonashackt.springbootvuejs.domain_refactored;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BehinderungKodierung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long d_code;
    private String bedeutung;

    public BehinderungKodierung(String bedeutung) {
        this.setD_code(d_code);
        this.setBedeutung(bedeutung);
    }

    protected BehinderungKodierung() {}

    public long getD_code() {
        return d_code;
    }

    public void setD_code(long d_code) {
        this.d_code = d_code;
    }

    public String getBedeutung() {
        return bedeutung;
    }

    public void setBedeutung(String bedeutung) {
        this.bedeutung = bedeutung;
    }
}
