package com.nyan.util.cryptUtil;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.nyan.util.FileContext;

/**
 * 
 * @author DUONG_VAN_NHAN
 *
 */
public class CryptAES {
	private static final String SHA ="sha-1";
	private static final String ALGO ="AES";
	private static final String CIPHER_ALGO="AES/CBC/PKCS5PADDING";
	private static CryptAES instance;
	private static FileContext fContext;
	private static SecretKeySpec secretKey;
	private CryptAES(){
		fContext = FileContext.getInstance();
		
	}
	
	public synchronized static CryptAES getInstance(){
		if(instance == null){
			instance = new CryptAES();
		}
		return instance;
	}
	
	/**
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	private static SecretKeySpec getSecretKey() throws IllegalArgumentException, IOException, NoSuchAlgorithmException{
		MessageDigest messDiget = null;
		if(secretKey == null){
			try{
				String key = fContext.getKey(FileContext.KEY_AES) != null ?fContext.getKey(FileContext.KEY_AES) :"nyan123qwe!@#";
				byte[] b = key.getBytes("UTF-8");
				messDiget = MessageDigest.getInstance(SHA);
				b = messDiget.digest(b); // bam key theo sha-1
				b = Arrays.copyOf(b, 16); // 16 bit
				secretKey = new SecretKeySpec(b, ALGO);
			}catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}catch (IOException e) {
				throw new IOException(e.getMessage());
			}catch(NoSuchAlgorithmException e){
				throw new NoSuchAlgorithmException("Error algorithm in get Secretkey");
			}
			
		}
		return secretKey;
	}
	
	/**
	 * 
	 * @param mode
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	private static Cipher getCipher(int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalArgumentException, IOException{
		Cipher cipher;
		try{
			cipher = Cipher.getInstance(CIPHER_ALGO);
			cipher.init(mode, getSecretKey());
		}catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException("Error Algorithm");
		}catch (NoSuchPaddingException e) {
			throw new NoSuchPaddingException("Error Padding");
		}catch(InvalidKeyException e){
			throw new InvalidKeyException("Error Invalid key");
		}catch (IOException e) {
			throw new IOException(e.getMessage());
		}catch(IllegalArgumentException e){
			throw new NoSuchAlgorithmException(e.getMessage());
		}
		return cipher;
	}
	
	
	public String enCrypt(String toEncrypt) throws IllegalArgumentException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException{
		String rs = null;
		if(toEncrypt == null){
			throw new IllegalArgumentException("string input is null");
		}else{
			try{
				Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
				if(cipher != null){
					byte[] bInput = toEncrypt.getBytes("UTF-8");
					byte[] bEncrypt = cipher.doFinal(bInput);
					rs = Base64.getEncoder().encodeToString(bEncrypt);
				}
			}catch (NoSuchAlgorithmException e) {
				throw new NoSuchAlgorithmException(e.getMessage());
			}catch (NoSuchPaddingException e) {
				throw new NoSuchPaddingException(e.getMessage());
			}catch(InvalidKeyException e){
				throw new InvalidKeyException(e.getMessage());
			}catch (IOException e) {
				throw new IOException(e.getMessage());
			}catch (IllegalBlockSizeException e) {
				throw new IllegalBlockSizeException("Error Block size");
			}catch (BadPaddingException e) {
				throw new BadPaddingException("Error Bad Padding");
			}
			
		}
		return rs;
	}
	
	public String deEcrypt(String toDecrypt)throws IllegalArgumentException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException{
		String rs = null;
		if(toDecrypt == null){
			throw new IllegalArgumentException("string input is null");
		}else{
			try{
				Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
				if(cipher != null){
					byte[] bInput = toDecrypt.getBytes("UTF-8");
					bInput = Base64.getDecoder().decode(bInput);
					byte[] bDecrypt = cipher.doFinal(bInput);
					rs = bDecrypt != null? new String(bDecrypt): null;
				}
			}catch (NoSuchAlgorithmException e) {
				throw new NoSuchAlgorithmException(e.getMessage());
			}catch (NoSuchPaddingException e) {
				throw new NoSuchPaddingException(e.getMessage());
			}catch(InvalidKeyException e){
				throw new InvalidKeyException(e.getMessage());
			}catch (IOException e) {
				throw new IOException(e.getMessage());
			}catch (IllegalBlockSizeException e) {
				throw new IllegalBlockSizeException("Error Block size");
			}catch (BadPaddingException e) {
				throw new BadPaddingException("Error Bad Padding");
			}
			
		}
		return rs;
	}
}
