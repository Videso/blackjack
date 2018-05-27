package org.bitbucket.videso.factory.impl;

import org.bitbucket.videso.factory.BlackjackCardFactory;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.CardSuit;

public class BlackjackCardFactoryImpl implements BlackjackCardFactory {


    public BlackjackCard createCard(CardSuit suit, String rank) {
        Integer value;
        try {
            value = Integer.parseInt(rank);
        } catch (NumberFormatException e) {
            if (rank.equalsIgnoreCase("ace")) {
                value = 11;
            } else {
                value = 10;
            }
        }

        return new BlackjackCard(suit, rank, value);
    }
}
