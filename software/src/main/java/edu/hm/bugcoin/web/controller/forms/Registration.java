package edu.hm.bugcoin.web.controller.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Projekt: bugcoin
 * Autor: Maximilian Pachl
 * 2016-10-14 13:14
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

public class Registration
{

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @NotNull
    @Size(min=3, max=30)
    private String firstname;

    @NotNull
    @Size(min=3, max=30)
    private String lastname;

    @NotNull
    @Size(min=3, max=30)
    private String street;

    @NotNull
    @Size(min=5, max=5)
    @Pattern(regexp = "[0-9]*")
    private String postalcode;

    @NotNull
    @Size(min=3, max=30)
    private String city;

    @NotNull
    @Size(min=3, max=30)
    //@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull
    @Size(min=3, max=30)
    private String username;

    @NotNull
    @Size(min=3, max=30)
    private String password;

    @NotNull
    @Size(min=3, max=30)
    private String password2;


    // ----------------------------------------------------------------------------------
    //  Konstruktoren
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getStreet()
    {
        return street;
    }

    public String getPostalcode()
    {
        return postalcode;
    }

    public String getCity()
    {
        return city;
    }

    public String getEmail()
    {
        return email;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPassword2()
    {
        return password2;
    }


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setPostalcode(String postalcode)
    {
        this.postalcode = postalcode;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setPassword2(String password2)
    {
        this.password2 = password2;
    }


    // ----------------------------------------------------------------------------------
    //  Auskunftsmethoden
    // ----------------------------------------------------------------------------------

    public boolean validate()
    {
        return getPassword().equals(getPassword2());
    }


    // ----------------------------------------------------------------------------------
    //  Aendernde Methoden
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Standardmethoden
    // ----------------------------------------------------------------------------------


    @Override
    public String toString()
    {
        return "Registration{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", street='" + street + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
