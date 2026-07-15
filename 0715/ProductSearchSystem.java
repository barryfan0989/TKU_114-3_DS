import java.util.Scanner;

public class ProductSearchSystem {

    public static void displayAll(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n-------------------------------------------");
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格 (元)", "庫存 (個)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-8d %-17s %-12d %-8d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println("-------------------------------------------");
    }

    public static void exactSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲搜尋的完整商品名稱：");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：關鍵字不能為空！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(keyword)) {
                System.out.println("找到商品：編號 " + (i + 1) + ", 名稱: " + names[i] 
                    + ", 價格: " + prices[i] + " 元, 庫存: " + stocks[i] + " 個");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到此商品！");
        }
    }

    public static void partialSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲搜尋的部分商品名稱：");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：關鍵字不能為空！");
            return;
        }

        boolean found = false;
        String lowerKeyword = keyword.toLowerCase();
        System.out.println("\n--- 部分搜尋結果 ---");
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(lowerKeyword)) {
                System.out.println("編號 " + (i + 1) + ", 名稱: " + names[i] 
                    + ", 價格: " + prices[i] + " 元, 庫存: " + stocks[i] + " 個");
                found = true;
            }
        }
        if (!found) {
            System.out.println("無符合條件的商品！");
        }
    }

    public static void showLongestName(String[] names) {
        String longest = names[0];
        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > longest.length()) {
                longest = names[i];
            }
        }
        System.out.println("\n名稱最長的商品是：" + longest + " (長度: " + longest.length() + ")");
    }

    public static void findKeywordIndex(Scanner sc, String[] names) {
        System.out.print("請輸入關鍵字：");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：關鍵字不能為空！");
            return;
        }

        boolean found = false;
        String lowerKeyword = keyword.toLowerCase();
        System.out.println("\n--- 關鍵字第一次出現位置 ---");
        for (int i = 0; i < names.length; i++) {
            String lowerName = names[i].toLowerCase();
            int idx = lowerName.indexOf(lowerKeyword);
            if (idx != -1) {
                System.out.println("商品名稱 \"" + names[i] + "\"：關鍵字 \"" 
                    + keyword + "\" 第一次出現在索引 " + idx);
                found = true;
            }
        }
        if (!found) {
            System.out.println("所有商品名稱中皆未包含此關鍵字！");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== 商品名稱搜尋系統 =====");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋 (不分大小寫、去空白)");
            System.out.println("3. 部分名稱搜尋 (不分大小寫、可多筆)");
            System.out.println("4. 顯示名稱最長的商品");
            System.out.println("5. 顯示商品名稱與關鍵字出現位置");
            System.out.println("0. 結束");
            System.out.print("請輸入您的選擇 (0~5)：");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        displayAll(names, prices, stocks);
                        break;
                    case 2:
                        exactSearch(sc, names, prices, stocks);
                        break;
                    case 3:
                        partialSearch(sc, names, prices, stocks);
                        break;
                    case 4:
                        showLongestName(names);
                        break;
                    case 5:
                        findKeywordIndex(sc, names);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("錯誤：無此選項，請輸入 0 到 5 之間的整數！");
                        break;
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數！");
                sc.next();
                sc.nextLine();
            }
        }

        sc.close();
        System.out.println("\n感謝使用本系統，再見！");
    }
}
