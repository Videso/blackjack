package org.bitbucket.videso.service;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.BlackjackDeck;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.enums.SpecialCase;
import org.bitbucket.videso.service.decision.DecisionTaker;
import org.bitbucket.videso.service.gamelogic.GameLogic;


public class Game {

    private static final Integer AMOUNT_OF_CARDS_TO_DRAW_ON_START = 2;

    private CardRandomizer<BlackjackCard> cardRandomizer;
    private StatisticsPrinter statisticsPrinter;
    private DecisionTaker decisionTaker;
    private GameLogic gameLogic;

    private BlackjackDeck deck;
    private Player dealer;
    private Player player;

    private Player currentTurn;
    private boolean isGameOn = false;

    @Inject
    public Game(
            CardRandomizer<BlackjackCard> cardRandomizer,
            StatisticsPrinter statisticsPrinter,
            DecisionTaker decisionTaker,
            GameLogic gameLogic,
            @Assisted BlackjackDeck blackjackDeck,
            @Assisted(value = "player") Player player,
            @Assisted(value = "dealer") Player dealer) {
        this.deck = blackjackDeck;
        this.player = player;
        this.cardRandomizer = cardRandomizer;
        this.statisticsPrinter = statisticsPrinter;
        this.gameLogic = gameLogic;
        this.decisionTaker = decisionTaker;
        this.dealer = dealer;
        this.currentTurn = player;
    }

    public void start() {
        isGameOn = true;
        addRandomizedCardsFromDeckToMembersHard(player);
        addRandomizedCardsFromDeckToMembersHard(dealer);
        statisticsPrinter.printStatistics(player, dealer);
        do {
            Move decision = decisionTaker.getDecision(player, dealer, currentTurn);
            SpecialCase turnResult = gameLogic.process(currentTurn, deck.getCards(), decision);
            changeTurnIfPlayerHasEndedTurn();
            statisticsPrinter.printStatistics(player, dealer);

            if (turnResult == SpecialCase.BURNED) {
                endGame(turnResult.toString(), false, false);
            } else if (turnResult == SpecialCase.BLACKJACK) {
                endGame(turnResult.toString(), true, false);
            }

            decideWhoWins();
        } while (isGameOn);
    }

    private void endGame(String reason, boolean currentTurnWinner, boolean draw) {
        this.isGameOn = false;
        if (draw) {
            System.out.println(reason);
            return;
        }

        if (currentTurnWinner) {
            System.out.println(currentTurn.getName() + " wins because " + reason);
        } else {
            System.out.println(currentTurn.getName() + " lose because " + reason);
        }
    }

    private void decideWhoWins() {
        if (player.isEndedTurn() && dealer.isEndedTurn()) {
            if (player.getPoints().equals(dealer.getPoints())) {
                endGame("DRAW", false, true);
            } else if (dealer.getPoints() > player.getPoints()) {
                endGame("HAVE LESS POINTS", true, false);
            } else if (player.getPoints() > dealer.getPoints()) {
                endGame("HAVE MORE POINTS", false, true);
            }
        }
    }

    private void changeTurnIfPlayerHasEndedTurn() {
        if (player.isEndedTurn()) {
            this.currentTurn = dealer;
        }
    }


    private void addRandomizedCardsFromDeckToMembersHard(Player member) {
        for (int i = 0; i < AMOUNT_OF_CARDS_TO_DRAW_ON_START; i++) {
            BlackjackCard card = cardRandomizer.randomizeCard(deck.getCards());
            deck.removeCard(card.getSuit(), card.getRank());
            if (member.isDealer() && i == (AMOUNT_OF_CARDS_TO_DRAW_ON_START - 1)) {
                card.setHidden(true);
            }
            member.addCardToHand(card);
        }
    }
}
