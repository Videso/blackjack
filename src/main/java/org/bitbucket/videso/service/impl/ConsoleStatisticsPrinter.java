package org.bitbucket.videso.service.impl;

import com.google.inject.Inject;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.StatisticsTemplate;
import org.bitbucket.videso.service.StatisticsPrinter;

public class ConsoleStatisticsPrinter implements StatisticsPrinter {

    private StatisticsTemplate statisticsTemplate;

    @Inject
    public ConsoleStatisticsPrinter(StatisticsTemplate statisticsTemplate) {
        this.statisticsTemplate = statisticsTemplate;
    }

    @Override
    public void printStatistics(Player player, Player dealer) {
        printStatisticsFor(player);
        printStatisticsFor(dealer);
    }

    private void printStatisticsFor(Player member) {
        String role = member.isDealer() ? "Dealer" : "Player";
        statisticsTemplate.setNickname(member.getName());
        statisticsTemplate.setRole(role);
        statisticsTemplate.setCards(member.getCardsInHand());
        statisticsTemplate.setPoints(member.getPoints());
        System.out.println(statisticsTemplate.format());
    }
}
