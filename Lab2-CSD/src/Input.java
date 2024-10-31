/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Input {
        
    private static final Scanner sc = new Scanner(System.in);

    public static int getInt(String message, String error,
            String invalid, int min, int max) {
        do {
            System.out.print(message);
            try {
                int num = Integer.parseInt(sc.nextLine());
                if (num >= min && num <= max) {
                    return num;
                }
                System.out.println(error);
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        } while (true);

    }

    public static double getDouble(String message, String error,
            String invalid, double min, double max) {
        do {
            System.out.print(message);
            try {
                double num = Double.parseDouble(sc.nextLine());
                if (num >= min && num <= max) {
                    return num;
                }
                System.out.println(error);
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        } while (true);

    }

    public static int getIntCond(int min, int max) {
        int n = getInt("", "err", "invalid", Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (n < min) {
            System.out.println("inp must be >= " + min);
            return getIntCond(min, max);
        }
        if (n > max) {
            System.out.println("inp must be <=" + max);
            return getIntCond(min, max);
        }
        return n;
    }

    public static double getDoubleCond(double min, double max) {
        double n = getDouble("", "err",
                "invalid", Double.MIN_VALUE, Double.MAX_VALUE);
        if (n < min) {
            System.out.println("inp must be >= " + min);
            return getDoubleCond(min, max);
        }
        if (n > max) {
            System.out.println("inp must be <= " + max);
            return getDoubleCond(min, max);
        }
        return n;
    }

    public static String getString(String messageInfo,
            String messageError, String invalid,
            final String REGEX) {
        do {
            System.out.print(messageInfo);
            try {
                String str = sc.nextLine();
                if (str.matches(REGEX)) {
                    return str;
                }
                System.err.println(messageError);
            } catch (Exception e) {
                System.err.println(invalid);
            }
        } while (true);
    }
}
