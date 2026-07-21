import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        ArrayList<CartItem> cart = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        // Add sample items
        cart.add(new CartItem("P001", "Apple", 20, 3));
        cart.add(new CartItem("P002", "Banana", 15, 5));

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== 購物車系統 =====");
            System.out.println("1. 加入商品");
            System.out.println("2. 修改商品數量");
            System.out.println("3. 移除商品");
            System.out.println("4. 顯示購物車與結帳總額");
            System.out.println("0. 結束程式");
            System.out.print("請輸入您的選擇 (0~4)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效數字！");
                scanner.next(); // clear invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addItem(scanner, cart);
                    break;
                case 2:
                    modifyItemQuantity(scanner, cart);
                    break;
                case 3:
                    removeItem(scanner, cart);
                    break;
                case 4:
                    displayCart(cart);
                    break;
                case 0:
                    System.out.println("結束購物，再見！");
                    break;
                default:
                    System.out.println("錯誤：請輸入 0 到 4 之間的整數！");
                    break;
            }
        }
        scanner.close();
    }

    public static void addItem(Scanner scanner, ArrayList<CartItem> cart) {
        System.out.print("請輸入商品代碼：");
        String code = scanner.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("錯誤：商品代碼不能為空白！");
            return;
        }

        CartItem existing = findItemByCode(cart, code);

        int qty;
        while (true) {
            System.out.print("請輸入加入數量：");
            if (scanner.hasNextInt()) {
                qty = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("錯誤：請輸入有效的整數數量！");
                scanner.next();
                scanner.nextLine();
            }
        }

        if (qty <= 0) {
            System.out.println("錯誤：數量必須大於 0！");
            return;
        }

        if (existing != null) {
            existing.addQuantity(qty);
            System.out.println("商品已存在，已自動增加數量。目前狀態：" + existing);
        } else {
            System.out.print("請輸入商品名稱：");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("錯誤：商品名稱不能為空白！");
                return;
            }

            int price;
            while (true) {
                System.out.print("請輸入商品單價：");
                if (scanner.hasNextInt()) {
                    price = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("錯誤：請輸入有效的整數單價！");
                    scanner.next();
                    scanner.nextLine();
                }
            }

            if (price < 0) {
                System.out.println("錯誤：單價不能為負數！");
                return;
            }

            cart.add(new CartItem(code, name, price, qty));
            System.out.println("成功將新商品加入購物車！");
        }
    }

    public static void modifyItemQuantity(Scanner scanner, ArrayList<CartItem> cart) {
        System.out.print("請輸入欲修改數量的商品代碼：");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(cart, code);
        if (item == null) {
            System.out.println("錯誤：找不到代碼為 \"" + code + "\" 的商品！");
            return;
        }

        System.out.println("目前商品資訊：" + item);
        int newQty;
        while (true) {
            System.out.print("請輸入新數量 (大於 0)：");
            if (scanner.hasNextInt()) {
                newQty = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("錯誤：請輸入有效的整數數量！");
                scanner.next();
                scanner.nextLine();
            }
        }

        if (newQty <= 0) {
            System.out.println("錯誤：數量小於或等於 0 時不接受更新！");
        } else {
            item.setQuantity(newQty);
            System.out.println("修改成功！最新資訊：" + item);
        }
    }

    public static void removeItem(Scanner scanner, ArrayList<CartItem> cart) {
        System.out.print("請輸入欲移除的商品代碼：");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(cart, code);
        if (item == null) {
            System.out.println("錯誤：找不到該代碼的商品，無法移除！");
            return;
        }

        cart.remove(item);
        System.out.println("已成功從購物車中移除商品：" + item.getName());
    }

    public static void displayCart(ArrayList<CartItem> cart) {
        System.out.println("\n--- 購物車明細 ---");
        if (cart.isEmpty()) {
            System.out.println("(購物車無任何商品)");
            System.out.println("------------------");
            System.out.println("結帳總金額：0 元");
            return;
        }

        int total = 0;
        for (CartItem item : cart) {
            System.out.println(item);
            total += item.getSubtotal();
        }
        System.out.println("------------------");
        System.out.println("結帳總金額：" + total + " 元");
    }

    // Helper method to query item in cart by code case-insensitively
    public static CartItem findItemByCode(ArrayList<CartItem> cart, String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }
}
