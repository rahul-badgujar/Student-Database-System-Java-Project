package menus;

import java.util.List;
import java.util.Scanner;

import jdk.jshell.execution.Util;
import utils.Utils;

public abstract class Menu {
    protected String menuTitle;
    protected List<String> menuItems;
    protected int optionsCount;

    protected Menu(String menuTitle) {
        this.menuTitle = menuTitle;
        this.menuItems = null;
        this.optionsCount = 0;
    }

    public void showMenu() {
        System.out.println("   " + this.menuTitle);
        System.out.println(formMenuString());
    }

    protected int takeMenuInput() {
        Scanner scanner = Utils.getInstance().scanner;
        System.out.print("Enter Your Choice: ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= optionsCount) {
            return choice;
        }
        return 0;
    }

    public void handleMenu() {
        int choice = takeMenuInput();
        actOnChoice(choice);
    }

    public abstract List<String> formItemsList();

    public abstract void actOnChoice(int choice);

    protected String formMenuString() {
        String menuStr = "";
        if (menuItems != null) {
            for (int i = 1; i <= this.optionsCount; i++) {
                String option = i + ". " + this.menuItems.get(i - 1) + "\n";
                menuStr = menuStr.concat(option);
            }
        }
        return menuStr;
    }
}
