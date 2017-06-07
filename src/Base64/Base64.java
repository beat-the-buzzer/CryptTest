package Base64;

public class Base64 {
	private static String src = "hello World!";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CCBase64(src);
		BCBase64(src);
	}
	
	public static void CCBase64(String str){
		byte[] encodeBytes = org.apache.commons.codec.binary.Base64.encodeBase64(str.getBytes());
		System.out.println("encode:" + new String(encodeBytes));
		byte[] decodeBytes = org.apache.commons.codec.binary.Base64.decodeBase64(encodeBytes);
		System.out.println("decode:" + new String(decodeBytes));
	}
	
	public static void BCBase64(String str){
		byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(str.getBytes());
		System.out.println("encode:" + new String(encodeBytes));
		byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		System.out.println("decode:" + new String(decodeBytes));
	}
}
