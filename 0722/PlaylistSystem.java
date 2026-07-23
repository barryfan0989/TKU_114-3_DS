public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("=== 1. 測試空清單操作 ===");
        playlist.printPlaylist();
        playlist.deleteByCode("S001");

        System.out.println("\n=== 2. 測試歌曲新增 ===");
        playlist.addLast("S001", "Song A");
        playlist.addLast("S002", "Song B");
        playlist.addLast("S003", "Song C");
        playlist.addLast("S004", "Song D");
        playlist.printPlaylist();

        System.out.println("\n=== 3. 測試歌曲防重機制 ===");
        playlist.addLast("S002", "Duplicate Song B");
        playlist.printPlaylist();

        System.out.println("\n=== 4. 測試依代碼搜尋歌曲 ===");
        PlaylistNode found = playlist.searchByCode("S003");
        if (found != null) {
            System.out.println("[搜尋成功] 找到了歌曲: [" + found.code + "] " + found.name);
        } else {
            System.out.println("[搜尋失敗] 找不到 S003");
        }

        PlaylistNode notFound = playlist.searchByCode("S999");
        if (notFound != null) {
            System.out.println("[搜尋成功] 找到了歌曲: [" + notFound.code + "] " + notFound.name);
        } else {
            System.out.println("[搜尋失敗] 找不到 S999");
        }

        System.out.println("\n=== 5. 測試刪除中間節點 (S002) ===");
        playlist.deleteByCode("S002");
        playlist.printPlaylist();

        System.out.println("\n=== 6. 測試刪除尾節點 (S004) ===");
        playlist.deleteByCode("S004");
        playlist.printPlaylist();

        System.out.println("\n=== 7. 測試刪除首節點 (S001) ===");
        playlist.deleteByCode("S001");
        playlist.printPlaylist();

        System.out.println("\n=== 8. 測試刪除唯一節點 (S003) 使其變空 ===");
        playlist.deleteByCode("S003");
        playlist.printPlaylist();

        System.out.println("\n=== 9. 測試找不到刪除與空清單刪除 ===");
        playlist.deleteByCode("S999");
    }
}
