package de.jonashackt.springbootvuejs.domain;

public class Allergy extends Limitation {

    private String medication;

    public Allergy(String name, String information, String medication) {
        super(name, information);
        this.medication = medication;
    }


    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
