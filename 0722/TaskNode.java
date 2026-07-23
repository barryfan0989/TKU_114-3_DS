public class TaskNode {
    String code;
    String description;
    boolean isCompleted;
    TaskNode next;

    public TaskNode(String code, String description) {
        this.code = code;
        this.description = description;
        this.isCompleted = false;
        this.next = null;
    }
}
