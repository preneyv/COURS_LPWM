package com.tarot.observer;
import com.tarot.model.Player;

public interface Observable {

    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(Player p);
}
