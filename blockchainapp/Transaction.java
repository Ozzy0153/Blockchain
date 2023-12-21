import java.util.Objects;

public class Transaction {

    private String sender;
    private String recipient;
    private double amount;
    private String signature;

    public Transaction(String sender, String recipient, double amount, String signature) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return String.format("Transaction{sender='%s', recipient='%s', amount=%.2f}", sender, recipient, amount);
    }

    // Ensure you have proper equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(sender, that.sender) &&
                Objects.equals(recipient, that.recipient) &&
                Objects.equals(signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipient, amount, signature);
    }
}
