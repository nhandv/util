package com.nyan.util.cryptUtil;

import java.security.MessageDigest;

public class CryptMD5 {
	private static final String ALGO ="md5";
	private static CryptMD5 instance;
	private CryptMD5(){
		
	}
	public synchronized static CryptMD5 getInstance(){
		if(instance == null){
			instance = new CryptMD5();
		}
		return instance;
	}
	
	public final String getMd5(final String toEncrypt) throws IllegalArgumentException, Exception {
		final StringBuilder sb = new StringBuilder("");
		if(toEncrypt == null){
			throw new IllegalArgumentException(" string input is null");
		}else{
			try{
				final MessageDigest messDigest = MessageDigest.getInstance(ALGO);
				messDigest.update(toEncrypt.getBytes());
				byte[] b = messDigest.digest();
				for(int i =0; i < b.length; i++){
					sb.append(String.format("%02X", b[i]));
				}
			}catch (Exception e) {
				throw new Exception("Error encrypt md5");
			}
		}
		
		return sb.toString();
	}
}
