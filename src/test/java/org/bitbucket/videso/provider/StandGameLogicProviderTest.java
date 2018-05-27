package org.bitbucket.videso.provider;

import org.bitbucket.videso.service.gamelogic.impl.StandGameLogicStrategy;
import org.bitbucket.videso.model.enums.Move;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StandGameLogicProviderTest {

    private Move supportedMoveType = Move.STAND;

    private StandGameLogicProvider provider;

    @Before
    public void setup() {
        this.provider = new StandGameLogicProvider();
    }

    @Test
    public void shouldReturnStandGameLogic() {
        //Given
        StandGameLogicStrategy expected = new StandGameLogicStrategy(supportedMoveType);

        //When
        StandGameLogicStrategy result = provider.get();

        //Then
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}
