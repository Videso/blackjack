package org.bitbucket.videso.service;

import org.bitbucket.videso.model.BlackjackCard;

import java.util.List;

public interface BlackjackPointsCalculator {

    Integer calculatePoints(List<BlackjackCard> cards);
}
