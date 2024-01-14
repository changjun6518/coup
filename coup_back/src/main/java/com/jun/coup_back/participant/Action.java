package com.jun.coup_back.participant;

import com.jun.coup_back.card.character.Duke;
import com.jun.coup_back.io.InputRobot;
import java.util.List;

public class Action {

    void addOneCoin(Participant participant) {
        System.out.println(participant.getId() + " 참가자 1코인을 획득했습니다.");
        participant.addCoin(1);
    }

    void addTwoCoin(Participant to, ParticipantList participantList) {
        List<Participant> aliveParticipantList = participantList.getAliveParticipantList(to.getId());
        boolean avalible = true;
        boolean chanllenge = false;
        for (Participant from : aliveParticipantList) {
            avalible = preventAddingCoinByDuke(from, to, avalible);
        }
        if (avalible) {
            to.addCoin(2);
            System.out.println(to.getId() + "참가자 2코인을 획득했습니다.");
        }
    }

    boolean preventAddingCoinByDuke(Participant from, Participant to, boolean avalible) {
        System.out.println(from.getId() + "번 참가자");
        System.out.println(to.getId() + "님이 해외원조하는 것을 방해하시겠습니까? (공작 패시브)");
        System.out.println("1. 나 공작이라서 너 2원 못가져가. 내려놔!");
        System.out.println("2. 넘어갈게요~~");
        int participantChoice = InputRobot.scanner.nextInt();
        switch (participantChoice) {
            case 1:
                System.out.println("1. 나 공작이라서 너 2원 못가져가. 내려놔!");
                avalible = challengeNotDuke(to, from, avalible);
                break;
            case 2:
                System.out.println("2. 넘어갈게요~~");
                break;
        }
        return avalible;
    }

    private boolean challengeNotDuke(Participant from, Participant to, boolean avalible) {
        System.out.println(from + "번 참가자");
        System.out.println(to.getId() + "님이 공작이 아니라는 것에 도전하시겠습니까?");
        System.out.println("1. 너 공작 아니잖아. 다시 2원 가져와");
        System.out.println("2. 젠장.. 넘어갈게요..");
        int secondParticipantChoice = InputRobot.scanner.nextInt();
        switch (secondParticipantChoice) {
            case 1:
                if (to.getCardA().isAlive() && to.getCardA() instanceof Duke
                        || to.getCardB().isAlive() && to.getCardB() instanceof Duke) {
                    System.out.println("공작 두두등장..!");
                    from.dead();
                    avalible = false;
                } else {
                    System.out.println("으악 공작이 없네??");
                    to.dead();
                }
                break;
            case 2:
                avalible = false;
                break;
        }
        return avalible;
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
