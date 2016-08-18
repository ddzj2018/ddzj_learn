package open.thl.apache;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * apache ���ܽ���ת�����ʾ��
 * 
 * @author zhouchangwei
 *
 */
public class CodecExample {

	public static String base64Encode(String data) {

		return Base64.encodeBase64String(data.getBytes());
	}

	public static byte[] base64Decode(String data) {

		return Base64.decodeBase64(data.getBytes());
	}

	public static String md5(String data) {

		return DigestUtils.md5Hex(data);
	}

	public static String sha1(String data) {

		return DigestUtils.sha1Hex(data);
	}

	public static String sha256Hex(String data) {

		return DigestUtils.sha256Hex(data);
	}
	

	
	
	
	
	/** 
     * ��Կ�㷨 
     * java֧��56λ��Կ��bouncycastle֧��64λ 
     * */  
    public static final String KEY_ALGORITHM="DES";  
    /** 
     * ����/�����㷨/����ģʽ/��䷽ʽ 
     * */  
    public static final String CIPHER_ALGORITHM="DES/ECB/PKCS5Padding";  
      
    /** 
     *  
     * ������Կ��java6ֻ֧��56λ��Կ��bouncycastle֧��64λ��Կ 
     * @return byte[] ��������Կ 
     * */  
    public static byte[] initkey() throws Exception{  
        //ʵ������Կ������  
        KeyGenerator kg=KeyGenerator.getInstance(KEY_ALGORITHM);  
        //��ʼ����Կ������  
        kg.init(56);  
        //������Կ  
        SecretKey secretKey=kg.generateKey();  
        //��ȡ��������Կ������ʽ  
        System.out.println("key bytes="+secretKey.getEncoded());
        System.out.println("key md5="+md5(secretKey.getEncoded().toString()));
        return secretKey.getEncoded();  
    }  
    /** 
     * ת����Կ 
     * @param key ��������Կ 
     * @return Key ��Կ 
     * */  
    public static Key toKey(byte[] key) throws Exception{  
        //ʵ����Des��Կ  
        DESKeySpec dks=new DESKeySpec(key);  
        //ʵ������Կ����  
        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(KEY_ALGORITHM);  
        //������Կ  
        SecretKey secretKey=keyFactory.generateSecret(dks);  
        return secretKey;  
    }  
      
    /** 
     * �������� 
     * @param data ���������� 
     * @param key ��Կ 
     * @return byte[] ���ܺ������ 
     * */  
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{  
        //��ԭ��Կ  
        Key k=toKey(key);  
        //ʵ����  
        Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);  
        //��ʼ��������Ϊ����ģʽ  
        cipher.init(Cipher.ENCRYPT_MODE, k);  
        //ִ�в���  
        return cipher.doFinal(data);  
    }  
    /** 
     * �������� 
     * @param data ���������� 
     * @param key ��Կ 
     * @return byte[] ���ܺ������ 
     * */  
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{  
        //��ӭ��Կ  
        Key k =toKey(key);  
        //ʵ����  
        Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);  
        //��ʼ��������Ϊ����ģʽ  
        cipher.init(Cipher.DECRYPT_MODE, k);  
        //ִ�в���  
        return cipher.doFinal(data);  
    } 
    
   
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String base64 = base64Encode("ricky");
		System.out.println("base64 encode=" + base64);

		byte[] buf = base64Decode(base64);
		System.out.println("base64 decode=" + new String(buf));

		String md5 = md5("ricky");
		System.out.println("md5=" + md5 + "**len=" + md5.length());

		String sha1 = sha1("test");
		System.out.println("sha1=" + sha1 + "**len=" + sha1.length());

		String sha256 = sha256Hex("test");
		System.out.println("sha256=" + sha256 + "**len=" + sha256.length());
		
		
		try {
			String content="ʷ�ٷ�ʷ�ٷ�,xx";  
	        System.out.println("�������ݣ�"+content);  
	        //des ��Կ����8λ  
	        String keyString="fefba2aec68f8a0be3eec851405f5a02";
	        //��Կת�ֽ�����
	        byte[] key=keyString.getBytes();
	        System.out.println("��Կ��"+key);  
	        //����===============================================
	        //�ȼ���
	        byte[] data=encrypt(content.getBytes(), key);
	        System.out.println("���ܺ�data��"+data);  
	        //��base64ת��
	        String dataStr=Base64.encodeBase64String(data);
	        System.out.println("����base64ת��֮�����ݣ�"+dataStr);  
	        
	        //����===============================================
	        //��base64����  
	        byte[] resultData=Base64.decodeBase64(dataStr);
	        System.out.println("����֮��resultData��"+resultData);  
	        //�ٽ���
	        byte[] xc=decrypt(resultData, key);
	        System.out.println("���ܺ�xc��"+xc);  
	        System.out.println("���ܺ�"+new String(xc));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
