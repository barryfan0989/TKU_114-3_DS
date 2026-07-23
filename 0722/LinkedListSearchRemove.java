public class LinkedListSearchRemove {
    public static void main(String[] args) {
        System.out.println("--- 建立初始串列 ---");
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);
        
        System.out.print("初始串列：");
        printList(head);
        
        System.out.println("\n--- 測試 contains ---");
        System.out.println("是否包含 20？ " + contains(head, 20));
        System.out.println("是否包含 50？ " + contains(head, 50));

        System.out.println("\n--- 測試刪除中間節點 (30) ---");
        head = removeValue(head, 30);
        System.out.print("刪除後串列：");
        printList(head);

        System.out.println("\n--- 測試刪除尾節點 (40) ---");
        head = removeValue(head, 40);
        System.out.print("刪除後串列：");
        printList(head);

        System.out.println("\n--- 測試刪除首節點 (10) ---");
        head = removeValue(head, 10);
        System.out.print("刪除後串列：");
        printList(head);

        System.out.println("\n--- 測試刪除不存在的資料 (99) ---");
        head = removeValue(head, 99);
        System.out.print("刪除後串列：");
        printList(head);

        System.out.println("\n--- 測試刪除唯一節點 (20) 使其變為空串列 ---");
        head = removeValue(head, 20);
        System.out.print("刪除後串列：");
        printList(head);

        System.out.println("\n--- 測試空串列邊界狀況 ---");
        System.out.println("空串列是否包含 10？ " + contains(head, 10));
        head = removeValue(head, 10);
        System.out.print("空串列刪除 10 後：");
        printList(head);
    }

    public static boolean contains(IntNode head, int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static IntNode removeValue(IntNode head, int target) {
        if (head == null) {
            System.out.println("[提示] 串列為空，無法刪除！");
            return null;
        }
        
        if (head.data == target) {
            System.out.println("[成功] 刪除首節點：" + target);
            return head.next;
        }

        IntNode previous = head;
        IntNode current = head.next;
        boolean found = false;

        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                found = true;
                break;
            }
            previous = current;
            current = current.next;
        }

        if (found) {
            System.out.println("[成功] 刪除節點：" + target);
        } else {
            System.out.println("[提示] 找不到指定數值，無法刪除：" + target);
        }

        return head;
    }

    public static void printList(IntNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
