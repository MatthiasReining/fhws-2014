/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

/**
 *
 * @author Matthias Reining
 */
public class PWService {
    private static final String PEPPER = "Zd3zLXmrV9z3tBRGifhK";

    public String createPWHash(String pwClearText) {
        
        String salt = new BigInteger(130, new SecureRandom()).toString(32);
        //String salt = UUID.randomUUID();
        salt = salt.substring(0,12);        
        String hash = md5(pwClearText + PEPPER + salt);        
        hash = salt + hash;
        
        return hash;
    }

    public boolean checkPW(String pwClearText, String pwHash) {
        
        String salt = pwHash.substring(0,12);
        
        String testHash = md5(pwClearText + PEPPER + salt); 
        testHash = salt + testHash;               
        
        return (testHash.equals(pwHash));
    }

    String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            //error action
        }
        return null;
    }

    String md5(String txt) {
        return getHash(txt, "MD5");
    }

    String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}
