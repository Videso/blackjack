package org.bitbucket.videso.service.decision.impl;

import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.service.decision.DecisionTaker;

import java.util.ArrayList;
import java.util.List;

public class CompositeDecisionTaker implements DecisionTaker {

    private List<DecisionTaker> strategies;

    public CompositeDecisionTaker(List<DecisionTaker> strategies) {
        this.strategies = strategies;
    }

    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        return strategies.stream()
                .filter(decisionTaker -> decisionTaker.supports(player, dealer, currentTurn))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No strategy found for " + player + " " + dealer))
                .getDecision(player, dealer, currentTurn);
    }

    @Override
    public boolean supports(Player player, Player dealer, Player currentTurn) {
        return strategies.stream()
                .anyMatch(decisionTaker -> decisionTaker.supports(player, dealer, currentTurn));
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private List<DecisionTaker> strategies = new ArrayList<>();

        public Builder registerStrategy(DecisionTaker strategy) {
            this.strategies.add(strategy);
            return this;
        }

        public CompositeDecisionTaker build() {
            return new CompositeDecisionTaker(strategies);
        }
    }
}
