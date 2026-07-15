import java.util.Scanner;

public class ProductDataManager {

    public static void displayTable(String[] names, int[] prices, int[] stocks, int count) {
        System.out.println("\n-------------------------------------------");
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格 (元)", "庫存 (個)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-8d %-17s %-12d %-8d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println("-------------------------------------------");
    }

    public static void searchProduct(Scanner sc, String[] names, int[] prices, int[] stocks, int count) {
        System.out.println("\n--- 搜尋選單 ---");
        System.out.println("1. 完整名稱搜尋 (不分大小寫)");
        System.out.println("2. 部分名稱搜尋 (不分大小寫)");
        System.out.print("請選擇搜尋類型 (1~2)：");
        
        int type = 0;
        if (sc.hasNextInt()) {
            type = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("錯誤：請輸入整數選項！");
            sc.next();
            sc.nextLine();
            return;
        }

        if (type != 1 && type != 2) {
            System.out.println("錯誤：無此搜尋選項！");
            return;
        }

        System.out.print("請輸入搜尋關鍵字：");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：搜尋關鍵字不能為空！");
            return;
        }

        boolean found = false;
        String lowerKeyword = keyword.toLowerCase();

        System.out.println("\n--- 搜尋結果 ---");
        for (int i = 0; i < count; i++) {
            String lowerName = names[i].toLowerCase();
            boolean isMatch = false;
            if (type == 1) {
                isMatch = lowerName.equals(lowerKeyword);
            } else {
                isMatch = lowerName.contains(lowerKeyword);
            }

            if (isMatch) {
                System.out.println("編號 " + (i + 1) + ", 名稱: " + names[i] 
                    + ", 價格: " + prices[i] + " 元, 庫存: " + stocks[i] + " 個");
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到相符的商品！");
        }
    }

    public static void showLowStock(String[] names, int[] prices, int[] stocks, int count) {
        System.out.println("\n--- 低庫存商品 (庫存 < 10) ---");
        boolean hasLow = false;
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格 (元)", "庫存 (個)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < count; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%-8d %-17s %-12d %-8d%n", (i + 1), names[i], prices[i], stocks[i]);
                hasLow = true;
            }
        }
        if (!hasLow) {
            System.out.println("目前沒有低庫存商品。");
        }
        System.out.println("-------------------------------------------");
    }

    public static long calculateTotalValue(int[] prices, int[] stocks, int count) {
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += (long) prices[i] * stocks[i];
        }
        return total;
    }

    public static int addNewRecord(String record, String[] names, int[] prices, int[] stocks, int count) {
        try {
            if (record == null) {
                throw new IllegalArgumentException("資料不能為空！");
            }
            
            String[] parts = record.split(",");
            if (parts.length != 3) {
                throw new IllegalArgumentException("欄位數量不正確，必須有且僅有 3 個以逗號分隔的欄位！");
            }

            String name = parts[0].trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("商品名稱不能為空！");
            }

            int price;
            try {
                price = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("價格格式錯誤，必須為有效的整數！");
            }

            int stock;
            try {
                stock = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("庫存格式錯誤，必須為有效的整數！");
            }

            if (price < 0 || stock < 0) {
                throw new IllegalArgumentException("價格與庫存數值必須大於或等於 0！");
            }

            names[count] = name;
            prices[count] = price;
            stocks[count] = stock;
            System.out.println("新增成功！");
            return count + 1;

        } catch (IllegalArgumentException e) {
            System.out.println("新增失敗原因：" + e.getMessage());
            return count;
        }
    }

    public static void main(String[] args) {
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        String[] names = new String[100];
        int[] prices = new int[100];
        int[] stocks = new int[100];
        int count = 0;

        for (String record : records) {
            count = addNewRecord(record, names, prices, stocks, count);
        }

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== 商品文字資料管理器 =====");
            System.out.println("1. 顯示商品表格");
            System.out.println("2. 搜尋商品 (完整/部分名稱)");
            System.out.println("3. 顯示低庫存商品");
            System.out.println("4. 顯示庫存總價值");
            System.out.println("5. 新增商品資料");
            System.out.println("0. 結束");
            System.out.print("請輸入您的選擇 (0~5)：");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        displayTable(names, prices, stocks, count);
                        break;
                    case 2:
                        searchProduct(sc, names, prices, stocks, count);
                        break;
                    case 3:
                        showLowStock(names, prices, stocks, count);
                        break;
                    case 4:
                        long totalValue = calculateTotalValue(prices, stocks, count);
                        System.out.println("\n目前全店庫存總價值為：" + totalValue + " 元。");
                        break;
                    case 5:
                        System.out.print("請輸入商品文字資料 (格式：品名,價格,庫存)：");
                        String newRecord = sc.nextLine();
                        count = addNewRecord(newRecord, names, prices, stocks, count);
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
        System.out.println("\n系統已成功結束，感謝您的使用！");
    }
}
