package edu.hm.bugcoin;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.identity.DummyIdentityProvider;
import edu.hm.bugcoin.identity.IdentityProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class Application
{

    public static IdentityProvider identityProvider;

    // ----------------------------------------------------------------------------------
    //  application entry
    // ----------------------------------------------------------------------------------

    public static void main(String[] args)
    {
        identityProvider = new DummyIdentityProvider();

        SpringApplication.run(Application.class, args);
    }

}
