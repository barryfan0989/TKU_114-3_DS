import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int option = -1;

        while (option != 0) {
            System.out.println("=== ATM Menu ===");
            System.out.println("1. 查詢餘額");
            System.out.println("2. 存款");
            System.out.println("3. 提款");
            System.out.println("0. 離開");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("目前餘額為：" + balance + " 元");
                    break;
                case 2:
                    System.out.print("請輸入存款金額：");
                    int depositAmount = sc.nextInt();
                    while (depositAmount <= 0) {
                        System.out.print("存款金額必須大於 0，請重新輸入：");
                        depositAmount = sc.nextInt();
                    }
                    balance += depositAmount;
                    System.out.println("存款成功！已存入 " + depositAmount + " 元，目前餘額為 " + balance + " 元。");
                    break;
                case 3:
                    System.out.print("請輸入提款金額：");
                    int withdrawAmount = sc.nextInt();
                    while (withdrawAmount <= 0) {
                        System.out.print("提款金額必須大於 0，請重新輸入：");
                        withdrawAmount = sc.nextInt();
                    }
                    if (withdrawAmount > balance) {
                        System.out.println("餘額不足！提款失敗。目前餘額為：" + balance + " 元");
                    } else {
                        balance -= withdrawAmount;
                        System.out.println("提款成功！已取出 " + withdrawAmount + " 元，目前餘額為 " + balance + " 元。");
                    }
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
}
