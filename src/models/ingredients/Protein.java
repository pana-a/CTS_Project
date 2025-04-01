package models.ingredients;

public class Protein extends AIngredient{

    public Protein(String name){ super(name); }
    public enum PROTEIN_TYPES {
        pui,
        berbecutz,
        falafel
    }
}
