import java.sql.Connection;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConsoleMenu menu = new ConsoleMenu();
        menu.executeMenu();
    }
}
