package org.bitbucket.videso.provider;

import org.bitbucket.videso.service.decision.impl.CompositeDecisionTaker;
import org.bitbucket.videso.service.decision.impl.ConsoleDecisionTaker;
import org.bitbucket.videso.service.decision.impl.DealerDecisionTaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CompositeDecisionTakerProviderTest {

    @Mock
    private DealerDecisionTaker dealerDecisionTaker;

    @Mock
    private ConsoleDecisionTaker consoleDecisionTaker;

    @InjectMocks
    private CompositeDecisionTakerProvider provider;

    @Test
    public void shouldReturnDecisionTakerWithTwoStrategies() {
        //Given
        CompositeDecisionTaker expected = CompositeDecisionTaker
                .builder()
                .registerStrategy(dealerDecisionTaker)
                .registerStrategy(consoleDecisionTaker)
                .build();

        //When
        CompositeDecisionTaker result = provider.get();

        //Then
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}
