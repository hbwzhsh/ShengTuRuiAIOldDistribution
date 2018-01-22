package com.utility;

import java.util.Locale;

/**
 * 
 * @author weiTaZhuang
 * @date 2016��6��7�� ����10:02:28
 * @Description 16����ת��
 */

public class ToHexUtil {
	private final static char[] mChars = "0123456789ABCDEF".toCharArray();
	private final static String mHexStr = "0123456789ABCDEF";

	
	/**
	 * ��16�����ַ���ת�����ֽ�����
	 * @param hexString
	 * @return byte[]
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	
	
	/**
	 * ���16�����ַ����Ƿ���Ч
	 * @param sHex String 16�����ַ���
	 * @return boolean
	 */
	public static boolean checkHexStr(String sHex) {
		String sTmp = sHex.toString().trim().replace(" ", "").toUpperCase(Locale.US);
		int iLen = sTmp.length();

		if (iLen > 1 && iLen % 2 == 0) {
			for (int i = 0; i < iLen; i++)
				if (!mHexStr.contains(sTmp.substring(i, i + 1)))
					return false;
			return true;
		} else
			return false;
	}

	/**
	 * �ַ���ת����int
	 */
	public static int strHexInt(String str) {
		return Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16);
	}

	/**
	 * �ַ���ת����ʮ�������ַ���
	 * 
	 * @param str
	 *            String ��ת����ASCII�ַ���
	 * @return String ÿ��Byte֮��ո�ָ�����: [61 6C 6B]
	 */
	public static String str2HexStr(String str) {
		StringBuilder sb = new StringBuilder();
		byte[] bs = str.getBytes();

		for (int i = 0; i < bs.length; i++) {
			sb.append(mChars[(bs[i] & 0xFF) >> 4]);
			sb.append(mChars[bs[i] & 0x0F]);
			// sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * ʮ�������ַ���ת���� ASCII�ַ���
	 * 
	 * @param str
	 *            String Byte�ַ���
	 * @return String ��Ӧ���ַ���
	 */
	public static String hexStr2Str(String hexStr) {
		hexStr = hexStr.toString().trim().replace(" ", "").toUpperCase(Locale.US);
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int iTmp = 0x00;

		for (int i = 0; i < bytes.length; i++) {
			iTmp = mHexStr.indexOf(hexs[2 * i]) << 4;
			iTmp |= mHexStr.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (iTmp & 0xFF);
		}
		return new String(bytes);
	}

	public static String asciiToString(String value) {
		StringBuffer sbu = new StringBuffer();
		String[] chars = value.split(",");
		for (int i = 0; i < chars.length; i++) {
			sbu.append((char) Integer.parseInt(chars[i]));
		}
		return sbu.toString();
	}

	/**
	 * bytesת����ʮ�������ַ���
	 * 
	 * @param b
	 *            byte[] byte����
	 * @param iLen
	 *            int ȡǰNλ���� N=iLen
	 * @return String ÿ��Byteֵ֮��ո�ָ�
	 */
	public static String byte2HexStr(byte[] b, int iLen) {
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < iLen; n++) {
			sb.append(mChars[(b[n] & 0xFF) >> 4]);
			sb.append(mChars[b[n] & 0x0F]);
			// sb.append(' ');
		}
		return sb.toString().trim().toUpperCase(Locale.US);
	}

	
	/**
	 * bytes�ַ���ת��ΪByteֵ
	 * 
	 * @param src
	 *            String Byte�ַ�����ÿ��Byte֮��û�зָ���(�ַ���Χ:0-9 A-F)
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		/* ������ֵ���й淶������ */
		src = src.trim().replace(" ", "").toUpperCase(Locale.US);
		// ����ֵ��ʼ��
		int m = 0, n = 0;
		int iLen = src.length() / 2; // ���㳤��
		byte[] ret = new byte[iLen]; // ����洢�ռ�

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	/**
	 * String���ַ���ת����unicode��String
	 * 
	 * @param strText
	 *            String ȫ���ַ���
	 * @return String ÿ��unicode֮���޷ָ���
	 * @throws Exception
	 */
	public static String strToUnicode(String strText) throws Exception {
		char c;
		StringBuilder str = new StringBuilder();
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc).toUpperCase();
			if (intAsc > 128)
				str.append("\\u");
			else // ��λ��ǰ�油00
				str.append("\\u00");
			str.append(strHex);
		}
		return str.toString();
	}

	

	private static int toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * unicode��Stringת����String���ַ���
	 * 
	 * @param hex
	 *            String 16����ֵ�ַ��� ��һ��unicodeΪ2byte��
	 * @return String ȫ���ַ���
	 * @see CHexConver.unicodeToString("\\u0068\\u0065\\u006c\\u006c\\u006f")
	 */
	public static String unicodeToString(String hex) {
		int t = hex.length() / 6;
		int iTmp = 0;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// ��16���Ƶ�stringתΪint
			iTmp = (Integer.valueOf(s.substring(2, 4), 16) << 8) | Integer.valueOf(s.substring(4), 16);
			// ��intת��Ϊ�ַ�
			str.append(new String(Character.toChars(iTmp)));
		}
		return str.toString();
	}

	/**���ַ��� ת����ascii���ַ���*/
	public static String parseAscii(String str) {
		StringBuilder sb = new StringBuilder();
		byte[] bs = str.getBytes();
		for (int i = 0; i < bs.length; i++)
			sb.append(toHex(bs[i]));
		return sb.toString();
	}

	
	/**
	 *  16����String ǰ��0
	 *  @param str   16����String
	 *  @param size   �̶�λ��
	 *  @param isprefixed   true��ǰ��0��false�Ⱥ�0
	 * @return
	 */
	public static String pad (String str ,int size ,boolean isprefixed) {
        if (str == null)
            str = "";
        int str_size = str.length();
        int pad_len = size - str_size;
        StringBuffer retvalue = new StringBuffer();
        for (int i = 0; i < pad_len; i++) {
            retvalue.append("0");
        }
        if (isprefixed) 
            return retvalue.append(str).toString();
        return retvalue.insert(0, str).toString();
    }
	
	
	/**
	 *  10 ����int ת16����String
	 * @param n  10 ����int
	 */
	public static String toHex(int n) {
		StringBuilder sb = new StringBuilder();
		if (n / 16 == 0) {
			return toHexUtil(n);
		} else {
			String t = toHex(n / 16);
			int nn = n % 16;
			sb.append(t).append(toHexUtil(nn));
		}
		return sb.toString();
	}

	private static String toHexUtil(int n) {
		String rt = "";
		switch (n) {
		case 10:
			rt += "A";
			break;
		case 11:
			rt += "B";
			break;
		case 12:
			rt += "C";
			break;
		case 13:
			rt += "D";
			break;
		case 14:
			rt += "E";
			break;
		case 15:
			rt += "F";
			break;
		default:
			rt += n;
		}
		return rt;
	}
	
	
	public static int char2ASCII(char c) {   
        return (int) c;   
    }
	
	/** �ַ���ת��Ϊ ʮ������ ASCII��   */
	public static String string2ASCII(String s) {
	        if (s == null || "".equals(s)) {   
	            return null;   
	        }   
	        char[] chars = s.toCharArray();   
	        int[] asciiArray = new int[chars.length];   
	      
	        for (int i = 0; i < chars.length; i++) {   
	            asciiArray[i] = char2ASCII(chars[i]);   
	        } 
	        //------------��ʮ����ת����ʮ������
	        String hexArray = "";
	    	for(int i=0;i<asciiArray.length;i++){
	    		hexArray = hexArray+Integer.toHexString(asciiArray[i]).toUpperCase();
	    	}
	    	return hexArray; 
	}
	
	
    //����16���ƶ�Ӧ����ֵ  
    public static int GetHex(char ch) throws Exception{  
        if ( ch>='0' && ch<='9' )  
            return (int)(ch-'0');  
        if ( ch>='a' && ch<='f' )  
            return (int)(ch-'a'+10);  
        if ( ch>='A' && ch<='F' )  
            return (int)(ch-'A'+10);  
        throw new Exception("error param");  
    }  
  //������  
    public static int GetPower(int nValue, int nCount) throws Exception{  
        if ( nCount <0 )  
            throw new Exception("nCount can't small than 1!");  
        if ( nCount == 0 )  
            return 1;  
        int nSum = 1;  
        for ( int i=0; i<nCount; ++i ){  
            nSum = nSum*nValue;  
        }  
        return nSum;  
    }  
    
  //�ж��Ƿ���16������  
    public static boolean IsHex(String strHex){  
    int i = 0;  
    if ( strHex.length() > 2 ){  
    if ( strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x') ){  
    i = 2;  
    }  
    }  
    for ( ; i<strHex.length(); ++i ){  
    char ch = strHex.charAt(i);  
    if ( (ch>='0' && ch<='9') || (ch>='A' && ch<='F') || (ch>='a' && ch<='f') )  
    continue;  
    return false;  
    }  
    return true;  
    } 
    
    
  //ʮ����  
    public static boolean isOctNumber(String str) {  
        boolean flag = false;  
        for(int i=0,n=str.length();i<n;i++){  
            char c = str.charAt(i);  
            if(c=='0'|c=='1'|c=='2'|c=='3'|c=='4'|c=='5'|c=='6'|c=='7'|c=='8'|c=='9'){  
                flag =true;  
            }  
        }  
        return flag;  
    }  
    
  //16����ת10����  
    public static int HexToInt(String strHex){  
        int nResult = 0;  
        if ( !IsHex(strHex) )  
            return nResult;  
        String str = strHex.toUpperCase();  
        if ( str.length() > 2 ){  
            if ( str.charAt(0) == '0' && str.charAt(1) == 'X' ){  
                str = str.substring(2);  
            }  
        }  
        int nLen = str.length();  
        for ( int i=0; i<nLen; ++i ){  
            char ch = str.charAt(nLen-i-1);  
            try {  
                nResult += (GetHex(ch)*GetPower(16, i));  
            } catch (Exception e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return nResult;  
    }  
    
    
}
