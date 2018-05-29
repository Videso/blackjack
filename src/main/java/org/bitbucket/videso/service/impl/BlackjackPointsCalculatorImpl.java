package org.bitbucket.videso.service.impl;

import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.service.BlackjackPointsCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackPointsCalculatorImpl implements BlackjackPointsCalculator {

    public Integer calculatePoints(List<BlackjackCard> cards) {
        return cards.stream()
                .filter(card -> !card.isHidden())
                .map(BlackjackCard::getValue)
                .collect(Collectors.toList())
                .stream()
                .reduce(0, (left, right) -> {
                    if (isAce(right) && left + right > 21) {
                        left += 1;
                    } else {
                        left += right;
                    }
                    return left;
                });
    }

    private boolean isAce(Integer points) {
        return (points >= 11);
    }
}
