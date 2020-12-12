package com.tarot.model;

import com.tarot.observer.*;
import java.util.ArrayList;

public abstract class AbstractModel implements Observable, IsSustainable {

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public abstract void addCard(Card c);
    public abstract void removeCard(Card c);
    public abstract void setPlayer(String f, String s);
    public abstract void setCard(Card cToRm,Card cToAdd);

    public void addObserver(Observer obs){this.listObserver.add(obs);}
    public void removeObserver(){this.listObserver = new ArrayList<Observer>();}
    public void notifyObserver(Player p){
        for(Observer o : this.listObserver)
        {
            o.update(p);
        }
    }




}
