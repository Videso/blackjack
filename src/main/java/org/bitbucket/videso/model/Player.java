package org.bitbucket.videso.model;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import lombok.Getter;
import lombok.Setter;
import org.bitbucket.videso.service.BlackjackPointsCalculator;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {

    private BlackjackPointsCalculator pointsCalculator;

    private List<BlackjackCard> cardsInHand = new ArrayList<>();
    private Integer points = 0;
    private String name;
    private boolean isDealer = false;

    @Setter
    private boolean endedTurn = false;

    @AssistedInject
    public Player(BlackjackPointsCalculator blackjackPointsCalculator,
                  @Assisted String name) {
        this.pointsCalculator = blackjackPointsCalculator;
        this.name = name;
    }

    @AssistedInject
    public Player(BlackjackPointsCalculator blackjackPointsCalculator,
                  @Assisted String name,
                  @Assisted boolean isDealer) {
        this.pointsCalculator = blackjackPointsCalculator;
        this.name = name;
        this.isDealer = isDealer;
    }

    public void makeAllCardsVisible(){
        for(BlackjackCard card : cardsInHand){
            card.setHidden(false);
        }
        updatePoints();
    }

    public void addCardToHand(BlackjackCard card) {
        cardsInHand.add(card);
        updatePoints();
    }

    private void updatePoints() {
        this.points = pointsCalculator.calculatePoints(this.cardsInHand);
    }
}