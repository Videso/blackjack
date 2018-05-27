package org.bitbucket.videso.service;

import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.service.decision.DecisionTaker;
import org.bitbucket.videso.service.decision.impl.CompositeDecisionTaker;
import org.bitbucket.videso.service.decision.impl.FixedDecisionTaker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CompositeDecisionTakerTest {

    private CompositeDecisionTaker compositeDecisionTaker;

    private List<DecisionTaker> strategies = new ArrayList<>();

    private Player player;
    private Player dealer;

    @Before
    public void setup() {
        this.compositeDecisionTaker = new CompositeDecisionTaker(strategies);
        this.player = mock(Player.class);
        this.dealer = mock(Player.class);
    }

    @Test
    public void shouldInvokeMethodOnOneStrategyAndReturnDecision() {
        //Given
        Move expectedDecision = Move.HIT;

        FixedDecisionTaker fixedDecisionTaker = new FixedDecisionTaker(expectedDecision, true);
        strategies.add(fixedDecisionTaker);
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));

        //Given
        Move result = compositeDecisionTaker.getDecision(player, dealer, player);

        //Then
        assertThat(result).isEqualTo(expectedDecision);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenNoStrategyFound() {
        //Given
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));

        //When
        compositeDecisionTaker.getDecision(player, dealer, dealer);
    }

    @Test
    public void shouldReturnTrueIfSupportedStrategyFound() {
        //Given
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, true));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));

        //When
        boolean result = compositeDecisionTaker.supports(player, dealer, dealer);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNoSupportedStrategyFound() {
        //Given
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));

        //When
        boolean result = compositeDecisionTaker.supports(player, dealer, dealer);

        //Then
        assertThat(result).isFalse();
    }
}
