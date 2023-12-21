import java.math.BigInteger;

public class CustomKeyPair {

    private BigInteger publicKey;
    private BigInteger privateKey;

    public CustomKeyPair(BigInteger publicKey, BigInteger privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }
}
