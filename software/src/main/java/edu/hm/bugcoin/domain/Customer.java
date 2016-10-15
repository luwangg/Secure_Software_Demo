package edu.hm.bugcoin.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
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

    @Column(nullable = false, unique = true)
    @NaturalId
    private String nickname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordhash;

    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------
    public Customer(String lastname, String firstname, String street, String postcode, String city, String email, String passwordhash) {
        super();
        this.lastname = lastname;
        this.firstname = firstname;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.passwordhash = passwordhash;
    }

    protected Customer() {
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

    public String nickname() {
        return nickname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
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


    public String getPasswordhash() {
        return passwordhash;
    }


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
                ", firstname='" + firstname + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordhash + '\'' +
                '}';
    }
}
