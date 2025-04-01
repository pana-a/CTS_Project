package utils;

import models.kebap.Kebap;

import java.util.ArrayList;
import java.util.List;

public class KebapManager {
    private List<Kebap> kebaps = new ArrayList<Kebap>();
    private static KebapManager instance = new KebapManager();

    private KebapManager() {}

    public static KebapManager getInstance() {
        return instance;
    }

    public void addKebap(Kebap kebap) {
        kebaps.add(kebap);
    }

    public List<Kebap> getKebaps() {
        return kebaps;
    }

    public void displayKebaps() {
        if(this.kebaps.isEmpty()) {
            System.out.println("Nu exista niciun kebap!");
        }
        for(int i=0; i<kebaps.size(); i++) {
            System.out.println(i+1 + ". " + kebaps.get(i).toString());
        }
    }

    public void deleteKebap(int option) {
        if(this.kebaps.get(option) == null) {
            System.out.println("Kebapul selectat nu exista");
        }
        else {
            kebaps.remove(option);
        }
    }

    public List<Kebap> filterKebaps(String proteinName) {
        return kebaps.stream().filter(
                        kebap -> kebap.getProtein()
                                .getName()
                                .equals(proteinName))
                .toList();
    }
}
