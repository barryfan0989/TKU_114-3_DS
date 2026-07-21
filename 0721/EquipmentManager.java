import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        ArrayList<Equipment> inventory = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        // Add some initial items for demo purposes
        inventory.add(new Equipment("EQ01", "Projector"));
        inventory.add(new Equipment("EQ02", "Laptop"));
        inventory.add(new Equipment("EQ03", "Speaker"));

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== 設備借用管理系統 =====");
            System.out.println("1. 新增設備");
            System.out.println("2. 依代碼搜尋設備");
            System.out.println("3. 借出設備");
            System.out.println("4. 歸還設備");
            System.out.println("5. 列出可借設備");
            System.out.println("6. 列出所有設備");
            System.out.println("0. 結束程式");
            System.out.print("請輸入您的選擇 (0~6)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效數字！");
                scanner.next(); // clear invalid token
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addEquipment(scanner, inventory);
                    break;
                case 2:
                    searchEquipment(scanner, inventory);
                    break;
                case 3:
                    borrowEquipment(scanner, inventory);
                    break;
                case 4:
                    returnEquipment(scanner, inventory);
                    break;
                case 5:
                    listAvailable(inventory);
                    break;
                case 6:
                    listAll(inventory);
                    break;
                case 0:
                    System.out.println("系統結束，感謝使用！");
                    break;
                default:
                    System.out.println("錯誤：請輸入 0 到 6 之間的整數！");
                    break;
            }
        }
        scanner.close();
    }

    public static void addEquipment(Scanner scanner, ArrayList<Equipment> inventory) {
        System.out.print("請輸入設備代碼：");
        String code = scanner.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("錯誤：設備代碼不能為空白！");
            return;
        }

        if (findEquipment(inventory, code) != null) {
            System.out.println("錯誤：此設備代碼已存在，不可重複！");
            return;
        }

        System.out.print("請輸入設備名稱：");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("錯誤：設備名稱不能為空白！");
            return;
        }

        inventory.add(new Equipment(code, name));
        System.out.println("設備新增成功！");
    }

    public static void searchEquipment(Scanner scanner, ArrayList<Equipment> inventory) {
        System.out.print("請輸入欲搜尋的設備代碼：");
        String code = scanner.nextLine().trim();
        
        Equipment eq = findEquipment(inventory, code);
        if (eq != null) {
            System.out.println("找到設備資訊：" + eq);
        } else {
            System.out.println("找不到代碼為 \"" + code + "\" 的設備。");
        }
    }

    public static void borrowEquipment(Scanner scanner, ArrayList<Equipment> inventory) {
        System.out.print("請輸入欲借出的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment eq = findEquipment(inventory, code);
        if (eq == null) {
            System.out.println("錯誤：找不到代碼為 \"" + code + "\" 的設備。");
            return;
        }

        if (!eq.isAvailable()) {
            System.out.println("借出失敗：設備 \"" + eq.getName() + "\" 目前已被借出！");
        } else {
            eq.setAvailable(false);
            System.out.println("借出成功！已借出設備：" + eq.getName());
        }
    }

    public static void returnEquipment(Scanner scanner, ArrayList<Equipment> inventory) {
        System.out.print("請輸入欲歸還的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment eq = findEquipment(inventory, code);
        if (eq == null) {
            System.out.println("錯誤：找不到代碼為 \"" + code + "\" 的設備。");
            return;
        }

        if (eq.isAvailable()) {
            System.out.println("提示：此設備目前本來就是可借用狀態，不需歸還。");
        } else {
            eq.setAvailable(true);
            System.out.println("歸還成功！已歸還設備：" + eq.getName());
        }
    }

    public static void listAvailable(ArrayList<Equipment> inventory) {
        System.out.println("\n--- 可借用設備清單 ---");
        boolean found = false;
        for (Equipment eq : inventory) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有可借用的設備。");
        }
        System.out.println("----------------------");
    }

    public static void listAll(ArrayList<Equipment> inventory) {
        System.out.println("\n--- 所有設備清單 ---");
        if (inventory.isEmpty()) {
            System.out.println("(無任何設備)");
        } else {
            for (Equipment eq : inventory) {
                System.out.println(eq);
            }
        }
        System.out.println("--------------------");
    }

    // Helper method to find an equipment by code case-insensitively
    public static Equipment findEquipment(ArrayList<Equipment> inventory, String code) {
        for (Equipment eq : inventory) {
            if (eq.getCode().equalsIgnoreCase(code)) {
                return eq;
            }
        }
        return null;
    }
}
