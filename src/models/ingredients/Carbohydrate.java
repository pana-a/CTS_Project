package models.ingredients;

public class Carbohydrate extends AIngredient{

    public Carbohydrate(String name){
        super(name);
    }

    public enum CARBOHYDRATE_TYPES {
        cartofi,
        orez
    }
}
