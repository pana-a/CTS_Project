package menus;

import interfaces.IMenu;

public abstract class AMenu implements IMenu {
    protected AMenu parentMenu;
    public AMenu(AMenu parentMenu){this.parentMenu= parentMenu;}

    @Override
    public void display() {

    }
}
