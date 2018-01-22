package com.utility;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesUtilTwo {

	private final static String aeskey ="0123456789abcdef";
	private final static String iv ="0000000000000000";


	byte[] key;
	IvParameterSpec enc_iv, dec_iv;

	public AesUtilTwo() {
		this.key = aeskey.getBytes();
		enc_iv = new IvParameterSpec(iv.getBytes());
		dec_iv = new IvParameterSpec(iv.getBytes());
	}


	public synchronized String encode(String s) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(this.key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key, enc_iv);
			byte[] encrypted = cipher.doFinal(s.getBytes());
			byte[] iv = new byte[16];
			System.arraycopy(encrypted, encrypted.length - 16, iv, 0, 16);
			enc_iv = new IvParameterSpec(iv);

			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public  synchronized String decode(String s) {
		try {
			byte[] encrypted = Base64.getDecoder().decode(s);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(this.key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, key, dec_iv);
			byte[] result = cipher.doFinal(encrypted);
			byte[] iv = new byte[16];
			System.arraycopy(encrypted, encrypted.length - 16, iv, 0, 16);
			dec_iv = new IvParameterSpec(iv);
			return new String(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
