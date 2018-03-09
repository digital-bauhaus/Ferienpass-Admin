package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Textfield {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long textfield_id;
    private String name;

    @OneToMany
    private List<Param> params = new ArrayList<>();

    public Textfield(String name, List<Param> params) {
        this.name = name;
        this.params = params;
    }

    public long getTextfield_id() {
        return textfield_id;
    }

    public void setTextfield_id(long textfield_id) {
        this.textfield_id = textfield_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }
}
