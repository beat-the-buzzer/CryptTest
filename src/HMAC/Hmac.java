package HMAC;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

public class Hmac {
	private static String src ="Hello World!"; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkHMACMD5(src);
		bcHMACMD5(src);
	}
	
	public static void jdkHMACMD5(String str){
		try {
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] key = secretKey.getEncoded();
			
			SecretKey restoreSecretKey = new SecretKeySpec(key, "HmacMD5");
			Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
			mac.init(restoreSecretKey);
			byte[] hmacMD5Bytes = mac.doFinal(str.getBytes());
			System.out.println("jdk hmacMD5:" + Hex.encodeHexString(hmacMD5Bytes));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bcHMACMD5(String str){
		HMac hmac = new HMac(new MD5Digest());
		hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaaaaa")));
		hmac.update(src.getBytes(), 0, str.getBytes().length);
		byte[] hmacMD5Bytes = new byte[hmac.getMacSize()];
		hmac.doFinal(hmacMD5Bytes, 0);
		System.out.println("bc hmacMD5:" + org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
	}

}
