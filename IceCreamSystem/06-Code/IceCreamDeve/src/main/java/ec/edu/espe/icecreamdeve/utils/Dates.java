package ec.edu.espe.icecreamdeve.utils;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class Dates {
    
    static Scanner scan = new Scanner(System.in);
    
    public static Date validatedate() {
        int month;
        int day = 0;
        do {
            try {
                System.out.println("Type the month(In numbers): ");
                month = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scan.nextLine();
                month = -1;
            }
        } while (month <= 0 || month > 12);
        switch (month-1) {
            case 1, 3, 5, 7, 8, 10, 12 -> day = dayValidator(month, 31);
            case 4, 6, 9, 11 -> day = dayValidator(month, 30);
            case 2 -> day = dayValidator(month, 28);
            default -> {
            }
        }
        
        return new Date(2023 - 1900, month-1, day);
    }

    public static int dayValidator(int month, int dayspermonth) {
        int day;
        do {
            try {
                System.out.println("Type the day(In numbers)");
                day = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scan.nextLine();
                day = -1;
            }
        } while (day > dayspermonth || day <= 0);
        return day;
    }
    
}
