package com.jun.coup_back.participant;

import com.jun.coup_back.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantList {

    List<Participant> participantList;

    public ParticipantList(Deck deck, int number) {
        participantList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            participantList.add(new Participant(i + 1, deck.give(), deck.give(), 2, new Action()));
        }
    }

    void printAliveParticipantListWithoutMe(int id) {
        for (Participant participant : getAliveParticipantList(id)) {
            System.out.println(participant.getId() + "번 참가자");
        }
    }

    List<Participant> getAliveParticipantList(int id) {
        return participantList.stream()
                .filter(participant -> participant.getId() != id)
                .filter(Participant::isAlive)
                .collect(Collectors.toList());
    }

    public boolean isAlive() {
        for (Participant participant : participantList) {
            if (Boolean.TRUE.equals(participant.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public void action(Deck deck) {
        for (Participant participant : participantList) {
            if (Boolean.TRUE.equals(participant.isAlive())) {
                participant.action(this, participantList, deck);
            }
        }
    }

    public Participant getLastAliveParticipant() {
        return participantList.stream()
                .filter(participant -> Boolean.TRUE.equals(participant.isAlive()))
                .findFirst()
                .orElseThrow();
    }
}
