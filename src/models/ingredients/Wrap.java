package models.ingredients;

public class Wrap extends AIngredient{

    public Wrap(String name){
        super(name);
    }
    public enum WRAP_TYPES {
        salata,
        lipie
    }
}