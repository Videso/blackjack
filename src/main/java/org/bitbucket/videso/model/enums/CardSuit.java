package org.bitbucket.videso.model.enums;

public enum CardSuit {

    HEARTS("heart"),
    SPADES("spades"),
    DIAMONDS("diamonds"),
    CLUBS("clubs");

    private String name;

    CardSuit(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
