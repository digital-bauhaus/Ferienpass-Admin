package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// DependingCheckbox (fast wie eine component mit name="CheckBox", aber who cares?

@Entity
public class Checkbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long checkbox_id;
    private String name;

    @OneToMany
    private List<Param> params = new ArrayList<>();

    public Checkbox(String name, List<Param> params) {
        this.name = name;
        this.params = params;
    }

    public long getCheckbox_id() {
        return checkbox_id;
    }

    public void setCheckbox_id(long checkbox_id) {
        this.checkbox_id = checkbox_id;
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
