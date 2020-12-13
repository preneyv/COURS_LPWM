package com.tarot.observer;
import com.tarot.model.Player;

/**
 * Interface for observed classes (here the Abstract Model)
 */
public interface Observable {

    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(Player p);
}
