package org.bitbucket.videso.service.gamelogic.impl;

import org.bitbucket.videso.service.gamelogic.GameLogic;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.SpecialCase;

import java.util.ArrayList;
import java.util.List;

public class CompositeGameLogicStrategy implements GameLogic {

    private List<GameLogic> strategies;

    public CompositeGameLogicStrategy(List<GameLogic> strategies) {
        this.strategies = strategies;
    }

    @Override
    public SpecialCase process(Player currentTurn, List<BlackjackCard> deck, Move moveType) {
        return strategies.stream()
                .filter(gameLogic -> gameLogic.supports(moveType))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No strategy found for  " + moveType))
                .process(currentTurn, deck, moveType);
    }

    @Override
    public boolean supports(Move moveType) {
        return strategies.stream()
                .anyMatch(gameLogic -> gameLogic.supports(moveType));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<GameLogic> strategies = new ArrayList<>();

        public Builder registerStrategy(GameLogic strategy) {
            this.strategies.add(strategy);
            return this;
        }

        public CompositeGameLogicStrategy build() {
            return new CompositeGameLogicStrategy(this.strategies);
        }
    }
}
