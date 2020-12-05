package com.tarot.vue;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tarot.controller.AbstractControllerPlayer;
import com.tarot.model.*;
import com.tarot.observer.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class VueTest extends JFrame implements Observer{

    //Other properties
    private final AbstractControllerPlayer controller;

    //Components
    //Main panel (Is a CardLayout so the user can switch with the panelFirstPage once he his name is set)
    private JPanel contentPane;
        //All the usefull components for Sign Up or start with his saved profile
         private JPanel panelInscription;
             //Contains each required components for the form
             private JPanel panelFormWelcome;
                //
                private JLabel sexLabel;
                private JPanel panelTextFieldToAdd;
                private JTextField prenomTextField;
                private final ButtonGroup buttonGroupChooseSex = new ButtonGroup();
                    //
                    private JRadioButton radioButtonBoy;
                    private JRadioButton radioButtonGirl;
                //
                private JButton validerFormWelcomeButton;
                private JLabel errorLabel;

        //this panel is set once the user is logged in
        private JPanel panelFirstPage;
            //left side of the panel
            private JPanel sideBar;
                //top of the sidebar
                private JPanel infoPlayerPanel;
                    //
                    private JLabel iconPlayer;
                    private JLabel playerName;
                //this bottom is in the bottom of SideBar
                private JButton sauvegarderVotreProfilButton;
            //right side of the panel
            private JPanel rightMain;
                //Right Panel of the RightMainPanel
                private JPanel panelOtherAction;

                    //Panel giving detail of the currently observed card
                    private JPanel detailCard;
                        //Panel used to center the title
                        private JPanel panelTitleCardInCharge;
                            //Title Label
                            private JLabel titlePanelDetailCard;
                        //This panel will get the Card the user wants to watch - This happens when the user click
                        //on one of his Card from his deck(see detailOfCard below l-525)
                        private JPanel panelDetailCard;

                    //Panel use for adding a new card to his deck
                    private JPanel formAddCard;
                        //Panel used to center the title
                        private JPanel panelTittleAddCard;
                            //Title Label
                            private JLabel titleAddCardPanelLabel;
                        //Panel with the usefull component for the form
                        private JPanel panelAddCardAndChooseCard;
                            //This panel will be filled by a list of Card the user shall select so he adds it to his deck
                            //(see the method fillComboBoxCard l-431)
                            private JPanel panelForList;
                                //
                                private JComboBox listWithCardToAdd;

                            //Staying components for the form but first....another JPanel
                            private JPanel panelFormAddCard;
                                //Panel that contains a ComboBox so the user choose a type.
                                private JPanel panelArcanesChoice;
                                    //
                                    private JLabel labelTypeCarte;
                                    private JComboBox comboBoxArcaneChoice;
                                //this Panel change depending on whether it's Majeure, Mineur or Majestueuse
                                private JPanel formForTheChoosenType;
                                    //
                                    private JPanel formForMajeurType;
                                        //
                                        private JLabel labelElement;
                                        private JComboBox comboBoxElementCardToAdd;
                                        private JLabel labelSexuality;
                                        private JComboBox comboBoxSexuality;
                                    //
                                    private JPanel formForMineurType;
                                        //
                                        private JLabel jLabelDomaineCardToAdd;
                                        private JComboBox listDomaineCardToAdd;
                                        private JLabel jLabelNumberCardToAdd;
                                        private JComboBox listeNumeroCarteToAdd;
                                    //
                                    private JPanel formForOtherType;
                                        //
                                        private JLabel planetLabelCardToAdd;
                                        private JComboBox listPlaneteCardToAdd;
                                //Panel for Last required Compononents
                                private JPanel mainFormAddCard;
                                    //
                                    private JLabel labelNameCardToAdd;
                                    private JTextField fieldNameCardToAdd;
                                    private JLabel numberCardToAdd;
                                    private JTextField fieldNumberCardToAdd;
                                    private JLabel errorFileChoosen;
                                    private JLabel labelChooseFile;
                                    private JTextField textFieldNameFileChoosen;
                                    private JButton btnOpenFileChoose;
                                //
                                private JButton btnValidFormAddCard;
                                private JLabel errorFormField;
                                private JButton btnRestForm;





                //Left Panel of the RightMain Panel
                private JPanel mainActionRightPanel;
                    //Panel To center the search Textfield
                    private JTextField searchTextField;
                        //
                        private JPanel panelSearch;
                    //
                    private JScrollPane scrollPanelSeeCard;
                        //This panel will contains the deck of the user. Each card is added once the form is complete
                        //and valid (see btnValidFormAddCard.addActionListener l-270)
                        private JPanel panelSeeCard;








    public VueTest(AbstractControllerPlayer absCtl)
    {
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        initComponents();
        this.controller = absCtl;
        this.setContentPane(this.contentPane);
        this.validate();
        this.setVisible(true);

        validerFormWelcomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    if(!("").equals(prenomTextField.getText()) && (radioButtonBoy.isSelected() || radioButtonGirl.isSelected()))
                    {
                        String sexuality = buttonGroupChooseSex.getSelection().getActionCommand();
                        String name = prenomTextField.getText();
                        controller.setPlayer(name,sexuality);
                        panelInscription.setVisible(false);
                        panelFirstPage.setVisible(true);

                    }else{
                        errorLabel.setVisible(true);
                    }


            }
        });

        sauvegarderVotreProfilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.savePlayerProfile();
            }
        });





        btnOpenFileChoose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldNameFileChoosen.setText("");

                JFrame frameFileToJoin = new JFrame("Ajouter une image");
                frameFileToJoin.setSize(new Dimension(500,500));
                frameFileToJoin.setLocationRelativeTo(null);
                frameFileToJoin.setResizable(false);
                JFileChooser jFc = new JFileChooser();
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("PNG File", "png","jpg");
                jFc.setFileFilter(fnef);
                int returnVal = jFc.showOpenDialog(frameFileToJoin);
                if (returnVal==JFileChooser.APPROVE_OPTION){

                    String ext = jFc.getSelectedFile().getName().substring(jFc.getSelectedFile().getName().lastIndexOf('.')+1);
                    if(("png").equals(ext.toLowerCase()) || ("jpg").equals(ext.toLowerCase()))
                    {
                        errorFileChoosen.setVisible(false);
                        textFieldNameFileChoosen.setText(jFc.getSelectedFile().getAbsolutePath());
                    }else{
                        errorFileChoosen.setVisible(true);
                    }

                }

            }
        });

        comboBoxArcaneChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String choice = (String)e.getItem();
                if("Arcanes Majeure".equals(choice)){formForMajeurType.setVisible(true);formForOtherType.setVisible(false);formForMineurType.setVisible(false);}
                if("Arcanes Mineure".equals(choice)){formForMineurType.setVisible(true);formForMajeurType.setVisible(false);formForOtherType.setVisible(false);}
                if("Arcanes Majestueuse".equals(choice)){formForOtherType.setVisible(true);formForMajeurType.setVisible(false);formForMineurType.setVisible(false);}
            }
        });
        btnValidFormAddCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validAddCardForm();
            }
        });

        btnRestForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetStateForm(true);
            }
        });
    }

    public void initComponents(){

        panelInscription.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        panelInscription.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        panelFormWelcome.setPreferredSize(new Dimension(panelFormWelcome.getParent().getPreferredSize().width/6, panelFormWelcome.getParent().getPreferredSize().height/6));
        panelFormWelcome.setMaximumSize(new Dimension(panelFormWelcome.getParent().getMaximumSize().width/6, panelFormWelcome.getParent().getMaximumSize().height/6));
        panelFormWelcome.setSize(new Dimension(panelFormWelcome.getParent().getPreferredSize().width/6, panelFormWelcome.getParent().getPreferredSize().height/6));
        panelFirstPage.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        panelFirstPage.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.scrollPanelSeeCard.setPreferredSize(new Dimension(this.scrollPanelSeeCard.getParent().getPreferredSize().width/2, 1080));
        this.scrollPanelSeeCard.setMaximumSize(new Dimension(this.scrollPanelSeeCard.getParent().getMaximumSize().width/2, 1080));
        this.panelSeeCard.setPreferredSize(new Dimension(this.panelSeeCard.getParent().getPreferredSize().width, -1));
        this.panelSeeCard.setMaximumSize(new Dimension(this.panelSeeCard.getParent().getMaximumSize().width,-1 ));
        this.panelOtherAction.setPreferredSize(new Dimension(this.panelOtherAction.getParent().getPreferredSize().width/2, -1));
        this.panelOtherAction.setMaximumSize(new Dimension(this.panelOtherAction.getParent().getMaximumSize().width/2, -1));
        this.panelAddCardAndChooseCard.setPreferredSize(new Dimension(this.panelAddCardAndChooseCard.getParent().getPreferredSize().width, -1));
        this.panelAddCardAndChooseCard.setMaximumSize(new Dimension(this.panelAddCardAndChooseCard.getParent().getMaximumSize().width, -1));
        this.radioButtonBoy.setActionCommand("Homme");
        this.radioButtonGirl.setActionCommand("Femme");
        this.buttonGroupChooseSex.add(radioButtonBoy);
        this.buttonGroupChooseSex.add(radioButtonGirl);
        prenomTextField = new RoundedJTextField(40);
        this.panelTextFieldToAdd.add(prenomTextField);
        this.scrollPanelSeeCard.setBorder(null);
        this.panelDetailCard.setPreferredSize(new Dimension(-1, this.panelDetailCard.getParent().getMaximumSize().height));
        this.panelDetailCard.setMaximumSize(new Dimension(-1, this.panelDetailCard.getParent().getMaximumSize().height));

        this.fillComboBoxCard();

        listWithCardToAdd.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //Pourquoi il le fait 2 fois, je ne sais pas...
                int indexChoice = listWithCardToAdd.getSelectedIndex();
                Card src = (Card) e.getItem();
                if(indexChoice != 0)
                {

                    comboBoxArcaneChoice.setEnabled(false);
                    if("ArcanesMajeures".equals(src.getClass().getSimpleName())){
                        comboBoxArcaneChoice.setSelectedIndex(1);
                        comboBoxElementCardToAdd.setSelectedItem(src.getElement());
                        comboBoxElementCardToAdd.setEnabled(false);
                        comboBoxSexuality.setSelectedItem(src.getSortOf());
                        comboBoxSexuality.setEnabled(false);
                    }
                    if("ArcanesMineures".equals(src.getClass().getSimpleName())){
                        comboBoxArcaneChoice.setSelectedIndex(2);
                        listDomaineCardToAdd.setSelectedItem(src.getDom());
                        listDomaineCardToAdd.setEnabled(false);
                        listeNumeroCarteToAdd.setSelectedItem(src.getNumber());
                        listeNumeroCarteToAdd.setEnabled(false);

                    }

                    fieldNameCardToAdd.setText(src.getNom());
                    fieldNameCardToAdd.setEnabled(false);
                    fieldNumberCardToAdd.setText(src.getNumber());
                    fieldNumberCardToAdd.setEnabled(false);
                    textFieldNameFileChoosen.setText((src.getPicture()));
                    textFieldNameFileChoosen.setEnabled(false);
                    btnOpenFileChoose.setEnabled(false);

                }

               if(indexChoice == 0){resetStateForm(false);}


            }
        });


        panelForList.setBorder(new EmptyBorder(10,10,10,10));
        panelForList.add(listWithCardToAdd,BorderLayout.CENTER);

        searchTextField.setBorder(new LineBorder(Color.BLACK,2));

        searchTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                String textDefaultSearchField = (!(("Rechercher une carte").equals(((JTextField)e.getSource()).getText())) ) ? ((JTextField)e.getSource()).getText() : "";
                ((JTextField)e.getSource()).setText(textDefaultSearchField);
            }

            @Override
            public void focusLost(FocusEvent e) {
                String textDefaultSearchField = ((("").equals(((JTextField)e.getSource()).getText())) ) ? "Rechercher une carte" : ((JTextField)e.getSource()).getText() ;
                ((JTextField)e.getSource()).setText(textDefaultSearchField);
                
            }
        });



    }

    public void fillComboBoxCard()
    {
        java.lang.reflect.Type listType = new TypeToken<ArrayList<ArcanesMajeures>>(){}.getType();
        java.lang.reflect.Type o_listType = new TypeToken<ArrayList<ArcanesMineures>>(){}.getType();
        ArrayList<Card> alCardArcMajeures=null;
        ArrayList<Card> alCardArcMineures=null;
        ArrayList<Card> alCard = new ArrayList<Card>();
        Gson profile = new Gson();
        try{
            Reader fr = new FileReader("toJsonArcanesMajeure");
            alCardArcMajeures = profile.fromJson(fr,listType);
            Reader o_fr = new FileReader("toJsonArcanesMineures.json");
            alCardArcMineures = profile.fromJson(o_fr,o_listType);

            alCard.addAll(alCardArcMajeures);
            alCard.addAll(alCardArcMineures);
        }catch(IOException e){
            e.printStackTrace();
        }
        listWithCardToAdd = new JComboBox(alCard.toArray(new Card[alCard.size()]));
        listWithCardToAdd.setRenderer(new ListRenderer());
    }

    public void resetStateForm(boolean afterValidation)
    {
        if(afterValidation)
            listWithCardToAdd.setSelectedIndex(0);

        comboBoxArcaneChoice.setEnabled(true);
        comboBoxArcaneChoice.setSelectedIndex(0);
        comboBoxSexuality.setEnabled(true);
        comboBoxSexuality.setSelectedIndex(0);
        comboBoxElementCardToAdd.setEnabled(true);
        comboBoxElementCardToAdd.setSelectedIndex(0);
        listPlaneteCardToAdd.setEnabled(true);
        listPlaneteCardToAdd.setSelectedIndex(0);
        listDomaineCardToAdd.setEnabled(true);
        listDomaineCardToAdd.setSelectedIndex(0);
        listeNumeroCarteToAdd.setEnabled(true);
        listeNumeroCarteToAdd.setSelectedIndex(0);
        fieldNameCardToAdd.setEnabled(true);
        fieldNameCardToAdd.setText("");
        fieldNumberCardToAdd.setEnabled(true);
        fieldNumberCardToAdd.setText("");
        textFieldNameFileChoosen.setEnabled(true);
        textFieldNameFileChoosen.setText("");
        btnOpenFileChoose.setEnabled(true);
        errorFormField.setVisible(false);

    }

    public void validAddCardForm(){

        String infoErreur ="<html>Il y a différentes erreurs de saisie : <br>";
        Card cToAdd=null;
        String nameFilToAdd="";
        Boolean readyToAdd = false;

        System.out.println(listWithCardToAdd.getSelectedIndex());
        if(listWithCardToAdd.getSelectedIndex() == 0)
        {
            File fToAdd = new File(textFieldNameFileChoosen.getText());
            try {
                BufferedImage bfI = ImageIO.read(fToAdd);
                String ext = fToAdd.getName().substring(fToAdd.getName().lastIndexOf('.')+1);

                ImageIO.write(bfI,ext, new File(getClass().getResource("/images/").getPath(),fToAdd.getName()));
                nameFilToAdd = fToAdd.getName();

            } catch (IOException ioException) {
                infoErreur += "- Une erreur à eu lieu avec le téléchargement de l'image<br>";

            }

        }else{
            nameFilToAdd = ((Card) listWithCardToAdd.getSelectedItem()).getPicture();
        }

        if((("").equals(textFieldNameFileChoosen.getText())==false) && (("").equals(fieldNameCardToAdd.getText())==false) && (("").equals(fieldNumberCardToAdd.getText())==false) && comboBoxArcaneChoice.getSelectedIndex()>0)
        {


            if(("Arcanes Majeure").equals(comboBoxArcaneChoice.getSelectedItem()))
            {
                if((comboBoxElementCardToAdd.getSelectedIndex()>0 ) && (comboBoxSexuality.getSelectedIndex() > 0))
                {

                    cToAdd = new ArcanesMajeures(fieldNumberCardToAdd.getText(), nameFilToAdd,fieldNameCardToAdd.getText(), (String)comboBoxElementCardToAdd.getSelectedItem(), (String)comboBoxSexuality.getSelectedItem());
                    readyToAdd = true;
                }else{
                    infoErreur += "- Element ou Genre de la carte <br>";
                }

            }else if(("Arcanes Mineure").equals(comboBoxArcaneChoice.getSelectedItem()))
            {
                if((listDomaineCardToAdd.getSelectedIndex()>0 ) && (listeNumeroCarteToAdd.getSelectedIndex()>0))
                {
                    fieldNameCardToAdd.setText(listeNumeroCarteToAdd.getSelectedItem() + " "+ listDomaineCardToAdd.getSelectedItem());
                    cToAdd = new ArcanesMineures((String)listeNumeroCarteToAdd.getSelectedItem(), nameFilToAdd, fieldNameCardToAdd.getText(), (String)listDomaineCardToAdd.getSelectedItem());
                    readyToAdd = true;
                }else{
                    infoErreur += "- Domaine ou Numéro de la carte <br>";
                }
            }else if(("Arcanes Majestueuse").equals(comboBoxArcaneChoice.getSelectedItem()))
            {
                if(listPlaneteCardToAdd.getSelectedIndex()>0 )
                {
                    cToAdd = new ArcanesMajestueuses(fieldNameCardToAdd.getText(), nameFilToAdd, fieldNumberCardToAdd.getText(), (String)listPlaneteCardToAdd.getSelectedItem());
                    readyToAdd = true;
                }else{
                    infoErreur += "- Planète de la carte <br>";
                }
            }else{
                infoErreur += "- Type de la carte <br>";
            }
        }else{
            readyToAdd = false;
            infoErreur += "- Nom ou numéro de la carte<br>";
        }

        errorFormField.setVisible(true);
        if(!readyToAdd)
        {

            errorFormField.setText(infoErreur+"<html>");
            errorFormField.setForeground(Color.red);

        }else{
            controller.addCollectionCardPlayer(cToAdd);
            errorFormField.setText("Carte ajoutée");
            errorFormField.setForeground(Color.green);
            resetStateForm(true);

        }

    }

    public void update(Player p) {

        this.playerName.setText(p.getFirstname());
        this.iconPlayer.setIcon(new ImageIcon(getClass().getResource("/images/").getPath()+p.getImageIcon()));
        if(p.getCollection().size()>0)
        {
            this.panelSeeCard.removeAll();
            for(Card c : p.getCollection())
            {
                JPanel containerCard = new JPanel();
                BorderLayout lt = new BorderLayout();

                lt.setHgap(0);
                lt.setVgap(0);
                containerCard.setLayout(lt);
                final JButton btnDel = new JButton(new ImageIcon(getClass().getResource("/images/").getPath()+"del.png"));
                btnDel.setOpaque(true);
                btnDel.setContentAreaFilled(false);
                btnDel.setBorderPainted(false);
                btnDel.setSize(20,20);
                btnDel.setVerticalAlignment(SwingConstants.TOP);
                btnDel.setMargin(new Insets(-2,0,0,0));
                btnDel.setVisible(false);
                btnDel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        JButton src = (JButton) e.getSource();
                        JPanel parent = (JPanel)src.getParent();
                        CardIsJButton toRm = (CardIsJButton) src.getParent().getComponent(1);
                        controller.removeCollectionCardPlayer(toRm.getCard());
                        parent.removeAll();
                        parent.getParent().remove(parent);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        ((JButton)e.getSource()).setVisible(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        ((JButton)e.getSource()).setVisible(false);
                    }
                });
                final CardIsJButton cardToAdd = new CardIsJButton(c);
                cardToAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        panelDetailCard.removeAll();
                        InfoJLabelDetail detailOfCard = new InfoJLabelDetail(cardToAdd.getCard());
                        panelDetailCard.add(detailOfCard);
                        super.mouseClicked(e);
                        panelDetailCard.validate();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        btnDel.setVisible(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        btnDel.setVisible(false);
                    }
                });

                containerCard.add(btnDel,BorderLayout.WEST);
                containerCard.add(cardToAdd,BorderLayout.EAST);
                containerCard.setBackground(new Color(50,51,50));
                this.panelSeeCard.add(containerCard);
            }
        }

        this.panelSeeCard.setPreferredSize(new Dimension(this.panelSeeCard.getParent().getPreferredSize().width, this.panelSeeCard.getComponentCount()/3*172));
        this.panelSeeCard.setMaximumSize(new Dimension(this.panelSeeCard.getParent().getMaximumSize().width,this.panelSeeCard.getComponentCount()/3*172 ));
        JViewport vw = new JViewport();
        vw.add("View",this.panelSeeCard);
        this.scrollPanelSeeCard.setViewport(vw);
        this.scrollPanelSeeCard.validate();
    }


}
