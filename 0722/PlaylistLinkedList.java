public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean containsCode(String code) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.code.equals(code)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addLast(String code, String name) {
        if (containsCode(code)) {
            System.out.println("[錯誤] 歌曲代碼 " + code + " 已存在，無法重複新增！");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(code, name);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[成功] 已將歌曲 [" + code + "] " + name + " 加至播放清單尾端。");
        return true;
    }

    public PlaylistNode searchByCode(String code) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.code.equals(code)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean deleteByCode(String code) {
        if (head == null) {
            System.out.println("[刪除失敗] 播放清單為空。");
            return false;
        }

        if (head.code.equals(code)) {
            System.out.println("[成功] 刪除首首歌曲：[" + head.code + "] " + head.name);
            head = head.next;
            size--;
            return true;
        }

        PlaylistNode previous = head;
        PlaylistNode current = head.next;
        while (current != null) {
            if (current.code.equals(code)) {
                System.out.println("[成功] 刪除歌曲：[" + current.code + "] " + current.name);
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("[刪除失敗] 找不到歌曲代碼為 " + code + " 的項目。");
        return false;
    }

    public void printPlaylist() {
        System.out.print("播放清單 (長度 " + size + ")：");
        if (head == null) {
            System.out.println("Empty (無任何歌曲)");
            return;
        }
        PlaylistNode current = head;
        while (current != null) {
            System.out.print("[" + current.code + ": " + current.name + "] -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
