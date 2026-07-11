import java.util.Scanner;

public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;
        int totalItems = 0;
        int totalAmount = 0;
        
        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            if (!sc.hasNextInt()) {
                System.out.println("無效的選項，請輸入數字。");
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
                System.out.println("無效的選項，請重新輸入。");
                System.out.println();
                continue;
            }
            
            String name = getItemName(option);
            int quantity = readValidQuantity(sc);
            int subtotal = calculateSubtotal(price, quantity);
            
            switch (option) {
                case 1:
                    blackTeaCount += quantity;
                    break;
                case 2:
                    greenTeaCount += quantity;
                    break;
                case 3:
                    milkTeaCount += quantity;
                    break;
                case 4:
                    coffeeCount += quantity;
                    break;
            }
            
            totalItems += quantity;
            totalAmount += subtotal;
            
            System.out.println(name + " x " + quantity);
            System.out.println("Subtotal: " + subtotal);
            System.out.println();
        }
        
        int finalAmount = calculateDiscountedTotal(totalAmount);
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, finalAmount);
        
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 45;
            case 4:
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
                return "Milk tea";
            case 4:
                return "Coffee";
            default:
                return "Unknown";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        int quantity = -1;
        while (true) {
            System.out.print("請輸入數量：");
            if (sc.hasNextInt()) {
                quantity = sc.nextInt();
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("數量必須大於 0，請重新輸入。");
                }
            } else {
                System.out.println("輸入格式錯誤，請輸入整數。");
                sc.next();
            }
        }
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        }
        return totalAmount;
    }

    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount, int finalAmount) {
        System.out.println();
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        
        String discount = (totalAmount >= 300) ? "Yes" : "No";
        System.out.println("Discount: " + discount);
        System.out.println("Final amount: " + finalAmount);
    }
}
