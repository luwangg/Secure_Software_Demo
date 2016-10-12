package edu.hm.bugcoin;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import org.jboss.aerogear.security.otp.api.Base32;


/**
 *
 */
public class TokenGen
{

    public static String getQrCode(String user, String host, String secret) {
        final String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
        return String.format(format, user, host, secret);
    }

    public static void main(String[] args)
    {
        final String secret = Base32.random();
        System.out.println(secret);
        System.out.println(getQrCode("maxi", "bugcoin", secret));
    }

}
