package com.jun.coup_back;

import com.jun.coup_back.card.Deck;
import com.jun.coup_back.participant.Participant;
import java.util.ArrayList;
import java.util.List;

public class Temp {

    public void game() {

        // 게임시작
        // 6명이서 카드 2장씩 받음
        Deck deck = new Deck();
        deck.init();
        List<Participant> participantList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            participantList.add(new Participant());
        }
        for (Participant participant : participantList) {
            participant.setCardA(deck.give());
            participant.setCardB(deck.give());
        }
        for (int i = 0; i < 6; i++) {
            System.out.println("A card = " + participantList.get(i).getCardA());
            System.out.println("B card = " + participantList.get(i).getCardB());
        }

        for (Participant participant : participantList) {
            participant.action();
        }
    }

}
