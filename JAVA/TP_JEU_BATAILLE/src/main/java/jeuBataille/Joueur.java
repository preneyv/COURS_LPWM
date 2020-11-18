package jeuBataille;
import java.util.ArrayList;

/**
 *
 * @author Valere
 * un joueur possède un paquet de carte et un nombre de point initilisé à 0.
 */
public class Joueur {

	private ArrayList<Carte> tabCarte = new ArrayList();
	private int point=0;
	
    /**
     *Constructeur de la classe
     */
    public Joueur() {}
	
    /**
     *
     * @return le paquet de carte du joueur
     */
    public  ArrayList<Carte> getPaquetJoueur(){return this.tabCarte;}
	
    /**
     *
     * @return la carte tiré dans le paquet. La première du paquet donc l'indice 0 de l'arrayList.
     */
    public Carte tireCarte() 
    {
		return tabCarte.get(0);
    }
	
    /**
     *
     * @param c la carte à retirer du paquet du joueur
     * on retire la carte du paquet du joueur et on lui ajoute un point.
     */
    public void gagneDuel(Carte c)
    {
		this.retireCarte(c);
		this.gainPoint();
    }
	
    /**
     *
     * @param maCarte ma carte qui passe de l'indice 0 du paquet au dernier indice. Car en cas de défaite, les cartes utilisées pour le duel sont réinsérées à la fin du paquet
     * @param saCarte la carte du joueur adverse à ajouter au paquet.
     */
    public void perdDuel(Carte maCarte, Carte saCarte) 
    {
		this.ajouteCarte(saCarte);
		this.retireCarte(maCarte);
		this.ajouteCarte(maCarte);
    }
	
    /**
     *
     * @param c ajoute la carte au paquet
     */
    public void ajouteCarte(Carte c) 
    {
		tabCarte.add(c);
    }
	
    /**
     *
     * @param c retire la carte du paquet
     */
    public void retireCarte(Carte c) {
		tabCarte.remove(tabCarte.indexOf(c));
    }
	
    /**
     *ajoute un point au joueur
     */
    public void gainPoint() {this.point+=1;}

    /**
     *
     * @return les points du joueur
     */
    public int getPoint() {return this.point;}
}
