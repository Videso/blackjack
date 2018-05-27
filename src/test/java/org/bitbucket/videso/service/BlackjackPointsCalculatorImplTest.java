package org.bitbucket.videso.service;

import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.CardSuit;
import org.bitbucket.videso.service.impl.BlackjackPointsCalculatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlackjackPointsCalculatorImplTest {

    private BlackjackPointsCalculatorImpl blackjackPointsCalculator = new BlackjackPointsCalculatorImpl();

    private BlackjackCardFactoryImpl cardFactory;

    @Before
    public void setup() {
        this.cardFactory = mock(BlackjackCardFactoryImpl.class);
        when(this.cardFactory.createCard(Mockito.any(), Mockito.anyString()))
                .thenCallRealMethod();
    }

    @Test
    public void shouldReturnAmountOfPoints() {
        //Given
        List<BlackjackCard> cards = new ArrayList<>();
        cards.add(cardFactory.createCard(CardSuit.HEARTS, "2"));
        cards.add(cardFactory.createCard(CardSuit.SPADES, "Jack"));
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "Queen"));
        Integer expected = 22;

        //When
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnAmountOfPointsWithAceValuedAs1() {
        //Given
        List<BlackjackCard> cards = new ArrayList<>();
        cards.add(cardFactory.createCard(CardSuit.HEARTS, "10"));
        cards.add(cardFactory.createCard(CardSuit.SPADES, "Jack"));
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "King"));
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "Ace"));
        Integer expected = 31;

        //When
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnAmountOfPointsWithAceValuedAs10() {
        //Given
        List<BlackjackCard> cards = new ArrayList<>();
        cards.add(cardFactory.createCard(CardSuit.HEARTS, "10"));
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "King"));
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "Ace"));
        Integer expected = 21;

        //When
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnValueWithoutCalculatingHiddenCards() {
        //Given
        List<BlackjackCard> cards = new ArrayList<>();
        BlackjackCard hiddenCard = cardFactory.createCard(CardSuit.HEARTS, "10");
        hiddenCard.setHidden(true);

        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "King"));
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "Ace"));
        cards.add(hiddenCard);
        Integer expected = 21;

        //When
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnCorrectValueWhenAceIsHidden(){
        //Given
        List<BlackjackCard> cards = new ArrayList<>();
        BlackjackCard hiddenAce = cardFactory.createCard(CardSuit.CLUBS, "ace");
        hiddenAce.setHidden(true);
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS,"10"));
        Integer expected = 10;

        //When
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnCorrectValueWhenAceIsNotHidden(){
        List<BlackjackCard> cards = new ArrayList<>();
        BlackjackCard hidden = cardFactory.createCard(CardSuit.DIAMONDS, "5");
        hidden.setHidden(true);

        cards.add(hidden);
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "ace"));
        Integer expected = 11;

        //When
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        //Then
        assertThat(result).isEqualTo(expected);
    }
}
