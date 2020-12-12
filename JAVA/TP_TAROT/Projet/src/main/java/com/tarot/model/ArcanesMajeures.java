package com.tarot.model;
import com.tarot.observer.Observable;

public class ArcanesMajeures extends Card {

    private String sortOf;
    private String element;

    public ArcanesMajeures(String n, String p, String nm, String sf, String el) {
        super(n, p, nm);
        this.sortOf = sf;
        this.element = el;
    }

    public String getSortOf(){return this.sortOf;}
    public String getElement(){return this.element;}
    @Override
    public String toString(){return "<html>Nom de la carte : "+this.name+
                                    "<br/><br> Chiffre : "+this.number+
                                    "<br><br>Genre : "+this.sortOf+
                                    "<br><br>Element : "+this.element+"</html>";}

    public String getDom() {return null;}
    @Override
    public String getPlanet() {return null;}
}
