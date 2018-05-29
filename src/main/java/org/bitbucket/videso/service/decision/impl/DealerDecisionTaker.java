package org.bitbucket.videso.service.decision.impl;

import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.service.decision.DecisionTaker;

public class DealerDecisionTaker implements DecisionTaker {

    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        if (!supports(player, dealer, currentTurn)) {
            throw new IllegalArgumentException("Strategy doesn't support  ...");
        }
        dealer.makeAllCardsVisible();

        Integer dealerPoints = dealer.getPoints();
        Integer playerPoints = player.getPoints();

        if (playerPoints > dealerPoints) {
            return Move.HIT;
        } else if (dealerPoints > playerPoints) {
            return Move.STAND;
        }

        return Move.STAND;
    }

    @Override
    public boolean supports(Player player, Player dealer, Player currentTurn) {
        return dealer == currentTurn;
    }
}
