package utils;

import java.util.Scanner;

public class Utils {
    public Scanner scanner;
    private static Utils instance = null;

    private Utils() {
        scanner = new Scanner(System.in);
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public void releaseResouces() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitForKeypress() {
        System.out.print("\nPress ENTER to continue... ");
        Utils.getInstance().scanner.nextLine();
    }

}
