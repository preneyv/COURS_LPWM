package com.tarot.vue;

import com.tarot.model.Card;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListRenderer  extends DefaultListCellRenderer  {

    public ListRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }


    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Card c = (Card) value;
        this.setIcon(c.resizeImage(25,50));
        this.setText(c.getNom());
        this.setHorizontalAlignment(LEFT);
        return this;

    }


}
