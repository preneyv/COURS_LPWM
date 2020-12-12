package com.tarot.controller;
import com.tarot.model.AbstractModel;
import com.tarot.model.Card;

/**
 *
 */
public abstract class AbstractControllerPlayer {

    protected AbstractModel absModel;
    protected String firstname;
    protected String sexuality;
    protected Card card;

    public AbstractControllerPlayer(AbstractModel aM)
    {
        this.absModel = aM;
    }

    public void addCollectionCardPlayer( Card c)
    {

        this.card = c;
        controlAddCard();
    }

    public void removeCollectionCardPlayer( Card c)
    {

        this.card = c;
        controlRemoveCard();
    }

    public void setPlayer( String f, String s)
    {
        this.firstname = f;
        this.sexuality = s;
        controlSetPlayer();
    }

    public void setCard(Card cToRm, Card cToAdd)
    {
        this.card = cToAdd;
        controlSetCard(cToRm);

    }

    public void savePlayerProfile(){
        this.absModel.objToJson();
    }
    abstract void controlAddCard();
    abstract void controlRemoveCard();
    abstract void controlSetPlayer();
    abstract void controlSetCard(Card cToRm);
}
