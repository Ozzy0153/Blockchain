import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private List<Block> chain;
    private List<Transaction> pendingTransactions;

    private int difficulty = 4; // Example difficulty level for PoW

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        createGenesisBlock();
    }

    private void createGenesisBlock() {
        Block genesisBlock = new Block(1, getCurrentTimestamp(), new ArrayList<>(), "0", difficulty);
        this.chain.add(genesisBlock);
    }


    public Block mineBlock(String minerAddress, CustomKeyPair minerKeyPair) {
        List<Transaction> transactions = new ArrayList<>(pendingTransactions);
        pendingTransactions.clear();

        // Include a reward transaction for the miner
        createTransaction("Blockchain", minerAddress, 1, minerKeyPair);

        String previousHash = hashBlock(chain.get(chain.size() - 1));

        Block newBlock = new Block(chain.size() + 1, getCurrentTimestamp(), transactions, previousHash, difficulty);
        newBlock.mineBlock(); // This is where the block is mined

        this.chain.add(newBlock);
        return newBlock;
    }

    public void createTransaction(String sender, String recipient, double amount, CustomKeyPair senderKeyPair) {
        // Create a transaction with a digital signature
        Transaction transaction = new Transaction(sender, recipient, amount, CryptoUtils.sign(sender + recipient + amount, senderKeyPair));
        this.pendingTransactions.add(transaction);
    }


    private String hashBlock(Block block) {
        // Hash a block using SHA-256
        String blockString = block.toString();
        return CryptoUtils.applySHA256(blockString);
    }

    private String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println(block);
        }
    }

}
