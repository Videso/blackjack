package org.bitbucket.videso.service.decision.impl;

import com.google.inject.Inject;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.service.BlackjackPointsCalculator;
import org.bitbucket.videso.service.decision.DecisionTaker;

import java.util.List;

public class DealerDecisionTaker implements DecisionTaker {

    private BlackjackPointsCalculator pointsCalculator;

    @Inject
    public DealerDecisionTaker(BlackjackPointsCalculator blackjackPointsCalculator) {
        this.pointsCalculator = blackjackPointsCalculator;
    }

    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        if (!supports(player, dealer, currentTurn)) {
            throw new IllegalArgumentException("Strategy doesn't support  ...");
        }
        setAllCardsVisible(dealer.getCardsInHand());
        dealer.setPoints(pointsCalculator.calculatePoints(dealer.getCardsInHand()));

        Integer dealerPoints = dealer.getPoints();
        Integer playerPoints = player.getPoints();

        if (playerPoints > dealerPoints) {
            return Move.HIT;
        } else if (dealerPoints > playerPoints) {
            return Move.STAND;
        }

        return Move.STAND;
    }

    private void setAllCardsVisible(List<BlackjackCard> hand) {
        for (BlackjackCard card : hand) {
            card.setHidden(false);
        }
    }

    @Override
    public boolean supports(Player player, Player dealer, Player currentTurn) {
        return dealer == currentTurn;
    }
}
