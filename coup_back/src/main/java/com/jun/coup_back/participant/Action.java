package com.jun.coup_back.participant;

public class Action {

    void addOneCoin(Participant participant) {
        System.out.println(participant.getId() + " 참가자 1코인을 획득했습니다.");
        participant.addCoin(1);
    }

    void addTwoCoin(Participant participant) {
        System.out.println(participant.getId() + "참가자 2코인을 획득했습니다.");
        participant.addCoin(2);
        // 공작 방해
    }

    void addThreeCoin(Participant participant) {
        System.out.println(participant.getId() + "참가자 3코인을 획득했습니다.");
        participant.addCoin(3);
        // 공작 의심
    }

    void assassinate(Participant from, ParticipantList participantList) {
        if (from.getCoin() < 3) {
            System.out.printf("%d번 참가자님, 코인이 부족합니다. 현재 코인 : %d%n", from.getId(), from.getCoin());
            return;
        }
        System.out.println("누구를 암살하시겠습니까?");
        participantList.printAliveParticipantListWithoutMe(from.getId());
       // 암살 진행
    }

    void exchangeCard(Participant from) {
        // 대사 의심

        // 카드 교
    }

    void extortCoin(Participant from, ParticipantList participantList) {
        System.out.println("어떤 플레이어의 코인을 갈취하시겠습니까?");
        participantList.printAliveParticipantListWithoutMe(from.getId());
        // to 지목
        // 사령관 의심
        // 집행 or die
    }

    void coup(Participant from, ParticipantList participantList) {
        System.out.println("쿠!!!!!");
        from.minusCoin(7);
        participantList.printAliveParticipantListWithoutMe(from.getId());
        // 지목당한사람 die
    }
}
