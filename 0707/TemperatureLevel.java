public class TemperatureLevel {
    public static void main(String[] args) {
        double temperature = 25.5;
        System.out.println("Temperature: " + temperature);

        if (temperature < 15) {
            System.out.println("Cold");
        } else if (temperature < 28) {
            System.out.println("Comfortable");
        } else {
            System.out.println("Hot");
        }
    }
}
