import menus.MainMenu;
import menus.Menu;

public class Main {

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.showMenu();
        int choice = mainMenu.takeMenuInput();
        System.out.println(choice);
    }

}
