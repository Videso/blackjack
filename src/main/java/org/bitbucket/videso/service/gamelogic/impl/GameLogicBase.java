package org.bitbucket.videso.service.gamelogic.impl;

import org.bitbucket.videso.service.gamelogic.GameLogic;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.SpecialCase;

import java.util.List;

public abstract class GameLogicBase implements GameLogic {

    private Move supportedMoveType;

    public GameLogicBase(Move supportedMoveType) {
        this.supportedMoveType = supportedMoveType;
    }

    public SpecialCase process(Player currentTurn, List<BlackjackCard> deck, Move moveType) {
        if (!supports(moveType)) {
            throw new IllegalArgumentException(moveType + " is not supported");
        }
        return process(currentTurn, deck);
    }

    protected abstract SpecialCase process(Player currentTurn, List<BlackjackCard> deck);

    public boolean supports(Move moveType) {
        return supportedMoveType.equals(moveType);
    }
}
