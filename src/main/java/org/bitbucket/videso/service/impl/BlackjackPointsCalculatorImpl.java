package org.bitbucket.videso.service.impl;

import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.service.BlackjackPointsCalculator;

import java.util.List;

public class BlackjackPointsCalculatorImpl implements BlackjackPointsCalculator {

    public Integer calculatePoints(List<BlackjackCard> cards) {
        Integer resultValue = 0;

        for (BlackjackCard card : cards) {
            if (card.isHidden()) continue;

            if (card.getRank().equalsIgnoreCase("ace")) {
                if ((resultValue + card.getValue()) > 21) {
                    resultValue += 1;
                } else {
                    resultValue += card.getValue();
                }
            } else {
                resultValue += card.getValue();
            }
        }

        return resultValue;
    }
}
