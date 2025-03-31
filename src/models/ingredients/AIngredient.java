package models.ingredients;

public abstract class AIngredient {
    protected String name;

    public AIngredient(String name){
        this.name=name;
    };

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
