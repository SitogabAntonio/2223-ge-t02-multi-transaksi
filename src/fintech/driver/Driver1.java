package fintech.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fintech.model.Account;
import fintech.model.Transaction;

// @ Author 12S21007 Dame J Sitinjak
// @ Author 12S21015 Sitogab Antonio Girsang

public class Driver1 {

    static Map<String, Account> accounts = new TreeMap<String, Account>();
    static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);
        String input = null;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] data = input.split("#");

             if (data[0].equals("create-account")) {
                Account newAccount = arrayToAccount(data);
                if (accounts.get(newAccount.getName()) == null) {
                    addAccount(newAccount);
                    System.out.println(newAccount);
                }
            } 
        }
        scanner.close();
    }

    private static void addAccount(Account account) {
        accounts.put(account.getName(), account);
    }

    private static Account arrayToAccount(String[] data) {
        Account account = new Account(data[1], data[2]);
        return account;
    }
}