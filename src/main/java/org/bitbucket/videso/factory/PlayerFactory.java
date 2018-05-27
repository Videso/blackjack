package org.bitbucket.videso.factory;

import com.google.inject.assistedinject.Assisted;
import org.bitbucket.videso.model.Player;

public interface PlayerFactory {

    Player createPlayer(@Assisted String name);

    Player createPlayer(@Assisted String name, @Assisted boolean isDealer);
}
