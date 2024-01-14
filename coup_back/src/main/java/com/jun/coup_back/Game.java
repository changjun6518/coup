package com.jun.coup_back;

import com.jun.coup_back.card.Deck;
import com.jun.coup_back.participant.Action;
import com.jun.coup_back.participant.Participant;
import com.jun.coup_back.participant.ParticipantList;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public void start() {

        // 게임시작
        // 6명이서 카드 2장씩 받음
        Deck deck = new Deck();
        deck.init();
        ParticipantList participantList = new ParticipantList(deck, 3);
        while (participantList.isAlive()) {
            participantList.action(deck);
        }

        System.out.println("#############################");
        System.out.println("~~~~~~~~~~~~게임 오바~~~~~~~~~~");
        System.out.println("#############################");
        System.out.println("\n");
        System.out.println(participantList.getLastAliveParticipant().getId() + "님이 우승하였습니다!");
    }

}
