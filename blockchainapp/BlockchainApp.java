import java.util.Scanner;

public class BlockchainApp {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        CustomKeyPair minerKeyPair = CustomKeyPairGenerator.generateKeyPair();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Creating transaction...");
                    blockchain.createTransaction("Alice", "Bob", 5, minerKeyPair);
                    System.out.println("Transaction created.");
                    break;

                case 2:
                    System.out.println("Mining block...");
                    Block minedBlock = blockchain.mineBlock("Miner", minerKeyPair);
                    System.out.println("Block mined. Details: " + minedBlock);
                    break;

                case 3:
                    System.out.println("Printing Blockchain...");
                    blockchain.printBlockchain();
                    break;

                case 4:
                    System.out.println("Exiting Blockchain CLI. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nBlockchain CLI Menu:");
        System.out.println("1. Create Transaction");
        System.out.println("2. Mine Block");
        System.out.println("3. Print Blockchain");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
