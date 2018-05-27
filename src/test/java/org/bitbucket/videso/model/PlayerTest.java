package org.bitbucket.videso.model;

import org.bitbucket.videso.factory.BlackjackCardFactory;
import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.model.enums.CardSuit;
import org.bitbucket.videso.service.BlackjackPointsCalculator;
import org.bitbucket.videso.service.impl.BlackjackPointsCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PlayerTest {

    private BlackjackPointsCalculator pointsCalculator;
    private BlackjackCardFactory cardFactory;

    private Player player;

    @Before
    public void setup(){
        this.pointsCalculator = new BlackjackPointsCalculatorImpl();
        this.cardFactory = new BlackjackCardFactoryImpl();
        this.player = new Player(pointsCalculator, "player");
    }

    @Test
    public void shouldAddCardToHandThenUpdatePoints(){
        //Given
        BlackjackCard toAdd = cardFactory.createCard(CardSuit.DIAMONDS, "5");
        Integer expectedPoints = 5;
        Integer expectedDeckSize = 1;

        //When
        player.addCardToHand(toAdd);

        //Then
        assertThat(player.getPoints()).isGreaterThanOrEqualTo(expectedPoints);
        assertThat(player.getCardsInHand().size()).isGreaterThanOrEqualTo(expectedDeckSize);
    }
}