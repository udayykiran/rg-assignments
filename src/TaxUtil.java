public class TaxUtil {
    static double rate = 0.15;

    public static double calculateTax(double amount) {
        return amount * rate;
    }
}
