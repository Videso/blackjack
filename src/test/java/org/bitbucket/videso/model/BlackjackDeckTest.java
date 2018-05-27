package org.bitbucket.videso.model;

import org.bitbucket.videso.factory.BlackjackCardFactory;
import org.bitbucket.videso.factory.BlackjackDeckFactory;
import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.factory.impl.BlackjackDeckFactoryImpl;
import org.bitbucket.videso.model.enums.CardSuit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BlackjackDeckTest {

    private BlackjackCardFactory cardFactory;
    private BlackjackDeckFactory deckFactory;
    private BlackjackDeck deck;

    @Before
    public void setup() {
        this.cardFactory = new BlackjackCardFactoryImpl();
        this.deckFactory = new BlackjackDeckFactoryImpl(this.cardFactory);
        this.deck = this.deckFactory.createDeck();
    }

    @Test
    public void shouldRemoveCardFromDeck() {
        //Given
        CardSuit suit = CardSuit.DIAMONDS;
        String rank = "10";
        BlackjackCard card = new BlackjackCard(suit, rank, 10);
        Integer expectedDeckSize = 51;

        //When
        deck.removeCard(suit, rank);

        //Then
        assertThat(deck.getCards()).doesNotContain(card);
        assertThat(deck.getCards().size()).isEqualTo(expectedDeckSize);
    }
}
