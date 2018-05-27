package org.bitbucket.videso.service;

import org.bitbucket.videso.model.Card;

import java.util.List;

public interface CardRandomizer<T extends Card> {

    T randomizeCard(List<T> deck);
}
