package com.tarot.vue;


import com.tarot.model.ArcanesMajestueuses;
import com.tarot.model.ArcanesMajeures;
import com.tarot.model.ArcanesMineures;
import com.tarot.model.Card;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelDetailCard extends JPanel {

    GridBagLayout gbL = new GridBagLayout();
    GridBagConstraints gbC = new GridBagConstraints();

    private Card cardInUse;
    private JPanel panelForTitleDetail = new JPanel(new BorderLayout());
    private JLabel titleDetail = new JLabel("Carte en cours de visualisation");
    private JLabel labelIconCard = new JLabel();
    private JPanel detailDependType = new JPanel();

    private AbstractPanelForArcanesType panelForDetail;


    public PanelDetailCard()
    {


        this.gbC.insets = new Insets(1,0,0,1);
        this.setBackground(new Color(50,51,50));
        this.setLayout(gbL);
        initComponent();

    }


    private void initComponent() {



        this.titleDetail.setForeground(Color.WHITE);
        this.titleDetail.setHorizontalTextPosition(SwingConstants.CENTER);
        this.titleDetail.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleDetail.setBorder(new EmptyBorder(15,0,15,0));
        this.panelForTitleDetail.setBackground(new Color(3,3,32));
        this.panelForTitleDetail.add(this.titleDetail);
        this.detailDependType.setLayout(new CardLayout());
        this.detailDependType.setPreferredSize(new Dimension(-1,200));
        this.detailDependType.setBackground(new Color(50,51,50));
        this.detailDependType.setBorder(new EmptyBorder(0,15,0,0));
        this.labelIconCard.setHorizontalAlignment(SwingConstants.RIGHT);
        this.labelIconCard.setVerticalAlignment(SwingConstants.CENTER);


        this.gbC.gridx = 0;
        this.gbC.gridy= 0;
        this.gbC.gridwidth = 2;
        this.gbC.weightx = 1;
        this.gbC.weighty = 0;
        this.gbC.fill = GridBagConstraints.BOTH;
        this.add(this.panelForTitleDetail,this.gbC);

        this.gbC.gridx = 0;
        this.gbC.gridy= 1;
        this.gbC.gridwidth = 1;
        this.gbC.gridheight = 1;
        this.gbC.weightx = 0.2;
        this.gbC.weighty = 1;
        this.gbC.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.labelIconCard,this.gbC);

        this.gbC.gridx = 1;
        this.gbC.gridy= 1;
        this.gbC.weightx = 0.8;
        this.add(this.detailDependType,this.gbC);


    }
    public void setPanelDetail(Card c){

        this.detailDependType.removeAll();
        this.cardInUse = c;
        this.setVisible(true);
        this.labelIconCard.setIcon(c.resizeImage(90,172));
        if(c.getClass() == ArcanesMajeures.class)
        {
            panelForDetail = new PanelForArcanesMajeureDetail((ArcanesMajeures)this.cardInUse);
        }

        if(c.getClass() == ArcanesMineures.class)
        {
            panelForDetail = new PanelForArcanesMineureDetail((ArcanesMineures)this.cardInUse);

        }

        if(c.getClass() == ArcanesMajestueuses.class)
        {
            panelForDetail = new PanelForArcanesMajestueuseDetail((ArcanesMajestueuses)this.cardInUse);

        }

        this.detailDependType.add(panelForDetail);
        this.panelForDetail.setVisible(true);
        this.panelForDetail.validate();
        this.detailDependType.validate();
    }

    public void clearPanel()
    {
        this.panelForDetail.removeAll();
        this.panelForDetail.setVisible(false);
        this.labelIconCard.setIcon(null);
        this.panelForDetail.validate();
        this.detailDependType.remove(this.panelForDetail);
        this.detailDependType.validate();
    }

    public Card getCardInUse(){return this.cardInUse;}




}