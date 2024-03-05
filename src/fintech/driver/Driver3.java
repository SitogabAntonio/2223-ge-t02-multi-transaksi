package fintech.driver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fintech.model.Account;
import fintech.model.Transaction;

public class Driver3 {

    static Map<String, Account> accounts = new TreeMap<String, Account>();
    static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = null;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] data = input.split("#");

            switch (data[0]) {
                case "create-account":
                    createAccount(data);
                    break;
                case "find-account":
                    findAccount(data);
                    break;
                case "create-transaction":
                    createTransaction(data);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        scanner.close();
    }

    private static void createAccount(String[] data) {
        Account newAccount = new Account(data[1], data[2]);
        if (accounts.get(newAccount.getName()) == null) {
            accounts.put(newAccount.getName(), newAccount);
            System.out.println(newAccount);
        }
    }

    private static void findAccount(String[] data) {
        String ownerName = data[1];
        Account account = accounts.values().stream().filter(a -> a.getName().equalsIgnoreCase(ownerName)).findFirst().orElse(null);
        if (account != null) {
            System.out.println(account);
            List<Transaction> transactionsForAccount = transactions.stream()
                    .filter(t -> t.getName().equalsIgnoreCase(ownerName)
                            || (t instanceof Transaction
                                    && ((Transaction) t).getName().equalsIgnoreCase(ownerName)))
                    .sorted(Comparator.comparing(Transaction::getPosted_at))
                    .collect(Collectors.toList());
            for (Transaction t : transactionsForAccount) {
            }
        } else {
        }
    }

    private static void createTransaction(String[] data) {
        Account account = accounts.get(data[1]);
        if (account != null && data[1] != null && (Double.parseDouble(data[2]) < 0
                && accounts.get(data[1]).getBalance() + Double.parseDouble(data[2]) > 0
                || Double.parseDouble(data[2]) > 0)) {
            Transaction newTransaction = new Transaction(data[1], Double.parseDouble(data[2]), data[3], data[4]);
            account.addTransaction(newTransaction);
            transactions.add(newTransaction);
        }
    }
}
