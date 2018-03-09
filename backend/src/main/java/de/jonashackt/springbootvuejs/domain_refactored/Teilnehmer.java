package de.jonashackt.springbootvuejs.domain_refactored;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "[User]")
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
    // foreign key as JoinColumn annotation with type of class

    @ManyToMany(cascade=CascadeType.ALL)
    /*@JoinTable(name="teilnehmer_projekte",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="projekt_id")
    )*/
    private List<Projekt> angemeldeteProjekte = new ArrayList<>();


    @ManyToMany(cascade=CascadeType.ALL)
    /*@JoinTable(name="teilnehmer_allergien",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="allergie_id")
    )*/
    private List<Allergie> allergien = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    /*@JoinTable(name="teilnehmer_krankheiten",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="krankheit_id")
    )*/
    private List<Krankheit> krankheiten = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    /*@JoinTable(name="teilnehmer_behinderungen",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="behinderung_id")
    )*/
    private List<Behinderung> behinderungen= new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    /*@JoinTable(name="teilnehmer_EssenLimitierung",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="essen_id")
    )*/
    private List<EssenLimitierung> essenLimitierungen= new ArrayList<>();


    @ManyToMany(cascade=CascadeType.ALL)
    /*@JoinTable(name="teilnehmer_stornierungen",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="projekt_id")
    )*/
    private List<Projekt> stornierungen = new ArrayList<>();


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
                ", angemeldete Projekte=" + getAngemeldeteProjekte() +
                ", Stornierungen=" + getStornierungen() +
                ", Behinderungen=" + getBehinderungen() +
                ", Krankheiten=" + getKrankheiten() +
                ", Essenslimitierungen=" + getEssenLimitierungen() +
                ", Allergien=" + getAllergien() +
                '}';
    }

    public Teilnehmer() {}

    public Teilnehmer(String firstName, String lastName, LocalDate birthDate, LocalDate registerDate, String street, String city, String postcode, String telephone, String healthcareNr,
                      boolean allowTreatment, Kontakt emergencyContact, boolean allowHomeAlone, boolean allowRiding, boolean allowSwimming, boolean hasPayed, Arzt doctor, List<Projekt> projects,
                      List<Allergie> allergien, List<EssenLimitierung> essenLimitierungen, List<Krankheit> krankheiten, List<Behinderung> behinderungen, List<Projekt> cancellations) {

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
        this.setAngemeldeteProjekte(projects);
        this.setAllergien(allergien);
        this.setEssenLimitierungen(essenLimitierungen);
        this.setKrankheiten(krankheiten);
        this.setBehinderungen(behinderungen);
        this.setStornierungen(cancellations);
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

    public List<Projekt> getAngemeldeteProjekte() {
        return angemeldeteProjekte;
    }

    public void setAngemeldeteProjekte(List<Projekt> angemeldeteProjekte) {
        this.angemeldeteProjekte = angemeldeteProjekte;
    }



    public List<Projekt> getStornierungen() {
        return stornierungen;
    }

    public void setStornierungen(List<Projekt> stornierungen) {
        this.stornierungen = stornierungen;
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

    public List<Behinderung> getBehinderungen() {
        return behinderungen;
    }

    public void setBehinderungen(List<Behinderung> behinderungen) {
        this.behinderungen = behinderungen;
    }

    public List<EssenLimitierung> getEssenLimitierungen() {
        return essenLimitierungen;
    }

    public void setEssenLimitierungen(List<EssenLimitierung> essenLimitierungen) {
        this.essenLimitierungen = essenLimitierungen;
    }
}
