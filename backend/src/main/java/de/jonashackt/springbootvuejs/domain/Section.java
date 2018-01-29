package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long section_id;
    private String title;

    @OneToMany
    private List<Component> components = new ArrayList<>();

    public Section(String title, List<Component> components) {
        this.title = title;
        this.components = components;
    }

    public long getSection_id() {
        return section_id;
    }

    public void setSection_id(long section_id) {
        this.section_id = section_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Component> getComponent() {
        return components;
    }

    public void setComponent(List<Component> component) {
        this.components = component;
    }
}
