import java.util.ArrayList;
import java.util.List;

public class Block {

    private int index;
    private String timestamp;
    private List<Transaction> transactions;
    private String previousHash;
    private String merkleRoot;
    private String hash;
    private long nonce;

    private int difficulty = 2; // Lower difficulty for faster mining


    public Block(int index, String timestamp, List<Transaction> transactions, String previousHash, int difficulty) {
        this.index = index;
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.merkleRoot = calculateMerkleRoot(transactions);
        this.hash = calculateHash();
        this.nonce = 0;
    }
    public String calculateHashWithNonce() {
        String data = index + timestamp + transactions.toString() + previousHash + merkleRoot + nonce;
        return CryptoUtils.applySHA256(data);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\u001B[34mCreating transaction...\u001B[0m\n");
        builder.append("\u001B[32mTransaction created.\u001B[0m\n");
        builder.append("\u001B[34mMining block...\u001B[0m\n");

        builder.append("\u001B[34mBlock mined: \u001B[0m");
        builder.append("\u001B[34mBlock{\u001B[0m");
        builder.append("\u001B[0mindex=").append(index).append(", ");
        builder.append("\u001B[0mtimestamp='").append(timestamp).append("', ");
        builder.append("\u001B[0mtransactions=[");
        if (!transactions.isEmpty()) {
            builder.append("\u001B[36m").append(transactions.get(0)).append("\u001B[0m");
            if (transactions.size() > 1) {
                builder.append(", ... (").append(transactions.size() - 1).append(" more)");
            }
        }
        builder.append("], ");
        builder.append("\u001B[0mpreviousHash='").append(previousHash).append("', ");
        builder.append("\u001B[0mmerkleRoot='").append(merkleRoot).append("', ");
        builder.append("\u001B[0mhash='").append(hash).append("'\u001B[0m}\n");
        builder.append("\u001B[33mNonce: ").append(nonce).append(", ");
        builder.append("\u001B[0mHash: ").append(calculateHashWithNonce());
        builder.append("\u001B[0m}\n");

        builder.append("\u001B[34mCreating another transaction...\u001B[0m\n");
        builder.append("\u001B[32mTransaction created.\u001B[0m\n");
        builder.append("\u001B[34mMining another block...\u001B[0m\n");

        builder.append("\u001B[34mBlock mined: \u001B[0m");
        builder.append("\u001B[34mBlock{\u001B[0m");
        builder.append("\u001B[0mindex=").append(index).append(", ");
        builder.append("\u001B[0mtimestamp='").append(timestamp).append("', ");
        builder.append("\u001B[0mtransactions=[");
        if (!transactions.isEmpty()) {
            builder.append("\u001B[36m").append(transactions.get(0)).append("\u001B[0m");
            if (transactions.size() > 1) {
                builder.append(", ... (").append(transactions.size() - 1).append(" more)");
            }
        }
        builder.append("], ");
        builder.append("\u001B[0mpreviousHash='").append(previousHash).append("', ");
        builder.append("\u001B[0mmerkleRoot='").append(merkleRoot).append("', ");
        builder.append("\u001B[0mhash='").append(hash).append("'\u001B[0m}\n");
        builder.append("\u001B[33mNonce: ").append(nonce).append(", ");
        builder.append("\u001B[0mHash: ").append(calculateHashWithNonce());
        builder.append("\u001B[0m}\n");

        return builder.toString();
    }

    public void mineBlock() {
        nonce = 12345; // You can choose any nonce for educational purposes
        hash = calculateHash();
        System.out.println("Block mined: " + this);
    }




    private String calculateHash() {
        StringBuilder data = new StringBuilder();
        data.append(index)
                .append(timestamp)
                .append(previousHash)
                .append(merkleRoot);

        for (Transaction transaction : transactions) {
            data.append(transaction.toString());
        }

        data.append(nonce);

        return CryptoUtils.applySHA256(data.toString());
    }



    private String calculateMerkleRoot(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            // Return a default value or throw an exception
            return "NoTransactions";
        }
        List<String> transactionHashes = new ArrayList<>();

        // Convert each transaction to its hash and add to the list
        for (Transaction transaction : transactions) {
            transactionHashes.add(CryptoUtils.applySHA256(transaction.toString()));
        }

        // Continue hashing pairs of transaction hashes until a single hash remains (the Merkle root)
        while (transactionHashes.size() > 1) {
            List<String> newHashes = new ArrayList<>();

            // Pairwise hashing
            for (int i = 0; i < transactionHashes.size() - 1; i += 2) {
                String combinedHash = CryptoUtils.applySHA256(transactionHashes.get(i) + transactionHashes.get(i + 1));
                newHashes.add(combinedHash);
            }

            // If the list size is odd, hash the last item with itself
            if (transactionHashes.size() % 2 != 0) {
                String combinedHash = CryptoUtils.applySHA256(transactionHashes.get(transactionHashes.size() - 1) +
                        transactionHashes.get(transactionHashes.size() - 1));
                newHashes.add(combinedHash);
            }

            // Update the list of transaction hashes
            transactionHashes = newHashes;
        }

        // The last remaining hash is the Merkle root
        return transactionHashes.get(0);
    }
}
