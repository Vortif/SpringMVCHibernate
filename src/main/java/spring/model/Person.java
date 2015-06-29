package spring.model;

import javax.persistence.*;

/**
 * Created by jkorzeni on 2015-06-26.
 */

@Entity
@Table(name = "persontable")
public class Person {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "person_seq", sequenceName = "persontable")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "id= " + id + " name= " + name + ", country= " + country;
    }
}
