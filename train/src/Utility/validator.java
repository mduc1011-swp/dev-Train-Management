/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.util.Scanner;

/**
 *
 * @author phank
 */
public class validator {

    private static final Scanner sc = new Scanner(System.in);

    public static int getInt(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

    }

    public static double getDouble(String message, double min, double max) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

    }

    public static String getString(String messageInfo) {
        while (true) {
            System.out.print(messageInfo);

            String str = sc.nextLine().trim();
            if (str.matches("^[a-zA-Z0-9\\s]+$")) {
                return str;
            } else {

                System.err.println("Invalid input! pleas input only alphanumeric and spaces");
            }

        }
    }

}
