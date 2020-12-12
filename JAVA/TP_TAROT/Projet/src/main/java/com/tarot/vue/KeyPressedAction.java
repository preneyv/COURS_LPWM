package com.tarot.vue;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPressedAction implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        JTextField clickedJbtn = (JTextField) e.getSource();
        if(VueTest.mapTextFieldKeyPress.get(clickedJbtn) != null)
            VueTest.mapTextFieldKeyPress.get(clickedJbtn).keyPress(clickedJbtn);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
