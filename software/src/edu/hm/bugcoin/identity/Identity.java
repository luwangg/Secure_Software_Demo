package edu.hm.bugcoin.identity;
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

    @NotNull
    @Size(min=8, max=30)
    private String username;

    @NotNull
    @Size(min=8, max=30)
    private String password;

    @NotNull
    private String otpKey;


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


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------


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
