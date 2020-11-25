package menus;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;
    protected String menuTitle;
    protected List<String> menuItems;
    protected int optionsCount;

    protected Menu(String menuTitle) {
        this.menuTitle = menuTitle;
        this.menuItems = null;
        this.optionsCount = 0;
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println(formMenuString());
    }

    public int takeMenuInput() {
        System.out.print("Enter Your Choice: ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= optionsCount) {
            return choice;
        }
        return 0;
    }

    public abstract List<String> formItemsList();

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
