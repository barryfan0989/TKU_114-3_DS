import java.util.Scanner;

public class TextAnalyzer {

    public static String readNonEmptyText(Scanner sc) {
        String text;
        while (true) {
            System.out.print("請輸入一行非空白文字：");
            text = sc.nextLine();
            if (text != null && !text.trim().isEmpty()) {
                break;
            }
            System.out.println("錯誤：輸入不能為空或全空白，請重新輸入！");
        }
        return text;
    }

    public static int getWordCount(String text) {
        String cleaned = text.trim();
        if (cleaned.isEmpty()) {
            return 0;
        }
        String[] words = cleaned.split("\\s+");
        return words.length;
    }

    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String text) {
        String cleaned = text.trim();
        if (cleaned.isEmpty()) {
            return "";
        }
        String[] words = cleaned.split("\\s+");
        String longest = words[0];
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }
        return longest;
    }

    public static int countKeywordOccurrences(String text, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return 0;
        }
        String lowerText = text.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();
        int count = 0;
        int index = 0;
        while ((index = lowerText.indexOf(lowerKeyword, index)) != -1) {
            count++;
            index += lowerKeyword.length();
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== 文字分析器 ===");
        
        String input = readNonEmptyText(sc);
        
        System.out.println("\n--- 分析結果 ---");
        System.out.println("原始字元數：" + input.length());
        
        String trimmed = input.trim();
        System.out.println("有效字元數 (trim 後)：" + trimmed.length());
        
        int wordCount = getWordCount(input);
        System.out.println("單字數量：" + wordCount);
        
        int vowelCount = countVowels(input);
        System.out.println("母音 (a, e, i, o, u) 總數：" + vowelCount);
        
        String longestWord = findLongestWord(input);
        System.out.println("最長單字：" + longestWord);
        
        System.out.print("\n請輸入搜尋關鍵字：");
        String keyword = sc.nextLine();
        int keywordCount = countKeywordOccurrences(input, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數：" + keywordCount);
        
        sc.close();
        System.out.println("\n分析結束，感謝使用！");
    }
}
