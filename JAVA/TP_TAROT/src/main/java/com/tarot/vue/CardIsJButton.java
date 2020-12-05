package com.tarot.vue;

import com.tarot.model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardIsJButton extends JButton {

    private Card cardOfLabel;

    public CardIsJButton(Card c)
    {
        this.cardOfLabel = c;
        this.setIcon(cardOfLabel.resizeImage(90,172));
        this.setText(this.cardOfLabel.getNom());
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        //this.setBackground(new Color(50,51,50));
        this.setOpaque(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setPreferredSize(new Dimension(100,190));
        this.setMargin(new Insets(5,-35,0 ,0));
        this.setBorder(null);
        this.setForeground(Color.WHITE);
    }

    public Card getCard(){return this.cardOfLabel;}



}
