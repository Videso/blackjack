package org.bitbucket.videso.service.gamelogic.impl;

import org.bitbucket.videso.service.gamelogic.GameLogic;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.SpecialCase;

import java.util.List;

public class FixedGameLogicStrategy implements GameLogic {

    private SpecialCase specialCase;
    private boolean supports;

    public FixedGameLogicStrategy(SpecialCase specialCase, boolean supports) {
        this.specialCase = specialCase;
        this.supports = supports;
    }

    @Override
    public SpecialCase process(Player currentTurn, List<BlackjackCard> deck, Move moveType) {
        return this.specialCase;
    }

    @Override
    public boolean supports(Move moveType) {
        return this.supports;
    }
}
