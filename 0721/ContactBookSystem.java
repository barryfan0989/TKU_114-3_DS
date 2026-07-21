import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        // Add sample contacts for demo
        contacts.add(new Contact("T01", "Alice", "0912-345678", "alice@example.com"));
        contacts.add(new Contact("T02", "Bob", "0923-456789", "bob@example.com"));

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== 聯絡人管理系統 =====");
            System.out.println("1. 新增聯絡人");
            System.out.println("2. 搜尋聯絡人");
            System.out.println("3. 修改聯絡人電話");
            System.out.println("4. 刪除聯絡人");
            System.out.println("5. 顯示聯絡人清單");
            System.out.println("0. 結束程式");
            System.out.print("請輸入您的選擇 (0~5)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效數字！");
                scanner.next(); // clear invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addContact(scanner, contacts);
                    break;
                case 2:
                    searchContact(scanner, contacts);
                    break;
                case 3:
                    updateContactPhone(scanner, contacts);
                    break;
                case 4:
                    deleteContact(scanner, contacts);
                    break;
                case 5:
                    displayAllContacts(contacts);
                    break;
                case 0:
                    System.out.println("系統結束，謝謝使用！");
                    break;
                default:
                    System.out.println("錯誤：請輸入 0 到 5 之間的數字！");
                    break;
            }
        }
        scanner.close();
    }

    // Method 1: Add a contact
    public static void addContact(Scanner scanner, ArrayList<Contact> list) {
        System.out.print("請輸入聯絡人代碼：");
        String code = scanner.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("錯誤：代碼不可為空白！");
            return;
        }

        if (findContactByCode(list, code) != null) {
            System.out.println("錯誤：代碼 \"" + code + "\" 已存在，不可重複！");
            return;
        }

        System.out.print("請輸入姓名：");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("錯誤：姓名不可為空白！");
            return;
        }

        System.out.print("請輸入電話：");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入電子郵件：");
        String email = scanner.nextLine().trim();

        list.add(new Contact(code, name, phone, email));
        System.out.println("聯絡人新增成功！");
    }

    // Method 2: Search contact by code or name
    public static void searchContact(Scanner scanner, ArrayList<Contact> list) {
        System.out.print("請輸入欲搜尋的關鍵字 (代碼或姓名)：");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：搜尋內容不可為空白！");
            return;
        }

        boolean found = false;
        System.out.println("\n--- 搜尋結果 ---");
        for (Contact c : list) {
            if (c.getCode().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到符合的聯絡人！");
        }
        System.out.println("----------------");
    }

    // Method 3: Update contact phone
    public static void updateContactPhone(Scanner scanner, ArrayList<Contact> list) {
        System.out.print("請輸入欲修改電話的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        Contact c = findContactByCode(list, code);
        if (c == null) {
            System.out.println("錯誤：找不到代碼為 \"" + code + "\" 的聯絡人！");
            return;
        }

        System.out.println("目前聯絡人資訊：" + c);
        System.out.print("請輸入新電話：");
        String newPhone = scanner.nextLine().trim();
        if (newPhone.isEmpty()) {
            System.out.println("錯誤：電話不能為空白！");
            return;
        }

        c.setPhone(newPhone);
        System.out.println("修改成功！最新資訊：" + c);
    }

    // Method 4: Delete contact
    public static void deleteContact(Scanner scanner, ArrayList<Contact> list) {
        System.out.print("請輸入欲刪除的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        Contact c = findContactByCode(list, code);
        if (c == null) {
            System.out.println("錯誤：找不到代碼為 \"" + code + "\" 的聯絡人！");
            return;
        }

        list.remove(c);
        System.out.println("刪除成功！已移除該聯絡人。");
    }

    // Method 5: Display all contacts
    public static void displayAllContacts(ArrayList<Contact> list) {
        System.out.println("\n--- 聯絡人通訊錄 ---");
        if (list.isEmpty()) {
            System.out.println("(通訊錄為空)");
        } else {
            for (Contact c : list) {
                System.out.println(c);
            }
        }
        System.out.println("--------------------");
    }

    // Custom helper method to search specifically by code
    public static Contact findContactByCode(ArrayList<Contact> list, String code) {
        for (Contact c : list) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}
