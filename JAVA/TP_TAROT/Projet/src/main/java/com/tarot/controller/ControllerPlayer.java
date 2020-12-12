package com.tarot.controller;

import com.tarot.model.AbstractModel;
import com.tarot.model.Card;

public class ControllerPlayer extends AbstractControllerPlayer {

    public ControllerPlayer(AbstractModel aM) {
        super(aM);
    }

    @Override
    public void controlAddCard() {
        this.absModel.addCard(this.card);
    }

    @Override
    public void controlRemoveCard(){
        this.absModel.removeCard(this.card);
    }

    @Override
    public void controlSetPlayer() {this.absModel.setPlayer(this.firstname, this.sexuality);}

    @Override
    public void controlSetCard(Card cToRm) {this.absModel.setCard(cToRm, this.card);}
}
