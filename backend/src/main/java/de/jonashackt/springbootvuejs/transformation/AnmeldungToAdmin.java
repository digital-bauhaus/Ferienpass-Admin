package de.jonashackt.springbootvuejs.transformation;

import de.jonashackt.springbootvuejs.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnmeldungToAdmin {

    public static Teilnehmer mapAnmeldedataToTeilnehmer(AnmeldungJson anmeldungJson) {

        Teilnehmer neuAngemeldeterTeilnehmer = new Teilnehmer();

        neuAngemeldeterTeilnehmer.setNachname(anmeldungJson.getBaseFamilyName());
        neuAngemeldeterTeilnehmer.setVorname(anmeldungJson.getBaseForename());
        neuAngemeldeterTeilnehmer.setGeburtsdatum(mapAnmeldeJsonDateToLocalDate(anmeldungJson));


//        //add some allergies
//        Allergie a1 = new Allergie("Arbeiten","Viele Aufgaben","Viel reden");
//        Allergie a2 = new Allergie("Freizeit","Urlaub","Spaß haben");
//        neuAngemeldeterTeilnehmer.setAllergien(new ArrayList<>());
//        neuAngemeldeterTeilnehmer.getAllergien().add(a1);
//        neuAngemeldeterTeilnehmer.getAllergien().add(a2);
//
//        //add some food limitations
//        EssenLimitierung e1 = new EssenLimitierung("Fleisch", "vegetarier");
//        EssenLimitierung e2 = new EssenLimitierung("Obst", "Sollte dennoch Obst essen");
//        neuAngemeldeterTeilnehmer.setEssenLimitierungen(new ArrayList<>());
//        neuAngemeldeterTeilnehmer.getEssenLimitierungen().add(e1);
//        neuAngemeldeterTeilnehmer.getEssenLimitierungen().add(e2);
//
//        //add some illnesses
//        Krankheit k1 = new Krankheit("Grippe", "Sollte viel Pausen machen", "Keine");
//        Krankheit k2 = new Krankheit("Husten", "Immer in den Arm husten", "Hustensaft");
//        neuAngemeldeterTeilnehmer.setKrankheiten(new ArrayList<>());
//        neuAngemeldeterTeilnehmer.getKrankheiten().add(k1);
//        neuAngemeldeterTeilnehmer.getKrankheiten().add(k2);
//
//        //add some handicaps
//        Behinderung b1 = new Behinderung("Arm", new BehinderungKodierung("A1"),true,false, false, true);
//        Behinderung b2 = new Behinderung("Bein", new BehinderungKodierung("A2"),true,false, false, true);
//        neuAngemeldeterTeilnehmer.setBehinderungen(new ArrayList<>());
//        neuAngemeldeterTeilnehmer.getBehinderungen().add(b1);
//        neuAngemeldeterTeilnehmer.getBehinderungen().add(b2);
//
//        //add some projects
//        List<Projekt> projekte = null;
//        neuAngemeldeterTeilnehmer.setAngemeldeteProjekte(projekte);


        return neuAngemeldeterTeilnehmer;
    }

    private static LocalDate mapAnmeldeJsonDateToLocalDate(AnmeldungJson anmeldungJson) {
        return LocalDate.of(
                Integer.valueOf(anmeldungJson.getBaseBirthdateYear()),
                Integer.valueOf(anmeldungJson.getBaseBirthdateMonth()),
                Integer.valueOf(anmeldungJson.getBaseBirthdateDay()));
    }
}
