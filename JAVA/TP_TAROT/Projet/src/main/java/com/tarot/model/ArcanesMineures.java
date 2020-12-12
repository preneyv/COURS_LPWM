package com.tarot.model;

public  class ArcanesMineures extends Card{

    private String dom;

    public ArcanesMineures( String number,String p,  String nm,  String dom) {
        super(number, p, nm);
        this.dom = dom;

    }

    public String getSortOf(){return null;};
    public  String getElement(){return null;};
    @Override
    public String getPlanet() {return null;}

    public String getDom() {return this.dom;}
    @Override
    public String toString(){return "<html>Nom de la carte : <br>"+this.name+"</html>";}
}
