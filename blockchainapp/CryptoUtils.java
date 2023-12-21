import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CryptoUtils {

    public static String sign(String message, CustomKeyPair keyPair) {
        BigInteger messageHash = new BigInteger(applySHA256(message), 16);
        BigInteger signature = messageHash.modPow(keyPair.getPrivateKey(), keyPair.getPublicKey());
        return signature.toString(16);
    }

    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            return new BigInteger(1, hash).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static BigInteger generatePrime() {
        Random random = new Random();
        BigInteger primeCandidate;
        do {
            primeCandidate = new BigInteger(16, random);
        } while (!primeCandidate.isProbablePrime(5));

        return primeCandidate;
    }

    public static BigInteger generateRandomExponent(BigInteger phi) {
        Random random = new Random();
        BigInteger e;

        do {
            e = new BigInteger(phi.subtract(BigInteger.ONE).bitLength(), random);
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.ONE));

        return e;
    }
}
