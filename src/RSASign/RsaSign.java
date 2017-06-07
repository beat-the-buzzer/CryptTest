package RSASign;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;

public class RsaSign {
	private static String src ="Hello World!"; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkRSA(src);
	}
	
	public static void jdkRSA(String str){
		try {
			//
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey =(RSAPublicKey)keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
			
			//2
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initSign(privateKey);
			signature.update(str.getBytes());
			byte[] result = signature.sign();
			System.out.println("jdk RSA sign:" + Hex.encodeHexString(result));
			
			//3
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(publicKey);
			signature.update(str.getBytes());
			boolean bool= signature.verify(result);
			System.out.println("jdk RSA verify:" + bool);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
