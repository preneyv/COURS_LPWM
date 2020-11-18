package lecteurDeFichier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;

/**
 *
 * @author Valere
 * 
 * La classe implémente l'interface fichierLecteur.java afin
 * d'utiliser les fonctions qui sont définies (par redéfinition).
 * @fichier recevra le fichier à lire.
 * @contenu contiendra un tableau de String. Chaque ligne du tableau correspondant
 * à une ligne lu par le buffer de la classe BufferReader.
 * @nom le nom du fichier.
 *
 */
public abstract class Fichier implements FichierLecteurInterface {
    
    private FileReader fichier;
    private ArrayList<String> contenu = new ArrayList();
    private String nom;
    
    /**
     *
     * @param t le nom de fichier à ouvrir.
     * @throws FileNotFoundException
     * Si une exception est retourné, la classe n'est meme pas créée. Et le programme 
     * est terminé. Je souhaiterais pouvoir demander de saisir un nouveau nom de fichier
     * au lieu de quitter le programme, mais je ne trouve pas de solution.(voir dans la classe main : tp.java
     */
    public Fichier(String t) throws FileNotFoundException{
        this.nom = t;
        try{
             fichier = new FileReader(t);
        }catch(FileNotFoundException e){
            System.out.println("Le fichier est introuvable");
            exit(0);
        }
       
    }
    
    /**
     *
     * @return contenu
     * Je ne sais pas si je fais bien de faire ça. Le but était de permettre au fils de récupérer 
     * le contenu du fichier pour le donner à son contenu pour qu'il puisse faire ses propres traitements dessus.
     * (voir les classes filles et notemment la méthode "public class" - je ne suis pas sur que ce que j'ai fais
     * soit correcte d'un point de vue "bon codage". Vous me direz cela.
     */
    public ArrayList<String> getContenu(){return this.contenu;}
    
    /**
     *Ouvre le fichier, mais ne le lit pas.
     */
    @Override
    public void ouvrirFichier(){
            try {
                    BufferedReader br = null;
                    String ligneCourante;
                    br = new BufferedReader(this.fichier);
                    while((ligneCourante = br.readLine()) != null){
                        this.contenu.add(ligneCourante);
                    }
                    
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
    
    }
    
    /**
     *Lit le fichier 
     */
    @Override
    public void lireFichier() {
        
        
            for(String l : this.contenu)
            {
                System.out.println(l);
            }
        
        
    }
    
    /**
     *Lit le fichier à l'envers : seulement au niveau des lignes.
     */
    @Override
    public void lireAlEnvers() {
     
       for(String l : this.contenu)
            {
                for(int c=l.length()-1;c>=0;c--)
                {
                    System.out.print(l.charAt(c));
                }
                System.out.println();
            }
    
    }

    /**
     *Lit le fichier de manière palindromique.
     */
    @Override
    public void palimdromique() {
       
            for(int l=this.contenu.size()-1;l>=0;l--)
            {
                for(int c=this.contenu.get(l).length()-1;c>=0;c--)
                {
                    System.out.print(contenu.get(l).charAt(c));
                }
                System.out.println();
            }
      
    }

    @Override
    public void compare(Fichier f) {
        
    
        if(f.getContenu().size() == this.contenu.size())
        {
               boolean sortie=false;
                for(int i=0; i<this.contenu.size()-1; i++)
                {
                    for(int j=0; j<this.contenu.get(i).length()-1;j++)
                    {
                        if(this.contenu.get(i).charAt(j) == f.getContenu().get(i).charAt(j))
                        {
                            sortie = true;
                            break;
                        }
                        
                    }
                }
                
             if (sortie == false)
             {
                 System.out.println("Les fichiers "+this.nom+" et "+f.getNom()+" sont identiques.");
             }else{
                 System.out.println("Les fichiers "+this.nom+" et "+f.getNom()+" sont différents.");
             }
            
        }else{
            System.out.println("Les deux fichiers ne font pas la même taille - Ils sont différends");
        }
    }
    
    /**
     *
     * @return nom
     * retourne le nom du fichier.
     */
    public String getNom(){
        return this.nom;
    }
    
    @Override
    public abstract String getExt();
    @Override
    public abstract String typeFichier();
       
}
