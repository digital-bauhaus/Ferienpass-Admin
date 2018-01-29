package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Form {
    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long form_id;
    private String title;

    @OneToMany
    private List<Section> sections = new ArrayList<>();

    public Form(String title, List<Section> sections) {
        this.title = title;
        this.sections = sections;
    }

    public long getForm_id() {
        return form_id;
    }

    public void setForm_id(long form_id) {
        this.form_id = form_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
