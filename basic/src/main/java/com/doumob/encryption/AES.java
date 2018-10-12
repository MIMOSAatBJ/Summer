package com.doumob.encryption;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.doumob.runtime.Config;

/**
 * AES加密算法的java实现
 * 
 * @author zh
 *
 */
public class AES {
	private static Logger logger = Logger.getLogger(AES.class);
	private static final String GENERATOR = "AES";
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";// 默认的加密算法

	/**
	 * @DESC 对content内容进行加密，密钥为
	 * @param content
	 * @param secretkey
	 * @return 加密后的byte数组
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version 1.0
	 */
	public static byte[] encrypt(String content, String secretkey) {
		try {
			// KeyGenerator提供（对称）密钥生成器的功能。使用getInstance类方法构造密钥生成器。
			KeyGenerator kgen = KeyGenerator.getInstance(GENERATOR);
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(secretkey.getBytes());
			// 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
			kgen.init(128, sr);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			// 使用SecretKeySpec类来根据一个字节数组构造一个 SecretKey,，而无须通过一个（基于
			// provider的）SecretKeyFactory.
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, GENERATOR);
			// 创建密码器 //为创建 Cipher对象，应用程序调用 Cipher 的 getInstance 方法并将所请求转换
			// 的名称传递给它。还可以指定提供者的名称（可选）。
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			byte[] byteContent = content.getBytes(Config.getEncoding());
			// 初始化
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此 Cipher 的初始化方式）。
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName());
		}
		// catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// logger.error(e.getClass().getSimpleName());
		// } catch (NoSuchPaddingException e) {
		// e.printStackTrace();
		// } catch (InvalidKeyException e) {
		// e.printStackTrace();
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// } catch (IllegalBlockSizeException e) {
		// e.printStackTrace();
		// } catch (BadPaddingException e) {
		// e.printStackTrace();
		// }
		return null;
	}

	/**
	 * @DESC 对content内容进行解密密，密钥为secretkey
	 * @param content
	 * @param secretkey
	 * @return
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version
	 */
	public static byte[] decrypt(byte[] content, String secretkey) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(GENERATOR);
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(secretkey.getBytes());
			kgen.init(128, sr);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, GENERATOR);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName());
		}
		// catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// } catch (NoSuchPaddingException e) {
		// e.printStackTrace();
		// } catch (InvalidKeyException e) {
		// e.printStackTrace();
		// } catch (IllegalBlockSizeException e) {
		// e.printStackTrace();
		// } catch (BadPaddingException e) {
		// e.printStackTrace();
		// }
		return null;
	}
}
