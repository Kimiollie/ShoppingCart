import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {
    private final Scanner scanner;
    private final PrintStream out;

    public ShoppingCart(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    public void run() {
        out.println("Select a language");
        out.println("1. Finnish");
        out.println("2. Swedish");
        out.println("3. Japanese");

        int choice = scanner.nextInt();
        Locale locale = getLocale(choice);

        ResourceBundle rb = getResourceBundle(locale);

        out.print(rb.getString("itemNumber"));
        int numberOfItems = scanner.nextInt();
        scanner.nextLine();

        double totalCost = calculateTotalCost(numberOfItems, rb);

        out.printf(rb.getString("total"), totalCost);
    }

    private Locale getLocale(int choice) {
        switch (choice) {
            case 1:
                return new Locale("fi", "FI");
            case 2:
                return new Locale("sv", "SE");
            case 3:
                return new Locale("ja", "JP");
            default:
                out.println("Invalid choice. Using English as default.");
                return new Locale("en", "US");
        }
    }

    private ResourceBundle getResourceBundle(Locale locale) {
        try {
            return ResourceBundle.getBundle("messages", locale);
        } catch (Exception e) {
            out.println("Invalid choice. Using English as default.");
            return ResourceBundle.getBundle("messages", new Locale("en", "US"));
        }
    }

    public double calculateTotalCost(int numberOfItems, ResourceBundle rb) {
        double totalCost = 0.0;
        for (int i = 0; i < numberOfItems; i++) {
            out.printf(rb.getString("itemPrice"), i + 1);
            double price = scanner.nextDouble();
            out.printf(rb.getString("itemQuantity"), i + 1);
            int quantity = scanner.nextInt();
            scanner.nextLine();

            double itemTotalCost = price * quantity;
            totalCost += itemTotalCost;
        }
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintStream out = System.out;
        new ShoppingCart(scanner, out).run();
    }
}