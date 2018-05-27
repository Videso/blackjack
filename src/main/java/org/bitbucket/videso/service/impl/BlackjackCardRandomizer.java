package org.bitbucket.videso.service.impl;

import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.service.CardRandomizer;

import java.util.List;
import java.util.Random;

public class BlackjackCardRandomizer implements CardRandomizer<BlackjackCard> {

    private static final Random RANDOM = new Random();

    public BlackjackCard randomizeCard(List<BlackjackCard> deck) {
        Integer randomCardIndex = RANDOM.nextInt(deck.size());
        return deck.get(randomCardIndex);
    }
}
