package com.jun.coup_back.participant;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.jun.coup_back.card.Deck;
import com.jun.coup_back.card.character.Assassin;
import com.jun.coup_back.card.character.Captain;
import com.jun.coup_back.card.character.Card;
import com.jun.coup_back.card.character.Contessa;
import com.jun.coup_back.card.character.Duke;
import com.jun.coup_back.card.character.Embassador;
import com.jun.coup_back.io.InputRobot;
import java.io.ObjectInputValidation;
import java.util.ArrayList;
import java.util.List;

public class Action {

    void addOneCoin(Participant participant) {
        System.out.println(participant.getId() + " 참가자 1코인을 획득했습니다.");
        participant.addCoin(1);
    }

    void addTwoCoin(Participant to, ParticipantList participantList) {
        boolean avalible = true;
        for (Participant from : participantList.getAliveParticipantList(to.getId())) {
            if (to.isChallenged()) continue;
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
                avalible = challengeNotDukeForTwoCoin(to, from, avalible);
                to.setChallenged(true);
                break;
            case 2:
                System.out.println("2. 넘어갈게요~~");
                break;
        }
        return avalible;
    }

    private boolean challengeNotDukeForTwoCoin(Participant from, Participant to, boolean avalible) {
        System.out.println(from.getId() + "번 참가자");
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

    void addThreeCoin(Participant to, ParticipantList participantList) {
        boolean avalible = true;
        for (Participant from : participantList.getAliveParticipantList(to.getId())) {
            if (to.isChallenged()) continue;
            avalible = challengeNotDukeForThreeCoin(from, to, avalible);
        }
        if (avalible) {
            to.addCoin(3);
            System.out.println(to.getId() + "참가자 3코인을 획득했습니다.");
        }
    }

    boolean challengeNotDukeForThreeCoin(Participant from, Participant to, boolean avalible) {
        System.out.println(from.getId() + "번 참가자");
        System.out.println(to.getId() + "님이 공작이 아니라는 것에 도전하시겠습니까?");
        System.out.println("1. 너 공작 아니잖아 ㅋ");
        System.out.println("2. 넘어갈게요~~");
        int participantChoice = InputRobot.scanner.nextInt();
        switch (participantChoice) {
            case 1:
                to.setChallenged(true);
                System.out.println("공작이 아니다에 도전하였습니다.");
                if (to.getCardA().isAlive() && to.getCardA() instanceof Duke || to.getCardB().isAlive() && to.getCardB() instanceof Duke) {
                    System.out.println("공작 두두등장..!");
                    from.dead();
                    avalible = true;
                } else {
                    System.out.println("으악 공작이 없네??");
                    to.dead();
                    avalible = false;
                }
                break;
            case 2:
                System.out.println("공작이라고?? 하 믿는다..");
                avalible = true;
        }
        return avalible;
    }

    void assassinate(Participant from, ParticipantList participantList) {
        if (from.getCoin() < 3) {
            System.out.println("3원이 없는데여?");
            return;
        }
        System.out.println("누구를 암살하시겠습니까?");
        participantList.printAliveParticipantListWithoutMe(from.getId());
        int otherId = InputRobot.scanner.nextInt();
        Participant to = participantList.getParticipantById(otherId);

        System.out.println("1. '암살자가 아니다'에 도전한다.");
        System.out.println("2. '귀부인이 있다'로 방어한다.");
        System.out.println("3. 순순히 암살당한다.");

        int otherChoice = InputRobot.scanner.nextInt();
        switch (otherChoice) {
            case 1: // 의심하거나
                System.out.println("상대가 암살자가 아니다에 도전하였습니다.");
                if (from.getCardA() instanceof Assassin || from.getCardB() instanceof Assassin) {
                    System.out.println("암살자 카드를 오픈합니다! 도전에 실패하여 상대 카드를 하나 제거합니다.");
                    to.dead();
                    if (to.isAlive()) {
                        to.dead();
                    }
                } else {
                    System.out.println("암살자 카드가 없습니다... 도전에 성공하여 자신의 카드를 하나 제거합니다.");
                    from.dead();
                }
                break;
            case 2: // 방어하거나
                System.out.println("상대가 귀부인으로 방어하였습니다.");
                System.out.println("1. 귀부인,,, 인정한다...");
                System.out.println("2. 귀부인??? 개소리마라!!!");
                int defenceChoice = InputRobot.scanner.nextInt();
                switch (defenceChoice) {
                    case 1:
                        System.out.println("귀부인,, 인정한다,,,");
                    case 2:
                        if (to.getCardA().isAlive() && to.getCardA() instanceof Contessa
                                || to.getCardB().isAlive() && to.getCardB() instanceof Contessa) {
                            System.out.println("아이고 이를 어째??? 귀부인 등장");
                            System.out.println("의심을 실패하였습니다. 자신의 카드를 제거합니다.");
                            from.dead();
                        } else {
                            System.out.println("귀.. 부인이 없다..");
                            to.dead();
                            if (to.isAlive()) {
                                to.dead();
                            }
                        }
                }
                break;
            case 3: // 순순히 죽거나
                to.dead();
                break;
        }
        from.minusCoin(3); // 암살 비용 3원 차감
    }

    void exchangeCard(Participant from, ParticipantList participantList, Deck deck) {
        System.out.println("5. 카드교환(카드 2장 가져와 교환) / 대사 케릭터 행동");
        System.out.println(from.getId() + "님이 카드를 교환하려고 합니다.");
        boolean success = true;
        for (Participant to : participantList.getAliveParticipantList(from.getId())) {
            if (from.isChallenged()) continue;

            System.out.println(to.getId() + "번 참가자");
            System.out.println(from.getId() + "님이 대사가 아닌 것에 도전하겠습니까?");
            System.out.println("1. 너 대사 아니지? 도전!!");
            System.out.println("2. 믿는다.. 스킵.");
            int participantChoice = InputRobot.scanner.nextInt();
            switch (participantChoice) {
                case 1:
                    from.setChallenged(true);
                    System.out.println("대사가 아니다에 도전하였습니다.");
                    if (from.getCardA().isAlive() && from.getCardA() instanceof Embassador
                            || from.getCardB().isAlive() && from.getCardB() instanceof Embassador) {
                        System.out.println("대사 두두등장..!");
                        to.dead();
                        success = true;
                    } else {
                        System.out.println("으악 대사가 없네??");
                        from.dead();
                        success = false;
                    }
                    break;
                case 2:
                    System.out.println("대사라고?? 하 믿는다..");
                    success = true;
                    break;
            }
        }
        if (success) {
            // 카드 변경
            embassadorAction(deck, from);
        }
    }

    private void embassadorAction(Deck deck, Participant participant) {
        Card giveA = deck.give();
        Card giveB = deck.give();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(participant.getCardA());
        cardList.add(participant.getCardB());
        cardList.add(giveA);
        cardList.add(giveB);
        for (int i = 0; i < cardList.size(); i++) {
            System.out.println((i + 1) + "번 카드 : " + cardList.get(i).getName());
        }
        System.out.println("카드를 선택해주세요.");
        int cardChoice = InputRobot.scanner.nextInt() - 1;
        System.out.println(cardList.get(cardChoice).getName() + " 카드를 선택하였습니다.");

        if (participant.getCardA().isAlive() && participant.getCardB().isAlive()) {
            participant.setCardA(cardList.get(cardChoice));
            System.out.println("한장의 카드를 더 선택해주세요.");

            for (int i = 0; i < cardList.size(); i++) {
                if (cardChoice == i) continue;
                System.out.println((i + 1) + "번 카드 : " + cardList.get(i).getName());
            }
            cardChoice = InputRobot.scanner.nextInt() - 1;
            participant.setCardB(cardList.get(cardChoice));
        } else if (participant.getCardA().isAlive()) {
            participant.setCardA(cardList.get(cardChoice));
        } else if (participant.getCardB().isAlive()) {
            participant.setCardB(cardList.get(cardChoice));
        } else {
            throw new IllegalArgumentException("앙 살아있는 카드가 없는데 ~~");
        }

        System.out.println("카드 선택을 완료했습니다. \n");
        participant.showAliveCard();
    }


    void extortCoin(Participant from, ParticipantList participantList) {
        System.out.println("6. 갈취(다른 플레이어 코인 2원 갈취) / 사령관 케릭터 행동");
        System.out.println("어떤 플레이어의 코인을 갈취하시겠습니까?");
        participantList.printAliveParticipantListWithoutMe(from.getId());
        boolean success = true;
        int playerChoice = InputRobot.scanner.nextInt();
        System.out.println(playerChoice + "번 참가자를 선택하였습니다.");
        Participant to = participantList.getParticipantById(playerChoice);

        System.out.println(to.getId() + "참가자님 어떤 선택을 하시렵니까???");
        System.out.println("1. 너 사령관 아니잖아");
        System.out.println("2. 나도 사령관이야 못가져가 어딜!");
        System.out.println("3. 허허 난 대사랍니다~ 못가져가지요");
        System.out.println("4. 하 그냥 줄게요..");
        int secondChoice = InputRobot.scanner.nextInt();
        switch (secondChoice) {
            case 1:
                if (from.getCardA().isAlive() && from.getCardA() instanceof Captain
                        || from.getCardB().isAlive() && from.getCardB() instanceof Captain) {
                    System.out.println("사령관 두두등장..!");
                    to.dead();
                    success = true;
                } else {
                    System.out.println("으악 사령관이 없네??");
                    from.dead();
                    success = false;
                }
                break;
            case 2:
                System.out.println(from.getId() + "참가자님");
                System.out.println("상대가 사령관이 아니라는 것에 도전하시겠습니까?");
                System.out.println("1. 너 사령관 아니잖아 왜그래");
                System.out.println("2. 하 이걸 믿으라고 ? ㅠ 믿을게");
                int 상대가_사령관_아님에_도전 = InputRobot.scanner.nextInt();
                switch (상대가_사령관_아님에_도전) {
                    case 1:
                        if (to.getCardA().isAlive() && to.getCardA() instanceof Captain
                                || to.getCardB().isAlive() && to.getCardB() instanceof Captain) {
                            System.out.println("사령관 두두등장..!");
                            from.dead();
                            success = false;
                        } else {
                            System.out.println("으악 사령관이 없네??");
                            to.dead();
                            success = true;
                        }
                        break;
                    case 2:
                        System.out.println("넘어간다~ 아무일도 일어나지 않았다..");
                        success = false;
                        break;
                }
                break;

            case 3:
                System.out.println("상대가 대사라고 주장하였습니다.");
                System.out.println("상대가 대사가 아니라는 것에 도전하시겠습니까?");
                System.out.println("1. 너 대사 아니잖아 왜그래");
                System.out.println("2. 하 이걸 믿으라고 ? ㅠ 믿을게");
                int 상대가_대사가_아님에_도전 = InputRobot.scanner.nextInt();
                switch (상대가_대사가_아님에_도전) {
                    case 1:
                        if (to.getCardA().isAlive() && to.getCardA() instanceof Embassador
                                || to.getCardB().isAlive() && to.getCardB() instanceof Embassador) {
                            System.out.println("대사 두두등장..!");
                            from.dead();
                            success = false;
                        } else {
                            System.out.println("으악 대사가 없네??");
                            to.dead();
                            success = true;
                        }
                        break;
                    case 2:
                        System.out.println("넘어간다~ 아무일도 일어나지 않았다..");
                        success = false;
                        break;
                }
                break;
            case 4:
                System.out.println("그냥 넘어간다...");
                success = true;
                break;
        }
        if (success) {
            int minusCoin = to.minusCoin();
            from.addCoin(minusCoin);
            System.out.println(minusCoin + "원 획득!");
        }
    }

    void coup(Participant from, ParticipantList participantList) {
        if (from.getCoin() < 7) {
            System.out.println("7원이 없는데요..?");
            return;
        }
        System.out.println("7. 쿠!!!(다른 플레이어 카드 1장 제거)");
        from.minusCoin(7);
        participantList.printAliveParticipantListWithoutMe(from.getId());

        int playerChoice = InputRobot.scanner.nextInt();
        Participant to = participantList.getParticipantById(playerChoice);
        to.dead();
    }
}
