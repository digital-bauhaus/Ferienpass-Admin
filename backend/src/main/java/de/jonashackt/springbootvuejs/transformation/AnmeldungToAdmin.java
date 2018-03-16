package de.jonashackt.springbootvuejs.transformation;

import de.jonashackt.springbootvuejs.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

        //TODO: Wir haben aktuell keine Hitzeempfindlichkeit im Admindatenmodell

        //TODO: Wir haben aktuell die Medikamente den Allergien und Krankheiten zugeordnet im Datenmodell. Aber so kommen die von der Anmeldeseite gar nicht an, sondern als Einzelliste

        teilnehmer.setEssenLimitierungen(mappeEssenslimitierungen(anmeldungJson));

        if("yes".equals(anmeldungJson.getConditionsChildTreatmentAllowed())) {
            // TODO: ist setErlaubeMedikamentation() das Gleiche wie "Behandlungserlaubnis bei Erkrankungen und Unfällen"?
            teilnehmer.setErlaubeMedikamentation(true);
        } else {
            teilnehmer.setErlaubeMedikamentation(false);
        }

        //TODO: Was ist mit der Krankenkasse?

        teilnehmer.setNotfallKontakt(mappeNotfallKontakt(anmeldungJson));
        //TODO: Warum muss die Notrufnummer zweimal gesetzt werden?
        teilnehmer.setNotrufnummer(anmeldungJson.getConditionsEmergencyPhoneNumber());

        teilnehmer.setArzt(mappeArzt(anmeldungJson));

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

    private static Arzt mappeArzt(AnmeldungJson anmeldungJson) {
        return new Arzt(
                anmeldungJson.getConditionsFamilyDoctorName(),
                anmeldungJson.getConditionsFamilyDoctorAddress(),
                anmeldungJson.getConditionsFamilyDoctorPhoneNumber());
    }

    private static Kontakt mappeNotfallKontakt(AnmeldungJson anmeldungJson) {
        return new Kontakt(
                anmeldungJson.getConditionsEmergencyName(),
                anmeldungJson.getConditionsEmergencyAddress(),
                anmeldungJson.getConditionsEmergencyPhoneNumber());
    }

    private static List<EssenLimitierung> mappeEssenslimitierungen(AnmeldungJson anmeldungJson) {

        List<EssenLimitierung> essenLimitierungen = new ArrayList<>();

        // TODO: Wozu ist die info?
        if(anmeldungJson.getConditionsVegetarian()) {
            // TODO: Die Namen der Essensunvertraeglichkeiten sollten direkt im Datenmodell abgebildet sein!
            essenLimitierungen.add(new EssenLimitierung("Vegetarier", null));
        }
        if(anmeldungJson.getConditionsLactoseIntolerance()) {
            essenLimitierungen.add(new EssenLimitierung("Laktose-Unverträglichkeit", null));
        }
        if(anmeldungJson.getConditionsEggIntolerance()) {
            essenLimitierungen.add(new EssenLimitierung("Eier-Unverträglichkeit", null));
        }
        essenLimitierungen.add(new EssenLimitierung(anmeldungJson.getConditionsNutrition0(), null));
        essenLimitierungen.add(new EssenLimitierung(anmeldungJson.getConditionsNutrition1(), null));
        essenLimitierungen.add(new EssenLimitierung(anmeldungJson.getConditionsNutrition2(), null));
        essenLimitierungen.add(new EssenLimitierung(anmeldungJson.getConditionsNutrition3(), null));
        essenLimitierungen.add(new EssenLimitierung(anmeldungJson.getConditionsNutrition4(), null));

        return essenLimitierungen;
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
