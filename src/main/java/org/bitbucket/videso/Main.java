package org.bitbucket.videso;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bitbucket.videso.factory.BlackjackDeckFactory;
import org.bitbucket.videso.factory.GameFactory;
import org.bitbucket.videso.factory.PlayerFactory;
import org.bitbucket.videso.model.BlackjackDeck;
import org.bitbucket.videso.model.Player;
import org.bitbucket.videso.service.Game;

public class Main {

    public static void main(String[] args) {
        MainModule module = new MainModule();
        Injector injector = Guice.createInjector(module);

        GameFactory gameFactory = injector.getInstance(GameFactory.class);
        BlackjackDeckFactory blackjackDeckFactory = injector.getInstance(BlackjackDeckFactory.class);
        PlayerFactory playerFactory = injector.getInstance(PlayerFactory.class);

        Player player = playerFactory.createPlayer("John");
        Player dealer = playerFactory.createPlayer("Dealer", true);

        BlackjackDeck deck = blackjackDeckFactory.createDeck();

        Game game = gameFactory.createGame(player, dealer, deck);
        game.start();
    }
}
