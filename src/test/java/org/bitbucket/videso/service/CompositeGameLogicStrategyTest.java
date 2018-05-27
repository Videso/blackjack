package org.bitbucket.videso.service;

import org.bitbucket.videso.service.gamelogic.GameLogic;
import org.bitbucket.videso.service.gamelogic.impl.CompositeGameLogicStrategy;
import org.bitbucket.videso.service.gamelogic.impl.FixedGameLogicStrategy;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.SpecialCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CompositeGameLogicStrategyTest {

    private CompositeGameLogicStrategy gameLogicStrategy;

    private List<GameLogic> strategies = new ArrayList<>();

    private Player currentTurn;
    private List<BlackjackCard> deck = new ArrayList<>();
    private Move moveType = Move.HIT;

    @Before
    public void setup() {
        this.currentTurn = mock(Player.class);
        this.gameLogicStrategy = new CompositeGameLogicStrategy(strategies);
    }

    @Test
    public void shouldInvokeMethodOnOneStrategyAndReturnResult() {
        //Given
        SpecialCase expected = SpecialCase.BLACKJACK;
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, true));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        // When
        SpecialCase result = gameLogicStrategy.process(currentTurn, deck, moveType);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenNoStrategyFound() {
        //Given
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        //When
        gameLogicStrategy.process(currentTurn, deck, moveType);
    }

    @Test
    public void shouldReturnTrueWhenStrategyFound() {
        //Given
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, true));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        //When
        boolean result = gameLogicStrategy.supports(moveType);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenNoStrategyFound() {
        //Given
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        //When
        boolean result = gameLogicStrategy.supports(moveType);

        //Then
        assertThat(result).isFalse();
    }
}
