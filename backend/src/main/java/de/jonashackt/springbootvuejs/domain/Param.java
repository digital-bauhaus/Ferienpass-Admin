package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Param {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long param_id;
    private String title;
    private boolean hideTitle;

    // ANGEBOTE INFO
    private String label;
    private String date;
    private String org;
    private int full;
    private int space;

    // ANGEBOTE
    private String introduction;
    private String note;

    // TEILNEHMER
    private String lastname;
    private String firstname;
    // private String date;
    private String street;
    private String location;
    private String phone;

    // ALLERGIEN UND DER GANZE MUELL
    // private String title;
    private String emptyMessage;

    // TEXTFELDER
    // private String label;
    private String placeholder;
    private boolean hideLabel;
    private boolean required;
    private String type;

    // DEPENDINGCHECKBOX
    @OneToOne
    private Checkbox checkbox;

    // DEPENDINGTEXTFIELD
    @OneToOne
    private Textfield textfield;

    @OneToMany
    private List<Component> components = new ArrayList<>();

    public Param() {
    }

    // DependingKram


    public Param(String title, String emptyMessage, Checkbox checkbox) {
        this.title = title;
        this.emptyMessage = emptyMessage;
        this.checkbox = checkbox;
    }

    public Param(String title, Checkbox checkbox) {
        this.title = title;
        this.checkbox = checkbox;
    }

    // Checkbox, RadioButton
    public Param(String label) {
        this.label = label;
    }

    // Hitzeempfindlichkeit, Erlaubnisse
    public Param(String title, List<Component> components) {
        this.title = title;
        this.components = components;
    }

    // Allergien > Textfelder
    public Param(String label, String placeholder, boolean hideLabel) {
        this.label = label;
        this.placeholder = placeholder;
        this.hideLabel = hideLabel;
    }

    // Allergien, Besonderheiten etc
    public Param(String title, String emptyMessage, List<Component> components) {
        this.title = title;
        this.emptyMessage = emptyMessage;
        this.components = components;
    }

    // Teilnehmer > Group (kein Plan was das sein soll)
    public Param(List<Component> components) {
        this.components = components;
    }

    // Teilnehmer > Group > Explizize Nutzer
    public Param(String lastname, String firstname, String street, String location, String phone) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.street = street;
        this.location = location;
        this.phone = phone;
    }

    // Angebote > Group
    public Param(String title, boolean hideTitle, String introduction, String note, List<Component> components) {
        this.title = title;
        this.hideTitle = hideTitle;
        this.introduction = introduction;
        this.note = note;
        this.components = components;
    }

    // Angebote > Group > Checkbox
    public Param(String label, String date, String org, int full, int space) {
        this.label = label;
        this.date = date;
        this.org = org;
        this.full = full;
        this.space = space;
    }

    // Grunddaten > Group
    public Param(String title, boolean hideTitle, List<Component> components) {
        this.title = title;
        this.hideTitle = hideTitle;
        this.components = components;
    }

    // * > TextField
    public Param(String label, boolean required) {
        this.label = label;
        this.required = required;
    }

    // Telefonnummern lol
    public Param(String label, boolean required, String type) {
        this.label = label;
        this.required = required;
        this.type = type;
    }

    public long getParam_id() {
        return param_id;
    }

    public void setParam_id(long param_id) {
        this.param_id = param_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHideTitle() {
        return hideTitle;
    }

    public void setHideTitle(boolean hideTitle) {
        this.hideTitle = hideTitle;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmptyMessage() {
        return emptyMessage;
    }

    public void setEmptyMessage(String emptyMessage) {
        this.emptyMessage = emptyMessage;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean isHideLabel() {
        return hideLabel;
    }

    public void setHideLabel(boolean hideLabel) {
        this.hideLabel = hideLabel;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
