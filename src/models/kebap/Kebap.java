package models.kebap;

import models.ingredients.*;
import models.sauces.Sauce;

import java.util.ArrayList;
import java.util.List;

public class Kebap {
    private static Integer counter=1;

    private Protein protein;
    private Carbohydrate carbohydrate;
    private Pickle pickle;
    private List<Sauce> sauces;
    private Wrap wrap;
    private Fiber fiber;
    private Healthy healthy;

    Kebap(Protein protein, Carbohydrate carbohydrate) {
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.sauces = new ArrayList<>();
    }

    static void setCounter(Integer counter) {
        Kebap.counter = counter;
    }

    void setProtein(Protein protein) {
        this.protein = protein;
    }

    void setCarbohydrate(Carbohydrate carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    void setPickle(Pickle pickle) {
        this.pickle = pickle;
    }

    void addSauce(Sauce sauce) {
        this.sauces.add(sauce);
    }

    void setWrap(Wrap wrap) {
        this.wrap = wrap;
    }

    void setFiber(Fiber fiber) {
        this.fiber = fiber;
    }

    void setHealthy(Healthy healthy) {
        this.healthy = healthy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Kebap #" +counter+ "\n");

        sb.append("Proteina: ").append(protein.getName()).append("\n");
        sb.append("Carbohidrati: ").append(carbohydrate.getName()).append("\n");

        if(pickle != null)
            sb.append("Muraturi: ").append(pickle.getName()).append("\n");

        if (!sauces.isEmpty()) {
            sb.append("Sosuri: ");
            for (Sauce sauce : sauces) {
                sb.append(" ").append(sauce.getName());
                if (sauce instanceof interfaces.IExpirable expirable) {
                    sb.append(" (Expira in ").append(expirable.getExpirationHours()).append("h)");
                }
                sb.append(";");
            }
        }
        sb.append("\n");
        if(wrap != null)
            sb.append("Invelis: ").append(wrap.getName()).append("\n");
        if(fiber != null)
            sb.append("Fibre: ").append(fiber.getName()).append("\n");
        if(healthy != null)
            sb.append("Healthy: ").append(healthy.getName()).append("\n");

        return sb.toString();
    }

}