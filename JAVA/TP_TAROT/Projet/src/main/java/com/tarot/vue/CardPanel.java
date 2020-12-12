package com.tarot.vue;

import com.tarot.model.Card;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel implements IGotBtnHover{

    private JPanel panelToListen;
    private JButton btnDel ;
    private final CardIsJButton cardToAdd;

    public CardPanel(Card crd,JPanel pnl)
    {

        cardToAdd = new CardIsJButton(crd);
        btnDel = new JButton(new ImageIcon(getClass().getResource("/images/").getPath()+"del.png"));
        this.panelToListen = pnl;
        this.setLayout(new BorderLayout());
        initComponent();
    }

    private void initComponent()
    {
        this.setBackground(null);
        this.setOpaque(false);
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.cardToAdd.setName("cardToAdd");
        this.btnDel.setName("btnDel");
        this.btnDel.setOpaque(true);
        this.btnDel.setContentAreaFilled(false);
        this.btnDel.setBorderPainted(false);
        this.btnDel.setSize(20,20);
        this.btnDel.setVerticalAlignment(SwingConstants.TOP);
        this.btnDel.setMargin(new Insets(-2,0,0,0));
        this.btnDel.setVisible(false);

        this.add(btnDel,BorderLayout.WEST);
        this.add(cardToAdd,BorderLayout.EAST);
        VueTest.mapBtnClickAction.put(this.cardToAdd,(PanelMainPage)this.panelToListen);
        VueTest.mapBtnClickAction.put(this.btnDel,(PanelMainPage)this.panelToListen);
        VueTest.mapBtnHoverAction.put(this.cardToAdd,this);
        VueTest.mapBtnHoverAction.put(this.btnDel,this);
        ButtonAction btnAct = new ButtonAction();
        this.btnDel.addMouseListener(btnAct);
        this.cardToAdd.addMouseListener(btnAct);
    }


    @Override
    public void hoverOut(Component c) {
        if(((JButton)c)==this.cardToAdd || ((JButton)c)==this.btnDel)
            this.btnDel.setVisible(false);
    }

    @Override
    public void hoverIn(Component c) {
        if(((JButton)c)==this.cardToAdd || ((JButton)c)==this.btnDel)
            this.btnDel.setVisible(true);
    }

    public CardIsJButton getCardToAdd(){return this.cardToAdd;}
}
