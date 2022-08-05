package com.nisum.security;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 
 * @author yadicksonvasquez@gmail.com
 *
 */
public class GenerateKeyUtil {

	public static String generateSafeToken() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
										// the 256 required bits
		random.nextBytes(bytes);
		Encoder encoder = Base64.getUrlEncoder().withoutPadding();
		return encoder.encodeToString(bytes);
	}

	public static void main(String[] args) {
		try {
			// create new key		
			System.out.print("encodedKey:" + generateSafeToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
