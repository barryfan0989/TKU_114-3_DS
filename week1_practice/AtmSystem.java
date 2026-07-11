import java.util.Scanner;

public class AtmSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int option = -1;
        
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;
        
        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            if (!sc.hasNextInt()) {
                System.out.println("無效的選項，請輸入數字。");
                sc.next();
                System.out.println();
                continue;
            }
            option = sc.nextInt();
            
            if (option == 0) {
                break;
            }
            
            switch (option) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    depositCount++;
                    totalDeposit += depositAmount;
                    System.out.println("Balance: " + balance);
                    break;
                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    if (canWithdraw(balance, withdrawAmount)) {
                        balance = withdraw(balance, withdrawAmount);
                        withdrawCount++;
                        totalWithdraw += withdrawAmount;
                        System.out.println("Balance: " + balance);
                    } else {
                        System.out.println("餘額不足！提款失敗。");
                    }
                    break;
                case 4:
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
            System.out.println();
        }
        
        printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = -1;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("金額必須大於 0，請重新輸入。");
                }
            } else {
                System.out.println("輸入格式錯誤，請輸入整數。");
                sc.next();
            }
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }

    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println();
        System.out.println("=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}
