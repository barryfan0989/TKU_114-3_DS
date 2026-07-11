import java.util.Scanner;

public class SimpleMenuRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                    System.out.println("執行功能 1");
                    break;
                case 2:
                    System.out.println("執行功能 2");
                    break;
                case 0:
                    System.out.println("離開");
                    break;
                default:
                    System.out.println("未知選項，請重新輸入");
            }
            System.out.println();
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Simple Menu ===");
        System.out.println("1. Start");
        System.out.println("2. Help");
        System.out.println("0. Exit");
    }
}
