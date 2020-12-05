package com.tarot.model;

public class ArcanesMajestueuses extends Card{

    private String planet;
    public ArcanesMajestueuses(String n, String p, String nm, String pl) {
        super(n, p, nm);
        this.planet = pl;
    }

    @Override
    public String getSortOf() {return null;}

    @Override
    public String getElement() {return null;}

    @Override
    public String getDom() {return null;}


    @Override
    public String getPlanet() {
        return this.planet;
    }

    @Override
    public String toString() {return "<html>Nom de la carte : "+this.name+
                                     "<br/><br> Chiffre : "+this.number+
                                     "<br><br>Planete : "+this.planet+"</html>";}
}
