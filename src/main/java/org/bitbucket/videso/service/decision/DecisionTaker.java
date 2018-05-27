package org.bitbucket.videso.service.decision;

import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;

public interface DecisionTaker {

    Move getDecision(Player player, Player dealer, Player currentTurn);

    boolean supports(Player player, Player dealer, Player currentTurn);
}
