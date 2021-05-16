package com.atriviss.raritycheck.config.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class SecurityCipher {
    private static final String keyStr = "x/A?D(G+KbPdSgVk";
    private static SecretKeySpec secretKeySpec;
    private static byte[] key;

    private SecurityCipher() {
        throw new AssertionError("Static!");
    }

    public static void setKey() {
        MessageDigest sha;
        try {
            key = keyStr.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt) {
        if (strToEncrypt == null)
            return null;

        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encodedWithCipher = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            String encodedWithBase64 = Base64.getEncoder().encodeToString(encodedWithCipher);

            return encodedWithBase64;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String strToDecrypt) {
        if (strToDecrypt == null)
            return null;

        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decodedWithBase64 = Base64.getDecoder().decode(strToDecrypt);
            byte[] decodedWithCipher = cipher.doFinal(decodedWithBase64);

            return new String(decodedWithCipher);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
