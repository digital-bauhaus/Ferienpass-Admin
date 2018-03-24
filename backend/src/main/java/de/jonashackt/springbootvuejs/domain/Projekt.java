package de.jonashackt.springbootvuejs.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Projekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projekt_id;
    private String name;
    private LocalDate datum;
    private int alterLimitierung;
    //private String organisation;
    private int kosten;
    private int slotsGesamt;
    private int slotsFrei;
    private int slotsReserviert;
    private String webLink;
    private boolean aktiv;
    //@ManyToMany(cascade= CascadeType.ALL)
    //@JoinTable(name="project_user",
    //        joinColumns = @JoinColumn(name="projekt_id"),
    //        inverseJoinColumns = @JoinColumn(name="id")
    //)
    @ManyToMany(cascade= CascadeType.ALL)
    private List<Teilnehmer> anmeldungen = new ArrayList<>();

    @ManyToMany(cascade= CascadeType.ALL)
    private List<Teilnehmer> stornierteTeilnehmer = new ArrayList<>();

    protected Projekt() {}

    public Projekt(String name, LocalDate datum, int alterLimitierung, int kosten, int slotsGesamt, int slotsReserviert, String webLink, List<Teilnehmer> anmeldungen) {
        this.setName(name);
        this.setDatum(datum);
        this.setAlterLimitierung(alterLimitierung);
        this.setKosten(kosten);
        this.setSlotsGesamt(slotsGesamt);
        this.setSlotsFrei(slotsGesamt - slotsReserviert);
        this.setSlotsReserviert(slotsReserviert);
        this.setWebLink(webLink);
        this.setAnmeldungen(anmeldungen);
        this.setAktiv(true);
    }

    @Override
    public String toString() {
        return String.format(
                "Projekt[id=%d, Name='%s', Datum='%s', alterLimitierung='%d', Kosten='%d' Slots Gesamt='%d', Frei Slots='%d', Reservierte Slots='%d', Weblink='%s']",
                getId(), getName(), getDatum(), getAlterLimitierung(), getKosten(), getSlotsGesamt(), getSlotsFrei(), getSlotsReserviert(), getWebLink());
    }

    public long getId() {
        return projekt_id;
    }

    public void setId(long id) {
        this.projekt_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getAlterLimitierung() {
        return alterLimitierung;
    }

    public void setAlterLimitierung(int alterLimitierung) {
        this.alterLimitierung = alterLimitierung;
    }

    public int getKosten() {
        return kosten;
    }

    public void setKosten(int kosten) {
        this.kosten = kosten;
    }

    public int getSlotsGesamt() {
        return slotsGesamt;
    }

    public void setSlotsGesamt(int slotsGesamt) {
        this.slotsGesamt = slotsGesamt;
    }

    public int getSlotsFrei() {
        return slotsFrei;
    }

    public void setSlotsFrei(int slotsFrei) {
        this.slotsFrei = slotsFrei;
    }

    public int getSlotsReserviert() {
        return slotsReserviert;
    }

    public void setSlotsReserviert(int slotsReserviert) {
        this.slotsReserviert = slotsReserviert;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public List<Teilnehmer> getAnmeldungen() {
        return anmeldungen;
    }

    public void setAnmeldungen(List<Teilnehmer> anmeldungen) {
        if(anmeldungen.size() >= this.getSlotsGesamt() - this.getSlotsReserviert())
            return;
        this.setSlotsFrei(this.getSlotsGesamt() - this.getSlotsReserviert() - anmeldungen.size());
        this.anmeldungen = anmeldungen;
    }

    public List<Teilnehmer> getStornierteTeilnehmer() {
        return stornierteTeilnehmer;
    }

    public void setStornierteTeilnehmer(List<Teilnehmer> stornierteTeilnehmer) {
        this.stornierteTeilnehmer = stornierteTeilnehmer;
    }
}
