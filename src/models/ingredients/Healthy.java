package models.ingredients;

public class Healthy extends AIngredient{

    public Healthy(String name){
        super(name);
    }
    public enum HEALTHY_TYPES {
        spanac,
        ridiche
    }
}