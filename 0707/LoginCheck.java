public class LoginCheck {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";

        boolean loginSuccess = username.equals(inputUsername) && password.equals(inputPassword);

        System.out.println("Username: " + inputUsername);
        System.out.println("Password: " + inputPassword);
        System.out.println("Login Success: " + loginSuccess);
    }
}
