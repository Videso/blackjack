package org.bitbucket.videso.factory;

import com.google.inject.assistedinject.Assisted;
import org.bitbucket.videso.service.Game;
import org.bitbucket.videso.model.BlackjackDeck;
import org.bitbucket.videso.model.Player;

public interface GameFactory {

    Game createGame(@Assisted(value = "player") Player player,
                    @Assisted(value = "dealer") Player dealer,
                    BlackjackDeck blackjackDeck);
}
