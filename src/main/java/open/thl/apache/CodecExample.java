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
 * apache 加密解密转码操作示例
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
     * 密钥算法 
     * java支持56位密钥，bouncycastle支持64位 
     * */  
    public static final String KEY_ALGORITHM="DES";  
    /** 
     * 加密/解密算法/工作模式/填充方式 
     * */  
    public static final String CIPHER_ALGORITHM="DES/ECB/PKCS5Padding";  
      
    /** 
     *  
     * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥 
     * @return byte[] 二进制密钥 
     * */  
    public static byte[] initkey() throws Exception{  
        //实例化密钥生成器  
        KeyGenerator kg=KeyGenerator.getInstance(KEY_ALGORITHM);  
        //初始化密钥生成器  
        kg.init(56);  
        //生成密钥  
        SecretKey secretKey=kg.generateKey();  
        //获取二进制密钥编码形式  
        System.out.println("key bytes="+secretKey.getEncoded());
        System.out.println("key md5="+md5(secretKey.getEncoded().toString()));
        return secretKey.getEncoded();  
    }  
    /** 
     * 转换密钥 
     * @param key 二进制密钥 
     * @return Key 密钥 
     * */  
    public static Key toKey(byte[] key) throws Exception{  
        //实例化Des密钥  
        DESKeySpec dks=new DESKeySpec(key);  
        //实例化密钥工厂  
        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(KEY_ALGORITHM);  
        //生成密钥  
        SecretKey secretKey=keyFactory.generateSecret(dks);  
        return secretKey;  
    }  
      
    /** 
     * 加密数据 
     * @param data 待加密数据 
     * @param key 密钥 
     * @return byte[] 加密后的数据 
     * */  
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{  
        //还原密钥  
        Key k=toKey(key);  
        //实例化  
        Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);  
        //初始化，设置为加密模式  
        cipher.init(Cipher.ENCRYPT_MODE, k);  
        //执行操作  
        return cipher.doFinal(data);  
    }  
    /** 
     * 解密数据 
     * @param data 待解密数据 
     * @param key 密钥 
     * @return byte[] 解密后的数据 
     * */  
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{  
        //欢迎密钥  
        Key k =toKey(key);  
        //实例化  
        Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);  
        //初始化，设置为解密模式  
        cipher.init(Cipher.DECRYPT_MODE, k);  
        //执行操作  
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
			String content="史蒂夫史蒂夫,xx";  
	        System.out.println("加密内容："+content);  
	        //des 密钥至少8位  
	        String keyString="fefba2aec68f8a0be3eec851405f5a02";
	        //密钥转字节数组
	        byte[] key=keyString.getBytes();
	        System.out.println("密钥："+key);  
	        //加密===============================================
	        //先加密
	        byte[] data=encrypt(content.getBytes(), key);
	        System.out.println("加密后data："+data);  
	        //再base64转码
	        String dataStr=Base64.encodeBase64String(data);
	        System.out.println("传输base64转码之后数据："+dataStr);  
	        
	        //解密===============================================
	        //先base64解码  
	        byte[] resultData=Base64.decodeBase64(dataStr);
	        System.out.println("解码之后resultData："+resultData);  
	        //再解密
	        byte[] xc=decrypt(resultData, key);
	        System.out.println("解密后xc："+xc);  
	        System.out.println("解密后："+new String(xc));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
