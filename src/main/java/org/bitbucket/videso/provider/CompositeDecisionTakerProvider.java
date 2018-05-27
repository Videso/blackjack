package org.bitbucket.videso.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.bitbucket.videso.service.decision.impl.CompositeDecisionTaker;
import org.bitbucket.videso.service.decision.impl.ConsoleDecisionTaker;
import org.bitbucket.videso.service.decision.impl.DealerDecisionTaker;

public class CompositeDecisionTakerProvider implements Provider<CompositeDecisionTaker> {

    private DealerDecisionTaker dealerDecisionTaker;
    private ConsoleDecisionTaker consoleDecisionTaker;

    @Inject
    public CompositeDecisionTakerProvider(DealerDecisionTaker dealerDecisionTaker,
                                          ConsoleDecisionTaker consoleDecisionTaker) {
        this.dealerDecisionTaker = dealerDecisionTaker;
        this.consoleDecisionTaker = consoleDecisionTaker;
    }

    @Override
    public CompositeDecisionTaker get() {
        return CompositeDecisionTaker.builder()
                .registerStrategy(dealerDecisionTaker)
                .registerStrategy(consoleDecisionTaker)
                .build();
    }
}
