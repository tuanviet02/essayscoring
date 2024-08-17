package Helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

    public String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String AES_ALGORITHM = "AES";
    private static final String FIXED_KEY = "ThisIsASecretKey";
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";

    public String encryptAES(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(), new IvParameterSpec(new byte[16])); // Sử dụng IV

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            
            String encryptedText = Base64.getUrlEncoder().encodeToString(encryptedBytes);
            return encryptedText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decryptAES(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            cipher.init(Cipher.DECRYPT_MODE, generateSecretKey(), new IvParameterSpec(new byte[16]));

            byte[] decodedBytes = Base64.getUrlDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKey generateSecretKey() {
        return new SecretKeySpec(FIXED_KEY.getBytes(), AES_ALGORITHM);
    }

}
