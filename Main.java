package ProyectoFinal;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount("Jose", 300);
        BankAccount.getNameBank();
        BankApp bankApp = new BankApp(bankAccount);
        bankApp.showMenu(scanner);
        scanner.close();
    }
}