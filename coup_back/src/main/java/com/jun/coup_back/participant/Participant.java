package com.jun.coup_back.participant;

import com.jun.coup_back.card.Deck;
import com.jun.coup_back.card.character.Assassin;
import com.jun.coup_back.card.character.Card;
import com.jun.coup_back.card.character.Contessa;
import com.jun.coup_back.card.character.Duke;
import com.jun.coup_back.card.character.Embassador;
import com.jun.coup_back.io.InputRobot;
import com.jun.coup_back.io.OutputRobot;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participant {

    private int id;
    private Card cardA;
    private Card cardB;
    private int coin;

    public void action(List<Participant> participantList, Deck deck) {
        System.out.println(id + "님 차례입니다.");
        showAliveCard();
        System.out.println("보유 coin : " + coin);
        OutputRobot.printActionMenu();
        int choice = InputRobot.selectChoice();
        boolean challenge = false;
        boolean success = false;
        switch (choice) {
            case 1:
                System.out.println("1코인을 획득했습니다.");
                coin++;
                break;
            case 2:
                boolean avalible = true;
                for (Participant participant : participantList) {
                    if (id == participant.getId()) {
                        continue;
                    }
                    if (!participant.isAlive()) {
                        continue;
                    }
                    if (challenge) {
                        continue;
                    }
                    System.out.println(participant.getId() + "번 참가자");
                    System.out.println(id + "님이 해외원조하는 것을 방해하시겠습니까? (공작 패시브)");
                    System.out.println("1. 나 공작이라서 너 2원 못가져가. 내려놔!");
                    System.out.println("2. 넘어갈게요~~");
                    int participantChoice = InputRobot.scanner.nextInt();
                    switch (participantChoice) {
                        case 1:
                            challenge = true;
                            System.out.println("2원 못가져 가도록 하였습니다.");
                            System.out.println(id + "번 참가자");
                            System.out.println(participant.getId() + "님이 공작이 아니라는 것에 도전하시겠습니까?");
                            System.out.println("1. 너 공작 아니잖아. 다시 2원 가져와");
                            System.out.println("2. 젠장.. 넘어갈게요..");
                            int secondParticipantChoice = InputRobot.scanner.nextInt();
                            switch (secondParticipantChoice) {
                                case 1:
                                    if (participant.getCardA().isAlive() && participant.getCardA() instanceof Duke || participant.getCardB().isAlive() && participant.getCardB() instanceof Duke) {
                                        System.out.println("공작 두두등장..!");
                                        this.dead();
                                        avalible = false;
                                    } else {
                                        System.out.println("으악 공작이 없네??");
                                        participant.dead();
                                    }
                                    break;
                                case 2:
                                    avalible = false;
                                    break;
                            }

                            break;
                        case 2:
                            break;
                    }
                }
                if (avalible) {
                    coin += 2;
                    System.out.println(id + "참가자 2코인을 획득했습니다.");
                }

                break;
            case 3:
                for (Participant participant : participantList) {
                    if (id == participant.getId()) {
                        continue;
                    }
                    if (!participant.isAlive()) {
                        continue;
                    }
                    if (challenge) {
                        continue;
                    }
                    System.out.println(participant.getId() + "번 참가자");
                    System.out.println(id + "님이 공작이 아닌 것에 도전하겠습니까?");
                    System.out.println("1. 너 공작 아니지? 도전!!");
                    System.out.println("2. 믿는다.. 스킵.");
                    int participantChoice = InputRobot.scanner.nextInt();
                    // TODO - 상황별 알고리즘을 짜고 코딩하는게 더 빠를듯??
                    switch (participantChoice) {
                        case 1:
                            challenge = true;
                            System.out.println("공작이 아니다에 도전하였습니다.");
                            if (cardA.isAlive() && cardA instanceof Duke || cardB.isAlive() && cardB instanceof Duke) {
                                System.out.println("공작 두두등장..!");
                                participant.dead();
                                System.out.println(id + "님이 3코인을 획득했습니다.");
                                coin += 3;
                            } else {
                                System.out.println("으악 공작이 없네??");
                                this.dead();
                            }
                            break;
                        case 2:
                            System.out.println("공작이라고?? 하 믿는다..");
                            System.out.println(id + "님이 3코인을 획득했습니다.");
                            coin += 3;
                            break;
                    }
                }
                break;
            case 4:
                if (coin >= 3) {
                    System.out.println("누구를 암살하시겠습니까?");
                    for (Participant participant : participantList) {
                        if (id == participant.getId()) {
                            continue;
                        }
                        if (!participant.isAlive()) {
                            continue;
                        }
                        System.out.println(participant.getId() + "번 참가자");
                    }
                    int otherId = InputRobot.scanner.nextInt();
                    for (Participant participant : participantList) {
                        if (otherId == participant.getId()) {
                            System.out.println("1. '암살자가 아니다'에 도전한다.");
                            System.out.println("2. '귀부인이 있다'로 방어한다.");
                            System.out.println("3. 순순히 암살당한다.");
                            int otherChoice = InputRobot.scanner.nextInt();
                            switch (otherChoice) {
                                case 1: // 의심하거나
                                    System.out.println("상대가 암살자가 아니다에 도전하였습니다.");
                                    if (cardA instanceof Assassin || cardB instanceof Assassin) {
                                        System.out.println("암살자 카드를 오픈합니다! 도전에 실패하여 상대 카드를 하나 제거합니다.");
                                        participant.dead();
                                        if (participant.isAlive()) {
                                            participant.dead();
                                        }
                                    } else {
                                        System.out.println("암살자 카드가 없습니다... 도전에 성공하여 자신의 카드를 하나 제거합니다.");
                                        this.dead();
                                    }
                                    break;
                                case 2: // 방어하거나
                                    System.out.println("상대가 귀부인으로 방어하였습니다.");
                                    System.out.println("1. 귀부인,,, 인정한다...");
                                    System.out.println("2. 귀부인??? 개소리마라!!!");
                                    int defenceChoice = InputRobot.scanner.nextInt();
                                    if (defenceChoice == 2) {
                                        if (participant.cardA instanceof Contessa || participant.cardB instanceof Contessa) {
                                            System.out.println("아이고 이를 어째??? 귀부인 등장");
                                            System.out.println("의심을 실패하였습니다. 자신의 카드를 제거합니다.");
                                            this.dead();
                                        }
                                    }
                                    break;
                                case 3: // 순순히 죽거나
                                    participant.dead();
                                    break;
                            }
                        }
                    }
                    this.coin -= 3; // 암살 비용 3원 차감
                }
                break;
            case 5:
                System.out.println("5. 카드교환(카드 2장 가져와 교환) / 대사 케릭터 행동");
                System.out.println(id + "님이 카드를 교환하려고 합니다.");

                for (Participant participant : participantList) {
                    if (id == participant.getId()) continue;
                    if (!participant.isAlive()) continue;
                    if (challenge) continue;

                    System.out.println(participant.getId() + "번 참가자");
                    System.out.println(id + "님이 대사가 아닌 것에 도전하겠습니까?");
                    System.out.println("1. 너 대사 아니지? 도전!!");
                    System.out.println("2. 믿는다.. 스킵.");
                    int participantChoice = InputRobot.scanner.nextInt();
                    switch (participantChoice) {
                        case 1:
                            challenge = true;
                            System.out.println("대사가 아니다에 도전하였습니다.");
                            if (cardA.isAlive() && cardA instanceof Embassador || cardB.isAlive() && cardB instanceof Embassador) {
                                System.out.println("대사 두두등장..!");
                                participant.dead();
                                success = true;
                            } else {
                                System.out.println("으악 대사가 없네??");
                                this.dead();
                                success = false;
                                System.out.println();
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
                    embassadorAction(deck);
                }
                break;
            case 6:
                System.out.println("6. 갈취(다른 플레이어 코인 2원 갈취) / 사령관 케릭터 행동");
                break;
            case 7:
                System.out.println("7. 쿠!!!(다른 플레이어 카드 1장 제거)");
                coin -= 7;
                for (Participant participant : participantList) {
                    if (id == participant.getId()) {
                        continue;
                    }
                    if (!participant.isAlive()) {
                        continue;
                    }
                    System.out.println(participant.getId() + "번 참가자");
                }
                int playerChoice = InputRobot.scanner.nextInt();
                for (Participant participant : participantList) {
                    if (participant.getId() == playerChoice) {
                        participant.dead();
                        break;
                    }
                }
                break;
        }
        System.out.println("\n\n");
    }

    private void showAliveCard() {
        if (Boolean.TRUE.equals(cardA.getState())) {
            System.out.println("cardA : " + cardA.toString());
        }
        if (Boolean.TRUE.equals(cardB.getState())) {
            System.out.println("cardB : " + cardB.toString());
        }
    }

    private void embassadorAction(Deck deck) {
        Card giveA = deck.give();
        Card giveB = deck.give();
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(cardA);
        cardList.add(cardB);
        cardList.add(giveA);
        cardList.add(giveB);
        for (int i = 0; i < cardList.size(); i++) {
            System.out.println((i + 1) + "번 카드 : " + cardList.get(i).getName());
        }
        System.out.println("카드를 선택해주세요.");
        int cardChoice = InputRobot.scanner.nextInt() - 1;
        System.out.println(cardList.get(cardChoice).getName() + " 카드를 선택하였습니다.");
        List<Card> aliveCardList = getAliveCard();
        Card card = aliveCardList.get(0);
        if (card.equals(cardA)) { // equals의 원리랄까..
            cardA = aliveCardList.get(0);
        } else {
            cardB = aliveCardList.get(0);
        }

        if (aliveCardList.size() == 2) {
            System.out.println("한장의 카드를 더 선택해주세요.");
            for (int i = 0; i < cardList.size(); i++) {
                if (cardChoice == i) continue;
                System.out.println((i + 1) + "번 카드 : " + cardList.get(i).getName());
            }
            cardChoice = InputRobot.scanner.nextInt() - 1;
            cardB = cardList.get(cardChoice);
        }
        System.out.println("카드 선택을 완료했습니다. \n");
        showAliveCard();
    }

    public void dead() {
        if (cardA.getState() && cardB.getState()) {
            System.out.println("cardA : " + cardA.toString());
            System.out.println("cardB : " + cardB.toString());
            System.out.println("1. cardA를 버린다.");
            System.out.println("2. cardB를 버린다.");
            int cardChoice = InputRobot.scanner.nextInt();
            if (cardChoice == 1) {
                cardA.setState(false);
            }
            if (cardChoice == 2) {
                cardB.setState(false);
            }
            return;
        }
        if (cardA.getState()) {
            cardA.setState(false);
            System.out.println("목숨을 모두 잃었습니다.. 사망!");
        }

        if (cardB.getState()) {
            cardB.setState(false);
            System.out.println("목숨을 모두 잃었습니다.. 사망!");
        }
    }

    public Boolean isAlive() {
        if (Boolean.TRUE.equals(cardA.getState())) {
            return true;
        }
        if (Boolean.TRUE.equals(cardB.getState())) {
            return true;
        }

        return false;
    }

    private List<Card> getAliveCard() {
        ArrayList<Card> cardList = new ArrayList<>();
        if (Boolean.TRUE.equals(cardA.getState())) {
            cardList.add(cardA);
        }
        if (Boolean.TRUE.equals(cardB.getState())) {
            cardList.add(cardB);
        }
        return cardList;
    }
}
