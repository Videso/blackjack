package org.bitbucket.videso.provider;

import com.google.inject.Provider;
import lombok.Setter;
import org.bitbucket.videso.service.gamelogic.impl.StandGameLogicStrategy;
import org.bitbucket.videso.model.enums.Move;

public class StandGameLogicProvider implements Provider<StandGameLogicStrategy> {

    private static final Move SUPPORTED_MOVE_TYPE = Move.STAND;

    @Setter
    private Move supportedMoveType = SUPPORTED_MOVE_TYPE;

    @Override
    public StandGameLogicStrategy get() {
        return new StandGameLogicStrategy(supportedMoveType);
    }
}
