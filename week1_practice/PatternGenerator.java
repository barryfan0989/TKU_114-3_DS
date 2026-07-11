import java.util.Scanner;

public class PatternGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            if (!sc.hasNextInt()) {
                System.out.println("無效的選項，請輸入數字。");
                sc.next();
                System.out.println();
                continue;
            }
            option = sc.nextInt();

            if (option == 0) {
                System.out.println("謝謝使用，再見！");
                break;
            }

            switch (option) {
                case 1:
                    System.out.println("=== 九九乘法表 ===");
                    printMultiplicationTable();
                    break;
                case 2:
                    int heightTri = readPositiveInt(sc, "請輸入正三角形高度：");
                    printTriangle(heightTri);
                    break;
                case 3:
                    int heightRevTri = readPositiveInt(sc, "請輸入倒三角形高度：");
                    printReverseTriangle(heightRevTri);
                    break;
                case 4:
                    int rows = readPositiveInt(sc, "請輸入列數（rows）：");
                    int cols = readPositiveInt(sc, "請輸入欄數（cols）：");
                    printNumberGrid(rows, cols);
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
            System.out.println();
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Pattern & Table Generator ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int val = -1;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                val = sc.nextInt();
                if (val > 0) {
                    break;
                } else {
                    System.out.println("數值必須大於 0，請重新輸入。");
                }
            } else {
                System.out.println("輸入格式錯誤，請輸入整數。");
                sc.next();
            }
        }
        return val;
    }

    public static void printMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(j + "x" + i + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + (j == cols ? "" : " "));
            }
            System.out.println();
        }
    }
}
