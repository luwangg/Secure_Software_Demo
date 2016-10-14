package edu.hm.bugcoin.web.auth;
/*
 * Projekt: bugcoin
 * Autor: Maximilian Pachl
 * 2016-10-14 14:53
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectAttr
{
    String session();
    String model();
}
