package com.zhl.job.util;

import java.security.MessageDigest;




public class MD5 {  
	public static String encrypt(String srcMessage,String key) {
		try {
			if (key == null) {
				return null;
			}
			char[] keyChar = key.toCharArray();
			if (keyChar.length != 16) {
				return null;
			}
			byte[] bytes = srcMessage.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			bytes = md.digest();
			int j = bytes.length;
			char[] chars = new char[j * 2];
			int k = 0;
			for (int i = 0; i < bytes.length; i++) {
				byte b = bytes[i];
				chars[k++] = keyChar[b >>> 4 & 0xf];
				chars[k++] = keyChar[b & 0xf];
			}

			return new String(chars);
		} catch (Exception e) {
			return null;
		}
	}
	public static String encrypt(String srcMessage) {
		try {
			byte[] bytes = srcMessage.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			bytes = md.digest();
			StringBuffer hexValue = new StringBuffer(); 
	        for (int i = 0; i < bytes.length; i++){  
	            int val = bytes[i] & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val)); 
	        }  
	        return hexValue.toString();  
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
	}
}