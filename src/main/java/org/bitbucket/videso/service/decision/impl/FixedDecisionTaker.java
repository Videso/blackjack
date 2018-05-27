package org.bitbucket.videso.service.decision.impl;

import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.service.decision.DecisionTaker;

public class FixedDecisionTaker implements DecisionTaker {

    private Move decision;
    private boolean supports;

    public FixedDecisionTaker(Move decision, boolean supports) {
        this.decision = decision;
        this.supports = supports;
    }

    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        return decision;
    }

    @Override
    public boolean supports(Player player, Player dealer, Player currentTurn) {
        return this.supports;
    }
}
