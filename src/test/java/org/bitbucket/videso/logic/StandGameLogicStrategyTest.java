package org.bitbucket.videso.logic;

import org.bitbucket.videso.service.gamelogic.impl.StandGameLogicStrategy;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.service.BlackjackPointsCalculator;
import org.bitbucket.videso.service.impl.BlackjackPointsCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StandGameLogicStrategyTest {

    private StandGameLogicStrategy standGameLogicStrategy;
    private Move supportedMoveType;

    private BlackjackPointsCalculator blackjackPointsCalculator;
    private Player currentTurn;

    @Before
    public void setup() {
        this.supportedMoveType = Move.STAND;
        this.standGameLogicStrategy = new StandGameLogicStrategy(supportedMoveType);

        this.blackjackPointsCalculator = new BlackjackPointsCalculatorImpl();
        this.currentTurn = new Player(this.blackjackPointsCalculator, "player");
    }

    @Test
    public void shouldSetPlayerEndTurnFlagToTrue() {
        //Given
        List<BlackjackCard> deck = new ArrayList<>();

        //When
        standGameLogicStrategy.process(currentTurn, deck, supportedMoveType);

        //Then
        assertThat(currentTurn.isEndedTurn()).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfNoSpecialCaseFoundForPoints() {
        //Given
        currentTurn.setPoints(-1);

        //When
        standGameLogicStrategy.process(currentTurn, new ArrayList<>(), supportedMoveType);
    }
}
