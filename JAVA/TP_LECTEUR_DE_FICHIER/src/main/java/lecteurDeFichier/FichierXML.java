package lecteurDeFichier;
import java.io.FileNotFoundException;
import java.util.ArrayList;



/**
 *
 * @author Valere
 * Classe permettant de lire les fichiers XML
 * @ext est l'extension du fichier.
 * @contenu est le contenu du fichier.
 */
public class FichierXML extends Fichier{

        private String ext;
        private ArrayList<String> contenu = new ArrayList();
        
    /**
     *
     * @param f le fichier à lire
     * @throws FileNotFoundException
     * On appelle la méthode public de la classe mère par super(). Puis on défini le contenu de la 
     * classe fille en utilisant getContenu de la classe mère. L'idée était de ne pas avoir à utiliser
     * la classe FileReader et BufferReader à nouveau afin d'éviter la redéfinition dans les méthodes des classes filles.
     * C'est de cela dont je ne suis pas sûr.
     * 
     * J'effectue également un split sur le nom du fichier afin de récupérer l'extension du fichier
     */
    public FichierXML(String f) throws FileNotFoundException{
            super(f);
            String[] ext = f.split("\\.");
            this.ext = "xml".equals(ext[1]) ? "xml" : null;
        }

    /**
     * ouvrirFichier() test d'abord si il s'agit bien d'un fichier xml ac l'attribut ext. Si ce n'est pas le cas, je signale une erreur avec rep
     * j'utilise ensuite ouvrirFichier de la classe mère pour que le BufferReader remplisse la varible 
     * contenu de la classe mère. Puis on défini le contenu de la 
     * classe fille en utilisant getContenu de la classe mère. L'idée était de ne pas avoir à utiliser
     * la classe FileReader et BufferReader à nouveau afin d'éviter la redéfinition dans les méthodes des classes filles.
     * C'est de cela dont je ne suis pas sûr.
     * 
     * Je stocke ensuite un message  dans rep pour dire si l'ouverture se passe bien ou non. Si le contenu n'est pas nul et que la première
     * ligne du contenu contient bien ce qu'il faut écrire en début de fichier XML.
     */
    @Override
    public void ouvrirFichier() {
        String rep="";
        if( "xml".equals(this.ext)){ 
            
            super.ouvrirFichier();
            this.contenu = super.getContenu();
            rep = (this.contenu.size()>0 && this.contenu.get(0).contains("<?xml")) ? "Le fichier est prêt à être lu" : "Il y a un problème avec le fichier";
 
        }else{
            rep = "Le fichier que vous tentez d'ouvrir n'est pas un fichier .xml";
        }
        System.out.println(rep);   
    }
    
    /**
     * 
     * @return ret 
     * retourne dans une phrase le type de fichier. Sinon inconnu si ce n'est pas XML.
     * Un peu inutile sachant que l'on est dans une classe de lecteur de fichier XML. C'est 
     * plus pour la pratique et me remette dans le bain des opérateurs ternaires en java.
     */
    @Override
    public String typeFichier() {
        String ret;
        return ret = this.ext == "xml" && this.contenu != null ? "C'est un fichier XML" : "Type de fichier inconnu";
    }
    
    /**
     * Lis le fichier. Vérifie avant tout si le contenu n'est pas vide et appel la fonction
     * lireFichier de la classe mère. On évite la redéfinition, on priviligie la surcharge.
     */
     @Override
    public void lireFichier() {
        
        if(this.contenu.size() > 0)
        {
            super.lireFichier();
        }else{
            System.out.println("Impossible de lire le fichier");
        }
    }
    
    /**
     * Pareil que la méthode précédente mais pour lire les lignes à l'envers.
     */
    @Override
    public void lireAlEnvers() {
     
        if(this.contenu.size() > 0)
        {
            super.lireAlEnvers();
        }else{
            System.out.println("Impossible de lire le fichier");
        }
    }

    /**
     * 
     * Encore et tjr comme la méthode précédente mais pour lire de manière palimdromique
     */
    @Override
    public void palimdromique() {
        if(this.contenu.size() > 0)
        {
            super.palimdromique();
        }else{
            System.out.println("Impossible de lire le fichier");
        }
    }
    
    @Override
    public String getExt(){
        return this.ext;
    }

    @Override
    public void compare(Fichier f) {
        
        if(this.ext.equals(f.getExt()))
        {
            super.compare(f);
        }else{
            System.out.println("Les fichiers "+super.getNom()+" et "+f.getNom()+" sont différents - Ils ne possèdent pas la même extension.");
        }
    }
    
   
}
