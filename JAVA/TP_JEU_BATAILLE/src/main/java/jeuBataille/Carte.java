package jeuBataille;

/**
 *
 * @author Valere
 * Les valeurs sont toutes des chiffres ou des nombres. Donc le Valet est 11... et l'As est 14.
 * Cela permet une comparaison des cartes plus simple.
 */
public class Carte {
	
	private String couleur;
	private int valeur;
	
	public Carte(String c, int v){
		this.couleur = c;
		this.valeur = v;
		
	}
	
    /**
     *
     * @return la couleur de la carte
     */
    public String getCouleur(){return couleur;}

    /**
     *
     * @return la valeur de la carte en type String.
     * Uniquement utilisée pour l'affichage des cartes.
     */
    public String getValeurLettre() {
            String val=null;
            if(this.valeur == 11){val = "Valet";}
            if(this.valeur == 12){val = "Dame";}
            if(this.valeur == 13){val = "Roi";}
            if(this.valeur == 14){val = "As";}
            if(this.valeur <= 10){val = Integer.toString(valeur);}
            return val;
        }
    
    /**
     *
     * @return la valeur de la carte en type int
     * Uniquement utilisée pour la comparaison.
     */
    public int getValeur(){return this.valeur;}
	
    /**
     *
     * @param c la carte à comparer
     * @return la carte qui a gagné le duel.
     */
    public Carte compareCarte(Carte c) {
		
		Carte res=null;
		if(this.getValeur()>c.getValeur()) {
			res = this;
			
		}else if(this.getValeur()<c.getValeur()) {
			res = c;
		}
		
		return res;
		
	}
	
}

