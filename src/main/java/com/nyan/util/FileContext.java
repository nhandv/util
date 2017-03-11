package com.nyan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author DUONG_VAN_NHAN
 *
 */
public class FileContext {
	private static final String PATH_FILE = "../etc/config.log";
	public static final String KEY_AES ="key_aes";
	public static final String KEY_DES="key_des";
	public static final String KEY_PUBLIC_RAS ="key_public_rsa";
	public static final String KEY_PRIVATE_RAS="key_private_rsa";
	private Properties ps;
	private static FileContext instance;
	
	private FileContext() {
		ps = new Properties();
	}
	
	public synchronized static FileContext getInstance(){
		if(instance == null){
			instance = new FileContext();
		}
		return instance;
	}
	
	private boolean getConfigucation() throws IOException{
		boolean rs = false;
		try{
			if(!ps.isEmpty())
				rs = true;
			else{
				File f = new File(PATH_FILE);
				if(f.exists()){
					InputStream inStream = new FileInputStream(f);
					ps.load(inStream);
					rs = true;
				}else{
					rs = false;
				}
			}
			
		}catch (IOException e) {
			throw new IOException("Error IO file configucation");
		}
		return rs;
	}
	
	/**
	 * 
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public String getKey(String k) throws IllegalArgumentException, IOException{
		String rs = null;
		if(k == null){
			throw new IllegalArgumentException("input is null");
		}else{
			try {
				boolean bl = getConfigucation();
				if(bl){
					rs = ps.getProperty(k);
				}
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			}
		}
		return rs;
	}
	
	
}
