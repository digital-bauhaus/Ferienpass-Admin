package de.jonashackt.springbootvuejs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import java.util.Date;

@Entity
public class Project {

    // PrimaryKey
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long project_id;
    private String name;
    private Date date;
    private int age;
    private int price;
    private int slots;
    private int slotsReserved;
    private String weblink;

    protected Project() {}

    public Project(String name, Date date, int age, int price, int slots, int slotsReserved, String weblink) {
        this.name = name;
        this.date = date;
        this.age = age;
        this.price = price;
        this.slots = slots;
        this.slotsReserved = slotsReserved;
        this.weblink = weblink;
    }

    @Override
    public String toString() {
        return String.format(
                "Project[project_id=%d, name='%s', date='%tD', age='%d', price='%d' slots='%d', slotsReserved='%d', weblink='%s']",
                project_id, name, date, age, price, slots, slotsReserved, weblink);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
