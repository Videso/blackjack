package org.bitbucket.videso;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.bitbucket.videso.factory.BlackjackCardFactory;
import org.bitbucket.videso.factory.BlackjackDeckFactory;
import org.bitbucket.videso.factory.GameFactory;
import org.bitbucket.videso.factory.PlayerFactory;
import org.bitbucket.videso.factory.impl.BlackjackCardFactoryImpl;
import org.bitbucket.videso.factory.impl.BlackjackDeckFactoryImpl;
import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.StatisticsTemplate;
import org.bitbucket.videso.provider.*;
import org.bitbucket.videso.service.BlackjackPointsCalculator;
import org.bitbucket.videso.service.CardRandomizer;
import org.bitbucket.videso.service.StatisticsPrinter;
import org.bitbucket.videso.service.decision.DecisionTaker;
import org.bitbucket.videso.service.gamelogic.GameLogic;
import org.bitbucket.videso.service.gamelogic.impl.HitGameLogicStrategy;
import org.bitbucket.videso.service.gamelogic.impl.StandGameLogicStrategy;
import org.bitbucket.videso.service.impl.BlackjackCardRandomizer;
import org.bitbucket.videso.service.impl.BlackjackPointsCalculatorImpl;
import org.bitbucket.videso.service.impl.ConsoleStatisticsPrinter;

public class MainModule extends AbstractModule {

    protected void configure() {
        bind(BlackjackDeckFactory.class).to(BlackjackDeckFactoryImpl.class);
        bind(BlackjackCardFactory.class).to(BlackjackCardFactoryImpl.class);
        bind(BlackjackPointsCalculator.class).to(BlackjackPointsCalculatorImpl.class);
        bind(GameLogic.class).toProvider(CompositeGameLogicStrategyProvider.class);
        bind(new TypeLiteral<CardRandomizer<BlackjackCard>>() {
        })
                .to(new TypeLiteral<BlackjackCardRandomizer>() {
                });
        bind(DecisionTaker.class).toProvider(CompositeDecisionTakerProvider.class);
        bind(StatisticsPrinter.class).to(ConsoleStatisticsPrinter.class);
        bind(HitGameLogicStrategy.class).toProvider(HitGameLogicProvider.class);
        bind(StandGameLogicStrategy.class).toProvider(StandGameLogicProvider.class);
        install(new FactoryModuleBuilder().build(GameFactory.class));
        install(new FactoryModuleBuilder().build(PlayerFactory.class));
        bind(StatisticsTemplate.class).toProvider(StatisticsTemplateProvider.class);
    }
}
