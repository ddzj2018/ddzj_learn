package open.thl.apache;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * apache 加密解密转码操作示例
 * 
 * @author zhouchangwei
 *
 */
public class CodecExample {
	private static String UTF8="UTF-8";
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
	 * base64转码
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Encode(String data) throws UnsupportedEncodingException {
		byte[] encodeByte=Base64.encodeBase64(data.getBytes(UTF8));
		return new String(encodeByte,UTF8);
	}
	/**
	 * base64解码
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Decode(String data) throws UnsupportedEncodingException {
		byte[] decodeByte=Base64.decodeBase64(data.getBytes(UTF8));
		return new String(decodeByte,UTF8);
	}
	/**
	 * md5加密
	 * @param data
	 * @return
	 */
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}
	/**
	 * sha1加密
	 * @param data
	 * @return
	 */
	public static String sha1(String data) {
		return DigestUtils.sha1Hex(data);
	}
	/**
	 * sha256加密
	 * @param data
	 * @return
	 */
	public static String sha256Hex(String data) {
		return DigestUtils.sha256Hex(data);
	}
	
	
      
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
    public static byte[] encryptDes(byte[] data,byte[] key) throws Exception{  
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
    public static byte[] decryptDes(byte[] data,byte[] key) throws Exception{  
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
		try {
			byte[] keyBytes=initkey();
			System.out.println(keyBytes);
			System.out.println(new String(keyBytes,UTF8));
			String content="史蒂夫史蒂夫,xx";  
	        System.out.println("加密内容："+content);  
	        //des 密钥至少8位  
	        String keyString="fefba2aec68f8a0be3eec851405f5a02";
	        //密钥转字节数组
	        byte[] key=keyString.getBytes();
	        System.out.println("密钥："+key);  
	        //加密===============================================
	        //先加密
	        byte[] data=encryptDes(content.getBytes(), key);
	        System.out.println("加密后data："+data);  
	        //再base64转码
	        String dataStr=Base64.encodeBase64String(data);
	        System.out.println("传输base64转码之后数据："+dataStr);  
	        
	        //解密===============================================
	        //先base64解码  
	        byte[] resultData=Base64.decodeBase64(dataStr);
	        System.out.println("解码之后resultData："+resultData);  
	        //再解密
	        byte[] xc=encryptDes(resultData, key);
	        System.out.println("解密后xc："+xc);  
	        System.out.println("解密后："+new String(xc));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
