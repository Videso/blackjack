package org.bitbucket.videso.factory;

import org.bitbucket.videso.model.BlackjackCard;
import org.bitbucket.videso.model.enums.CardSuit;

public interface BlackjackCardFactory {

    BlackjackCard createCard(CardSuit suit, String rank);
}
