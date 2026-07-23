public class NumberHistoryList {
    private IntNode head;
    private int size;

    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("[新增] 前端新增數值：" + value);
    }

    public void addLast(int value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            head = newNode;
        } else {
            IntNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[新增] 尾端新增數值：" + value);
    }

    public boolean contains(int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            return false;
        }
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }
        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void printList() {
        System.out.print("目前串列：");
        IntNode current = head;
        if (current == null) {
            System.out.println("null");
            return;
        }
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public int size() {
        return size;
    }

    public int getSum() {
        int sum = 0;
        IntNode current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }

    public Integer getMax() {
        if (head == null) {
            return null;
        }
        int max = head.data;
        IntNode current = head.next;
        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    public Integer getMin() {
        if (head == null) {
            return null;
        }
        int min = head.data;
        IntNode current = head.next;
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }

    public void printStatistics() {
        System.out.println("--- 串列統計資訊 ---");
        System.out.println("元素個數 (Size): " + size());
        System.out.println("數值總和 (Sum) : " + getSum());
        Integer max = getMax();
        Integer min = getMin();
        System.out.println("最大數值 (Max) : " + (max == null ? "N/A (空串列)" : max));
        System.out.println("最小數值 (Min) : " + (min == null ? "N/A (空串列)" : min));
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        NumberHistoryList history = new NumberHistoryList();

        System.out.println("=== 測試 1：初始空串列統計 ===");
        history.printList();
        history.printStatistics();

        System.out.println("\n=== 測試 2：進行 8 次以上的操作 ===");

        history.addLast(50);
        history.printList();

        history.addFirst(30);
        history.printList();

        history.addLast(70);
        history.printList();

        history.addFirst(10);
        history.printList();

        boolean has30 = history.contains(30);
        System.out.println("[搜尋] 是否包含 30？ " + has30);

        boolean has99 = history.contains(99);
        System.out.println("[搜尋] 是否包含 99？ " + has99);

        history.printStatistics();

        boolean isRemoved30 = history.removeValue(30);
        System.out.println("[刪除] 刪除 30 是否成功？ " + isRemoved30);
        history.printList();

        boolean isRemoved10 = history.removeValue(10);
        System.out.println("[刪除] 刪除 10 是否成功？ " + isRemoved10);
        history.printList();

        boolean isRemoved99 = history.removeValue(99);
        System.out.println("[刪除] 刪除 99 是否成功？ " + isRemoved99);
        history.printList();

        history.printStatistics();
    }
}
