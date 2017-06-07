package SHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Sha {
	private static String src = "CCNU";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkSHA1(src);
		jdkSHA512(src);
	}
	
	public static void jdkSHA1(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(str.getBytes());
			System.out.println("jdk SHA1:" + Hex.encodeHexString(md.digest()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void jdkSHA512(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(str.getBytes());
			System.out.println("jdk SHA512:" + Hex.encodeHexString(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
