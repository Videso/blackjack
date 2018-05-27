package org.bitbucket.videso.service.gamelogic.impl;

import com.google.inject.Inject;
import org.bitbucket.videso.service.gamelogic.GameLogic;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.SpecialCase;

import java.util.List;

public class StandGameLogicStrategy extends GameLogicBase implements GameLogic {

    @Inject
    public StandGameLogicStrategy(Move supportedMoveType) {
        super(supportedMoveType);
    }

    @Override
    protected SpecialCase process(Player currentTurn, List<BlackjackCard> deck) {
        Integer playerPoints = currentTurn.getPoints();
        currentTurn.setEndedTurn(true);
        return SpecialCase.get(playerPoints)
                .orElseThrow(() -> new IllegalStateException("No strategy found for " + playerPoints));
    }
}
