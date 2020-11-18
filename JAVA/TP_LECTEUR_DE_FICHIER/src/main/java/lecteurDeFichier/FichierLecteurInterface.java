package lecteurDeFichier;
/**
 *
 * @author Valere
 */
public interface FichierLecteurInterface {
    void ouvrirFichier();
    void lireFichier();
    String typeFichier();
    void lireAlEnvers();
    void palimdromique();
    void compare(Fichier f);
    String getExt();
    
}
