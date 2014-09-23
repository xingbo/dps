package com.dps.common.util;

import java.security.MessageDigest;

public class MD5EnDeCryptUtil {
	private static char hexDigits[] = { '1', '9', '8', '4', '4', '5', '6', '7', '8', '9','w', 'e', 'i', 'n', 'i', 'n', 'g' };
	  
	public final static String MD5(String s) {
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	 public static String enDeCrypt(String inStr) {   
		  // String s = new String(inStr);   
		  char[] a = inStr.toCharArray();   
		  for (int i = 0; i < a.length; i++) {   
		   a[i] = (char) (a[i] ^ 't'^ 'z'^ 'q'&'f');   
		  }   
		  String s = new String(a);   
		  return s;   
		 }    
	 
	 public static void main(String args[]){
		 String a= "weining";
		 System.out.println(MD5EnDeCryptUtil.MD5(a));
		 System.out.println(MD5EnDeCryptUtil.enDeCrypt(a));
		 System.out.println(MD5EnDeCryptUtil.enDeCrypt(MD5EnDeCryptUtil.enDeCrypt(a)));
		 System.out.println(MD5EnDeCryptUtil.enDeCrypt(MD5EnDeCryptUtil.MD5(a)).equalsIgnoreCase(MD5EnDeCryptUtil.enDeCrypt(MD5EnDeCryptUtil.MD5(a))));
	 }
}
