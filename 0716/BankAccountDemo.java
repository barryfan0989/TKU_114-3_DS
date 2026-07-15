public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount first = new BankAccount("A001", "Amy", 5000);
        BankAccount second = new BankAccount("A002", "Ben", 1000);

        System.out.println("Amy 存款 500 成功：" + first.deposit(500));
        System.out.println("Ben 提款 300 成功：" + second.withdraw(300));
        System.out.println("Amy 轉帳 2000 給 Ben 成功：" + first.transferTo(second, 2000));
        System.out.println("Ben 超額轉帳 10000 給 Amy 成功：" + second.transferTo(first, 10000));

        System.out.println(first);
        System.out.println(second);
    }
}
