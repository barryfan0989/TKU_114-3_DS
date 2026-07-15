import java.util.Scanner;

public class ArrayStatistics {

    public static int readCount(Scanner sc) {
        int count = -1;
        while (true) {
            System.out.print("請輸入資料筆數（1～50）：");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                if (count >= 1 && count <= 50) {
                    break;
                } else {
                    System.out.println("錯誤：筆數必須在 1 到 50 之間！");
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數！");
                sc.next();
            }
        }
        return count;
    }

    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績（0～100）：");
                if (sc.hasNextInt()) {
                    int score = sc.nextInt();
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break;
                    } else {
                        System.out.println("錯誤：成績必須在 0 到 100 之間！");
                    }
                } else {
                    System.out.println("錯誤：請輸入有效的整數！");
                    sc.next();
                }
            }
        }
    }

    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    public static int countPass(int[] scores) {
        int count = 0;
        for (int score : scores) {
            if (score >= 60) {
                count++;
            }
        }
        return count;
    }

    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== 成績陣列統計系統 ===");
        int count = readCount(sc);
        int[] scores = new int[count];
        
        System.out.println("\n--- 開始輸入成績 ---");
        inputScores(sc, scores);
        
        System.out.println("\n--- 所有成績 ---");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i == scores.length - 1 ? "" : ", "));
        }
        System.out.println();
        
        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);
        int passCount = countPass(scores);
        int failCount = scores.length - passCount;
        
        System.out.println("\n--- 統計結果 ---");
        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f%n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount);
        System.out.println("不及格人數：" + failCount);
        
        System.out.println("\n--- 成績搜尋 ---");
        System.out.print("請輸入要搜尋的目標成績：");
        if (sc.hasNextInt()) {
            int target = sc.nextInt();
            int index = findIndex(scores, target);
            if (index >= 0) {
                System.out.println("成績 " + target + " 第一次出現在索引 " + index + "（第 " + (index + 1) + " 筆）");
            } else {
                System.out.println("找不到成績 " + target);
            }
        } else {
            System.out.println("輸入的搜尋目標不是有效的整數！");
        }
        
        sc.close();
        System.out.println("\n感謝使用成績統計系統！");
    }
}
