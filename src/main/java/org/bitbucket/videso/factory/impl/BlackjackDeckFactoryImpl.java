package org.bitbucket.videso.factory.impl;

import com.google.inject.Inject;
import org.bitbucket.videso.factory.BlackjackCardFactory;
import org.bitbucket.videso.factory.BlackjackDeckFactory;
import org.bitbucket.videso.model.BlackjackDeck;
import org.bitbucket.videso.model.enums.CardSuit;

public class BlackjackDeckFactoryImpl implements BlackjackDeckFactory {

    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    private BlackjackCardFactory blackjackCardFactory;

    @Inject
    public BlackjackDeckFactoryImpl(BlackjackCardFactory blackjackCardFactory) {
        this.blackjackCardFactory = blackjackCardFactory;
    }

    public BlackjackDeck createDeck() {
        BlackjackDeck deck = new BlackjackDeck();
        CardSuit[] cardSuits = CardSuit.values();

        for (String rank : RANKS) {
            for (CardSuit suit : cardSuits) {
                deck.addCard(blackjackCardFactory.createCard(suit, rank));
            }
        }

        return deck;
    }
}
