package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
@RestController
class ServerController{
    @RequestMapping("/hash")
    public String myHash(){
    	String data = "Hello World Check Sum!";
    	//Initialize message digest and checksum variables
    	MessageDigest md = null;
    	String checkSum = null;
    	
    	try { //Create the Message Digest object with SHA-256 hash
    		md = MessageDigest.getInstance("SHA-256");
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	
    	//Pass the data to the Message Digest object
    	md.update(data.getBytes());
    	
    	//Compute the message digest
    	byte[] digest = md.digest();
    	
    	//Convert byte array to hex string
    	checkSum = this.bytesToHex(digest);
       
        return "<p>Checksum Verification by Cielo Neal.<br>Inputted text: " +data+ "<br>Output: " +checkSum+ "</p>";
    }
    
    //Function to convert bytes to hex
    public String bytesToHex(byte[] bytes) {
    	StringBuilder result = new StringBuilder();
    	
    	for (byte i : bytes) {
    		int decimal = (int)i & 0XFF;
    		String hex = Integer.toHexString(decimal);
    		
    		if (hex.length() % 2 == 1) {
    			hex = "0" +hex;
    		}
    		
    		result.append(hex);
    	}
    	return result.toString();
    }
}