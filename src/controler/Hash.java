/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sarra
 */
public class Hash {
    
    public Hash(){
    
            String d = "sq";
    }
    
    
    int sha1(String stringToEncrypt) {
        int strlen =  stringToEncrypt.length();
        int hash = 7;
        for (int i = 0; i < strlen; i++) {
            hash = hash*31 + stringToEncrypt.charAt(i);
        }
        
        return hash;
    }
    
    
    
    
}
