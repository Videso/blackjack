package org.bitbucket.videso.service;

import org.bitbucket.videso.factory.BlackjackCardFactory;
import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.CardSuit;
import org.bitbucket.videso.service.impl.BlackjackCardRandomizer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BlackjackCardRandomizerTest {

    private BlackjackCardRandomizer blackjackCardRandomizer;

    private BlackjackCardFactory cardFactory;

    @Before
    public void setup() {
        this.cardFactory = new BlackjackCardFactoryImpl();
        this.blackjackCardRandomizer = new BlackjackCardRandomizer();
    }

    @Test
    public void shouldReturnRandomizeCard() {
        //Given
        List<BlackjackCard> deck = new ArrayList<>();
        deck.add(cardFactory.createCard(CardSuit.DIAMONDS, "2"));
        deck.add(cardFactory.createCard(CardSuit.HEARTS, "5"));
        deck.add(cardFactory.createCard(CardSuit.CLUBS, "Jack"));

        //When
        BlackjackCard result = blackjackCardRandomizer.randomizeCard(deck);

        //Then
        assertThat(result).isIn(deck);
    }
}
