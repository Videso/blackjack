package org.bitbucket.videso.model;

import lombok.Getter;
import org.bitbucket.videso.model.enums.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class BlackjackDeck {

    private static final Integer MAX_AMOUNT_OF_CARDS = 52;

    @Getter
    private List<BlackjackCard> cards = new ArrayList<>();

    public void addCard(BlackjackCard card) {
        if (cards.size() >= MAX_AMOUNT_OF_CARDS) {
            throw new IllegalStateException("Deck is full!");
        }
        cards.add(card);
    }

    public void removeCard(CardSuit suit, String rank) {
        cards.removeIf(card -> card.getRank().equalsIgnoreCase(rank) && card.getSuit().equals(suit));
    }
}