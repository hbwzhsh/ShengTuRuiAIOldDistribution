package com.utility;


import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author weiTaZhuang
 * @date 2016��6��7�� ����10:00:32
 * @Description AES���ܽ����㷨
 */

public class AesUtil  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752558110445292179L;
	
	public static final String Key = "0123456789abcdef";// ��Կ
	public static final String IV = "0123456789abcdef";// ��Կƫ����,��ʼiv

	/** �㷨/ģʽ/��� **/
	private static final String CipherMode = "AES/CBC/NoPadding";

	
	private Cipher en = null, de = null;

	
	
	public Cipher getEn() {
		return en;
	}

	public void setEn(Cipher en) {
		this.en = en;
	}

	public Cipher getDe() {
		return de;
	}

	public void setDe(Cipher de) {
		this.de = de;
	}

	public AesUtil() {
		init();
	}

	public void init() {
		try {
			if (en == null || de == null) {
				en = Cipher.getInstance(CipherMode);
				en.init(Cipher.ENCRYPT_MODE, createKey(Key), createIV(IV));
				 
				de = Cipher.getInstance(CipherMode);
				de.init(Cipher.DECRYPT_MODE, createKey(Key), createIV(IV));
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}

	/** ����16����byte �������� �� **/
	public byte[] encrypt(byte[] content) {
		try {
			byte[] input = PKCS5padding(content);
			byte[] result = en.update(input);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** ����(���Ϊ16�����ַ���) ������ �� **/
	public String encrypt(String content) {
		byte[] data = null;
		try {
			data = content.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = encrypt(data);
		String result = ToHexUtil.byte2HexStr(data, data.length);
		return result;
	}

	/** �����ֽ����� ȫ��Ϊ16����byte �� **/
	public byte[] decrypt(byte[] content) {
		try {
			byte[] result = de.update(content);
			return PKCS5UnPadding(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** ���� �����ĵ� �� **/
	public String decrypt(String content) {
		byte[] data = null;
		try {
			data = ToHexUtil.hexStr2Bytes(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = decrypt(data);
		if (data == null)
			return null;
		String result = null;
		try {
//			MLog.e("data1="+ToHexUtil.byte2HexStr(data, data.length));
			
			result = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	private byte[] PKCS5padding(byte[] in) {
		int len = in.length;
		if (len % 16 > 0)
			len += (16 - len % 16);
		else
			len += 16;
		byte[] out = new byte[len];
		for (int i = 0; i < len; i++) {
			if (i < in.length)
				out[i] = in[i];
			else
				out[i] = (byte) (len - in.length);
		}
		return out;
	}

	private byte[] PKCS5UnPadding(byte[] in) {
		if (in.length <= 0 || in.length % 16 != 0)
			return new byte[0];
		byte padding = in[in.length - 1];
		if (in.length - padding <1) 
			return new byte[0];
		 
		byte[] out = new byte[in.length - padding];
		for (int i = 0; i < in.length; i++) {
			if (i < out.length)
				out[i] = in[i];
			else if (in[i] != padding)
				return new byte[0];
		}
		return out;
	}

	/** ������Կ **/
	private SecretKeySpec createKey(String key) {
		byte[] data = null;
		if (key == null) {
			key = "";
		}
		StringBuffer sb = new StringBuffer(16);
		sb.append(key);
		while (sb.length() < 16) {
			sb.append("0");
		}
		if (sb.length() > 16) {
			sb.setLength(16);
		}

		try {
			data = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new SecretKeySpec(data, "AES");
	}

// -------------------�ָ��ߣ����治Ҫ--------------------------------------------------------------------------------------------------------------------------------------------

	private IvParameterSpec createIV(String password) {
		byte[] data = null;
		if (password == null) {
			password = "";
		}
		StringBuffer sb = new StringBuffer(16);
		sb.append(password);
		while (sb.length() < 16) {
			sb.append("0");
		}
		if (sb.length() > 16) {
			sb.setLength(16);
		}

		try {
			data = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new IvParameterSpec(data);
	}

	/** �����ֽ����� �������� **/
	public byte[] encrypt(byte[] content, String password, String ivString) {
		try {
			SecretKeySpec key = createKey(password);
			Cipher cipher = Cipher.getInstance(CipherMode);
			if (iv == null) {
				iv = createIV(ivString);
			}
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** ����(���Ϊ16�����ַ���) ������ **/
	public String encrypt(String content, String password, String ivString) {
		byte[] data = null;
		try {
			data = content.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		data = encrypt(data, password, ivString);
		String result = ToHexUtil.byte2HexStr(data, data.length);
		return result;
	}

	IvParameterSpec iv = null;

	/** �����ֽ����� ȫ��Ϊ16�����ַ��� **/
	public byte[] decrypt(byte[] content, String password, String ivString) {
		try {
			SecretKeySpec key = createKey(password);
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.DECRYPT_MODE, key, createIV(ivString));
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** ���� �����ĵ� **/
	public String decrypt(String content, String password, String ivString) {
		byte[] data = null;
		try {
			data = ToHexUtil.hexStr2Bytes(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = decrypt(data, password, ivString);
		if (data == null)
			return null;
		String result = null;
		try {
			result = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
