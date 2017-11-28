package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DisabilityCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long d_code;
    private String meaning;

    public DisabilityCode(long d_code, String meaning) {
        this.d_code = d_code;
        this.meaning = meaning;
    }
}
