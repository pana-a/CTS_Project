package models.sauces;

import interfaces.IExpirable;

public class FermentableSauce extends Sauce implements IExpirable {
    private int expirationHours;

    public FermentableSauce(String name, int expirationHours) {
        super(name);
        this.expirationHours = expirationHours;
    }

    @Override
    public Integer getExpirationHours() {
        return this.expirationHours;
    }
}