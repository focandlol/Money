package focandlol.util.encrypt;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptComponent {

    private static final String SECRET_KEY = "12345678901234561234567890123456";

    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();

    public String encryptString(String encryptString) {
        try {
            byte[] encryptedBytes = cipherPkcs5(Cipher.ENCRYPT_MODE, SECRET_KEY)
                    .doFinal(encryptString.getBytes(StandardCharsets.UTF_8));

            return new String(encoder.encode(encryptedBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("encrypting error", e);
        }
    }

    public String decryptString(String decryptString) {
        try {
            byte[] decodedBytes = decoder.decode(decryptString.getBytes(StandardCharsets.UTF_8));
            byte[] decryptedBytes = cipherPkcs5(Cipher.DECRYPT_MODE, SECRET_KEY)
                    .doFinal(decodedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("decrypting error", e);
        }
    }

    private Cipher cipherPkcs5(int opMode, String secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(secretKey.substring(0, 16).getBytes(StandardCharsets.UTF_8));
            cipher.init(opMode, keySpec, ivSpec);
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException("Cipher error", e);
        }
    }
}
