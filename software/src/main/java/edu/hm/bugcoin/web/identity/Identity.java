package edu.hm.bugcoin.web.identity;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 */
public class Identity
{

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    private String username;
    private String password;
    private String otpKey;

    private String firstname;
    private String lastname;


    // ----------------------------------------------------------------------------------
    //  Konstruktoren
    // ----------------------------------------------------------------------------------


    public Identity()
    {
    }

    public Identity(String username, String password, String otpKey)
    {
        this.username = username;
        this.password = password;
        this.otpKey = otpKey;
    }


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getOtpKey()
    {
        return otpKey;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------


    public Identity setUsername(String username)
    {
        this.username = username;
        return this;
    }

    public Identity setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public Identity setOtpKey(String otpKey)
    {
        this.otpKey = otpKey;
        return this;
    }

    public Identity setFirstname(String firstname)
    {
        this.firstname = firstname;
        return this;
    }

    public Identity setLastname(String lastname)
    {
        this.lastname = lastname;
        return this;
    }


    // ----------------------------------------------------------------------------------
    //  Auskunftsmethoden
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Aendernde Methoden
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Standardmethoden
    // ----------------------------------------------------------------------------------


    @Override
    public String toString()
    {
        return "Identity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", otpKey='" + otpKey + '\'' +
                '}';
    }
}
