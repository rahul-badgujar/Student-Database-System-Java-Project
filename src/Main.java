
import menus.MainMenu;
import menus.Menu;
import utils.Utils;

public class Main {

    public static void main(String[] args) {
        Utils.clearScreen();
        Menu mainMenu = new MainMenu();
        mainMenu.showMenu();
        Utils.getInstance().releaseResouces();
    }

}
