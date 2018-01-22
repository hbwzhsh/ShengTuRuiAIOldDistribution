package com.utility;

/**
 * 
 * @author weiTaZhuang
 * @date 2016年6月7日 下午3:02:49
 * @Description CRC8相关计算
 */

public class CRC8 {
	static byte[] crc8_tab = { 0x00, 0x07, 0x0E, 0x09, 0x1C, 0x1B, 0x12, 0x15, 0x38, 0x3F, 0x36, 0x31, 0x24, 0x23,
			0x2A, 0x2D, 0x70, 0x77, 0x7E, 0x79, 0x6C, 0x6B, 0x62, 0x65, 0x48, 0x4F, 0x46, 0x41, 0x54, 0x53, 0x5A, 0x5D,
			(byte) 0xE0, (byte) 0xE7, (byte) 0xEE, (byte) 0xE9, (byte) 0xFC, (byte) 0xFB, (byte) 0xF2, (byte) 0xF5,
			(byte) 0xD8, (byte) 0xDF, (byte) 0xD6, (byte) 0xD1, (byte) 0xC4, (byte) 0xC3, (byte) 0xCA, (byte) 0xCD,
			(byte) 0x90, (byte) 0x97, (byte) 0x9E, (byte) 0x99, (byte) 0x8C, (byte) 0x8B, (byte) 0x82, (byte) 0x85,
			(byte) 0xA8, (byte) 0xAF, (byte) 0xA6, (byte) 0xA1, (byte) 0xB4, (byte) 0xB3, (byte) 0xBA, (byte) 0xBD,
			(byte) 0xC7, (byte) 0xC0, (byte) 0xC9, (byte) 0xCE, (byte) 0xDB, (byte) 0xDC, (byte) 0xD5, (byte) 0xD2,
			(byte) 0xFF, (byte) 0xF8, (byte) 0xF1, (byte) 0xF6, (byte) 0xE3, (byte) 0xE4, (byte) 0xED, (byte) 0xEA,
			(byte) 0xB7, (byte) 0xB0, (byte) 0xB9, (byte) 0xBE, (byte) 0xAB, (byte) 0xAC, (byte) 0xA5, (byte) 0xA2,
			(byte) 0x8F, (byte) 0x88, (byte) 0x81, (byte) 0x86, (byte) 0x93, (byte) 0x94, (byte) 0x9D, (byte) 0x9A,
			0x27, 0x20, 0x29, 0x2E, 0x3B, 0x3C, 0x35, 0x32, 0x1F, 0x18, 0x11, 0x16, 0x03, 0x04, 0x0D, 0x0A, 0x57, 0x50,
			0x59, 0x5E, 0x4B, 0x4C, 0x45, 0x42, 0x6F, 0x68, 0x61, 0x66, 0x73, 0x74, 0x7D, 0x7A, (byte) 0x89,
			(byte) 0x8E, (byte) 0x87, (byte) 0x80, (byte) 0x95, (byte) 0x92, (byte) 0x9B, (byte) 0x9C, (byte) 0xB1,
			(byte) 0xB6, (byte) 0xBF, (byte) 0xB8, (byte) 0xAD, (byte) 0xAA, (byte) 0xA3, (byte) 0xA4, (byte) 0xF9,
			(byte) 0xFE, (byte) 0xF7, (byte) 0xF0, (byte) 0xE5, (byte) 0xE2, (byte) 0xEB, (byte) 0xEC, (byte) 0xC1,
			(byte) 0xC6, (byte) 0xCF, (byte) 0xC8, (byte) 0xDD, (byte) 0xDA, (byte) 0xD3, (byte) 0xD4, 0x69, 0x6E,
			0x67, 0x60, 0x75, 0x72, 0x7B, 0x7C, 0x51, 0x56, 0x5F, 0x58, 0x4D, 0x4A, 0x43, 0x44, 0x19, 0x1E, 0x17, 0x10,
			0x05, 0x02, 0x0B, 0x0C, 0x21, 0x26, 0x2F, 0x28, 0x3D, 0x3A, 0x33, 0x34, 0x4E, 0x49, 0x40, 0x47, 0x52, 0x55,
			0x5C, 0x5B, 0x76, 0x71, 0x78, 0x7F, 0x6A, 0x6D, 0x64, 0x63, 0x3E, 0x39, 0x30, 0x37, 0x22, 0x25, 0x2C, 0x2B,
			0x06, 0x01, 0x08, 0x0F, 0x1A, 0x1D, 0x14, 0x13, (byte) 0xAE, (byte) 0xA9, (byte) 0xA0, (byte) 0xA7,
			(byte) 0xB2, (byte) 0xB5, (byte) 0xBC, (byte) 0xBB, (byte) 0x96, (byte) 0x91, (byte) 0x98, (byte) 0x9F,
			(byte) 0x8A, (byte) 0x8D, (byte) 0x84, (byte) 0x83, (byte) 0xDE, (byte) 0xD9, (byte) 0xD0, (byte) 0xD7,
			(byte) 0xC2, (byte) 0xC5, (byte) 0xCC, (byte) 0xCB, (byte) 0xE6, (byte) 0xE1, (byte) 0xE8, (byte) 0xEF,
			(byte) 0xFA, (byte) 0xFD, (byte) 0xF4, (byte) 0xF3 };

	public static String calcCrc8(byte[] data) {
		byte crc = calcCrc8(data, 0, data.length, (byte) 0);
		if ((0x00ff & crc) < 16) {
			return "0" + Integer.toHexString(0x00ff & crc).toUpperCase();
		}
		return Integer.toHexString(0x00ff & crc).toUpperCase();
	}

	public static byte calcCrc8_2(byte[] data) {
		byte crc = calcCrc8(data, 0, data.length, (byte) 0);
		return crc;
	}

	public static byte calcCrc8(byte[] data, int offset, int len) {
		return calcCrc8(data, offset, len, (byte) 0);
	}

	public static byte calcCrc8(byte[] data, int offset, int len, byte preval) {
		byte ret = preval;
		for (int i = offset; i < (offset + len); ++i) {
			ret = crc8_tab[(0x00ff & (ret ^ data[i]))];
		}
		return ret;
	}

	public static void main(String[] args) {
		// 0x13 0x31 0x32 0x33 0x34 0x35 0x36 0x37 0x38 0x39 0x61 0x62 0x63 0x40
		// 0x71 0x71 0x2E 0x63 0x6F 0x6D //=0a
		// byte[] ptr = new byte[] { 0x13, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
		// 0x37, 0x38, 0x39, 0x61, 0x62, 0x63, 0x40, 0x71, 0x71, 0x2E, 0x63,
		// 0x6F, 0x6D };
		byte[] ptr = new byte[] { 0x44, 0x45, 0x56, 0x2D, 0x49, 0x4E, 0x53, 0x45, 0x52, 0x54, 0x3A, 0x66, 0x65, 0x30,
				0x30, 0x31, 0x65, 0x30, 0x37, 0x30, 0x30, 0x34, 0x62, 0x31, 0x32, 0x30, 0x0A };// =9b

		System.out.println("CRC8 = " + calcCrc8(ptr));
	}
}
