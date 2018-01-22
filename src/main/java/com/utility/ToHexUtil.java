package com.utility;

import java.util.Locale;

/**
 * 
 * @author weiTaZhuang
 * @date 2016年6月7日 上午10:02:28
 * @Description 16进制转换
 */

public class ToHexUtil {
	private final static char[] mChars = "0123456789ABCDEF".toCharArray();
	private final static String mHexStr = "0123456789ABCDEF";

	
	/**
	 * 把16进制字符串转换成字节数组
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
	 * 检查16进制字符串是否有效
	 * @param sHex String 16进制字符串
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
	 * 字符串转换成int
	 */
	public static int strHexInt(String str) {
		return Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16);
	}

	/**
	 * 字符串转换成十六进制字符串
	 * 
	 * @param str
	 *            String 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
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
	 * 十六进制字符串转换成 ASCII字符串
	 * 
	 * @param str
	 *            String Byte字符串
	 * @return String 对应的字符串
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
	 * bytes转换成十六进制字符串
	 * 
	 * @param b
	 *            byte[] byte数组
	 * @param iLen
	 *            int 取前N位处理 N=iLen
	 * @return String 每个Byte值之间空格分隔
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
	 * bytes字符串转换为Byte值
	 * 
	 * @param src
	 *            String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		/* 对输入值进行规范化整理 */
		src = src.trim().replace(" ", "").toUpperCase(Locale.US);
		// 处理值初始化
		int m = 0, n = 0;
		int iLen = src.length() / 2; // 计算长度
		byte[] ret = new byte[iLen]; // 分配存储空间

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	/**
	 * String的字符串转换成unicode的String
	 * 
	 * @param strText
	 *            String 全角字符串
	 * @return String 每个unicode之间无分隔符
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
			else // 低位在前面补00
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
	 * unicode的String转换成String的字符串
	 * 
	 * @param hex
	 *            String 16进制值字符串 （一个unicode为2byte）
	 * @return String 全角字符串
	 * @see CHexConver.unicodeToString("\\u0068\\u0065\\u006c\\u006c\\u006f")
	 */
	public static String unicodeToString(String hex) {
		int t = hex.length() / 6;
		int iTmp = 0;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 将16进制的string转为int
			iTmp = (Integer.valueOf(s.substring(2, 4), 16) << 8) | Integer.valueOf(s.substring(4), 16);
			// 将int转换为字符
			str.append(new String(Character.toChars(iTmp)));
		}
		return str.toString();
	}

	/**把字符串 转化成ascii码字符串*/
	public static String parseAscii(String str) {
		StringBuilder sb = new StringBuilder();
		byte[] bs = str.getBytes();
		for (int i = 0; i < bs.length; i++)
			sb.append(toHex(bs[i]));
		return sb.toString();
	}

	
	/**
	 *  16进制String 前后补0
	 *  @param str   16进制String
	 *  @param size   固定位数
	 *  @param isprefixed   true向前补0，false先后补0
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
	 *  10 进制int 转16进制String
	 * @param n  10 进制int
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
	
	/** 字符串转换为 十六进制 ASCII码   */
	public static String string2ASCII(String s) {
	        if (s == null || "".equals(s)) {   
	            return null;   
	        }   
	        char[] chars = s.toCharArray();   
	        int[] asciiArray = new int[chars.length];   
	      
	        for (int i = 0; i < chars.length; i++) {   
	            asciiArray[i] = char2ASCII(chars[i]);   
	        } 
	        //------------将十进制转换成十六进制
	        String hexArray = "";
	    	for(int i=0;i<asciiArray.length;i++){
	    		hexArray = hexArray+Integer.toHexString(asciiArray[i]).toUpperCase();
	    	}
	    	return hexArray; 
	}
	
	
    //计算16进制对应的数值  
    public static int GetHex(char ch) throws Exception{  
        if ( ch>='0' && ch<='9' )  
            return (int)(ch-'0');  
        if ( ch>='a' && ch<='f' )  
            return (int)(ch-'a'+10);  
        if ( ch>='A' && ch<='F' )  
            return (int)(ch-'A'+10);  
        throw new Exception("error param");  
    }  
  //计算幂  
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
    
  //判断是否是16进制数  
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
    
    
  //十进制  
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
    
  //16进制转10进制  
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
