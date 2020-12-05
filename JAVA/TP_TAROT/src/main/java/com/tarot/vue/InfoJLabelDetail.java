package com.tarot.vue;

import com.tarot.model.Card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InfoJLabelDetail extends JPanel {

    private Card cardOfLabel;
    private JLabel pictureOfCard;
    private JLabel nameAndNumberOfCard;

    public InfoJLabelDetail(Card c)
    {
        super();
        this.setLayout(new BorderLayout(10,10));
        this.setBackground(new Color(124,141,245));
        this.cardOfLabel = c;
        this.pictureOfCard = new JLabel();
        this.nameAndNumberOfCard = new JLabel();
        this.setComponent();

        this.add(this.pictureOfCard, BorderLayout.WEST);
        this.add(this.nameAndNumberOfCard, BorderLayout.EAST);


    }

    public void setComponent()
    {
        this.pictureOfCard.setIcon(cardOfLabel.resizeImage(90,172));
        this.nameAndNumberOfCard.setText(cardOfLabel.toString());
        this.nameAndNumberOfCard.setVerticalAlignment(SwingConstants.TOP);
        this.nameAndNumberOfCard.setForeground(Color.WHITE);

    }



}
