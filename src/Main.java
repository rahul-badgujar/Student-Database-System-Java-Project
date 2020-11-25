import java.sql.SQLException;

import data_models.StudentModel;
import db_helpers.tables.StudentTable;
import menus.MainMenu;
import menus.Menu;

public class Main {

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.showMenu();
        mainMenu.handleMenu();
    }

}
