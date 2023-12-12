package ec.edu.espe.icecream.utils;
import java.util.Scanner;
/**
 *
 * @author mateo
 */
public class Authenticator {
            private static final String allowedUsername = "WiliamGranda";
    private static final String storedHashedPassword = "5b5cbe834b5ba800aa8adcaeece7a60d79de982fe48d16e7fe98a8b4a08d1cb7"; // "Glacial"

    public static boolean authenticateUser(Scanner scanner) {
        System.out.println("Welcome to the Ice Cream System!");

        int attempts = 3;
        do {
            System.out.print("Enter your username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.nextLine();

            if (enteredUsername.equals(allowedUsername) && checkPassword(enteredPassword)) {
                System.out.println("Authentication successful! Welcome, " + enteredUsername + "!");
                return true;
            } else {
                attempts--;
                System.out.println("Authentication failed. " + attempts + " attempts remaining.");
            }
        } while (attempts > 0);

        System.out.println("Too many failed attempts. Exiting the Ice Cream System.");
        return false;
    }

    private static boolean checkPassword(String enteredPassword) {
        return enteredPassword.equals("Glacial");
    }
}