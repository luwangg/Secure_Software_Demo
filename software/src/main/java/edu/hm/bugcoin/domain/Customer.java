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
    private String password;

    @Column(nullable = false, name = "otpkey")
    private String otpKey;

    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------

    public Customer() { }


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getNickname() {
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


    public String getPassword() {
        return password;
    }

    public String getOtpKey()
    {
        return otpKey;
    }


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------

    public Customer setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Customer setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public Customer setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Customer setStreet(String street) {
        this.street = street;
        return this;
    }

    public Customer setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public Customer setCity(String city) {
        this.city = city;
        return this;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer setPassword(String passwordHash) {
        this.password = passwordHash;
        return this;
    }

    public Customer setOtpKey(String otpKey)
    {
        this.otpKey = otpKey;
        return this;
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
                ", passwordHash='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        return nickname != null ? nickname.equals(customer.nickname) : customer.nickname == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }
}
