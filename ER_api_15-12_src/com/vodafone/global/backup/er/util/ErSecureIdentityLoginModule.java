package com.vodafone.global.er.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.jboss.resource.security.ConfiguredIdentityLoginModule;

import com.vodafone.config.ConfigProvider;

public class ErSecureIdentityLoginModule extends ConfiguredIdentityLoginModule
{

	public static final String CONFIG_USE_BASE64_CONVERTER_ENABLE_FLAG = "DB.LOGIN.MODULE.BASE64.CONVERTER.ENABLED";

	private static final Logger log = Logger.getLogger(ErSecureIdentityLoginModule.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(Subject subject, CallbackHandler handler, Map sharedState, Map options)
	{
		try {
			Map<String, String> newOptions = new HashMap<String, String>(options);
			String pw = newOptions.get("password");
			boolean useBase64 = ConfigProvider.getPropertyAsBoolean(ErSecureIdentityLoginModule.CONFIG_USE_BASE64_CONVERTER_ENABLE_FLAG, false);
			pw = ErSecureIdentityLoginModule.decrypt(pw, useBase64);
			newOptions.put("password", pw);
			newOptions.put("userName", newOptions.get("username"));
			newOptions.put("principal", newOptions.get("username"));
			newOptions = Collections.unmodifiableMap(newOptions);
			super.initialize(subject, handler, sharedState, newOptions);
		} catch (Exception e) {
			log.warn("Failed to decode password", e);
		}
	}

	private static String encrypt(String secret, boolean useBase64)	throws NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException
			{
		byte[] kbytes = "h90h ui tb3 w!3".getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encoding = cipher.doFinal(secret.getBytes());

		String result = null;
		if(!useBase64){
			System.out.println("Not using Base64");
			BigInteger n = new BigInteger(encoding);
			result = n.toString(16);
		}else{
			System.out.println("Using Base64");
			result = new String(Base64.encodeBase64(encoding));
		}
		return result;
	}

	/**
	 * Decrypts a password.  Will use base64 if DB.LOGIN.MODULE.BASE64.CONVERTER.ENABLED is set to true
	 * @param secret - String to be encrypted
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decrypt(String secret)
	throws NoSuchPaddingException, NoSuchAlgorithmException,
	InvalidKeyException, BadPaddingException, IllegalBlockSizeException
	{
		boolean useBase64 = ConfigProvider.getPropertyAsBoolean(ErSecureIdentityLoginModule.CONFIG_USE_BASE64_CONVERTER_ENABLE_FLAG, false);
		return decrypt(secret, useBase64);
	}

	/**
	 * 
	 * @param secret - String to be encrypted
	 * @param useBase64
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decrypt(String secret, boolean useBase64)
			throws NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException
			{
		byte[] kbytes = "h90h ui tb3 w!3".getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

		byte[] encoding = null;

		if(!useBase64){
			log.debug("Not Using Base64");
			BigInteger n = new BigInteger(secret, 16);
			encoding = n.toByteArray();
		}else{
			log.debug("Using Base64");
			encoding =  Base64.decodeBase64(secret.getBytes());
		}

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decode = cipher.doFinal(encoding);
		//return new String(decode).toCharArray();
		return new String(decode);
			}

	/** Main entry point to encrypt a password using the hard-coded pass phrase 
	 * 
	 * @param args[3] = [{boolean enable base64 converter}, {operation: encrypt or decrypt}, {the password to encode}]
	 * @throws Exception
	 */ 
	public static void main(String[] args) throws Exception
	{

		if(args == null || args.length != 3){ 
			for (String arg: args){
				System.out.print(arg+" ");
			}
			System.out.println("usage: ErSecureIdentityLoginModule [{boolean enable base64 converter}, {operation: encrypt or decrypt}, {the password to encode}]");
		}else{

			boolean useBase64Converter =  Boolean.parseBoolean(args[0]); 

			String operation = args[1];	//ie encrypt or decrypt

			String pw = args[2];

			System.out.println(operation + " password: " + pw);

			if(operation != null && operation.equals("encrypt")){
				System.out.println("Encoded password: "+encrypt(pw, useBase64Converter));
			}	else if(operation != null && operation.equals("decrypt")){
				System.out.println("Decoded password: "+decrypt(pw, useBase64Converter));
			}	else	{
				System.out.println("unknown operation ["+operation+"]");
			}
		}
	}

}
