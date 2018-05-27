package org.bitbucket.videso.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import lombok.Setter;
import org.bitbucket.videso.service.gamelogic.impl.HitGameLogicStrategy;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.Move;
import org.bitbucket.videso.service.CardRandomizer;

public class HitGameLogicProvider implements Provider<HitGameLogicStrategy> {

    private static final Move SUPPORTED_MOVE_TYPE = Move.HIT;

    @Setter
    private Move supportedMoveType = SUPPORTED_MOVE_TYPE;

    private CardRandomizer<BlackjackCard> cardRandomizer;

    @Inject
    public HitGameLogicProvider(CardRandomizer<BlackjackCard> cardRandomizer) {
        this.cardRandomizer = cardRandomizer;
    }


    @Override
    public HitGameLogicStrategy get() {
        return new HitGameLogicStrategy(cardRandomizer, supportedMoveType);
    }
}
