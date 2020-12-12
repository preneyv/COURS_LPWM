package com.tarot.vue;


import com.tarot.model.ArcanesMineures;
import com.tarot.model.Card;

import javax.swing.*;
import java.awt.*;

public class PanelForArcanesMineureDetail extends AbstractPanelForArcanesType {




    private JPanel panelDomain = new JPanel(new GridLayout(1,1));
    private JLabel domain = new JLabel();
    private JButton iconSetDomain = new JButton();

    private JComboBox comboBoxNumber = new JComboBox();
        private String numberModel[] = {"Choisissez...", "As", "2", "3", "4", "5", "6", "7", "8", "9","10", "Valet", "Cavalier", "Reine", "Roi"};
        private final DefaultComboBoxModel modelNumber = new DefaultComboBoxModel(numberModel);
    private JComboBox comboBoxDomain = new JComboBox();
        private String domainModel[] = {"Choisissez...","Coupes", "Epées", "Batons","Deniers"};
        private final DefaultComboBoxModel modelDomain = new DefaultComboBoxModel(domainModel);


    private GridLayout gbL = new GridLayout(3,1);

    public PanelForArcanesMineureDetail(ArcanesMineures c)
    {
        super();
        this.setLayout(gbL);
        this.setOpaque(false);
        this.setBackground(new Color(3,3,32));
        initComponent();

        this.currentCard = c;
        this.name.setText(c.getNom());
        this.number.setText(c.getNumber());
        this.domain.setText(c.getDom());
        setStyleComponent();
        fillHashMap();

    }



    public void initComponent() {

        super.initComponent();
        this.panelDomain.setName("panelDomain");
        this.comboBoxDomain.setModel(this.modelDomain);
        this.comboBoxNumber.setModel(this.modelNumber);
        this.setPreferredSize(new Dimension(100,30));
        this.iconSetDomain.setIcon(new ImageIcon(getClass().getResource("/images/").getPath()+"edit.png"));
        this.panelDomain.add(this.domain);
        VueTest.mapBtnHoverAction.put(this.domain,this);
        this.domain.addMouseListener(btnAct);
        this.panelDomain.add(this.iconSetDomain);
        VueTest.mapBtnHoverAction.put(this.iconSetDomain,this);
        VueTest.mapBtnClickAction.put(this.iconSetDomain,this);
        this.iconSetDomain.addMouseListener(btnAct);
        VueTest.mapBtnHoverAction.put(this.comboBoxDomain,this);
        this.comboBoxDomain.addMouseListener(btnAct);

        this.panelDomain.setBackground(new Color(50,51,50));
        this.add(this.panelDomain);

    }

    public void fillHashMap()
    {
        super.fillHashMap();
        this.fieldAndHisKey.put(this.panelDomain.getName(),this.comboBoxDomain);
        this.fieldAndHisKey.put(this.panelNumber.getName(),this.comboBoxNumber);
        this.labelAndHisKey.put(this.panelDomain.getName(),this.domain);
        this.labelAndHisKey.put(this.panelNumber.getName(),this.number);
    }



    @Override
    public Card modifyCardAfterValidForm() {
        Card cToAdd = new ArcanesMineures(this.number.getText(), this.currentCard.getPicture(), this.name.getText(), this.domain.getText());
        return cToAdd;
    }


    public void setStyleComponent() {

        super.setStyleComponent();
        this.domain.setForeground(Color.WHITE);
    }




    public void resetStateField()
    {
        super.resetStateField();
        this.panelDomain.remove(0);
        this.panelDomain.add(this.domain,0);
        this.panelDomain.getComponent(0).setVisible(true);
        ((JButton)this.panelDomain.getComponent(1)).setIcon((new ImageIcon(getClass().getResource("/images/").getPath() + "edit.png")));

    }
}
