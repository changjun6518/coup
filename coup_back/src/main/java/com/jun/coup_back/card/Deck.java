package com.jun.coup_back.card;

import com.jun.coup_back.card.character.Assassin;
import com.jun.coup_back.card.character.Captain;
import com.jun.coup_back.card.character.Ability;
import com.jun.coup_back.card.character.Card;
import com.jun.coup_back.card.character.Contessa;
import com.jun.coup_back.card.character.Duke;
import com.jun.coup_back.card.character.Embassador;
import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> cardList;

    public Deck init() {
        cardList = new Stack<>();
        for (int i = 0; i < 5; i++) {
            Assassin assassin = new Assassin("암살자");
            Captain captain = new Captain("사령관");
            Contessa contessa = new Contessa("귀부인");
            Duke duke = new Duke("공작");
            Embassador embassador = new Embassador("대사");
            cardList.add(assassin);
            cardList.add(captain);
            cardList.add(contessa);
            cardList.add(duke);
            cardList.add(embassador);
        }
        shuffle();
        return this;
    }

    private void shuffle() {
        Collections.shuffle(cardList);
    }

    public Card give() {
        return cardList.pop();
    }

    public void receive(Card card) {
        cardList.add(card);
    }
}
