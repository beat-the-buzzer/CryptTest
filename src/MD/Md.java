package MD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

public class Md {
	private static String src = "CCNU";
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		long begintime = System.nanoTime();
		jdkMD5(src);
		long endtime = System.nanoTime();
		long costTime = endtime - begintime;
		System.out.println("MD5 time:" + costTime+" nm");
			
		jdkMD2(src);
		
		bcMD4(src);	
		bcMD5(src);
		
		ccMD2(src);
		ccMD5(src);
	}
	
	//加密函数的实现
	public static void jdkMD5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md.digest(str.getBytes());
			System.out.println("JDK MD5:" + Hex.encodeHexString(md5Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void jdkMD2(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD2");
			byte[] md2Bytes = md.digest(str.getBytes());
			System.out.println("JDK MD2:" + Hex.encodeHexString(md2Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bcMD4(String str){
		Digest digest = new MD4Digest();
		digest.update(src.getBytes(), 0, str.getBytes().length);
		byte[] MD4Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(MD4Bytes, 0);
		System.out.println("bcMD4:" + org.bouncycastle.util.encoders.Hex.toHexString(MD4Bytes));
	}
	
	public static void bcMD5(String str){
		Digest digest = new MD5Digest();
		digest.update(src.getBytes(), 0, str.getBytes().length);
		byte[] MD5Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(MD5Bytes, 0);
		System.out.println("bcMD5:" + org.bouncycastle.util.encoders.Hex.toHexString(MD5Bytes));
	}
	
	public static void ccMD5(String str){
		System.out.println("ccMD5:" + DigestUtils.md5Hex(str.getBytes()));
	}
	
	public static void ccMD2(String str){
		System.out.println("ccMD2:" + DigestUtils.md2Hex(str.getBytes()));
	}	
}
