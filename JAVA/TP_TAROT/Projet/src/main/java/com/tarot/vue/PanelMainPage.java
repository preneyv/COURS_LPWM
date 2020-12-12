package com.tarot.vue;


import com.tarot.model.Card;
import com.tarot.model.Player;
import com.tarot.observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelMainPage extends JPanel implements IGotBtnClickable, Observer {

    private PanelSideBar sideBar;
    //top of the sidebar

    private JPanel rightMain = new JPanel();
        private PanelCollectionCard collectionCard;
        private JPanel detailAndAddCard = new JPanel();
            //
            private PanelDetailCard panelDetail;
            private PanelFormAddCard panelFormAddCard = new PanelFormAddCard();


    private GridBagLayout bL = new GridBagLayout();
    private GridBagConstraints gbC = new GridBagConstraints();
    public PanelMainPage()
    {

        this.setLayout(bL);
        initComponent();
        this.setVisible(true);
    }

    private void initComponent()
    {
        /*Init required properties of this panel*/
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setSize(new Dimension(500, 500));


        this.sideBar = new PanelSideBar();
        this.collectionCard =  new PanelCollectionCard(this);
        this.panelDetail =  new PanelDetailCard();

        /*Set Constraints of the sideBAr*/

        this.gbC.gridx = 1;
        this.gbC.gridy= 0;
        this.gbC.weightx = 0.05;
        this.gbC.weighty = 1;
        this.gbC.fill = GridBagConstraints.BOTH;
        this.add(this.sideBar,gbC);

        /*Set Constraints of the collectionCard Panel*/
        this.rightMain.setLayout(bL);
        this.gbC.gridx = 1;
        this.gbC.gridy= 0;
        this.gbC.weightx = 0.7;
        this.gbC.weighty = 1;
        this.gbC.fill = GridBagConstraints.BOTH;
        this.rightMain.add(this.collectionCard,this.gbC);

        /*Add Elements to panel detail an add card*/

        this.detailAndAddCard.setLayout(bL);
        this.gbC.gridx = 0;
        this.gbC.gridy= 0;
        this.gbC.weightx = 1;
        this.gbC.weighty = 0.1;
        this.gbC.fill = GridBagConstraints.BOTH;
        this.detailAndAddCard.add(this.panelDetail,this.gbC);

        this.gbC.gridx = 0;
        this.gbC.gridy= 1;
        this.gbC.weightx = 1;
        this.gbC.weighty = 0.8;
        this.gbC.fill = GridBagConstraints.BOTH;
        this.detailAndAddCard.add(this.panelFormAddCard,this.gbC);

        /*Set Constraints of the detail and add card Panel*/
        this.gbC.gridx = 2;
        this.gbC.gridy= 0;
        this.gbC.weightx = 0.3;
        this.gbC.weighty = 1;
        this.rightMain.setBorder(new EmptyBorder(5,5,5,5));
        this.rightMain.add(this.detailAndAddCard,this.gbC);

        this.rightMain.setBackground(new Color(54,54,58));
        this.gbC.gridx = 2;
        this.gbC.gridy= 0;
        this.gbC.weightx = 0.95;
        this.add(this.rightMain,gbC);


    }

    @Override
    public void update(Player p) {
        this.sideBar.update(p);
        this.collectionCard.update(p);
    }

    @Override
    public void pressBtn(Component c) {
        if(c.getName() == "btnDel")
        {
            Card cardToDel = ((CardIsJButton)c.getParent().getComponent(1)).getCard();
            if(cardToDel == this.panelDetail.getCardInUse())
                this.panelDetail.clearPanel();

            VueTest.listController.get(0).removeCollectionCardPlayer(cardToDel);
        }
        if(c.getName() == "cardToAdd")
        {
            CardIsJButton cardClicked = (CardIsJButton)c;
            this.panelDetail.setPanelDetail(cardClicked.getCard());
        }

    }
}
