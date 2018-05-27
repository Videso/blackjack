package org.bitbucket.videso.factory;

import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.factory.impl.BlackjackDeckFactoryImpl;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.BlackjackDeck;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BlackjackDeckFactoryImplTest {

    private BlackjackCardFactory cardFactory;

    private BlackjackDeckFactoryImpl deckFactory;

    @Before
    public void setup() {
        this.cardFactory = new BlackjackCardFactoryImpl();
        this.deckFactory = new BlackjackDeckFactoryImpl(this.cardFactory);
    }

    @Test
    public void shouldCreateDeckContainingDistinctCards() {
        //When
        BlackjackDeck result = deckFactory.createDeck();

        //Then
        assertThat(result.getCards()).doesNotHaveDuplicates();
    }

    @Test
    public void shouldCreateDeckContains52Cards() {
        //When
        BlackjackDeck result = deckFactory.createDeck();

        //Then
        assertThat(result.getCards().size()).isEqualTo(52);
    }

    @Test
    public void shouldCreateDeckContainingAceValuedAs11() {
        //When
        BlackjackDeck result = deckFactory.createDeck();
        List<BlackjackCard> aces = new ArrayList<>();
        for (BlackjackCard card : result.getCards()) {
            if (card.getRank().equalsIgnoreCase("ace")) {
                aces.add(card);
            }
        }

        //Then
        assertThat(aces).extracting("value").containsOnly(11);
    }
}
