package com.jun.coup_back.card.character;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Card {

    private Boolean state = true; // 생존, 죽음
    private String name;

    public Card(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
