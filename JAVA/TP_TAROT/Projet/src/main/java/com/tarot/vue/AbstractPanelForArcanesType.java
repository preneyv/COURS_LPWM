package com.tarot.vue;


import com.tarot.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class AbstractPanelForArcanesType extends JPanel implements IGotBtnHover, IGotBtnClickable{


    protected Card currentCard;
    protected JPanel panelName = new JPanel(new GridLayout(1,1));
        protected JLabel name = new JLabel();
        protected JButton iconSetName = new JButton();
    protected JPanel panelNumber = new JPanel(new GridLayout(1,1));
        protected JLabel number = new JLabel();
        protected JButton iconSetNumber = new JButton();
    protected ButtonAction btnAct = new ButtonAction();

    protected JTextField nameTextField = new JTextField();
    protected JTextField numberTextField = new JTextField();

    protected HashMap<String, Component> fieldAndHisKey = new HashMap<String, Component>();
    protected HashMap<String, JLabel> labelAndHisKey = new HashMap<String, JLabel>();


    private Component compCurrentlyModifying = null;

    public AbstractPanelForArcanesType()
    {
        super();
    }

    public void initComponent()
    {
        this.panelName.setName("panelName");
        this.iconSetName.setIcon(new ImageIcon(getClass().getResource("/images/").getPath()+"edit.png"));
        this.panelName.add(this.name);
        VueTest.mapBtnHoverAction.put(this.name,this);
        this.name.addMouseListener(btnAct);
        this.panelName.add(this.iconSetName);
        VueTest.mapBtnHoverAction.put(this.iconSetName,this);
        VueTest.mapBtnClickAction.put(this.iconSetName,this);
        this.iconSetName.addMouseListener(btnAct);
        VueTest.mapBtnHoverAction.put(this.nameTextField,this);
        this.nameTextField.addMouseListener(btnAct);

        this.panelNumber.setName("panelNumber");
        this.iconSetNumber.setIcon(new ImageIcon(getClass().getResource("/images/").getPath()+"edit.png"));
        this.panelNumber.add(this.number);
        VueTest.mapBtnHoverAction.put(this.number,this);
        this.number.addMouseListener(btnAct);
        this.panelNumber.add(this.iconSetNumber);
        VueTest.mapBtnHoverAction.put(this.iconSetNumber,this);
        VueTest.mapBtnClickAction.put(this.iconSetNumber,this);
        this.iconSetNumber.addMouseListener(btnAct);
        VueTest.mapBtnHoverAction.put(this.numberTextField,this);
        this.numberTextField.addMouseListener(btnAct);

        this.panelName.setBackground(new Color(50,51,50));
        this.add(this.panelName);
        this.panelNumber.setBackground(new Color(50,51,50));
        this.add(this.panelNumber);
    }

    public void fillHashMap()
    {
        this.fieldAndHisKey.put(this.panelName.getName(),this.nameTextField);
        this.fieldAndHisKey.put(this.panelNumber.getName(),this.numberTextField);
        this.labelAndHisKey.put(this.panelName.getName(),this.name);
        this.labelAndHisKey.put(this.panelNumber.getName(),this.number);
    }

    public boolean testValidityField(Component c)
    {
        Boolean res = true;
        if(c.getClass() == JComboBox.class)
        {
            if(((JComboBox)c).getSelectedIndex()==0)
            {
                c.setBackground(new Color(245,103,101));
                res =  false;
            }
        }

        if(c.getClass() == JTextField.class)
        {
            if(("").equals(((JTextField)c).getText()))
            {
                c.setBackground(new Color(245,103,101));
                res =  false;
            }
        }

        return res;
    }
    @Override
    public void pressBtn(Component c) {

        JPanel parent = (JPanel)c.getParent();
        if(parent.getComponent(0).getClass() == JLabel.class)
        {
            resetStateField();
            Component compToBeAdd=this.fieldAndHisKey.get(parent.getName());

            if(compToBeAdd.getClass() == JTextField.class)
            {
                    ((JTextField)compToBeAdd).setText(((JLabel)parent.getComponent(0)).getText());
            }

            if(compToBeAdd.getClass() == JComboBox.class)
            {
                    ((JComboBox)compToBeAdd).setSelectedItem(((JLabel)parent.getComponent(0)).getText());
            }


            parent.getComponent(0).setVisible(false);
            parent.remove(0);
            parent.add(compToBeAdd,0);
            parent.getComponent(0).setVisible(true);

            ((JButton)parent.getComponent(1)).setIcon((new ImageIcon(getClass().getResource("/images/").getPath()+"valid.png")));
            this.compCurrentlyModifying = parent;

        }else if(parent.getComponent(0).getClass() == JTextField.class || parent.getComponent(0).getClass() == JComboBox.class )
        {

            Component cToValid = parent.getComponent(0);
            Boolean checkForm = true;
            if(checkForm = this.testValidityField(cToValid)) {

                Component compToBeAdd = this.labelAndHisKey.get(parent.getName());

                if (cToValid.getClass() == JTextField.class) {
                    ((JLabel) compToBeAdd).setText(((JTextField)cToValid).getText());
                }


                if (cToValid.getClass() == JComboBox.class) {

                    ((JLabel) compToBeAdd).setText((String) (((JComboBox) cToValid).getSelectedItem()));
                }


                parent.getComponent(0).setBackground(Color.white);
                parent.getComponent(0).setVisible(false);
                parent.remove(0);
                parent.add(compToBeAdd, 0);
                parent.getComponent(0).setVisible(true);
                ((JButton) parent.getComponent(1)).setIcon((new ImageIcon(getClass().getResource("/images/").getPath() + "edit.png")));

                Card cToAdd = this.modifyCardAfterValidForm();
                VueTest.listController.get(0).setCard(this.currentCard, cToAdd);
                this.currentCard = cToAdd;
                this.compCurrentlyModifying = null;
            }
        }
        parent.validate();
    }

    public void setStyleComponent()
    {
        this.name.setForeground(Color.WHITE);
        this.number.setForeground(Color.WHITE);
        for (Component c:this.getComponents())
        {
            ((JButton)(((JPanel)c).getComponent(1))).setOpaque(true);
            ((JButton)(((JPanel)c).getComponent(1))).setContentAreaFilled(false);
            ((JButton)(((JPanel)c).getComponent(1))).setBorderPainted(false);
            ((JButton)(((JPanel)c).getComponent(1))).setSize(20,20);
            ((JButton)(((JPanel)c).getComponent(1))).setMargin(new Insets(-2,0,0,0));
            ((JButton)(((JPanel)c).getComponent(1))).setVisible(false);
        }
    }

    public void resetStateField()
    {
        this.panelName.remove(0);
        this.panelName.add(this.name,0);
        this.panelName.getComponent(0).setVisible(true);
        ((JButton)this.panelName.getComponent(1)).setIcon((new ImageIcon(getClass().getResource("/images/").getPath() + "edit.png")));
        this.panelNumber.remove(0);
        this.panelNumber.add(this.number,0);
        this.panelNumber.getComponent(0).setVisible(true);
        ((JButton)this.panelNumber.getComponent(1)).setIcon((new ImageIcon(getClass().getResource("/images/").getPath() + "edit.png")));
    }

    @Override
    public void hoverOut(Component c) {
        ((JPanel)c.getParent()).getComponent(1).setVisible(false);
        if(c.getParent() == this.compCurrentlyModifying)
            ((JPanel)c.getParent()).getComponent(1).setVisible(true);
    }

    @Override
    public void hoverIn(Component c) {
        ((JPanel)c.getParent()).getComponent(1).setVisible(true);
    }

    abstract public Card modifyCardAfterValidForm();

}
