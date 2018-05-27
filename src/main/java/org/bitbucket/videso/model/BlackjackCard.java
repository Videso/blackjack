package org.bitbucket.videso.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bitbucket.videso.model.enums.CardSuit;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BlackjackCard extends Card {

    private Integer value;

    @Getter
    @Setter
    private boolean isHidden = false;

    public BlackjackCard(CardSuit suit, String rank, Integer value) {
        super(suit, rank);
        this.value = value;
    }
}
