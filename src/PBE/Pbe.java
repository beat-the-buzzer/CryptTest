package PBE;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class Pbe {
	private static String src ="Hello World!"; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkPBE(src);
	}
	
	public static void jdkPBE(String str){
		try {
			
			SecureRandom random = new SecureRandom();
			byte[] salt = random.generateSeed(8);
			
			 
			String password = "123456";
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key = factory.generateSecret(pbeKeySpec);
			
			 
			PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("jdk PBE encrypt:" + Base64.encodeBase64String(result));
			
			 
			cipher.init(Cipher.DECRYPT_MODE, key,pbeParameterSpec);
			result = cipher.doFinal(result);
			System.out.println("jdk PBE decrypt:" + new String(result));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
