package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long component_id;
    private String name;

    @OneToMany
    private List<Param> params = new ArrayList<>();

    public Component() {
    }

    public Component(List<Param> params) {
        this.params = params;
    }

    public Component(String name) {
        this.name = name;
    }

    public Component(String name, List<Param> params) {
        this.name = name;
        this.params = params;
    }

    public long getComponent_id() {
        return component_id;
    }

    public void setComponent_id(long component_id) {
        this.component_id = component_id;
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
