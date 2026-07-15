import java.util.Scanner;

public class ProductArraySystem {

    public static void displayProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n-------------------------------------------");
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格 (元)", "庫存 (個)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-8d %-17s %-12d %-8d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println("-------------------------------------------");
    }

    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲查詢的商品編號 (1~" + names.length + ")：");
        if (sc.hasNextInt()) {
            int id = sc.nextInt();
            if (id >= 1 && id <= names.length) {
                int index = id - 1;
                System.out.println("\n--- 商品查詢結果 ---");
                System.out.println("商品編號：" + id);
                System.out.println("商品名稱：" + names[index]);
                System.out.println("商品價格：" + prices[index] + " 元");
                System.out.println("商品庫存：" + stocks[index] + " 個");
            } else {
                System.out.println("錯誤：無此商品編號！");
            }
        } else {
            System.out.println("錯誤：請輸入有效的整數！");
            sc.next();
        }
    }

    public static void buyProduct(Scanner sc, String[] names, int[] prices, int[] stocks, int[] sessionPurchased) {
        System.out.print("請輸入欲購買的商品編號 (1~" + names.length + ")：");
        if (sc.hasNextInt()) {
            int id = sc.nextInt();
            if (id >= 1 && id <= names.length) {
                int index = id - 1;
                System.out.println("您選購的是：" + names[index] + "，目前庫存：" + stocks[index] + " 個，單價：" + prices[index] + " 元");
                
                if (stocks[index] == 0) {
                    System.out.println("抱歉，該商品已無庫存，無法購買！");
                    return;
                }

                while (true) {
                    System.out.print("請輸入購買數量 (大於 0 且不超過庫存)：");
                    if (sc.hasNextInt()) {
                        int quantity = sc.nextInt();
                        if (quantity > 0 && quantity <= stocks[index]) {
                            stocks[index] -= quantity;
                            sessionPurchased[index] += quantity;
                            int cost = prices[index] * quantity;
                            System.out.println("購買成功！購買 " + quantity + " 個 " + names[index] + "，總計：" + cost + " 元。");
                            break;
                        } else if (quantity <= 0) {
                            System.out.println("錯誤：購買數量必須大於 0！");
                        } else {
                            System.out.println("錯誤：庫存不足，目前僅剩 " + stocks[index] + " 個！");
                        }
                    } else {
                        System.out.println("錯誤：請輸入有效的整數！");
                        sc.next();
                    }
                }
            } else {
                System.out.println("錯誤：無此商品編號！");
            }
        } else {
            System.out.println("錯誤：請輸入有效的整數！");
            sc.next();
        }
    }

    public static void replenishStock(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入欲補貨的商品編號 (1~" + names.length + ")：");
        if (sc.hasNextInt()) {
            int id = sc.nextInt();
            if (id >= 1 && id <= names.length) {
                int index = id - 1;
                System.out.println("商品名稱：" + names[index] + "，目前庫存：" + stocks[index] + " 個");
                
                while (true) {
                    System.out.print("請輸入欲補貨的數量 (必須大於 0)：");
                    if (sc.hasNextInt()) {
                        int quantity = sc.nextInt();
                        if (quantity > 0) {
                            stocks[index] += quantity;
                            System.out.println("補貨成功！" + names[index] + " 最新庫存為：" + stocks[index] + " 個。");
                            break;
                        } else {
                            System.out.println("錯誤：補貨數量必須大於 0！");
                        }
                    } else {
                        System.out.println("錯誤：請輸入有效的整數！");
                        sc.next();
                    }
                }
            } else {
                System.out.println("錯誤：無此商品編號！");
            }
        } else {
            System.out.println("錯誤：請輸入有效的整數！");
            sc.next();
        }
    }

    public static void displayLowStock(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 低庫存商品清單 (庫存小於 10) ---");
        boolean hasLowStock = false;
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格 (元)", "庫存 (個)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%-8d %-17s %-12d %-8d%n", (i + 1), names[i], prices[i], stocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前沒有庫存小於 10 的商品。");
        }
        System.out.println("-------------------------------------------");
    }

    public static long calculateTotalValue(int[] prices, int[] stocks) {
        long totalValue = 0;
        for (int i = 0; i < prices.length; i++) {
            totalValue += (long) prices[i] * stocks[i];
        }
        return totalValue;
    }

    public static void displaySessionSummary(String[] names, int[] prices, int[] sessionPurchased) {
        System.out.println("\n===========================================");
        System.out.println("               本次消費摘要                ");
        System.out.println("===========================================");
        int totalSpent = 0;
        boolean purchasedAny = false;
        System.out.printf("%-15s %-10s %-10s%n", "商品名稱", "購買數量", "小計 (元)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            if (sessionPurchased[i] > 0) {
                int subtotal = prices[i] * sessionPurchased[i];
                System.out.printf("%-17s %-12d %-10d%n", names[i], sessionPurchased[i], subtotal);
                totalSpent += subtotal;
                purchasedAny = true;
            }
        }
        if (!purchasedAny) {
            System.out.println("本次無購買任何商品。");
        } else {
            System.out.println("-------------------------------------------");
            System.out.println("本次消費總金額：" + totalSpent + " 元");
        }
        System.out.println("===========================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int[] sessionPurchased = new int[names.length];

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== 商品陣列管理系統 =====");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依商品編號查詢");
            System.out.println("3. 購買商品並扣除庫存");
            System.out.println("4. 補充商品庫存");
            System.out.println("5. 顯示低庫存商品");
            System.out.println("6. 顯示全部庫存總價值");
            System.out.println("0. 結束並顯示操作摘要");
            System.out.print("請輸入您的選擇 (0~6)：");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        displayProducts(names, prices, stocks);
                        break;
                    case 2:
                        queryProduct(sc, names, prices, stocks);
                        break;
                    case 3:
                        buyProduct(sc, names, prices, stocks, sessionPurchased);
                        break;
                    case 4:
                        replenishStock(sc, names, stocks);
                        break;
                    case 5:
                        displayLowStock(names, prices, stocks);
                        break;
                    case 6:
                        long totalVal = calculateTotalValue(prices, stocks);
                        System.out.println("\n目前全店庫存總價值為：" + totalVal + " 元。");
                        break;
                    case 0:
                        displaySessionSummary(names, prices, sessionPurchased);
                        break;
                    default:
                        System.out.println("錯誤：無此選項，請輸入 0 到 6 之間的整數！");
                        break;
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數！");
                sc.next();
            }
        }

        sc.close();
        System.out.println("\n系統已成功結束，感謝您的使用！");
    }
}
