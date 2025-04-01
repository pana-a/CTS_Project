package utils;

import models.sauces.Sauce;

import java.util.ArrayList;
import java.util.List;

public class SauceManager {
    //The sauces list is managed by the sauce manager, while the sauce manager is the same instance for the whole restaurant
    private List<Sauce> sauces = new ArrayList<>();
    private static SauceManager instance = new SauceManager();

    private SauceManager(){}

    public static SauceManager getInstance() {
        return instance;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public void addSauce(Sauce sauce){
        sauces.add(sauce);
    }

    public void displaySauces(){
        if(sauces.isEmpty()){
            System.out.println("Nu exista niciun sos!");
        }
        else{
            for(int i = 0; i<sauces.size(); i++){
                System.out.println(i+1 + ". " + sauces.get(i));
            }
            System.out.println("Selectati numarul sosului pe care doriti sa il stergeti");
        }
    }

    public void deleteSauce(int option){
        if(sauces.get(option)==null){
            System.out.println("Sosul selectat nu exista!");
        }
        else{
            sauces.remove(option);
        }
    }

}
