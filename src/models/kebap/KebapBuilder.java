package models.kebap;

import exceptions.InvalidOrderException;
import exceptions.MaximumListSizeException;
import exceptions.MissingMandatoryFieldException;
import interfaces.IBuilder;
import models.ingredients.*;
import models.sauces.Sauce;

import java.util.ArrayList;
import java.util.List;

public class KebapBuilder implements IBuilder {
    private Protein protein;
    private Carbohydrate carbohydrate;
    private Pickle pickle;
    private List<Sauce> sauces;
    private Wrap wrap;
    private Fiber fiber;
    private Healthy healthy;

    public KebapBuilder(Protein protein, Carbohydrate carbohydrate) {
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.sauces = new ArrayList<>();
    }

    public KebapBuilder addPickle(Pickle pickle){
        this.pickle = pickle;
        return this;
    }

    public KebapBuilder addSauce(Sauce sauce){
        if(protein==null){
            throw new InvalidOrderException("Sosul nu poate fi adaugat inaintea proteinei!");
        }
        if(this.sauces.size()>=3){
            throw new MaximumListSizeException("Nu putem avea mai mult de 3 sosuri!");
        }
        this.sauces.add(sauce);
        return this;
    }

    public KebapBuilder addWrap(Wrap wrap){
        this.wrap = wrap;
        return this;
    }

    public KebapBuilder addFiber(Fiber fiber){
        this.fiber = fiber;
        return this;
    }

    public KebapBuilder addHealthy(Healthy healthy){
        this.healthy = healthy;
        return this;
    }

    @Override
    public Kebap build(){
        if(protein== null || carbohydrate==null){
            throw new MissingMandatoryFieldException("Nu putem crea un kebap fara proteine sau carbohidrati");
        }
        Kebap kebap = new Kebap(protein, carbohydrate);
        kebap.setPickle(pickle);

        for(Sauce sauce: sauces){
            kebap.addSauce(sauce);
        }

        kebap.setFiber(fiber);
        kebap.setWrap(wrap);
        kebap.setHealthy(healthy);
        reset();
        return kebap;
    }

    void reset(){
        this.protein = null;
        this.carbohydrate = null;
        this.pickle = null;
        this.sauces = null;
        this.fiber=null;
        this.healthy=null;
        this.wrap=null;
    }
}
