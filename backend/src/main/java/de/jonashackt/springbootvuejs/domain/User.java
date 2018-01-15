package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[User]")
public class User {

    // PrimaryKey
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private Date birthDate;

    private Date registerDate;

    private String street;
    private String city;
    private String postcode;
    private String telephone;
    private String healthcareNr;
    private boolean allowTreatment;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Contact emergencyContact;
    private boolean allowHomeAlone;
    private boolean allowRiding;
    private boolean allowSwimming;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    // foreign key as JoinColumn annotation with type of class

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_project",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="project_id")
    )
    private List<Project> projects = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_limitation",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="limitation_id")
    )
    private List<Limitation> limits = new ArrayList<>();


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", registerDate=" + registerDate +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", telephone='" + telephone + '\'' +
                ", healthcareNr='" + healthcareNr + '\'' +
                ", allowTreatment=" + allowTreatment +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", allowHomeAlone=" + allowHomeAlone +
                ", allowRiding=" + allowRiding +
                ", allowSwimming=" + allowSwimming +
                ", doctor=" + doctor +
                ", projects=" + projects +
                ", limits=" + limits +
                '}';
    }

    public User() {}

    public User(String firstName, String lastName, Date birthDate, Date registerDate, String street,
                String city, String postcode, String telephone, String healthcareNr, boolean allowTreatment,
                Contact emergencyContact, boolean allowHomeAlone, boolean allowRiding,
                boolean allowSwimming, Doctor doctor, List<Project> projects, List<Limitation> limits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.registerDate = registerDate;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.telephone = telephone;
        this.healthcareNr = healthcareNr;
        this.allowTreatment = allowTreatment;
        this.emergencyContact = emergencyContact;
        this.allowHomeAlone = allowHomeAlone;
        this.allowRiding = allowRiding;
        this.allowSwimming = allowSwimming;
        this.doctor = doctor;
        this.projects = projects;
        this.limits = limits;
    }


    public List<Limitation> getLimits() {
        return limits;
    }

    public void setLimits(List<Limitation> limits) {
        this.limits = limits;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHealthcareNr() {
        return healthcareNr;
    }

    public void setHealthcareNr(String healthcareNr) {
        this.healthcareNr = healthcareNr;
    }

    public Contact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(Contact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public boolean isAllowHomeAlone() {
        return allowHomeAlone;
    }

    public void setAllowHomeAlone(boolean allowHomeAlone) {
        this.allowHomeAlone = allowHomeAlone;
    }

    public boolean isAllowRiding() {
        return allowRiding;
    }

    public void setAllowRiding(boolean alowRiding) {
        this.allowRiding = alowRiding;
    }

    public boolean isAllowSwimming() {
        return allowSwimming;
    }

    public void setAllowSwimming(boolean allowSwimming) {
        this.allowSwimming = allowSwimming;
    }

    public boolean isAllowTreatment() {return allowTreatment;}

    public void setAllowTreatment(boolean allowTreatment) {this.allowTreatment = allowTreatment; }
}
