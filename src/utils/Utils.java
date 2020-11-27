package utils;

import java.util.Scanner;

public class Utils {
    public Scanner scanner;
    private static Utils instance = null;

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";

    private Utils() {
        scanner = new Scanner(System.in);
    }

    public static void printlnWithColor(String text, String color) {
        System.out.println(color + text);
        System.out.print(ANSI_RESET);
    }

    public static void printWithColor(String text, String color) {
        System.out.print(color + text);
        System.out.print(ANSI_RESET);
    }

    public static void printError(String text) {
        System.out.print(ANSI_RED + text);
        System.out.print(ANSI_RESET);
    }

    public static void printlnError(String text) {
        System.out.println(ANSI_RED + text);
        System.out.print(ANSI_RESET);
    }

    public static void printSuccess(String text) {
        System.out.print(ANSI_GREEN + text);
        System.out.print(ANSI_RESET);

    }

    public static void printlnSuccess(String text) {
        System.out.println(ANSI_GREEN + text);
        System.out.print(ANSI_RESET);

    }

    public static void printException(String text) {
        System.out.print(ANSI_BRIGHT_RED + text);
        System.out.print(ANSI_RESET);
    }

    public static void printlnException(String text) {
        System.out.println(ANSI_BRIGHT_RED + text);
        System.out.print(ANSI_RESET);
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
