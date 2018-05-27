package org.bitbucket.videso.provider;

import org.bitbucket.videso.service.gamelogic.impl.CompositeGameLogicStrategy;
import org.bitbucket.videso.service.gamelogic.impl.HitGameLogicStrategy;
import org.bitbucket.videso.service.gamelogic.impl.StandGameLogicStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CompositeGameLogicStrategyProviderTest {

    @Mock
    private HitGameLogicStrategy hitGameLogicStrategy;

    @Mock
    private StandGameLogicStrategy standGameLogicStrategy;

    @InjectMocks
    private CompositeGameLogicStrategyProvider provider;


    @Test
    public void shouldReturnCompositeGameLogicWithTwoStrategies() {
        //Given
        CompositeGameLogicStrategy expected = CompositeGameLogicStrategy.builder()
                .registerStrategy(hitGameLogicStrategy)
                .registerStrategy(standGameLogicStrategy)
                .build();

        //When
        CompositeGameLogicStrategy result = provider.get();

        //Then
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}
