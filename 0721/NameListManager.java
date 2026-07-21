import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== 名單管理系統 =====");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部姓名");
            System.out.println("0. 結束程式");
            System.out.print("請輸入您的選擇 (0~5)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效的選單數字！");
                scanner.next(); // clear invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addName(scanner, names);
                    break;
                case 2:
                    searchName(scanner, names);
                    break;
                case 3:
                    modifyName(scanner, names);
                    break;
                case 4:
                    deleteName(scanner, names);
                    break;
                case 5:
                    displayAll(names);
                    break;
                case 0:
                    System.out.println("感謝使用名單管理系統，再見！");
                    break;
                default:
                    System.out.println("錯誤：請輸入 0 到 5 之間的整數！");
                    break;
            }
        }
        scanner.close();
    }

    public static void addName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入欲新增的姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：姓名不能為空白！");
            return;
        }

        // Check if name already exists case-insensitively to prevent duplicates
        if (findNameIndex(names, name) != -1) {
            System.out.println("提示：該姓名已存在於名單中。");
        }

        names.add(name);
        System.out.println("姓名新增成功：" + name);
    }

    public static void searchName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入欲搜尋的姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：搜尋內容不能為空白！");
            return;
        }

        int index = findNameIndex(names, name);
        if (index != -1) {
            System.out.println("找到姓名 \"" + names.get(index) + "\"，位於索引 " + index);
        } else {
            System.out.println("找不到姓名 \"" + name + "\"。");
        }
    }

    public static void modifyName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入欲修改的姓名：");
        String oldName = scanner.nextLine().trim();

        int index = findNameIndex(names, oldName);
        if (index == -1) {
            System.out.println("錯誤：找不到姓名 \"" + oldName + "\"，無法修改！");
            return;
        }

        System.out.print("請輸入新的姓名：");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("錯誤：新姓名不能為空白！");
            return;
        }

        String actualOldName = names.set(index, newName);
        System.out.println("修改成功！將 \"" + actualOldName + "\" 修改為 \"" + newName + "\"");
    }

    public static void deleteName(Scanner scanner, ArrayList<String> names) {
        System.out.print("請輸入欲刪除的姓名：");
        String name = scanner.nextLine().trim();

        int index = findNameIndex(names, name);
        if (index != -1) {
            String removedName = names.remove(index);
            System.out.println("刪除成功！已移除姓名：" + removedName);
        } else {
            System.out.println("刪除失敗：找不到姓名 \"" + name + "\"。");
        }
    }

    public static void displayAll(ArrayList<String> names) {
        System.out.println("\n--- 目前名單 ---");
        if (names.isEmpty()) {
            System.out.println("(名單為空)");
        } else {
            for (int i = 0; i < names.size(); i++) {
                System.out.println((i + 1) + ". " + names.get(i));
            }
        }
        System.out.println("----------------");
    }

    // Helper method: returns index of name case-insensitively, or -1 if not found
    public static int findNameIndex(ArrayList<String> names, String target) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}
