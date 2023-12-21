import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class CustomKeyPairGenerator {

    public static CustomKeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // You can adjust the key size
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            return new CustomKeyPair(
                    ((RSAPublicKey) keyPair.getPublic()).getModulus(),
                    ((RSAPrivateKey) keyPair.getPrivate()).getPrivateExponent()
            );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
