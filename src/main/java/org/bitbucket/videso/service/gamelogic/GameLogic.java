package org.bitbucket.videso.service.gamelogic;

import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.model.enums.SpecialCase;

import java.util.List;

public interface GameLogic {

    SpecialCase process(Player currentTurn, List<BlackjackCard> deck, Move moveType);

    boolean supports(Move moveType);
}
