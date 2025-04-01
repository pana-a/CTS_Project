package models.menus;

import interfaces.IMenu;

import java.util.Scanner;

public class MainMenu extends AMenu implements IMenu {

    public MainMenu(AMenu parentMenu) {
        super(parentMenu);
    }

    @Override
    public void display() {
        System.out.println("---Main Menu---");
        System.out.println("1. Meniu kebap");
        System.out.println("2. Meniu sosuri");
        System.out.println("0. Iesire");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        KebapMenu kebapMenu = new KebapMenu(this);
        SauceMenu sauceMenu = new SauceMenu(this);

        if(option.equals("0")) {
            System.out.println("Inchidere...");
        }
        else {
            switch(option){
                case "1" :
                    kebapMenu.display();
                    break;
                case "2" :
                    sauceMenu.display();
                    break;
                default:
                    System.out.println("Ati introdus o valoare invalida!");
                    display();
                    break;
            }
        }
    }

}