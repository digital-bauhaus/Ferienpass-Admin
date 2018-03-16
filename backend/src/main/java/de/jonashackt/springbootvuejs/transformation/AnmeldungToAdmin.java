package de.jonashackt.springbootvuejs.transformation;

import de.jonashackt.springbootvuejs.domain.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AnmeldungToAdmin {

    public static Teilnehmer mapAnmeldedataToTeilnehmer(AnmeldungJson anmeldungJson) {

        Teilnehmer teilnehmer = new Teilnehmer();

        teilnehmer.setNachname(anmeldungJson.getBaseFamilyName());
        teilnehmer.setVorname(anmeldungJson.getBaseForename());
        teilnehmer.setGeburtsdatum(mappeGeburtsdatum(anmeldungJson));
        //TODO: Hat das Adminbackend Strasse und Hausnummer getrennt?
        teilnehmer.setStrasse(anmeldungJson.getBaseStreetName() + " " + anmeldungJson.getBaseHouseNumber());
        teilnehmer.setPostleitzahl(anmeldungJson.getBaseZipCode());
        teilnehmer.setStadt(anmeldungJson.getBaseResidence());
        teilnehmer.setTelefon(anmeldungJson.getBasePhoneNumber());

        //        //add some projects
//        List<Projekt> projekte = null;
//        neuAngemeldeterTeilnehmer.setAngemeldeteProjekte(projekte);

        teilnehmer.setAllergien(mappeAllergien(anmeldungJson));
        teilnehmer.setKrankheiten(mappeKrankheiten(anmeldungJson));



//        //add some illnesses
//        Krankheit k1 = new Krankheit("Grippe", "Sollte viel Pausen machen", "Keine");
//        Krankheit k2 = new Krankheit("Husten", "Immer in den Arm husten", "Hustensaft");
//        neuAngemeldeterTeilnehmer.setKrankheiten(new ArrayList<>());
//        neuAngemeldeterTeilnehmer.getKrankheiten().add(k1);
//        neuAngemeldeterTeilnehmer.getKrankheiten().add(k2);


//        //add some food limitations
//        EssenLimitierung e1 = new EssenLimitierung("Fleisch", "vegetarier");
//        EssenLimitierung e2 = new EssenLimitierung("Obst", "Sollte dennoch Obst essen");
//        neuAngemeldeterTeilnehmer.setEssenLimitierungen(new ArrayList<>());
//        neuAngemeldeterTeilnehmer.getEssenLimitierungen().add(e1);
//        neuAngemeldeterTeilnehmer.getEssenLimitierungen().add(e2);
//

//
//        //add some handicaps
//        Behinderung b1 = new Behinderung("Arm", new BehinderungKodierung("A1"),true,false, false, true);
//        Behinderung b2 = new Behinderung("Bein", new BehinderungKodierung("A2"),true,false, false, true);
//        neuAngemeldeterTeilnehmer.setBehinderungen(new ArrayList<>());
//        neuAngemeldeterTeilnehmer.getBehinderungen().add(b1);
//        neuAngemeldeterTeilnehmer.getBehinderungen().add(b2);
//



        return teilnehmer;
    }

    private static List<Allergie> mappeAllergien(AnmeldungJson anmeldungJson) {

        // TODO: Aktuell haben wir keine Zuordnung im Anmeldefrontend von Allergien zu Medikamenten!
        return Arrays.asList(
                new Allergie(anmeldungJson.getConditionsAllergies0(), null, null),
                new Allergie(anmeldungJson.getConditionsAllergies1(), null, null),
                new Allergie(anmeldungJson.getConditionsAllergies2(), null, null),
                new Allergie(anmeldungJson.getConditionsAllergies3(), null, null),
                new Allergie(anmeldungJson.getConditionsAllergies4(), null, null)
        );
    }

    private static List<Krankheit> mappeKrankheiten(AnmeldungJson anmeldungJson) {

        // TODO: Aktuell haben wir keine Zuordnung im Anmeldefrontend von Krankheiten zu Medikamenten!
        return Arrays.asList(
                new Krankheit(anmeldungJson.getConditionsDiseases0(), null, null),
                new Krankheit(anmeldungJson.getConditionsDiseases1(), null, null),
                new Krankheit(anmeldungJson.getConditionsDiseases2(), null, null),
                new Krankheit(anmeldungJson.getConditionsDiseases3(), null, null),
                new Krankheit(anmeldungJson.getConditionsDiseases4(), null, null)
        );
    }

    private static LocalDate mappeGeburtsdatum(AnmeldungJson anmeldungJson) {
        return LocalDate.of(
                Integer.valueOf(anmeldungJson.getBaseBirthdateYear()),
                Integer.valueOf(anmeldungJson.getBaseBirthdateMonth()),
                Integer.valueOf(anmeldungJson.getBaseBirthdateDay()));
    }
}
