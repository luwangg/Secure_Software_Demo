package edu.hm.bugcoin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by shreaker on 14.10.16.
 */
@Entity
public class Customer implements Serializable {


    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordhash;

    protected Customer() {
    }

    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------
    public Customer(String lastname, String name, String street, String postcode, String city, String email, String passwordhash) {
        super();
        this.lastname = lastname;
        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.passwordhash = passwordhash;
    }

    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }


    public String getStreet() {
        return street;
    }


    public String getPostcode() {
        return postcode;
    }


    public String getCity() {
        return city;
    }


    public String getEmail() {
        return email;
    }


    public String getPasswordHash() {
        return passwordhash;
    }


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordhash = passwordHash;
    }

    // ----------------------------------------------------------------------------------
    //  Override
    // ----------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordhash + '\'' +
                '}';
    }
}
