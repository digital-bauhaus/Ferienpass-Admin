package de.jonashackt.springbootvuejs.domain;

public class Illness extends Limitation {

    private String medication;

    protected Illness() {}

    public Illness(String name, String information, String medication) {
        super(name, information);
        this.medication = medication;
    }

    @Override
    public String toString() {
        return String.format(
                "Limitation[limit_id=%d, name='%s', information='%s']",
                limit_id, name, information);
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }


}
