package org.bitbucket.videso.service;

import org.bitbucket.videso.model.Player;

public interface StatisticsPrinter {

    void printStatistics(Player player, Player dealer);
}
