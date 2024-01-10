package com.jun.coup_back.participant;

import java.util.List;

public class ParticipantList {

    List<Participant> participantList;

    public ParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    void printAliveParticipantListWithoutMe(int id) {
        for (Participant participant : participantList) {
            if (id == participant.getId()) {
                continue;
            }
            if (!participant.isAlive()) {
                continue;
            }
            System.out.println(participant.getId() + "번 참가자");
        }
    }
}
