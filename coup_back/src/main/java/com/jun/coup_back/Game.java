package com.jun.coup_back;

import com.jun.coup_back.card.Deck;
import com.jun.coup_back.participant.Participant;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public void start() {

        // 게임시작
        // 6명이서 카드 2장씩 받음
        Deck deck = new Deck();
        deck.init();
        List<Participant> participantList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            participantList.add(new Participant(i + 1, deck.give(), deck.give(), 2));
        }
        boolean flag = true;
        while (flag) {
            int part = 0;
            for (Participant participant : participantList) {
                if (participant.isAlive()) {
                    part++;
                    participant.action(participantList);
                }
            }
            if (part > 1) {
                flag = true;
            } else {
                flag = false;
            }
        }

        System.out.println("#############################");
        System.out.println("~~~~~~~~~~~~게임 오바~~~~~~~~~~");
        System.out.println("#############################");
        System.out.println("\n");

        for (Participant participant : participantList) {
            if (participant.isAlive()) {
                System.out.println(participant.getId() + "님이 우승하였습니다!");
            }
        }
    }

}
