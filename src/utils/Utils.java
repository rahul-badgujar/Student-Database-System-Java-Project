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

}
