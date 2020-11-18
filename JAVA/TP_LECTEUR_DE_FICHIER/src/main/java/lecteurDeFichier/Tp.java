package lecteurDeFichier;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Valere
 * C'est un petit programme avec saisi pour faire de la lecture de fichier.
 * Vous pouvez le quitter presque à tout moment ;) . Le seul problème que je rencontre est lorsque 
 * fichier.java detecte une FileNotFound Exception. Je force la fin du programme, mais je souhaiterais
 * que le programme redemande la saisie d'un nouveau fichier.(voir premier Switch - case )
 * Je vous laisse vous y balader.
 */
 public class Tp {
    public static void main(String[] args) throws FileNotFoundException {

        String lireAutre="y";
        int c= -1;
        Fichier f= null;
        int choix;
        
        Scanner scanner = new Scanner(System.in);
        do{
            do{
                System.out.println("Quel type de fichier voulez-vous ouvrir ?");
                System.out.println("1) Fichier Texte");
                System.out.println("2) Fichier HTML");
                System.out.println("3) Fichier XML");
                System.out.println("4) Sortir");

                
                choix = scanner.nextInt();
                String err = (choix != 1 && choix != 2 && choix != 3 && choix != 4) ? "Vous devez saisir un choix de la liste" : "" ;
                System.out.println(err);
                
            }while(choix != 1 && choix != 2 && choix != 3 && choix != 4);
            
           if(choix != 4)
           {
                    System.out.println("Saisissez maintenant le nom du fichier :");
                    String nom = scanner.next();

                    switch(choix){
                        case 1 :
                               f = new FichierTxt(nom);
                                break;
                        case 2 :
                                f = new FichierHTML(nom);
                                break;
                        case 3 :
                                f = new FichierXML(nom);
                                break;
                        default :
                                ;
                    }

                do{
                    System.out.println("Que voulez-vous faire avec le fichier "+f.getNom()+" ? ");
                    System.out.println("1) Ouvrir le fichier - necessaire pour pouvoir le lire");
                    System.out.println("2) Lire le fichier");
                    System.out.println("3) Le lire à l'envers");
                    System.out.println("4) Le lire de manière palindromique");
                    System.out.println("5) Le comparer avec un autre fichier");
                    System.out.println("6) Afficher son type");
                    System.out.println("Tapez 0 pour sortir");

                    c = scanner.nextInt();

                    switch(c){
                        case 0 : break;
                        case 1 :
                                f.ouvrirFichier();
                                break;
                        case 2 :
                                f.lireFichier();
                                break;
                        case 3 :
                                f.lireAlEnvers();
                                break;
                        case 4 :
                                f.palimdromique();
                                break;
                        case 5 :
                                    
                                    System.out.print("Entre le nom d'un fichier ");
                                    if(choix == 1){ System.out.print("texte");}
                                    if(choix == 2){ System.out.print("HTML");}
                                    if(choix == 3){ System.out.print("XML");}
                                    System.out.println(" à comparer ");
                                    
                                    String nomFToCompare = scanner.next();
                                    Fichier fToCompare = null;
                                    if(choix == 1){ fToCompare = new FichierTxt(nomFToCompare);}
                                    if(choix == 2){ fToCompare = new FichierHTML(nomFToCompare);}
                                    if(choix == 3){ fToCompare = new FichierXML(nomFToCompare);}
                                    f.ouvrirFichier();
                                    fToCompare.ouvrirFichier();
                                    f.compare(fToCompare);
                                    break;    
                        case 6 :
                                System.out.println(f.typeFichier());
                                break;
                        default :
                                System.out.println("Vous n'avez pas choisi parmi les choix proposés");
                                break;
                        }

                }while(c != 0);
                
                System.out.println("Voulez-vous ouvrir un autre fichier ? (y/n)");
                lireAutre = scanner.next();
           }else {
               lireAutre = "n";
           }

            
            
        }while("y".equals(lireAutre));
        System.out.println("AUREVOIR !!!!!");
    }
}
