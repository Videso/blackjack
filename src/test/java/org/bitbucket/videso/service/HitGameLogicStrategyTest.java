package org.bitbucket.videso.service;

import org.bitbucket.videso.factory.BlackjackCardFactory;
import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.model.enums.CardSuit;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.enums.SpecialCase;
import org.bitbucket.videso.service.gamelogic.impl.HitGameLogicStrategy;
import org.bitbucket.videso.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HitGameLogicStrategyTest {

    @Mock
    private CardRandomizer<BlackjackCard> cardRandomizer;

    private Move supportedMoveType = Move.HIT;

    private HitGameLogicStrategy hitGameLogicStrategy;

    private BlackjackCardFactory cardFactory;

    private Player currentTurn;
    private List<BlackjackCard> deck = new ArrayList<>();

    @Before
    public void setup() {
        this.cardFactory = new BlackjackCardFactoryImpl();
        this.currentTurn = mock(Player.class);
        this.hitGameLogicStrategy = new HitGameLogicStrategy(cardRandomizer, supportedMoveType);

        this.deck.add(cardFactory.createCard(CardSuit.CLUBS, "2"));
        this.deck.add(cardFactory.createCard(CardSuit.CLUBS, "8"));


        when(cardRandomizer.randomizeCard(anyList())).thenReturn(this.cardFactory.createCard(CardSuit.DIAMONDS, "Ace"));
    }

    @Test
    public void shouldReturnResult() {
        //Given
        when(currentTurn.getPoints()).thenReturn(20);

        //When
        SpecialCase result = hitGameLogicStrategy.process(currentTurn, deck, supportedMoveType);

        //Then
        assertThat(result).isEqualTo(SpecialCase.FINE);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenNoStrategyFoundForPoints() {
        //Given
        when(currentTurn.getPoints()).thenReturn(-1);

        //When
        hitGameLogicStrategy.process(currentTurn, deck, supportedMoveType);
    }
}
