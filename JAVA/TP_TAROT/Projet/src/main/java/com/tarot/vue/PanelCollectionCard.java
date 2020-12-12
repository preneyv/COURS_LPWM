package com.tarot.vue;


import com.tarot.model.Card;
import com.tarot.model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelCollectionCard extends JPanel implements IGotTextFieldKeyListening, IGotFocusComponent {

    private JPanel panelToListen;

    private GridBagLayout gbL = new GridBagLayout();
    private GridBagConstraints gbC = new GridBagConstraints();

    private JPanel panelForSearchBar = new JPanel();
    private JTextField searchBar = new JTextField();

    private JScrollPane scrollForCardList = new JScrollPane();
    private JPanel panelInsideScroll = new JPanel();


    public PanelCollectionCard(JPanel pnlToListen)
    {
        super();
        this.setBackground(new Color(54,54,58));
        this.setBorder(new EmptyBorder(0,5,5,5));
        this.panelToListen = pnlToListen;
        this.setLayout(gbL);
        this.initComponent();
    }

    private void initComponent()
    {
        //Define PanelSearchBar properties
            this.panelForSearchBar.setBackground(new Color(3,3,32));
            this.panelForSearchBar.setLayout(new GridLayout(1,1,10,10));
        //Define searchBar Properties
            this.searchBar.setBackground(new Color(224,230,224));
            this.searchBar.setBorder(new LineBorder(new Color(3,3,32),9));
            this.searchBar.setText("Rechercher une carte");
            this.searchBar.setHorizontalAlignment(SwingConstants.CENTER);
            this.searchBar.setForeground(Color.GRAY);
            VueTest.mapTextFieldKeyPress.put(this.searchBar,this);
            KeyPressedAction kpAction = new KeyPressedAction();
            this.searchBar.addKeyListener(kpAction);
            VueTest.mapFocusComponent.put(this.searchBar,this);
            FocusAction fcAction = new FocusAction();
            this.searchBar.addFocusListener(fcAction);
        //Add searchBar to his panel
            this.panelForSearchBar.add(this.searchBar);

        //Define properties of the panelScrollBar

            this.panelInsideScroll.setBackground(new Color(50,51,50));
            this.panelInsideScroll.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.panelInsideScroll.setMaximumSize(new Dimension(500,this.panelInsideScroll.getComponentCount()/3*172 ));
            this.panelInsideScroll.setPreferredSize(new Dimension(500,this.panelInsideScroll.getComponentCount()/3*172 ));


        //Define gridBagConstraints before adding panelSearchBar to his parent panel
            this.gbC.gridx = 0;
            this.gbC.gridy= 0;
            this.gbC.weightx = 1;
            this.gbC.weighty = 0.02;
            this.gbC.fill = GridBagConstraints.BOTH;
            this.add(this.panelForSearchBar,gbC);


            this.scrollForCardList.setBorder(null);


        //Define gridBagConstraints before adding  scrollPanel to his parent panel
        this.gbC.gridx = 0;
        this.gbC.gridy= 1;
        this.gbC.weightx = 1;
        this.gbC.weighty = 0.90;
        this.gbC.fill = GridBagConstraints.BOTH;
        this.add(this.scrollForCardList,gbC);

    }

    public void update(Player p)
    {
        this.panelInsideScroll.removeAll();
        if(p.getCollection().size()>0) {

            for (Card c : p.getCollection()) {
                CardPanel cardPanel = new CardPanel(c, this.panelToListen);
                this.panelInsideScroll.add(cardPanel);
            }

        }
        JViewport vw = new JViewport();
        vw.add("View",this.panelInsideScroll);
        this.scrollForCardList.setViewport(vw);
        this.scrollForCardList.validate();

    }


    @Override
    public void keyPress(JTextField c) {
        for (Component crd:this.panelInsideScroll.getComponents())
        {
            if((((CardPanel)crd).getCardToAdd().getCard().getNom().contains(c.getText()))==false && (((CardPanel)crd).getCardToAdd().getCard().getNumber().contains(c.getText()))==false)
            {
                crd.setVisible(false);

            }else{
                if(!crd.isVisible())
                    crd.setVisible(true);
            }


        }
    }

    @Override
    public void focusOn(Component c) {
        String textDefaultSearchField = (!(("Rechercher une carte").equals(((JTextField)c).getText())) ) ? ((JTextField)c).getText() : "";
        ((JTextField)c).setText(textDefaultSearchField);
    }

    @Override
    public void focusOut(Component c) {
        String textDefaultSearchField = ((("").equals(((JTextField)c).getText())) ) ? "Rechercher une carte" : ((JTextField)c).getText() ;
        ((JTextField)c).setText(textDefaultSearchField);
    }
}