import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== 動態成績管理系統 =====");
        System.out.println("請輸入成績 (範圍 0 ~ 100，輸入 -1 結束)：");

        while (true) {
            System.out.print("請輸入成績：");
            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入整數成績！");
                scanner.next(); // clear invalid input
                continue;
            }

            int score = scanner.nextInt();
            if (score == -1) {
                break;
            }

            if (score < 0 || score > 100) {
                System.out.println("錯誤：成績必須介於 0 到 100 之間！");
                continue;
            }

            scores.add(score);
        }

        System.out.println("\n===== 統計結果 =====");
        if (scores.isEmpty()) {
            System.out.println("未輸入任何有效成績。");
        } else {
            printStatistics(scores);
            ArrayList<Integer> passedScores = filterPassed(scores);
            System.out.println("及格名單 (>= 60)：" + passedScores);
        }

        scanner.close();
    }

    // Method to calculate and print size, average, max, and min
    public static void printStatistics(ArrayList<Integer> scores) {
        if (scores == null || scores.isEmpty()) {
            return;
        }

        int count = scores.size();
        int sum = 0;
        int max = scores.get(0);
        int min = scores.get(0);

        for (int score : scores) {
            sum += score;
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }

        double average = (double) sum / count;

        System.out.println("總筆數：" + count + " 筆");
        System.out.printf("平均分數：%.2f 分%n", average);
        System.out.println("最高分數：" + max + " 分");
        System.out.println("最低分數：" + min + " 分");
    }

    // Method to filter scores that are 60 or above
    public static ArrayList<Integer> filterPassed(ArrayList<Integer> scores) {
        ArrayList<Integer> passed = new ArrayList<>();
        if (scores != null) {
            for (int score : scores) {
                if (score >= 60) {
                    passed.add(score);
                }
            }
        }
        return passed;
    }
}
