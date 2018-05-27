package org.bitbucket.videso.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatisticsTemplate {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String template;

    private String role;
    private String nickname;
    private List<BlackjackCard> cards = new ArrayList<>();
    private Integer points;

    public StatisticsTemplate(String template) {
        this.template = template;
    }

    public String format() {
        String cardsInfo = prepareCards();
        return String.format(template, role, nickname, cardsInfo, points);
    }

    private String prepareCards() {
        StringBuilder cardsInfo = new StringBuilder();

        for (BlackjackCard card : cards) {
            cardsInfo.append("(");
            if (card.isHidden()) {
                cardsInfo.append("hidden");
            } else {
                cardsInfo.append(card.getRank())
                        .append(" ")
                        .append(card.getSuit());
            }
            cardsInfo.append(")").append(" ");
        }

        return cardsInfo.toString();
    }
}
