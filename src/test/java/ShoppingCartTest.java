import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {

    @Test
    public void testCalculateTotalCost() {
        String input = "100.0\n2\n200.0\n3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Scanner scanner = new Scanner(in);
        scanner.useLocale(Locale.US);
        PrintStream printStream = new PrintStream(out);

        ShoppingCart cart = new ShoppingCart(scanner, printStream);
        ResourceBundle rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));

        double totalCost = cart.calculateTotalCost(2, rb);

        assertEquals(800.0, totalCost);
    }
}