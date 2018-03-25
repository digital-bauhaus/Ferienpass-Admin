package de.jonashackt.springbootvuejs.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "[User]")
@JsonIgnoreProperties(value= {"angemeldeteProjekte","stornierungen"})
public class Teilnehmer {
    // PrimaryKey
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;

    private LocalDate registrierungsdatum;

    private String strasse;
    private String stadt;
    private String postleitzahl;
    private String telefon;
    private String notrufnummer;
    private boolean erlaubeMedikamentation;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="kontakt_id")
    private Kontakt notfallKontakt;
    private boolean darfAlleinNachHause;
    private boolean darfReiten;
    private boolean darfSchwimmen;
    private boolean bezahlt;
    private boolean aktiv;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="arzt_id")
    private Arzt arzt;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Allergie> allergien = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Krankheit> krankheiten = new ArrayList<>();

    private boolean liegtBehinderungVor;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="behinderung_id")
    private Behinderung behinderung;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<EssenLimitierung> essenLimitierungen= new ArrayList<>();



    @Override
    public String toString() {
        return "Teilnehmer{" +
                "id=" + getId() +
                ", Vorname='" + getVorname() + '\'' +
                ", Nachname='" + getNachname() + '\'' +
                ", Geburtsdatum=" + getGeburtsdatum() +
                ", Registrierungsdatum=" + getRegistrierungsdatum() +
                ", Straße='" + getStrasse() + '\'' +
                ", Stadt='" + getStadt() + '\'' +
                ", Postleitzahl='" + getPostleitzahl() + '\'' +
                ", Telefon='" + getTelefon() + '\'' +
                ", Notrufnummer='" + getNotrufnummer() + '\'' +
                ", erlaube Medikamentation=" + isErlaubeMedikamentation() +
                ", Notfallkontakt='" + getNotfallKontakt() + '\'' +
                ", darf allein nach Hause=" + isDarfAlleinNachHause() +
                ", darf Reiten=" + isDarfReiten() +
                ", darf Schwimmen=" + isDarfSchwimmen() +
                ", bezahlt=" + isBezahlt() +
                ", Arzt=" + getArzt() +
                ", liegt Beeinträchtigung vor=" + isLiegtBehinderungVor() +
                ", Behinderung=" + getBehinderung() +
                ", Krankheiten=" + getKrankheiten() +
                ", Essenslimitierungen=" + getEssenLimitierungen() +
                ", Allergien=" + getAllergien() +
                '}';
    }

    public Teilnehmer() {}

    public Teilnehmer(String firstName, String lastName, LocalDate birthDate, LocalDate registerDate, String street, String city, String postcode, String telephone, String healthcareNr,
                      boolean allowTreatment, Kontakt emergencyContact, boolean allowHomeAlone, boolean allowRiding, boolean allowSwimming, boolean hasPayed, Arzt doctor,
                      List<Allergie> allergien, List<EssenLimitierung> essenLimitierungen, List<Krankheit> krankheiten, boolean beeintraechtigt, Behinderung behinderung) {

        this.setVorname(firstName);
        this.setNachname(lastName);
        this.setGeburtsdatum(birthDate);
        this.setRegistrierungsdatum(registerDate);
        this.setStrasse(street);
        this.setStadt(city);
        this.setPostleitzahl(postcode);
        this.setTelefon(telephone);
        this.setNotrufnummer(healthcareNr);
        this.setErlaubeMedikamentation(allowTreatment);
        this.setNotfallKontakt(emergencyContact);
        this.setDarfAlleinNachHause(allowHomeAlone);
        this.setDarfReiten(allowRiding);
        this.setDarfSchwimmen(allowSwimming);
        this.setBezahlt(hasPayed);
        this.setArzt(doctor);
        this.setAllergien(allergien);
        this.setEssenLimitierungen(essenLimitierungen);
        this.setKrankheiten(krankheiten);
        this.setLiegtBehinderungVor(beeintraechtigt);
        this.setBehinderung(behinderung);
        this.setAktiv(true);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public LocalDate getRegistrierungsdatum() {
        return registrierungsdatum;
    }

    public void setRegistrierungsdatum(LocalDate registrierungsdatum) {
        this.registrierungsdatum = registrierungsdatum;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNotrufnummer() {
        return notrufnummer;
    }

    public void setNotrufnummer(String notrufnummer) {
        this.notrufnummer = notrufnummer;
    }

    public boolean isErlaubeMedikamentation() {
        return erlaubeMedikamentation;
    }

    public void setErlaubeMedikamentation(boolean erlaubeMedikamentation) {
        this.erlaubeMedikamentation = erlaubeMedikamentation;
    }

    public Kontakt getNotfallKontakt() {
        return notfallKontakt;
    }

    public void setNotfallKontakt(Kontakt notfallKontakt) {
        this.notfallKontakt = notfallKontakt;
    }

    public boolean isDarfAlleinNachHause() {
        return darfAlleinNachHause;
    }

    public void setDarfAlleinNachHause(boolean darfAlleinNachHause) {
        this.darfAlleinNachHause = darfAlleinNachHause;
    }

    public boolean isDarfReiten() {
        return darfReiten;
    }

    public void setDarfReiten(boolean darfReiten) {
        this.darfReiten = darfReiten;
    }

    public boolean isDarfSchwimmen() {
        return darfSchwimmen;
    }

    public void setDarfSchwimmen(boolean darfSchwimmen) {
        this.darfSchwimmen = darfSchwimmen;
    }

    public boolean isBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(boolean bezahlt) {
        this.bezahlt = bezahlt;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public Arzt getArzt() {
        return arzt;
    }

    public void setArzt(Arzt arzt) {
        this.arzt = arzt;
    }

    public List<Allergie> getAllergien() {
        return allergien;
    }

    public void setAllergien(List<Allergie> allergien) {
        this.allergien = allergien;
    }

    public List<Krankheit> getKrankheiten() {
        return krankheiten;
    }

    public void setKrankheiten(List<Krankheit> krankheiten) {
        this.krankheiten = krankheiten;
    }

    public void setBehinderung(Behinderung behinderung) {
        this.behinderung = behinderung;
    }

    public Behinderung getBehinderung() {

        return behinderung;
    }

    public List<EssenLimitierung> getEssenLimitierungen() {
        return essenLimitierungen;
    }

    public void setEssenLimitierungen(List<EssenLimitierung> essenLimitierungen) {
        this.essenLimitierungen = essenLimitierungen;
    }

    public boolean isLiegtBehinderungVor() {
        return liegtBehinderungVor;
    }

    public void setLiegtBehinderungVor(boolean liegtBehinderungVor) {
        this.liegtBehinderungVor = liegtBehinderungVor;
    }
}
