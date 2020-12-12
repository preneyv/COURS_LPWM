package com.tarot.vue;

import javax.swing.*;
import java.awt.*;


public class FormPanelWelcome extends JPanel implements IGotBtnClickable{

    private GridBagLayout bL = new GridBagLayout();
    private GridBagConstraints gbC = new GridBagConstraints();

    private JPanel panelForRoundedTextField = new JPanel();
    private JPanel panelForRadioButton = new JPanel();
    private RoundedJTextField roundedJTextField = new RoundedJTextField(40);
    private ButtonGroup radioButtonGroup = new ButtonGroup();
    private JRadioButton radioButtonMan = new JRadioButton();
    private JRadioButton radioButtonWoman = new JRadioButton();
    private JLabel labelForRadioButtonTitle = new JLabel();
    private JButton validForm = new JButton();
    private JLabel errorSubmit = new JLabel();

    private Component panelToListen;


    public FormPanelWelcome(Component pnl)
    {
        super();
        this.panelToListen = pnl;
        this.setLayout(bL);
        gbC.fill = GridBagConstraints.HORIZONTAL;
        initComponent();
        this.setVisible(true);

    }

    private void initComponent() {
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));
        this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 500, Toolkit.getDefaultToolkit().getScreenSize().height - 100));

        this.setSize(new Dimension(500, 500));
        this.setBackground(new Color(59,68,174));


        panelForRoundedTextField.setBackground(new Color(59,68,174));
        panelForRoundedTextField.add(roundedJTextField);



        radioButtonMan.setText("Homme");
        radioButtonMan.setActionCommand("Homme");
        radioButtonMan.setForeground(Color.WHITE);
        radioButtonWoman.setText("Femme");
        radioButtonWoman.setActionCommand("Femme");
        radioButtonWoman.setForeground(Color.WHITE);
        radioButtonGroup.add(radioButtonWoman);
        radioButtonGroup.add(radioButtonMan);
        radioButtonMan.setBackground(new Color(59,68,174));
        radioButtonWoman.setBackground(new Color(59,68,174));
        labelForRadioButtonTitle.setText("Genre : ");
        labelForRadioButtonTitle.setForeground(Color.WHITE);
        panelForRadioButton.add(labelForRadioButtonTitle);
        panelForRadioButton.add(radioButtonMan);
        panelForRadioButton.add(radioButtonWoman);
        panelForRadioButton.setBackground(new Color(59,68,174));

        validForm.setBackground(new Color(60,63,65));
        validForm.setText("Valider");
        validForm.setForeground(Color.WHITE);
        VueTest.mapBtnClickAction.put(this.validForm,this);
        ButtonAction checkForm = new ButtonAction();
        this.validForm.addMouseListener(checkForm);

        errorSubmit.setForeground(Color.RED);
        errorSubmit.setText("Erreur lors de la soumission du formulaire !");
        errorSubmit.setVisible(false);

        gbC.gridwidth = 1;
        gbC.gridheight = 1;
        gbC.gridx =0;
        gbC.gridy =0;
        this.add(panelForRoundedTextField,gbC);
        gbC.gridy =1;
        this.add(panelForRadioButton,gbC);
        gbC.gridy =2;
        this.add(validForm,gbC);
        gbC.gridy =3;
        this.add(errorSubmit,gbC);





    }

    @Override
    public void pressBtn(Component c) {
        if (!("").equals(this.roundedJTextField.getText()) && (this.radioButtonWoman.isSelected() || this.radioButtonMan.isSelected())) {
            String sexuality = this.radioButtonGroup.getSelection().getActionCommand();
            String name = this.roundedJTextField.getText();
            VueTest.listController.get(0).setPlayer(name, sexuality);
            ((VueTest)this.panelToListen).switchPanel();

        } else {
            errorSubmit.setVisible(true);
        }
    }
}
