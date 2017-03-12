package com.nyan.util.stringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ConvertByte {

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	public static String getHexString(byte[] b) throws IllegalArgumentException {
		if (b == null) {
			throw new IllegalArgumentException(" b is null");
		} else {
			char[] c = new char[b.length * 2];
			for (int i = 0; i < b.length; i++) {
				c[i * 2] = HEX_CHARS[(b[i] & 0xFF) >>> 4];
				c[i * 2 + 1] = HEX_CHARS[(b[i] & 0xFF) & 0x0F];

			}
			return new String(c);
		}
	}

	public static byte[] hexStringToByteArray(String sInHex) throws IllegalArgumentException {
		if (sInHex == null) {
			throw new IllegalArgumentException("sInHex is null");
		} else {
			byte[] data = new byte[sInHex.length() / 2];
			for (int i = 0; i < sInHex.length(); i += 2) {
		        data[i / 2] = (byte) ((Character.digit(sInHex.charAt(i), 16) << 4)
                        + Character.digit(sInHex.charAt(i+1), 16));
			}
			return data;
		}
	}

	public static byte[] stringToByteArray(String s, String format) throws IllegalArgumentException {
		if (s == null) {
			throw new IllegalArgumentException("s is null");
		} else {
			if (format != null && !"".equals(format))
				return s.getBytes(Charset.forName(format));
			else
				return s.getBytes(Charset.forName("ASCII"));
		}
	}

	public static String byteArrayToString(byte[] b, String format)
			throws IllegalArgumentException, UnsupportedEncodingException {
		if (b == null) {
			throw new IllegalArgumentException("b is null");
		} else {
			if (format != null && !"".equals(format))
				return new String(b, format);
			else {
				return new String(b, "ASCII");
			}
		}
	}
}
