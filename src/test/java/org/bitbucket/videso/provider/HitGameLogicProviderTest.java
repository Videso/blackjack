package org.bitbucket.videso.provider;

import org.bitbucket.videso.service.gamelogic.impl.HitGameLogicStrategy;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.service.CardRandomizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HitGameLogicProviderTest {

    @Mock
    private CardRandomizer<BlackjackCard> cardRandomizer;

    @InjectMocks
    private HitGameLogicProvider hitGameLogicStrategyProvider;

    private Move supportedMoveType = Move.HIT;

    @Test
    public void shouldReturnHitGameLogicStrategy() {
        //Given
        HitGameLogicStrategy expected = new HitGameLogicStrategy(cardRandomizer, supportedMoveType);

        //When
        HitGameLogicStrategy result = hitGameLogicStrategyProvider.get();

        //Then
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}
