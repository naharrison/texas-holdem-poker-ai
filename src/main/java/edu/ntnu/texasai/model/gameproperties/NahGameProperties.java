package edu.ntnu.texasai.model.gameproperties;

import edu.ntnu.texasai.controller.phase2.PlayerControllerPhaseIINormal;
import edu.ntnu.texasai.model.Player;

import javax.inject.Inject;

public class NahGameProperties extends GameProperties {
    @Inject
    public NahGameProperties(final PlayerControllerPhaseIINormal playerControllerPhaseIINormal) {
        super(5, 100, 10, 5); // numberOfHands, initialMoney, bigBlind, smallBlind

        addPlayer(new Player(1, getInitialMoney(), playerControllerPhaseIINormal));
        addPlayer(new Player(2, getInitialMoney(), playerControllerPhaseIINormal));
        addPlayer(new Player(3, getInitialMoney(), playerControllerPhaseIINormal));
        addPlayer(new Player(4, getInitialMoney(), playerControllerPhaseIINormal));
        addPlayer(new Player(5, getInitialMoney(), playerControllerPhaseIINormal));
    }

}
