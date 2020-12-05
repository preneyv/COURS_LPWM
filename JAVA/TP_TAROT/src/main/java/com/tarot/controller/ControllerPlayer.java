package com.tarot.controller;

import com.tarot.model.AbstractModel;

public class ControllerPlayer extends AbstractControllerPlayer {

    public ControllerPlayer(AbstractModel aM) {
        super(aM);
    }

    public void controlAddCard() {
        this.absModel.addCard(this.card);
    }

    public void controlRemoveCard(){
        this.absModel.removeCard(this.card);
    }

    public void controlSetPlayer() {this.absModel.setPlayer(this.firstname, this.sexuality);}
}
