package ec.edu.espe.icecream.utils;

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
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = dayValidator(month, 31);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = dayValidator(month, 30);
                break;
            case 2:
                day = dayValidator(month, 28);
                break;
            default:
                break;
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
