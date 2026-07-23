public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("=== 1. 測試空工作項目系統 ===");
        taskList.printAllTasks();
        taskList.printUncompletedTasks();
        taskList.removeTask("T001");
        taskList.markComplete("T001");

        System.out.println("\n=== 2. 測試新增工作項目 ===");
        taskList.addRegular("T001", "撰寫資料結構報告");
        taskList.addRegular("T002", "準備期末考複習");
        taskList.addUrgent("T003", "完成7/22作業 (緊急)");
        taskList.addRegular("T004", "整理電腦桌面檔案");
        taskList.addUrgent("T005", "回覆教授重要信件 (緊急)");
        taskList.printAllTasks();

        System.out.println("\n=== 3. 測試代碼重複新增檢查 ===");
        taskList.addRegular("T001", "重複的工作");
        taskList.addUrgent("T003", "重複的工作");

        System.out.println("\n=== 4. 測試標記完成狀態 ===");
        taskList.markComplete("T003");
        taskList.markComplete("T001");
        taskList.markComplete("T999");
        taskList.printAllTasks();

        System.out.println("\n=== 5. 測試僅列出未完成工作 ===");
        taskList.printUncompletedTasks();

        System.out.println("\n=== 6. 測試刪除首節點工作 (T005) ===");
        taskList.removeTask("T005");
        taskList.printAllTasks();

        System.out.println("\n=== 7. 測試刪除尾節點工作 (T004) ===");
        taskList.removeTask("T004");
        taskList.printAllTasks();

        System.out.println("\n=== 8. 測試刪除中間節點工作 (T001) ===");
        taskList.removeTask("T001");
        taskList.printAllTasks();

        System.out.println("\n=== 9. 測試全部標記完成與剩餘刪除 ===");
        taskList.markComplete("T002");
        taskList.printAllTasks();
        taskList.printUncompletedTasks();
        taskList.removeTask("T003");
        taskList.removeTask("T002");
        taskList.printAllTasks();
    }
}
