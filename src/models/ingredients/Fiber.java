package models.ingredients;

public class Fiber extends AIngredient{

    public Fiber(String name){
        super(name);
    }

    public enum FIBER_TYPES {
        varza,
        rosii,
        morcovi
    }
}
