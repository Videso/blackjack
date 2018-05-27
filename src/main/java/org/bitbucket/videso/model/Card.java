package org.bitbucket.videso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bitbucket.videso.model.enums.CardSuit;

@Data
@AllArgsConstructor
public class Card {

    private CardSuit suit;

    private String rank;
}
