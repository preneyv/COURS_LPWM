package com.tarot.model;
import com.tarot.observer.Observable;
import com.tarot.observer.Observer;

import javax.swing.*;
import java.awt.*;

public abstract class Card {

    protected String number;
    protected String picture;
    protected String name;

    public Card(String n, String p, String nm)
    {
        this.number = n;
        this.picture = p;
        this.name = nm;
    }

    public String getPicture(){return this.picture;}
    public String getNom(){return this.name;}
    public String getNumber(){return this.number;}
    public ImageIcon resizeImage(int width, int height)
    {
       ImageIcon imgToResize = new ImageIcon(getClass().getResource("/images/").getPath()+this.picture);
       Image img = imgToResize.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
       ImageIcon imgToReturn = new ImageIcon(img);
       return imgToReturn;
    }

    public abstract String getSortOf();
    public abstract String getElement();
    public abstract String getDom();
    public abstract String getPlanet();
    public abstract String toString();


}
