import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入整數 (輸入 0 結束)：");
        int num = sc.nextInt();

        while (num != 0) {
            System.out.println("你輸入了：" + num);
            System.out.print("請輸入整數 (輸入 0 結束)：");
            num = sc.nextInt();
        }

        sc.close();
    }
}
