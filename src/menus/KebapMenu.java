package menus;

import interfaces.IMenu;
import models.ingredients.*;
import models.kebap.Kebap;
import models.kebap.KebapBuilder;
import models.sauces.Sauce;
import utils.KebapManager;
import utils.SauceManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KebapMenu extends AMenu implements IMenu {
    private Scanner scanner = new Scanner(System.in);
    KebapManager kebapManager = KebapManager.getInstance();

    public KebapMenu(AMenu parentMenu) {
        super(parentMenu);
    }

    @Override
    public void display() {
        System.out.println("---Kebap Menu---");
        System.out.println("1. Creare kebap");
        System.out.println("2. Stergere kebap");
        System.out.println("3. Listare");
        System.out.println("4. Filtrare");
        System.out.println("0. Inapoi");

        String option = scanner.nextLine();
        if(option.equals("0")) {
            parentMenu.display();
        }
        else {
            switch(option){
                case "1":
                    handleKebapCreation(scanner);
                    break;
                case "2":
                    handleKebapDeletion(scanner);
                    break;
                case"3":
                    kebapManager.displayKebaps();
                    break;
                case "4":
                    handleKebapsFiltering(scanner);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Ati introdus o valoare invalida!");
                    break;
            }
            display();
        }
    }

    public void handleKebapDeletion(Scanner scanner) {
        if(kebapManager.getKebaps().isEmpty()) {
            System.out.println("Nu exista kebapuri");
        }
        else {
            try  {
                kebapManager.displayKebaps();
                String option = scanner.nextLine();

                if(Integer.parseInt(option) <= 0 || Integer.parseInt(option) > kebapManager.getKebaps().size()) {
                    System.out.println("Ati introdus o valoare invalida!");
                    handleKebapDeletion(scanner);
                }

                kebapManager.deleteKebap(Integer.parseInt(option) - 1);
            }
            catch(Exception e) {
                System.out.println("Ati introdus o valoare invalida!");
                handleKebapDeletion(scanner);
            }
        }
    }

    public void handleKebapCreation(Scanner scanner) {
        Protein protein = handleKebapProteinSelection(scanner);
        if(protein != null) {
            //TODO: Sa ne asiguram ca s-a adaugat proteina, e obligatorie
            System.out.println(protein);
        }

        Carbohydrate carbohydrate = handleKebapCarbohydrateSelection(scanner);
        if(carbohydrate != null) {
            System.out.println(carbohydrate);
        }

        List<Sauce> kebapSauces = new ArrayList<>();
        for(int i =0; i<3; i++) {
            Sauce sauce = handleKebapAddSauce(scanner);
            if(sauce != null) {
                kebapSauces.add(sauce);
            }
        }



        Fiber fiber = handleKebapAddFiber(scanner);
        Healthy healthy = handleKebapAddHealthy(scanner);
        Pickle pickle = handleKebapAddPickle(scanner);
        Wrap wrap = handleKebapAddWrap(scanner);
        if(protein != null && carbohydrate != null) {
            KebapBuilder kebapBuilder = new KebapBuilder(protein, carbohydrate);
            for(Sauce sauce : kebapSauces) {
                kebapBuilder.addSauce(sauce);
            }
            kebapBuilder
                    .addFiber(fiber)
                    .addHealthy(healthy)
                    .addPickle(pickle)
                    .addWrap(wrap);

            Kebap kebap = kebapBuilder.build();

            kebapManager.addKebap(kebap);
        }
        else {
            System.out.println("Ceva s-a intamplat gresit, incearca din nou");
            handleKebapCreation(scanner);
        }
    }

    public Protein handleKebapProteinSelection(Scanner scanner) {
        Protein protein = null;
        scanner = new Scanner(System.in);
        Protein.PROTEIN_TYPES[] proteinTypes = Protein.PROTEIN_TYPES.values();
        try{
            System.out.println("Alegeti proteina: ");
            for(int i =0; i< proteinTypes.length; i++) {
                System.out.println(i+1 + ". " + proteinTypes[i]);
            }
            int option = scanner.nextInt();

            if(option < 1 || option > proteinTypes.length) {
                System.out.println("Optiune invalida!");
                return handleKebapProteinSelection(scanner);
            }

            protein = new Protein(proteinTypes[option-1].toString());
        }
        catch(InputMismatchException e) {
            System.out.println("Optiune invalida!");
            protein = handleKebapProteinSelection(scanner);
        }
        return protein;
    }

    public Carbohydrate handleKebapCarbohydrateSelection(Scanner scanner) {
        Carbohydrate carbohydrate = null;
        scanner = new Scanner(System.in);

        Carbohydrate.CARBOHYDRATE_TYPES[] carbohydrateTypes = Carbohydrate.CARBOHYDRATE_TYPES.values();
        try{
            System.out.println("Alegeti sursa de carbohidrati: ");
            for(int i =0; i< carbohydrateTypes.length; i++) {
                System.out.println(i+1 + ". " + carbohydrateTypes[i]);
            }
            int option = scanner.nextInt();

            if(option < 1 || option > carbohydrateTypes.length) {
                System.out.println("Optiune invalida!");
                return handleKebapCarbohydrateSelection(scanner);
            }
            else {
                carbohydrate = new Carbohydrate(carbohydrateTypes[option-1].toString());
            }

        }
        catch(InputMismatchException e) {
            System.out.println("Optiune invalida!");
            carbohydrate = handleKebapCarbohydrateSelection(scanner);
        }
        return carbohydrate;
    }

    public Sauce handleKebapAddSauce(Scanner scanner) {
        Sauce sauce = null;
        scanner = new Scanner(System.in);

        SauceManager sauceManager = SauceManager.getInstance();
        System.out.println("Alegeti sosul: ");

        sauceManager.displaySauces();
        System.out.println("0. Nu doresc sos");
        int option = scanner.nextInt();

        if(option == 0) {
            return null;
        }

        if(option < 1 || option > sauceManager.getSauces().size()) {
            System.out.println("Optiune invalida");
            sauce = handleKebapAddSauce(scanner);
        }
        else {
            sauce = sauceManager.getSauces().get(option-1);
        }
        return sauce;
    }

    public Fiber handleKebapAddFiber(Scanner scanner) {
        Fiber fiber = null;
        scanner = new Scanner(System.in);
        Fiber.FIBER_TYPES[] fiberTypes = Fiber.FIBER_TYPES.values();
        try{
            System.out.println("Alegeti fibra: ");
            for(int i =0; i< fiberTypes.length; i++) {
                System.out.println(i+1 + ". " + fiberTypes[i]);
            }
            System.out.println("0. Nu doresc fibra");
            int option = scanner.nextInt();

            if(option == 0) {
                return null;
            }

            if(option < 1 || option > fiberTypes.length) {
                System.out.println("Optiune invalida!");
                return handleKebapAddFiber(scanner);
            }

            fiber = new Fiber(fiberTypes[option-1].toString());
        }
        catch(InputMismatchException e) {
            System.out.println("Optiune invalida!");
            fiber = handleKebapAddFiber(scanner);
        }
        return fiber;
    }

    public Healthy handleKebapAddHealthy(Scanner scanner) {
        Healthy healthy=null;
        scanner = new Scanner(System.in);
        Healthy.HEALTHY_TYPES[] healthyTypes = Healthy.HEALTHY_TYPES.values();
        try{
            System.out.println("Alegeti healthy: ");
            for(int i =0; i<healthyTypes.length; i++) {
                System.out.println(i+1 + ". " + healthyTypes[i]);
            }
            System.out.println("0. Nu doresc healthy");
            int option = scanner.nextInt();

            if(option == 0) {
                return null;
            }

            if(option < 1 || option > healthyTypes.length) {
                System.out.println("Optiune invalida!");
                return  handleKebapAddHealthy(scanner);
            }

            healthy = new Healthy(healthyTypes[option-1].toString());
        }
        catch(InputMismatchException e) {
            System.out.println("Optiune invalida!");
            healthy = handleKebapAddHealthy(scanner);
        }
        return healthy;
    }

    public Pickle handleKebapAddPickle(Scanner scanner) {
        Pickle pickle = null;
        scanner = new Scanner(System.in);
        Pickle.PICKLE_TYPES[] pickleTypes = Pickle.PICKLE_TYPES.values();
        try{
            System.out.println("Alegeti pickle: ");
            for(int i =0; i<pickleTypes.length; i++) {
                System.out.println(i+1 + ". " + pickleTypes[i]);
            }
            System.out.println("0. Nu doresc pickle");
            int option = scanner.nextInt();

            if(option == 0) {
                return null;
            }

            if(option < 1 || option > pickleTypes.length) {
                System.out.println("Optiune invalida!");
                return  handleKebapAddPickle(scanner);
            }

            pickle = new Pickle(pickleTypes[option-1].toString());
        }
        catch(InputMismatchException e) {
            System.out.println("Optiune invalida!");
            pickle = handleKebapAddPickle(scanner);
        }
        return pickle;

    }

    public Wrap handleKebapAddWrap(Scanner scanner) {
        Wrap wrap = null;
        scanner = new Scanner(System.in);
        Wrap.WRAP_TYPES[] wrapTypes = Wrap.WRAP_TYPES.values();
        try{
            System.out.println("Alegeti wrap: ");
            for(int i =0; i<wrapTypes.length; i++) {
                System.out.println(i+1 + ". " + wrapTypes[i]);
            }
            System.out.println("0. Nu doresc wrap");
            int option = scanner.nextInt();

            if(option == 0) {
                return null;
            }

            if(option < 1 || option > wrapTypes.length) {
                System.out.println("Optiune invalida!");
                return  handleKebapAddWrap(scanner);
            }

            wrap = new Wrap(wrapTypes[option-1].toString());
        }
        catch(InputMismatchException e) {
            System.out.println("Optiune invalida!");
            wrap = handleKebapAddWrap(scanner);
        }
        return wrap;

    }



    public void handleKebapsFiltering(Scanner scanner) {
        Protein.PROTEIN_TYPES[] proteinTypes = Protein.PROTEIN_TYPES.values();
        for(int i=0; i<proteinTypes.length; i++) {
            System.out.println(i+1 + ". " + proteinTypes[i]);
        }
        scanner = new Scanner(System.in);

        int option = scanner.nextInt();
        if(option < 1 || option > proteinTypes.length) {
            System.out.println("Optiune invalida!");
            handleKebapsFiltering(scanner);
        }

        List<Kebap> filteredKebaps = kebapManager.filterKebaps(proteinTypes[option-1].toString());

        System.out.println("Lista cu kebapuri filtrate:");
        for(Kebap kebap : filteredKebaps) {
            System.out.println(kebap);
        }

    }
}
