package menus;

import java.util.List;
import java.util.Scanner;
import utils.Utils;

public abstract class Menu {
    protected String menuTitle;
    protected List<String> menuItems;
    protected int optionsCount;
    protected boolean isLive;

    protected Menu(String menuTitle) {
        this.menuTitle = menuTitle;
        this.menuItems = null;
        this.optionsCount = 0;
        this.isLive = true;
    }

    public void showMenu() {
        while (this.isLive) {
            Utils.clearScreen();
            System.out.println("\t" + this.menuTitle);
            System.out.println(formMenuString());
            handleMenu();
            if (this.isLive)
                Utils.waitForKeypress();
        }
    }

    protected Integer takeMenuInput() {
        Scanner scanner = Utils.getInstance().scanner;
        System.out.print("Enter Your Choice: ");
        Integer choice = Integer.valueOf(scanner.nextLine());
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

    public abstract void actOnChoice(Integer choice);

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

    protected void closeMenu() {
        this.isLive = false;
    }
}
