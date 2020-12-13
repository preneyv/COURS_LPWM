package com.tarot.model;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Player entity
 */
public class Player extends AbstractModel implements IsSustainable{

    private String firstname=null;
    private String sexuality=null;
    private String imageIcon=null;
    private ArrayList<Card> collectionCard = new ArrayList();

    public Player()
    {   Card c1 = new ArcanesMajeures("2","amoureux.jpg","L'amoureux","Andros","Feu");
        Card c2 = new ArcanesMineures("5","cinqEpees.jpg","Cinq epees","Baton");
        Card c3 = new ArcanesMajestueuses("2","deuxEpees.jpg","La majestuese","Terre");
        this.collectionCard.add(c1);
        this.collectionCard.add(c2);
        this.collectionCard.add(c3);
    }

    /**
     * Constructor 2
     * @param f firstname of the player
     * @param s sexuality of the player
     */
    public Player(String f, String s)
    {
        this.firstname=f;
        this.sexuality =s;
        this.imageIcon = s=="Homme" ?"avatar.png" : "avatar2.png";
        this.collectionCard = new ArrayList<Card>();
    }

    /**
     *
     * @return irstname of the player
     */
    public String getFirstname(){return this.firstname;}

    /**
     *
     * @return sexuality of the player
     */
    public String getSexuality(){return this.sexuality;}

    /**
     *
     * @return ImageIcon of the player (only th path)
     */
    public String getImageIcon(){return this.imageIcon;}

    /**
     * Add a new card to his collection then notify all the observer
     * @param c card to add
     */
    @Override
    public void addCard(Card c){

        this.collectionCard.add(c);
        notifyObserver(this);
    }

    /**
     *
     * @param c
     */
    @Override
    public void removeCard(Card c)
    {
        this.collectionCard.remove(c);
        notifyObserver(this);

    }

    public void setPlayer(String f, String s) {
       this.firstname = f;
       this.sexuality = s;
        this.imageIcon = s=="Homme" ?"avatar.png" : "avatar2.png";

       notifyObserver(this);
    }

    /**
     * Set a card into his collection
     * @param cToRm card to remove
     * @param cToAdd new card that will takes the place of cToRm
     */
    @Override
    public void setCard(Card cToRm, Card cToAdd) {
        int index = this.collectionCard.indexOf(cToRm);
        this.collectionCard.remove(index);
        this.collectionCard.add(index,cToAdd);
        notifyObserver(this);

    }

    /**
     *
     * @return the collection card of the player
     */
    public ArrayList<Card> getCollection(){return this.collectionCard;}

    /**
     * Save the profil into a json file
     */
    public void objToJson() {
        Gson profile = new Gson();
        try{
            FileWriter fw = new FileWriter("profile.json");
            //Player playerToSave = new Player(this.firstname, this.sexuality, this.collectionCard);
            String jSonRes = profile.toJson(this);
            fw.write(jSonRes);
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return the profile of the player from the saving file
     */
    public Player jsonToObj() {
        Player resetPlayer = null;
        Gson profile = new Gson();
        try{
            Reader fr = new FileReader("profile.json");
            resetPlayer = profile.fromJson(fr,Player.class);
        }catch(IOException e){
            e.printStackTrace();
        }
        return resetPlayer;
    }

}
