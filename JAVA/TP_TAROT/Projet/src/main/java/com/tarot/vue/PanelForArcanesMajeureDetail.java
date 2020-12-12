package com.tarot.vue;

import com.tarot.model.ArcanesMajeures;
import com.tarot.model.Card;

import javax.swing.*;
import java.awt.*;

public class PanelForArcanesMajeureDetail extends AbstractPanelForArcanesType{


    private JPanel panelSortOf = new JPanel(new GridLayout(1,1));
        private JLabel sortOf = new JLabel();
        private JButton iconSetSortOf = new JButton();
    private JPanel panelElement = new JPanel(new GridLayout(1,1));
     private JLabel element = new JLabel();
        private JButton iconSetElement = new JButton();


    private JComboBox comboBoxElement = new JComboBox();
        String elementsModel[] = {"Choisissez...","Feu", "Terre", "Air","Eau"};
        private final DefaultComboBoxModel modelElement = new DefaultComboBoxModel(elementsModel);
    private JComboBox comboBoxSortOf = new JComboBox();
        String sortOfModel[] = {"Choisissez...","Masculin", "Feminin", "Androgine","Neutre"};
        private final DefaultComboBoxModel modelSortOf = new DefaultComboBoxModel(sortOfModel);

    GridLayout gbL = new GridLayout(4,1);


    public PanelForArcanesMajeureDetail(ArcanesMajeures c)
    {
        super();

        this.setLayout(gbL);
        this.setOpaque(false);
        this.setBackground(new Color(3,3,32));
        initComponent();
        this.currentCard = c;
        this.name.setText(this.currentCard.getNom());
        this.number.setText(this.currentCard.getNumber());
        this.element.setText(this.currentCard.getElement());
        this.sortOf.setText(this.currentCard.getSortOf());
        setStyleComponent();
        this.fillHashMap();


    }

    public void initComponent() {


        super.initComponent();
        this.panelElement.setName("panelElement");
        this.comboBoxElement.setModel(this.modelElement);
        this.setPreferredSize(new Dimension(100,30));
        this.iconSetElement.setIcon(new ImageIcon(getClass().getResource("/images/").getPath()+"edit.png"));
        this.panelElement.add(this.element);
        VueTest.mapBtnHoverAction.put(this.element,this);
        this.element.addMouseListener(btnAct);
        this.panelElement.add(this.iconSetElement);
        VueTest.mapBtnHoverAction.put(this.iconSetElement,this);
        VueTest.mapBtnClickAction.put(this.iconSetElement,this);
        this.iconSetElement.addMouseListener(btnAct);
        VueTest.mapBtnHoverAction.put(this.comboBoxElement,this);
        this.comboBoxElement.addMouseListener(btnAct);

        this.panelSortOf.setName("panelSortOf");
        this.comboBoxSortOf.setModel(this.modelSortOf);
        this.iconSetSortOf.setIcon(new ImageIcon(getClass().getResource("/images/").getPath()+"edit.png"));
        this.panelSortOf.add(this.sortOf);
        VueTest.mapBtnHoverAction.put(this.sortOf,this);
        this.sortOf.addMouseListener(btnAct);
        this.panelSortOf.add(this.iconSetSortOf);
        VueTest.mapBtnHoverAction.put(this.iconSetSortOf,this);
        VueTest.mapBtnClickAction.put(this.iconSetSortOf,this);
        this.iconSetSortOf.addMouseListener(btnAct);
        VueTest.mapBtnHoverAction.put(this.comboBoxSortOf,this);
        this.comboBoxSortOf.addMouseListener(btnAct);


        this.panelElement.setBackground(new Color(50,51,50));
        this.add(this.panelElement);
        this.panelSortOf.setBackground(new Color(50,51,50));
        this.add(this.panelSortOf);
    }

    public void fillHashMap()
    {
        super.fillHashMap();
        this.fieldAndHisKey.put(this.panelSortOf.getName(),this.comboBoxSortOf);
        this.fieldAndHisKey.put(this.panelElement.getName(),this.comboBoxElement);
        this.labelAndHisKey.put(this.panelSortOf.getName(),this.sortOf);
        this.labelAndHisKey.put(this.panelElement.getName(),this.element);
    }

    public void setStyleComponent(){

        super.setStyleComponent();
        this.element.setForeground(Color.WHITE);
        this.sortOf.setForeground(Color.WHITE);

    }


    @Override
    public Card modifyCardAfterValidForm() {
         Card cToAdd = new ArcanesMajeures(this.number.getText(), this.currentCard.getPicture(), this.name.getText(), this.sortOf.getText(), this.element.getText());
        return cToAdd;
    }


    public void resetStateField()
    {
        super.resetStateField();
        this.panelElement.remove(0);
        this.panelElement.add(this.element,0);
        this.panelElement.getComponent(0).setVisible(true);
        ((JButton)this.panelElement.getComponent(1)).setIcon((new ImageIcon(getClass().getResource("/images/").getPath() + "edit.png")));
        this.panelSortOf.remove(0);
        this.panelSortOf.add(this.sortOf,0);
        this.panelSortOf.getComponent(0).setVisible(true);
        ((JButton)this.panelSortOf.getComponent(1)).setIcon((new ImageIcon(getClass().getResource("/images/").getPath() + "edit.png")));
    }

}
