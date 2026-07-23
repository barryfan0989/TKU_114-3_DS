public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean containsCode(String code) {
        TaskNode current = head;
        while (current != null) {
            if (current.code.equals(code)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addUrgent(String code, String description) {
        if (containsCode(code)) {
            System.out.println("[錯誤] 工作代碼 " + code + " 已存在，無法重複新增！");
            return false;
        }
        TaskNode newNode = new TaskNode(code, description);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("[緊急新增] [" + code + "] " + description);
        return true;
    }

    public boolean addRegular(String code, String description) {
        if (containsCode(code)) {
            System.out.println("[錯誤] 工作代碼 " + code + " 已存在，無法重複新增！");
            return false;
        }
        TaskNode newNode = new TaskNode(code, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[一般新增] [" + code + "] " + description);
        return true;
    }

    public boolean markComplete(String code) {
        TaskNode current = head;
        while (current != null) {
            if (current.code.equals(code)) {
                if (current.isCompleted) {
                    System.out.println("[提示] 工作 [" + code + "] 已處於完成狀態。");
                    return true;
                }
                current.isCompleted = true;
                System.out.println("[完成工作] 已將工作 [" + code + "] 標記為完成。");
                return true;
            }
            current = current.next;
        }
        System.out.println("[提示] 找不到工作代碼：" + code);
        return false;
    }

    public boolean removeTask(String code) {
        if (head == null) {
            System.out.println("[刪除失敗] 工作清單為空。");
            return false;
        }

        if (head.code.equals(code)) {
            System.out.println("[成功] 刪除工作：[" + head.code + "] " + head.description);
            head = head.next;
            size--;
            return true;
        }

        TaskNode previous = head;
        TaskNode current = head.next;
        while (current != null) {
            if (current.code.equals(code)) {
                System.out.println("[成功] 刪除工作：[" + current.code + "] " + current.description);
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("[刪除失敗] 找不到工作代碼：" + code);
        return false;
    }

    public int getTotalCount() {
        return size;
    }

    public int getUncompletedCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

    public void printAllTasks() {
        System.out.println("--- 所有工作清單 (總共 " + getTotalCount() + "，未完成 " + getUncompletedCount() + ") ---");
        if (head == null) {
            System.out.println("(無工作項目)");
            return;
        }
        TaskNode current = head;
        while (current != null) {
            String status = current.isCompleted ? "[v] 已完成" : "[ ] 未完成";
            System.out.println(status + " - [" + current.code + "] " + current.description);
            current = current.next;
        }
        System.out.println("----------------------------------------------");
    }

    public void printUncompletedTasks() {
        System.out.println("--- 未完成工作清單 ---");
        TaskNode current = head;
        boolean hasUncompleted = false;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("[ ] - [" + current.code + "] " + current.description);
                hasUncompleted = true;
            }
            current = current.next;
        }
        if (!hasUncompleted) {
            System.out.println("(無未完成工作)");
        }
        System.out.println("----------------------");
    }
}
