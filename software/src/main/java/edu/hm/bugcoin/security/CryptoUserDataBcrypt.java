package edu.hm.bugcoin.security;


import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by shreaker on 17.10.16.
 */
public class CryptoUserDataBcrypt implements CryptoUserData {


    @Override
    public boolean verifyUserData(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }

    @Override
    public String hashUserData(String plaintext, String salt) {
        return BCrypt.hashpw(plaintext, salt);
    }

    @Override
    public String generateSalt(){
        return BCrypt.gensalt();
    }

}



