package edu.hm.bugcoin.identity;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

/**
 *
 */
public interface IdentityProvider
{
    Identity resolve(final String username);
}
