import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            if (!sc.hasNextInt()) {
                System.out.println("無效的選項，請重新輸入。");
                sc.next();
                System.out.println();
                continue;
            }
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    break;
                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    balance = withdraw(balance, withdrawAmount);
                    break;
                case 0:
                    System.out.println("謝謝使用！再見。");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
            System.out.println();
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("0. 離開");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("無效的輸入，請輸入數字。");
            System.out.print(message);
            sc.next();
        }
        int amount = sc.nextInt();
        while (amount <= 0) {
            System.out.print("金額必須大於 0，請重新輸入：");
            while (!sc.hasNextInt()) {
                System.out.println("無效的輸入，請輸入數字。");
                System.out.print("金額必須大於 0，請重新輸入：");
                sc.next();
            }
            amount = sc.nextInt();
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        int newBalance = balance + amount;
        System.out.println("存款成功！已存入 " + amount + " 元，目前餘額為 " + newBalance + " 元。");
        return newBalance;
    }

    public static int withdraw(int balance, int amount) {
        if (amount > balance) {
            System.out.println("餘額不足！提款失敗。目前餘額為：" + balance + " 元");
            return balance;
        } else {
            int newBalance = balance - amount;
            System.out.println("提款成功！已取出 " + amount + " 元，目前餘額為 " + newBalance + " 元。");
            return newBalance;
        }
    }

    public static void printBalance(int balance) {
        System.out.println("Currently balance is: " + balance + " 元");
        // Or in Chinese to match original logic:
        System.out.println("目前餘額為：" + balance + " 元");
    }
}
