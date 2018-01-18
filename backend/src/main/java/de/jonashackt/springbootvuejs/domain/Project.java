package de.jonashackt.springbootvuejs.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {

    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long project_id;
    private String name;
    private String date;
    private int age;
    private int price;
    private int slots;
    private int slotsFree;
    private int slotsReserved;
    private String weblink;
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="project_user",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="id")
    )
    private List<User> users = new ArrayList<>();

    protected Project() {}

    public Project(String name, String date, int age, int price, int slots, int slotsReserved, String weblink, List<User> users) {
        this.name = name;
        this.date = date;
        this.age = age;
        this.price = price;
        this.slots = slots;
        this.slotsFree = users.size();
        this.slotsReserved = slotsReserved;
        this.weblink = weblink;
        this.users = users;
    }

    @Override
    public String toString() {
        return String.format(
                "Project[project_id=%d, name='%s', date='%s', age='%d', price='%d' slots='%d', slotsFree='%d', slotsReserved='%d', weblink='%s']",
                project_id, name, date, age, price, slots, slotsFree, slotsReserved, weblink);
    }


    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public int getSlotsReserved() {
        return slotsReserved;
    }

    public void setSlotsReserved(int slotsReserved) {
        this.slotsReserved = slotsReserved;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public int getSlotsFree() {
        return slotsFree;
    }

    public void setSlotsFree(int slotsFree) {
        this.slotsFree = slotsFree;
    }
}
