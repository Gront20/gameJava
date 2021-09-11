/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Artem
 */
public class HMACkey {
    private static final String HMAC_ALGO = "HmacSHA256";
    public static byte []bytes = new byte[16];
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length*2);
        for(byte b: bytes) {
           sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public static String generateKey() throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return "HMAC Key: " + bytesToHex(bytes);
    } 
    
    public static String HMAC(String turn) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException{  
        Mac signer = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(bytes, HMAC_ALGO);
        signer.init(keySpec);
        
        String messageStr = turn;
        byte[] digest = signer.doFinal(messageStr.getBytes("utf-8"));
        
        return "HMAC: " + bytesToHex(digest);
    }
}
