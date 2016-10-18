package edu.hm.bugcoin.security;

/**
 * Created by shreaker on 18.10.16.
 */
public interface CryptoUserData {


    public boolean verifyUserData(String plaintext, String hashed);

    public String hashUserData(String plaintext, String salt);

    public String generateSalt();

}
