package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;

@Entity
public class Behinderung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Merkzeichen
    private boolean aussergewoehnlicheGehbehinderung_aG;
    private boolean hilflosigkeit_H;
    private boolean blind_Bl;
    private boolean gehoerlos_Gl;
    private boolean berechtigtZurMitnahmeEinerBegleitperson_B;
    private boolean erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G;
    private boolean taubblind_TBL;

    private boolean rollstuhlNutzungNotwendig;
    private String weitereHilfsmittel;
    private boolean wertmarkeVorhanden;

    private boolean begleitungNotwendig;
    private boolean begleitpersonPflege;
    private boolean begleitpersonMedizinischeVersorgung;
    private boolean begleitpersonMobilitaet;
    private boolean begleitpersonOrientierung;
    private boolean begleitpersonSozialeBegleitung;

    private String eingeschraenkteSinne;
    private String hinweiseZumUmgangMitDemKind;
    private boolean unterstuetzungSucheBegleitpersonNotwendig;
    private String gewohnterBegleitpersonenDienstleister;
    private boolean beantragungKostenuebernahmeBegleitpersonNotwendig;


    public Behinderung(boolean aussergewoehnlicheGehbehinderung_aG, boolean hilflosigkeit_H, boolean blind_Bl, boolean gehoerlos_Gl, boolean berechtigtZurMitnahmeEinerBegleitperson_B, boolean erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G, boolean taubblind_TBL, boolean rollstuhlNutzungNotwendig, String weitereHilfsmittel, boolean wertmarkeVorhanden, boolean begleitungNotwendig, boolean begleitpersonPflege, boolean begleitpersonMedizinischeVersorgung, boolean begleitpersonMobilitaet, boolean begleitpersonOrientierung, boolean begleitpersonSozialeBegleitung, String eingeschraenkteSinne, String hinweiseZumUmgangMitDemKind, boolean unterstuetzungSucheBegleitpersonNotwendig, String gewohnterBegleitpersonenDienstleister, boolean beantragungKostenuebernahmeBegleitpersonNotwendig) {
        this.aussergewoehnlicheGehbehinderung_aG = aussergewoehnlicheGehbehinderung_aG;
        this.hilflosigkeit_H = hilflosigkeit_H;
        this.blind_Bl = blind_Bl;
        this.gehoerlos_Gl = gehoerlos_Gl;
        this.berechtigtZurMitnahmeEinerBegleitperson_B = berechtigtZurMitnahmeEinerBegleitperson_B;
        this.erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G = erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G;
        this.taubblind_TBL = taubblind_TBL;
        this.rollstuhlNutzungNotwendig = rollstuhlNutzungNotwendig;
        this.weitereHilfsmittel = weitereHilfsmittel;
        this.wertmarkeVorhanden = wertmarkeVorhanden;
        this.begleitungNotwendig = begleitungNotwendig;
        this.begleitpersonPflege = begleitpersonPflege;
        this.begleitpersonMedizinischeVersorgung = begleitpersonMedizinischeVersorgung;
        this.begleitpersonMobilitaet = begleitpersonMobilitaet;
        this.begleitpersonOrientierung = begleitpersonOrientierung;
        this.begleitpersonSozialeBegleitung = begleitpersonSozialeBegleitung;
        this.eingeschraenkteSinne = eingeschraenkteSinne;
        this.hinweiseZumUmgangMitDemKind = hinweiseZumUmgangMitDemKind;
        this.unterstuetzungSucheBegleitpersonNotwendig = unterstuetzungSucheBegleitpersonNotwendig;
        this.gewohnterBegleitpersonenDienstleister = gewohnterBegleitpersonenDienstleister;
        this.beantragungKostenuebernahmeBegleitpersonNotwendig = beantragungKostenuebernahmeBegleitpersonNotwendig;
    }

    public void setAussergewoehnlicheGehbehinderung_aG(boolean aussergewoehnlicheGehbehinderung_aG) {
        this.aussergewoehnlicheGehbehinderung_aG = aussergewoehnlicheGehbehinderung_aG;
    }

    public void setHilflosigkeit_H(boolean hilflosigkeit_H) {
        this.hilflosigkeit_H = hilflosigkeit_H;
    }

    public void setBlind_Bl(boolean blind_Bl) {
        this.blind_Bl = blind_Bl;
    }

    public void setGehoerlos_Gl(boolean gehoerlos_Gl) {
        this.gehoerlos_Gl = gehoerlos_Gl;
    }

    public void setBerechtigtZurMitnahmeEinerBegleitperson_B(boolean berechtigtZurMitnahmeEinerBegleitperson_B) {
        this.berechtigtZurMitnahmeEinerBegleitperson_B = berechtigtZurMitnahmeEinerBegleitperson_B;
    }

    public void setErheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G(boolean erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G) {
        this.erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G = erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G;
    }

    public void setTaubblind_TBL(boolean taubblind_TBL) {
        this.taubblind_TBL = taubblind_TBL;
    }

    public void setRollstuhlNutzungNotwendig(boolean rollstuhlNutzungNotwendig) {
        this.rollstuhlNutzungNotwendig = rollstuhlNutzungNotwendig;
    }

    public void setWeitereHilfsmittel(String weitereHilfsmittel) {
        this.weitereHilfsmittel = weitereHilfsmittel;
    }

    public void setWertmarkeVorhanden(boolean wertmarkeVorhanden) {
        this.wertmarkeVorhanden = wertmarkeVorhanden;
    }

    public void setBegleitungNotwendig(boolean begleitungNotwendig) {
        this.begleitungNotwendig = begleitungNotwendig;
    }

    public void setBegleitpersonPflege(boolean begleitpersonPflege) {
        this.begleitpersonPflege = begleitpersonPflege;
    }

    public void setBegleitpersonMedizinischeVersorgung(boolean begleitpersonMedizinischeVersorgung) {
        this.begleitpersonMedizinischeVersorgung = begleitpersonMedizinischeVersorgung;
    }

    public void setBegleitpersonMobilitaet(boolean begleitpersonMobilitaet) {
        this.begleitpersonMobilitaet = begleitpersonMobilitaet;
    }

    public void setBegleitpersonOrientierung(boolean begleitpersonOrientierung) {
        this.begleitpersonOrientierung = begleitpersonOrientierung;
    }

    public void setBegleitpersonSozialeBegleitung(boolean begleitpersonSozialeBegleitung) {
        this.begleitpersonSozialeBegleitung = begleitpersonSozialeBegleitung;
    }

    public void setEingeschraenkteSinne(String eingeschraenkteSinne) {
        this.eingeschraenkteSinne = eingeschraenkteSinne;
    }

    public void setHinweiseZumUmgangMitDemKind(String hinweiseZumUmgangMitDemKind) {
        this.hinweiseZumUmgangMitDemKind = hinweiseZumUmgangMitDemKind;
    }

    public void setUnterstuetzungSucheBegleitpersonNotwendig(boolean unterstuetzungSucheBegleitpersonNotwendig) {
        this.unterstuetzungSucheBegleitpersonNotwendig = unterstuetzungSucheBegleitpersonNotwendig;
    }

    public void setGewohnterBegleitpersonenDienstleister(String gewohnterBegleitpersonenDienstleister) {
        this.gewohnterBegleitpersonenDienstleister = gewohnterBegleitpersonenDienstleister;
    }

    public void setBeantragungKostenuebernahmeBegleitpersonNotwendig(boolean beantragungKostenuebernahmeBegleitpersonNotwendig) {
        this.beantragungKostenuebernahmeBegleitpersonNotwendig = beantragungKostenuebernahmeBegleitpersonNotwendig;
    }

    public boolean isAussergewoehnlicheGehbehinderung_aG() {

        return aussergewoehnlicheGehbehinderung_aG;
    }

    public boolean isHilflosigkeit_H() {
        return hilflosigkeit_H;
    }

    public boolean isBlind_Bl() {
        return blind_Bl;
    }

    public boolean isGehoerlos_Gl() {
        return gehoerlos_Gl;
    }

    public boolean isBerechtigtZurMitnahmeEinerBegleitperson_B() {
        return berechtigtZurMitnahmeEinerBegleitperson_B;
    }

    public boolean isErheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G() {
        return erheblicheBeeintraechtigungDerBewegungsfaehigkeitImStrassenverkehr_G;
    }

    public boolean isTaubblind_TBL() {
        return taubblind_TBL;
    }

    public boolean isRollstuhlNutzungNotwendig() {
        return rollstuhlNutzungNotwendig;
    }

    public String getWeitereHilfsmittel() {
        return weitereHilfsmittel;
    }

    public boolean isWertmarkeVorhanden() {
        return wertmarkeVorhanden;
    }

    public boolean isBegleitungNotwendig() {
        return begleitungNotwendig;
    }

    public boolean isBegleitpersonPflege() {
        return begleitpersonPflege;
    }

    public boolean isBegleitpersonMedizinischeVersorgung() {
        return begleitpersonMedizinischeVersorgung;
    }

    public boolean isBegleitpersonMobilitaet() {
        return begleitpersonMobilitaet;
    }

    public boolean isBegleitpersonOrientierung() {
        return begleitpersonOrientierung;
    }

    public boolean isBegleitpersonSozialeBegleitung() {
        return begleitpersonSozialeBegleitung;
    }

    public String getEingeschraenkteSinne() {
        return eingeschraenkteSinne;
    }

    public String getHinweiseZumUmgangMitDemKind() {
        return hinweiseZumUmgangMitDemKind;
    }

    public boolean isUnterstuetzungSucheBegleitpersonNotwendig() {
        return unterstuetzungSucheBegleitpersonNotwendig;
    }

    public String getGewohnterBegleitpersonenDienstleister() {
        return gewohnterBegleitpersonenDienstleister;
    }

    public boolean isBeantragungKostenuebernahmeBegleitpersonNotwendig() {
        return beantragungKostenuebernahmeBegleitpersonNotwendig;
    }

    protected Behinderung() {}



}
