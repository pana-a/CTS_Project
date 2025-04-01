package menus;

import interfaces.IMenu;
import models.sauces.FermentableSauce;
import models.sauces.Sauce;
import utils.SauceManager;
import utils.SauceSerializer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SauceMenu extends AMenu implements IMenu {

    SauceManager sauceManager = SauceManager.getInstance();

    public SauceMenu(AMenu parentMenu) {
        super(parentMenu);
    }

    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Sauce Menu---");
        System.out.println("1. Creare sos");
        System.out.println("2. Stergere sos");
        System.out.println("3. Serializare lista de sosuri");
        System.out.println("4. Deserializare lista de sosuri");
        System.out.println("0. Inapoi");

        String option = scanner.nextLine();

        if(option.equals("0")) {
            parentMenu.display();
        }
        else {
            switch (option) {
                case "1":
                    handleSauceCreation(scanner);
                    break;
                case "2":
                    handleSauceDeletion(scanner);
                    break;
                case "3":
                    handleSauceSerialization(scanner);
                    break;
                case "4":
                    handleSauceDeserialization(scanner);
                    break;
                default:
                    System.out.println("Ati introdus o valoare invalida!");
                    break;
            }
            display();
        }
    }

    public void handleSauceDeletion(Scanner scanner) {
        if(sauceManager.getSauces().isEmpty()) {
            System.out.println("Nu exista sosuri!");
        }
        else {
            System.out.println("Selectati numarul sosului pe care doriti sa il stergeti");
            sauceManager.displaySauces();
            String option = scanner.nextLine();

            // check for strictly positive options too due to Integer.parseInt() cast being able to convert "-1" into a valid index:
            while(Integer.parseInt(option) <= 0 || Integer.parseInt(option) > sauceManager.getSauces().size())
            {
                System.out.println("Ati introdus o valoare invalida!");
                option = scanner.nextLine();
            }
            // option-1 because the options start from 1:
            sauceManager.deleteSauce(Integer.parseInt(option) - 1);
        }
    }

    public void handleSauceCreation(Scanner scanner){
        System.out.println("Introduceti numele sosului: ");
        String name = scanner.nextLine();
        System.out.println("Fermenteaza?");
        System.out.println("\t1. DA");
        System.out.println("\t2. NU");
        String option = scanner.nextLine();

        while(!option.equals("1") && !option.equals("2")){
            System.out.println("Ati introdus o valoare invalida");
            option = scanner.nextLine();
        }

        Sauce sauce = null;
        if(option.equals("1")){
            int expiryHours = handleExpiryDateInput(scanner);
            sauce = new FermentableSauce(name, expiryHours);
            sauceManager.addSauce(sauce);
        }
        else {
            sauce = new Sauce(name);
            sauceManager.addSauce(sauce);
        }

        if(sauce != null) {
            System.out.println("Am creat " + sauce);
        }
    }

    public int handleExpiryDateInput(Scanner scanner) {
        // Reset the scanner so it doesn't enter an infinite loop:
        scanner = new Scanner(System.in);

        System.out.println("Introduceti durata de valabilitate (ore): ");
        int choice;
        try {
            choice = scanner.nextInt();
        }
        // Gracefully handle non-int customer input:
        catch(InputMismatchException e) {
            System.out.println("Durata invalida!");
            choice = handleExpiryDateInput(scanner);
        }
        return choice;
    }

    public void handleSauceSerialization(Scanner scanner){
        if (SauceManager.getInstance().getSauces().isEmpty()){
            System.out.println("Nu exista sosuri de serializat");
            return;
        }
        System.out.println("Introduceti numele fisierului unde doriti sa salvati lista serializata:");
        String fileName = scanner.nextLine();
        SauceSerializer.write(SauceManager.getInstance().getSauces(), fileName);
    }

    public void handleSauceDeserialization(Scanner scanner){
        System.out.println("Introduceti numele fisierului de unde doriti sa preluati lista cu sosuri");
        String fileName = scanner.nextLine();
        List<Sauce> sauces = SauceSerializer.read(fileName);
        for(Sauce sauce:sauces){
            System.out.println(sauce);
        }
    }
}
