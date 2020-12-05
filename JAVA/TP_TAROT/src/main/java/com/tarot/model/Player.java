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
    {
    }

    public Player(String f, String s, String i, ArrayList<Card> c)
    {
        this.firstname=f;
        this.sexuality =s;
        this.imageIcon = i;
        this.collectionCard = c;
    }
    public String getFirstname(){return this.firstname;}
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
       if(s=="Homme"){
           this.imageIcon = "avatar.png";
       }else{
           this.imageIcon = "avatar2.png";
       }

       notifyObserver(this);
    }

    public ArrayList<Card> getCollection(){return this.collectionCard;}

    public void objToJson() {
        Gson profile = new Gson();
        try{
            FileWriter fw = new FileWriter("profile.json");
            Player playerToSave = new Player(this.firstname, this.sexuality, this.imageIcon, this.collectionCard);
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
