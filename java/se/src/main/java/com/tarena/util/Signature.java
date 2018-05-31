package com.tarena.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by ant_shake_tree on 16/7/28.
 */
public class Signature {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	/**
	 * Computes
	 * * @param data
	 * The data to be signed.
	 *
	 * @param key The signing key.
	 * @return The Base64-encoded RFC 2104-compliant HMAC signature.
	 * @throws java.security.SignatureException when signature generation fails
	 */
	public static String calculateRFCHMAC(String key, String data) {
		String result;
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
			result = Base64.getEncoder().encodeToString(rawHmac);
		} catch (Exception e) {
			throw new RuntimeException("Failed to generate HMAC : " + e.getMessage());
		}
		return result;
	}

	public static String calculateRFCHMAC_Hex(String key, String data) {
		String result;
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
			result = Hex.encodeHexString(rawHmac);
			result = Base64.getEncoder().encodeToString(result.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Failed to generate HMAC : " + e.getMessage());
		}
		return result;
	}
}
