package menus;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {
    public static final String MENU_TITLE = "Main Menu";

    public MainMenu() {
        super(MENU_TITLE);

        this.menuItems = formItemsList();
        this.optionsCount = this.menuItems.size();
    }

    @Override
    public List<String> formItemsList() {
        List<String> items = new ArrayList<>();
        items.add("Add Student Record");
        items.add("Search Student Record");
        items.add("Delete Student Record");
        items.add("Update Student Record");
        items.add("Exit");
        return items;
    }

}
