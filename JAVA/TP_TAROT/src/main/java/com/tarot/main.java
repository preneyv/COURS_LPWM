package com.tarot;

import com.tarot.controller.*;
import com.tarot.model.*;
import com.tarot.vue.VueTest;


public class main {

    public static void main(String[] args) {
        AbstractModel nM = new Player();
        /*for(int i = 0; i<14;i++)
        {
            Card c = new ArcanesMajeures("1","/images/c1.jpg", "Le bateleur", "Androgyne", "Feu");
            nM.addCard(c);
        }
        for(int i = 0; i<18;i++)
        {
            Card c = new ArcanesMineures("1","/images/c2.jpg", "Coupe", "As");
            nM.addCard(c);
        }*/

        AbstractControllerPlayer nC= new ControllerPlayer(nM);
        VueTest nVue = new VueTest(nC);
        nM.addObserver(nVue);

    }

}
