package org.bitbucket.videso.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.bitbucket.videso.service.gamelogic.impl.CompositeGameLogicStrategy;
import org.bitbucket.videso.service.gamelogic.impl.HitGameLogicStrategy;
import org.bitbucket.videso.service.gamelogic.impl.StandGameLogicStrategy;

public class CompositeGameLogicStrategyProvider implements Provider<CompositeGameLogicStrategy> {

    private HitGameLogicStrategy hitGameLogicStrategy;
    private StandGameLogicStrategy standGameLogicStrategy;

    @Inject
    public CompositeGameLogicStrategyProvider(HitGameLogicStrategy hitGameLogicStrategy, StandGameLogicStrategy standGameLogicStrategy) {
        this.hitGameLogicStrategy = hitGameLogicStrategy;
        this.standGameLogicStrategy = standGameLogicStrategy;
    }

    @Override
    public CompositeGameLogicStrategy get() {
        return CompositeGameLogicStrategy.builder()
                .registerStrategy(hitGameLogicStrategy)
                .registerStrategy(standGameLogicStrategy)
                .build();
    }
}
