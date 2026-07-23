public class LinkedListReverse {
    public static void main(String[] args) {
        System.out.println("--- 測試空串列反轉 ---");
        IntNode head1 = null;
        System.out.print("反轉前：");
        printList(head1);
        head1 = reverse(head1);
        System.out.print("反轉後：");
        printList(head1);

        System.out.println("\n--- 測試單一節點反轉 ---");
        IntNode head2 = new IntNode(10);
        System.out.print("反轉前：");
        printList(head2);
        head2 = reverse(head2);
        System.out.print("反轉後：");
        printList(head2);

        System.out.println("\n--- 測試多節點反轉 (10 -> 20 -> 30) ---");
        IntNode head3 = new IntNode(10);
        head3.next = new IntNode(20);
        head3.next.next = new IntNode(30);
        System.out.print("反轉前：");
        printList(head3);
        head3 = reverse(head3);
        System.out.print("反轉後：");
        printList(head3);
    }

    public static IntNode reverse(IntNode head) {
        IntNode previous = null;
        IntNode current = head;

        while (current != null) {
            IntNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
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
