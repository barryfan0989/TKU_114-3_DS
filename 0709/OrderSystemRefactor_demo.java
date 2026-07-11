import java.util.Scanner;

public class OrderSystemRefactor_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            if (!sc.hasNextInt()) {
                System.out.println("無效的輸入，請輸入數字。");
                sc.next();
                System.out.println();
                continue;
            }
            int option = sc.nextInt();

            if (option == 0) {
                break;
            }

            int price = getPrice(option);
            if (price == 0) {
                System.out.println("Unknown option");
                System.out.println();
                continue;
            }

            int quantity = readValidQuantity(sc);
            int subtotal = calculateSubtotal(price, quantity);
            
            totalItems += quantity;
            totalAmount += subtotal;
            
            System.out.println("Subtotal: " + subtotal);
            System.out.println();
        }

        printReceipt(totalItems, totalAmount);
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 50;
            default:
                return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1:
                return "Black tea";
            case 2:
                return "Green tea";
            case 3:
                return "Coffee";
            default:
                return "Unknown";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        while (!sc.hasNextInt()) {
            System.out.println("無效的輸入，請輸入數字。");
            System.out.print("請輸入數量：");
            sc.next();
        }
        int quantity = sc.nextInt();
        while (quantity <= 0) {
            System.out.print("數量必須大於 0，請重新輸入：");
            while (!sc.hasNextInt()) {
                System.out.println("無效的輸入，請輸入數字。");
                System.out.print("請輸入數量：");
                sc.next();
            }
            quantity = sc.nextInt();
        }
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println();
        System.out.println("=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }
}
