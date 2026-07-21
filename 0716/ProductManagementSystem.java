import java.util.Scanner;

public class ProductManagementSystem {

    public static int initProducts(Product[] products) {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        return 5;
    }

    public static void displayAll(Product[] products, int count) {
        System.out.println("\n-------------------------------------------");
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格 (元)", "庫存 (個)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-8d %-17s %-12d %-8d%n", (i + 1), products[i].getName(), products[i].getPrice(),
                    products[i].getStock());
        }
        System.out.println("-------------------------------------------");
    }

    public static Product findProductByName(Product[] products, int count, String name) {
        if (name == null) {
            return null;
        }
        String cleanName = name.trim();
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(cleanName)) {
                return products[i];
            }
        }
        return null;
    }

    public static boolean isNameDuplicate(Product[] products, int count, String name) {
        return findProductByName(products, count, name) != null;
    }

    public static int addProduct(Scanner sc, Product[] products, int count) {
        if (count >= products.length) {
            System.out.println("錯誤：商品庫位已滿，無法新增商品！");
            return count;
        }

        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();
        if (isNameDuplicate(products, count, name)) {
            System.out.println("錯誤：商品名稱已存在，不可重複新增！");
            return count;
        }

        int price;
        while (true) {
            System.out.print("請輸入商品價格 (大於 0)：");
            if (sc.hasNextInt()) {
                price = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("錯誤：請輸入整數價格！");
                sc.next();
                sc.nextLine();
            }
        }

        int stock;
        while (true) {
            System.out.print("請輸入初始庫存 (大於或等於 0)：");
            if (sc.hasNextInt()) {
                stock = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("錯誤：請輸入整數庫存！");
                sc.next();
                sc.nextLine();
            }
        }

        products[count] = new Product(name, price, stock);
        System.out.println("商品新增成功：" + products[count]);
        return count + 1;
    }

    public static void sellProduct(Scanner sc, Product[] products, int count, int[] sessionSales) {
        System.out.print("請輸入欲出售的商品名稱：");
        String name = sc.nextLine();
        Product product = findProductByName(products, count, name);
        if (product == null) {
            System.out.println("找不到此商品！");
            return;
        }

        System.out.println("目前選中：" + product);
        System.out.print("請輸入出售數量：");
        int qty;
        if (sc.hasNextInt()) {
            qty = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("錯誤：請輸入整數數量！");
            sc.next();
            sc.nextLine();
            return;
        }

        if (product.sell(qty)) {
            int index = -1;
            for (int i = 0; i < count; i++) {
                if (products[i] == product) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                sessionSales[index] += qty;
            }
            System.out.println("出售成功！");
        } else {
            System.out.println("出售失敗：數量錯誤或庫存不足！");
        }
    }

    public static void restockProduct(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入欲補貨的商品名稱：");
        String name = sc.nextLine();
        Product product = findProductByName(products, count, name);
        if (product == null) {
            System.out.println("找不到此商品！");
            return;
        }

        System.out.println("目前選中：" + product);
        System.out.print("請輸入補貨數量：");
        int qty;
        if (sc.hasNextInt()) {
            qty = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("錯誤：請輸入整數數量！");
            sc.next();
            sc.nextLine();
            return;
        }

        if (product.restock(qty)) {
            System.out.println("補貨成功！最新資料：" + product);
        } else {
            System.out.println("補貨失敗：補貨數量必須大於 0！");
        }
    }

    public static void updatePrice(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入欲修改價格的商品名稱：");
        String name = sc.nextLine();
        Product product = findProductByName(products, count, name);
        if (product == null) {
            System.out.println("找不到此商品！");
            return;
        }

        System.out.println("目前選中：" + product);
        System.out.print("請輸入新價格：");
        int price;
        if (sc.hasNextInt()) {
            price = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("錯誤：請輸入整數價格！");
            sc.next();
            sc.nextLine();
            return;
        }

        if (product.setPrice(price)) {
            System.out.println("修改成功！最新資料：" + product);
        } else {
            System.out.println("修改失敗：價格必須大於 0！");
        }
    }

    public static void displayLowStock(Product[] products, int count) {
        System.out.println("\n--- 低庫存商品 (庫存 < 10) ---");
        boolean hasLow = false;
        System.out.printf("%-6s %-15s %-10s %-6s%n", "編號", "商品名稱", "價格 (元)", "庫存 (個)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                System.out.printf("%-8d %-17s %-12d %-8d%n", (i + 1), products[i].getName(), products[i].getPrice(),
                        products[i].getStock());
                hasLow = true;
            }
        }
        if (!hasLow) {
            System.out.println("目前沒有低庫存商品。");
        }
        System.out.println("-------------------------------------------");
    }

    public static void displayTotalValue(Product[] products, int count) {
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getInventoryValue();
        }
        System.out.println("\n目前全店庫存總價值為：" + total + " 元。");
    }

    public static void displaySummary(Product[] products, int count, int[] sessionSales) {
        System.out.println("\n===========================================");
        System.out.println("               本日銷售摘要                ");
        System.out.println("===========================================");
        int totalSalesAmount = 0;
        boolean soldAny = false;
        System.out.printf("%-15s %-10s %-10s%n", "商品名稱", "出售數量", "銷售額 (元)");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < count; i++) {
            if (sessionSales[i] > 0) {
                int subtotal = products[i].getPrice() * sessionSales[i];
                System.out.printf("%-17s %-12d %-10d%n", products[i].getName(), sessionSales[i], subtotal);
                totalSalesAmount += subtotal;
                soldAny = true;
            }
        }
        if (!soldAny) {
            System.out.println("本次無出售 any 商品。");
        } else {
            System.out.println("-------------------------------------------");
            System.out.println("本次銷售總金額：" + totalSalesAmount + " 元");
        }
        System.out.println("===========================================");
    }

    public static void main(String[] args) {
        Product[] products = new Product[10];
        int count = initProducts(products);
        int[] sessionSales = new int[products.length];

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== 物件導向商品管理系統 =====");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依完整名稱搜尋");
            System.out.println("3. 新增商品");
            System.out.println("4. 出售商品");
            System.out.println("5. 補充庫存");
            System.out.println("6. 修改商品價格");
            System.out.println("7. 顯示低庫存商品");
            System.out.println("8. 顯示全部庫存總價值");
            System.out.println("0. 結束並顯示操作摘要");
            System.out.print("請輸入您的選擇 (0~8)：");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        displayAll(products, count);
                        break;
                    case 2:
                        System.out.print("請輸入欲搜尋的商品名稱：");
                        String searchName = sc.nextLine();
                        Product found = findProductByName(products, count, searchName);
                        if (found != null) {
                            System.out.println("找到商品：" + found);
                        } else {
                            System.out.println("找不到此商品！");
                        }
                        break;
                    case 3:
                        count = addProduct(sc, products, count);
                        break;
                    case 4:
                        sellProduct(sc, products, count, sessionSales);
                        break;
                    case 5:
                        restockProduct(sc, products, count);
                        break;
                    case 6:
                        updatePrice(sc, products, count);
                        break;
                    case 7:
                        displayLowStock(products, count);
                        break;
                    case 8:
                        displayTotalValue(products, count);
                        break;
                    case 0:
                        displaySummary(products, count, sessionSales);
                        break;
                    default:
                        System.out.println("錯誤：無此選項，請輸入 0 到 8 之間的整數！");
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
