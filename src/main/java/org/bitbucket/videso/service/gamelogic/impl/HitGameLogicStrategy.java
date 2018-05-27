package org.bitbucket.videso.service.gamelogic.impl;

import com.google.inject.Inject;
import org.bitbucket.videso.service.gamelogic.GameLogic;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.SpecialCase;
import org.bitbucket.videso.service.CardRandomizer;

import java.util.List;

public class HitGameLogicStrategy extends GameLogicBase implements GameLogic {

    private CardRandomizer<BlackjackCard> cardRandomizer;

    @Inject
    public HitGameLogicStrategy(CardRandomizer<BlackjackCard> cardRandomizer,
                                Move supportedMoveType) {
        super(supportedMoveType);
        this.cardRandomizer = cardRandomizer;
    }

    protected SpecialCase process(Player currentTurn, List<BlackjackCard> deck) {
        BlackjackCard randomizedCard = cardRandomizer.randomizeCard(deck);
        currentTurn.addCardToHand(randomizedCard);
        deck.remove(randomizedCard);
        Integer playersPoints = currentTurn.getPoints();

        return SpecialCase.get(playersPoints)
                .orElseThrow(() -> new IllegalStateException("No strategy found for " + playersPoints));
    }
}
