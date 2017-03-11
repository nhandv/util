package com.nyan.util.cryptUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class CryptDES {
	private static final String ALGO = "DES";
	private static final String CIPHER_ALGO = "DES/ECB/PKCS5Padding";
	private static CryptDES instance;

	private CryptDES() {

	}

	public synchronized static CryptDES getInstance() {
		if (instance == null) {
			instance = new CryptDES();
		}
		return instance;
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private SecretKey getDESKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance(ALGO);
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException("Error get key des");
		}
		return keyGenerator.generateKey();
	}

	/**
	 * 
	 * @param mode
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 */
	private Cipher getCipher(int mode) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(CIPHER_ALGO);
			cipher.init(mode, getDESKey());
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException(e.getMessage());
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException(" Error invalid key");
		} catch (NoSuchPaddingException e) {
			throw new NoSuchPaddingException("Error Padding cipher");
		}
		return cipher;
	}

	public byte[] enCrypt(String toEncrypt) throws IllegalArgumentException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher;
		byte[] rs = null;
		if (toEncrypt == null) {
			throw new IllegalArgumentException("string input is null");
		} else {
			try {
				byte[] bInput = toEncrypt.getBytes("UTF-8");
				cipher = getCipher(Cipher.ENCRYPT_MODE);
				rs = cipher.doFinal(bInput);
			} catch (InvalidKeyException e) {
				throw new InvalidKeyException(e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				throw new NoSuchAlgorithmException(e.getMessage());
			} catch (NoSuchPaddingException e) {
				throw new NoSuchPaddingException(e.getMessage());
			} catch (IllegalBlockSizeException e) {
				throw new IllegalBlockSizeException(" Error block size in encrypt");
			} catch (BadPaddingException e) {
				throw new BadPaddingException(" Error bad padding in encrypt");
			}
		}
		return rs;
	}

	public String deCrypt(byte[] bDecrypt) throws IllegalArgumentException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher;
		byte[] rs = null;
		if (bDecrypt == null) {
			throw new IllegalArgumentException("byte input is null");
		} else {
			try {
				cipher = getCipher(Cipher.DECRYPT_MODE);
				rs = cipher.doFinal(bDecrypt);
			} catch (InvalidKeyException e) {
				throw new InvalidKeyException(e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				throw new NoSuchAlgorithmException(e.getMessage());
			} catch (NoSuchPaddingException e) {
				throw new NoSuchPaddingException(e.getMessage());
			} catch (IllegalBlockSizeException e) {
				throw new IllegalBlockSizeException(" Error block size in decrypt");
			} catch (BadPaddingException e) {
				throw new BadPaddingException(" Error bad padding in decrypt");
			}
		}
		return rs != null? new String(rs): null;
	}
}
