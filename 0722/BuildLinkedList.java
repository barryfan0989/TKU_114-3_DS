public class BuildLinkedList {
    public static void main(String[] args) {
        IntNode first = new IntNode(10);
        IntNode second = new IntNode(20);
        IntNode third = new IntNode(30);
        IntNode fourth = new IntNode(40);

        IntNode head = first;
        first.next = second;
        second.next = third;
        third.next = fourth;

        System.out.print("鏈結串列內容：");
        printList(head);

        int count = 0;
        int sum = 0;
        IntNode current = head;
        while (current != null) {
            count++;
            sum += current.data;
            current = current.next;
        }

        System.out.println("節點總數：" + count);
        System.out.println("數值總和：" + sum);

        System.out.print("\n[測試邊界] 空串列走訪：");
        IntNode emptyHead = null;
        printList(emptyHead);
        
        int emptyCount = 0;
        int emptySum = 0;
        current = emptyHead;
        while (current != null) {
            emptyCount++;
            emptySum += current.data;
            current = current.next;
        }
        System.out.println("空串列節點總數：" + emptyCount);
        System.out.println("空串列數值總和：" + emptySum);
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
