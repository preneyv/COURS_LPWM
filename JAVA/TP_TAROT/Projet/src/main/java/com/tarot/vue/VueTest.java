package com.tarot.vue;

import com.tarot.controller.AbstractControllerPlayer;
import com.tarot.controller.ControllerPlayer;
import com.tarot.model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class VueTest extends JFrame{


    static HashMap<Component, IGotBtnClickable> mapBtnClickAction = new HashMap<Component, IGotBtnClickable>();
    static HashMap<Component, IGotBtnHover> mapBtnHoverAction = new HashMap<Component, IGotBtnHover>();
    static HashMap<JComboBox, IGotComboxChange> mapBtnComboBoxItemChange = new HashMap<JComboBox, IGotComboxChange>();
    static HashMap<JTextField, IGotTextFieldKeyListening> mapTextFieldKeyPress = new HashMap<JTextField, IGotTextFieldKeyListening>();
    static HashMap<Component, IGotFocusComponent> mapFocusComponent = new HashMap<Component, IGotFocusComponent>();
    static ArrayList<AbstractControllerPlayer> listController = new ArrayList<AbstractControllerPlayer>();
    private AbstractModel modelAbsract = new Player();
    private AbstractControllerPlayer controller= new ControllerPlayer(modelAbsract);
    //Components
    //Main panel (Is a CardLayout so the user can switch with the panelFirstPage once he his name is set)
    private JPanel contentPane;
        //All the usefull components for Sign Up or start with his saved profile
         private JPanel panelInscription;
         private FormPanelWelcome formPanelWelcome;
         private PanelMainPage mainPage;



    public VueTest()
    {
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setLayout(new CardLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        initComponents();
        this.controllerApplicationHanddler();
        this.setContentPane(this.contentPane);
        this.validate();
        this.setVisible(true);


    }

    private void initComponents(){

       this.formPanelWelcome = new FormPanelWelcome(this);
        this.mainPage = new PanelMainPage();
        this.contentPane.add(this.formPanelWelcome);
        this.contentPane.add(mainPage);



    }

    private void controllerApplicationHanddler(){
        modelAbsract.addObserver(mainPage);
        listController.add(this.controller);
    }

    public void switchPanel(){
        this.formPanelWelcome.setVisible(false);
        this.mainPage.setVisible(true);
    }


}
