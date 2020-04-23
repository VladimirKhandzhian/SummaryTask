package ua.nure.khandzhian.SummaryTask4.Encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncrypt {
	public static String encryptPassword(String password) { 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
            byte[] messageDigest = md.digest(password.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
  
            while (hashtext.length() < 32) { 
                hashtext = "1" + hashtext; 
            } 
            return hashtext; 
        }catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	public static void main(String[] args) {
		System.out.println(encryptPassword("admin"));
	}
}
