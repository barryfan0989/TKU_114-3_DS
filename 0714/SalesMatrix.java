import java.util.Scanner;

public class SalesMatrix {

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.print("請輸入商品 " + (i + 1) + " 第 " + (j + 1) + " 天的銷售量：");
                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();
                        if (value >= 0) {
                          sales[i][j] = value;
                          break;
                        } else {
                          System.out.println("錯誤：銷售量不得小於 0！");
                        }
                    } else {
                        System.out.println("錯誤：請輸入有效的整數！");
                        sc.next();
                    }
                }
            }
        }
    }

    public static void printSalesTable(int[][] sales) {
        System.out.println("\n===== 銷售量報表表格 =====");
        System.out.printf("%-10s %8s %8s %8s %8s%n", "商品\\天數", "第 1 天", "第 2 天", "第 3 天", "第 4 天");
        for (int i = 0; i < sales.length; i++) {
            System.out.printf("商品 %-7d", (i + 1));
            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%10d", sales[i][j]);
            }
            System.out.println();
        }
        System.out.println("=========================");
    }

    public static int[] calculateProductTotals(int[][] sales) {
        int[] productTotals = new int[sales.length];
        for (int i = 0; i < sales.length; i++) {
            int sum = 0;
            for (int j = 0; j < sales[i].length; j++) {
                sum += sales[i][j];
            }
            productTotals[i] = sum;
        }
        return productTotals;
    }

    public static int[] calculateDayTotals(int[][] sales) {
        int[] dayTotals = new int[sales[0].length];
        for (int j = 0; j < sales[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < sales.length; i++) {
                sum += sales[i][j];
            }
            dayTotals[j] = sum;
        }
        return dayTotals;
    }

    public static int findTopProduct(int[] productTotals) {
        int maxIdx = 0;
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > productTotals[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        System.out.println("=== 銷售矩陣輸入系統 ===");
        inputSales(sc, sales);

        printSalesTable(sales);

        int[] productTotals = calculateProductTotals(sales);
        System.out.println("\n--- 每項商品總銷售量 ---");
        for (int i = 0; i < productTotals.length; i++) {
            System.out.println("商品 " + (i + 1) + " 總銷售量：" + productTotals[i]);
        }

        int[] dayTotals = calculateDayTotals(sales);
        System.out.println("\n--- 每日全部商品總銷售量 ---");
        for (int j = 0; j < dayTotals.length; j++) {
            System.out.println("第 " + (j + 1) + " 天總銷售量：" + dayTotals[j]);
        }

        int topProductIdx = findTopProduct(productTotals);
        System.out.println("\n--- 銷售冠軍統計 ---");
        System.out.println("總銷售量最高的商品為：商品 " + (topProductIdx + 1) 
            + "，總銷售量為：" + productTotals[topProductIdx]);

        sc.close();
        System.out.println("\n感謝使用銷售矩陣報表系統！");
    }
}
