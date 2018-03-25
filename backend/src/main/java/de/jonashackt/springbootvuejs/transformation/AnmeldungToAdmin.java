package de.jonashackt.springbootvuejs.transformation;

import de.jonashackt.springbootvuejs.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnmeldungToAdmin {

    public static Teilnehmer mapAnmeldedataToTeilnehmer(AnmeldungJson anmeldungJson, List<Projekt> alleProjekte) {

        Teilnehmer teilnehmer = new Teilnehmer();

        mappeBasisInformationen(anmeldungJson, teilnehmer);

        mappeProjekte(anmeldungJson, teilnehmer, alleProjekte);

        mappeAllergienKrankheitenNotfallkontaktEtc(anmeldungJson, teilnehmer);

        mappeDatenZuBehinderungen(anmeldungJson, teilnehmer);

        mappeErklaerung(anmeldungJson, teilnehmer);

        // TODO: Kein Feld für Datenschutzerklärung!!!

        return teilnehmer;
    }

    private static void mappeProjekte(AnmeldungJson anmeldungJson, Teilnehmer teilnehmer, List<Projekt> alleProjekte) {

        List<Projekt> angemeldeteProjekte = new ArrayList<>();
//        if(anmeldungJson.getProjectsId1()) {
//            alleProjekte.stream().map(einesVonAllenProjekten -> {
//                if(einesVonAllenProjekten.getId() == 1) {
//                    angemeldeteProjekte.add(einesVonAllenProjekten);
//                }
//                return null;
//            });
//        }

//        teilnehmer.setAngemeldeteProjekte(angemeldeteProjekte);
    }

    private static void mappeErklaerung(AnmeldungJson anmeldungJson, Teilnehmer teilnehmer) {
        teilnehmer.setDarfAlleinNachHause(mappeYesOrNoToBoolean(anmeldungJson.getDeclarationGoingHomeAloneAllowed()));
        teilnehmer.setDarfReiten(mappeYesOrNoToBoolean(anmeldungJson.getDeclarationHorseRidingAllowed()));
        teilnehmer.setDarfSchwimmen(mappeYesOrNoToBoolean(anmeldungJson.getDeclarationSwimmingAllowed()));
        //TODO: Das Admin-Datenmodell kennt kein Schwimmabzeichen! Aktuell daher ungemappt!
    }

    private static boolean mappeYesOrNoToBoolean(String yesOrNo) {
        if("yes".equals(yesOrNo)) {
            return true;
        }
        return false;
    }

    private static void mappeDatenZuBehinderungen(AnmeldungJson anmeldungJson, Teilnehmer teilnehmer) {

        teilnehmer.setLiegtBehinderungVor(anmeldungJson.getDisabilitiesDisabilityExistent());

        if(anmeldungJson.getDisabilitiesDisabilityExistent()) {
            Behinderung behinderung = new Behinderung();

            mappeMerkzeichen(anmeldungJson, behinderung);

            behinderung.setRollstuhlNutzungNotwendig(anmeldungJson.getDisabilitiesWheelchair());
            behinderung.setWeitereHilfsmittel(anmeldungJson.getDisabilitiesAdditionalTools());
            behinderung.setWertmarkeVorhanden(anmeldungJson.getDisabilitiesTokenAvailable());

            mappeBegleitpersonenDaten(anmeldungJson, behinderung);
            behinderung.setEingeschraenkteSinne(mappeEingeschraenkteSinne(anmeldungJson));

            behinderung.setHinweiseZumUmgangMitDemKind(anmeldungJson.getDisabilitiesCompanionAdditionalNotes());

            teilnehmer.setBehinderung(behinderung);
        }
    }

    private static String mappeEingeschraenkteSinne(AnmeldungJson anmeldungJson) {
        return new StringBuilder()
                .append(anmeldungJson.getDisabilitiesAffectedSenses0())
                .append("; ")
                .append(anmeldungJson.getDisabilitiesAffectedSenses1())
                .append("; ")
                .append(anmeldungJson.getDisabilitiesAffectedSenses2())
                .append("; ")
                .append(anmeldungJson.getDisabilitiesAffectedSenses3())
                .toString();
    }

    private static void mappeBegleitpersonenDaten(AnmeldungJson anmeldungJson, Behinderung behinderung) {
        behinderung.setBegleitungNotwendig(anmeldungJson.getDisabilitiesCompanionRequired());
        behinderung.setBegleitpersonPflege(anmeldungJson.getDisabilitiesCompanionForNursing());
        behinderung.setBegleitpersonMedizinischeVersorgung(anmeldungJson.getDisabilitiesCompanionForHealthCare());
        behinderung.setBegleitpersonMobilitaet(anmeldungJson.getDisabilitiesCompanionForMobility());
        behinderung.setBegleitpersonOrientierung(anmeldungJson.getDisabilitiesCompanionForOrientation());
        behinderung.setBegleitpersonSozialeBegleitung(anmeldungJson.getDisabilitiesCompanionSocial());

        behinderung.setUnterstuetzungSucheBegleitpersonNotwendig(anmeldungJson.getDisabilitiesCompanionHelpFindingRequired());
        behinderung.setGewohnterBegleitpersonenDienstleister(anmeldungJson.getDisabilitiesCompanionUsualService());
        behinderung.setBeantragungKostenuebernahmeBegleitpersonNotwendig(anmeldungJson.getDisabilitiesCompanionCostTakeover());

    }

    private static void mappeMerkzeichen(AnmeldungJson anmeldungJson, Behinderung behinderung) {
        behinderung.setMerkzeichen_AussergewoehnlicheGehbehinderung_aG(anmeldungJson.getDisabilitiesMarkAg());
        behinderung.setMerkzeichen_Hilflosigkeit_H(anmeldungJson.getDisabilitiesMarkH());
        behinderung.setMerkzeichen_Blind_Bl(anmeldungJson.getDisabilitiesMarkBl());
        behinderung.setMerkzeichen_Gehoerlos_Gl(anmeldungJson.getDisabilitiesMarkGl());
        behinderung.setMerkzeichen_BerechtigtZurMitnahmeEinerBegleitperson_B(anmeldungJson.getDisabilitiesMarkB());
        behinderung.setMerkzeichen_ErheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G(anmeldungJson.getDisabilitiesMarkG());
        behinderung.setMerkzeichen_Taubblind_TBL(anmeldungJson.getDisabilitiesMarkTbl());
    }


    private static void mappeBasisInformationen(AnmeldungJson anmeldungJson, Teilnehmer teilnehmer) {
        teilnehmer.setNachname(anmeldungJson.getBaseFamilyName());
        teilnehmer.setVorname(anmeldungJson.getBaseForename());
        teilnehmer.setGeburtsdatum(mappeGeburtsdatum(anmeldungJson));
        //TODO: Hat das Adminbackend Strasse und Hausnummer getrennt?
        teilnehmer.setStrasse(anmeldungJson.getBaseStreetName() + " " + anmeldungJson.getBaseHouseNumber());
        teilnehmer.setPostleitzahl(anmeldungJson.getBaseZipCode());
        teilnehmer.setStadt(anmeldungJson.getBaseResidence());
        teilnehmer.setTelefon(anmeldungJson.getBasePhoneNumber());
    }

    private static void mappeAllergienKrankheitenNotfallkontaktEtc(AnmeldungJson anmeldungJson, Teilnehmer teilnehmer) {
        teilnehmer.setAllergien(mappeAllergien(anmeldungJson));
        teilnehmer.setKrankheiten(mappeKrankheiten(anmeldungJson));

        //TODO: Wir haben aktuell keine Hitzeempfindlichkeit im Admindatenmodell

        //TODO: Wir haben aktuell die Medikamente den Allergien und Krankheiten zugeordnet im Datenmodell. Aber so kommen die von der Anmeldeseite gar nicht an, sondern als Einzelliste

        teilnehmer.setEssenLimitierungen(mappeEssenslimitierungen(anmeldungJson));
        // TODO: ist setErlaubeMedikamentation() das Gleiche wie "Behandlungserlaubnis bei Erkrankungen und Unfällen"?
        teilnehmer.setErlaubeMedikamentation(mappeYesOrNoToBoolean(anmeldungJson.getConditionsChildTreatmentAllowed()));

        //TODO: Was ist mit der Krankenkasse?

        teilnehmer.setNotfallKontakt(mappeNotfallKontakt(anmeldungJson));
        //TODO: Warum muss die Notrufnummer zweimal gesetzt werden?
        teilnehmer.setNotrufnummer(anmeldungJson.getConditionsEmergencyPhoneNumber());

        teilnehmer.setArzt(mappeArzt(anmeldungJson));
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
