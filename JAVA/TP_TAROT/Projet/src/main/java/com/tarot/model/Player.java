package com.tarot.model;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Player extends AbstractModel{

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

    public Player(String f, String s, ArrayList<Card> c)
    {
        this.firstname=f;
        this.sexuality =s;
        this.imageIcon = s=="Homme" ?"avatar.png" : "avatar2.png";
        this.collectionCard = c;
    }
    public String getFirstname(){return this.firstname;}
    public String getSexuality(){return this.sexuality;}
    public String getImageIcon(){return this.imageIcon;}

    @Override
    public void addCard(Card c){

        this.collectionCard.add(c);
        notifyObserver(this);
    }

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

    @Override
    public void setCard(Card cToRm, Card cToAdd) {
        int index = this.collectionCard.indexOf(cToRm);
        this.collectionCard.remove(index);
        this.collectionCard.add(index,cToAdd);
        notifyObserver(this);

    }

    public ArrayList<Card> getCollection(){return this.collectionCard;}

    public void objToJson() {
        Gson profile = new Gson();
        try{
            FileWriter fw = new FileWriter("profile.json");
            Player playerToSave = new Player(this.firstname, this.sexuality, this.collectionCard);
            String jSonRes = profile.toJson(playerToSave);
            fw.write(jSonRes);
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
