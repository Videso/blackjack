package org.bitbucket.videso.factory;

import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.CardSuit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class BlackjackCardFactoryImplTest {

    private BlackjackCardFactoryImpl cardFactory;

    @Before
    public void setup(){
        this.cardFactory = new BlackjackCardFactoryImpl();
    }

    @Test
    public void shouldReturnCreatedCard(){
        //Given
        CardSuit suit1 = CardSuit.DIAMONDS;
        String rank1 = "Jack";

        CardSuit suit2 = CardSuit.HEARTS;
        String rank2 = "5";

        BlackjackCard expected1 = new BlackjackCard(suit1, rank1,10);
        BlackjackCard expected2 = new BlackjackCard(suit2, rank2, 5);

        //When
        BlackjackCard card1 = cardFactory.createCard(suit1, rank1);
        BlackjackCard card2 = cardFactory.createCard(suit2, rank2);

        //Then
        assertThat(card1).isEqualToComparingFieldByField(expected1);
        assertThat(card2).isEqualToComparingFieldByField(expected2);
    }
}
