package com.tarot.model;

import com.google.gson.Gson;
import com.tarot.observer.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public abstract class AbstractModel implements Observable, IsSustainable {

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public abstract void addCard(Card c);
    public abstract void removeCard(Card c);
    public abstract void setPlayer(String f, String s);
    public abstract void objToJson();
    public abstract Player jsonToObj();

    public void addObserver(Observer obs){this.listObserver.add(obs);}
    public void removeObserver(){this.listObserver = new ArrayList<Observer>();}
    public void notifyObserver(Player p){
        for(Observer o : this.listObserver)
        {
            o.update(p);
        }
    }




}
