package jeuBataille;

import java.util.ArrayList;
import java.util.Collections;

public class Bataille {

	public static void main(String[] args) {
            /*Déclarations des éléments necessaires à la création de la bataille*/
            
		final String [] tabCouleur = {"coeur","pique", "trefle", "carreau"}; // tableau des couleurs
		final int [] tabValeur = {2,3,4,5,6,7,8,9,10,11,12,13,14}; // tableau des valeurs
		final ArrayList<Carte> paquet= new ArrayList(56);// Création du paquet de carte
		
		final Joueur j1 = new Joueur();//Création des deux joueurs
		final Joueur j2 = new Joueur();
		
                //J'initialise chaque carte du paquet. L'initialisation suit les valeurs de tabCouleur et tabValeur. Puis je l'ajoute au paquet.
		for(int i=0; i<=tabCouleur.length-1; i++)
		{
			for (int j=0; j<=tabValeur.length-1; j++)
			{
				Carte newCarte = new Carte(tabCouleur[i], tabValeur[j]);
				paquet.add(newCarte);
			
				
			}
		}
		
		//Melange du paquet - afin d'avoir une distribution équitable et plus aléatoire
		Collections.shuffle(paquet);
		
		//Distribution des cartes : si i est impair, je donne une carte à joueur1 sinon à joueur 2. Chacun ajoute la carte reçu dans son paquet.
		for(int i=0; i<paquet.size();i++)
		{
			if(i%2!=0)
			{
				j1.ajouteCarte(paquet.get(i));
			}
			
			if(i%2==0) {
				j2.ajouteCarte(paquet.get(i));
			}
			
			
		}
		
                //J'affiche les paquets pour voir si la distribution a bien été effectuée. On peut commenter cette partie du code - il s'agit de simple vérification.
		//Affichage paquet J1
		System.out.println("Les cartes du Joueur 1 : ");
		for(int i=0; i<j1.getPaquetJoueur().size();i++)
		{
			System.out.println((i+1) +":"+j1.getPaquetJoueur().get(i).getValeurLettre()+" de "+j1.getPaquetJoueur().get(i).getCouleur());
		}
		
		//Affichage paquet J2
		System.out.println("Les cartes du Joueur 2 : ");
		for(int i=0; i<j2.getPaquetJoueur().size();i++)
		{
			System.out.println((i+1) +":"+j2.getPaquetJoueur().get(i).getValeurLettre()+" de "+j2.getPaquetJoueur().get(i).getCouleur());
		}
		
		// La partie continue tant que l'un des deux joueurs n'a pas 10 points.
		while(j1.getPoint()<10 && j2.getPoint() <10)
		{
			System.out.println("#########################################");
			System.out.println("Nouveau Duel");
			Carte cEnMainJ1 = j1.tireCarte();//tire une carte dans son paquet.
			Carte cEnMainJ2 = j2.tireCarte();//tire une carte dans son paquet
			
			System.out.println("Joueur 1 à "+cEnMainJ1.getValeurLettre()+" de "+cEnMainJ1.getCouleur()+" en main");
			System.out.println("Joueur 2 à "+cEnMainJ2.getValeurLettre()+" de "+cEnMainJ2.getCouleur()+" en main");
			Carte carteVictorieuse = cEnMainJ1.compareCarte(cEnMainJ2);// je compare la carte du joueur 1 à celle du joueur 2. carteVictorieuse reçoit la carte avec la valeur la plus haute.
			
			if(carteVictorieuse == null) {
				System.out.println("Egalité");
				Collections.shuffle(j1.getPaquetJoueur());//En cas d'égalité je mélange les paquets des joueurs.
				Collections.shuffle(j2.getPaquetJoueur());
			}else {
				
				System.out.println("La carte victorieuse est "+carteVictorieuse.getValeurLettre()+" de "+carteVictorieuse.getCouleur());
                                
                                //Si la carte victorieuse est celle du joueur1 alors la carte est retirée du paquet du joueur1 et elle est ajouté à celle du joueur 2. Sinon c'est l'inverse.
				if(carteVictorieuse == cEnMainJ1)
				{
					j1.gagneDuel(cEnMainJ1);
					j2.perdDuel(cEnMainJ2, cEnMainJ1);
					
					System.out.println("Joueur 1 remporte ce duel");
					
				}else {
					
					j2.gagneDuel(cEnMainJ2);
					j1.perdDuel(cEnMainJ1, cEnMainJ2);
					System.out.println("Joueur 2 remporte ce duel");
					
				}
			}
			
			
			System.out.println("Joueur 1 à "+j1.getPoint()+" points");// affichage des points.
			System.out.println("Joueur 2 à "+j2.getPoint()+" points");
		}
		
		
		
		
	}
		

}
