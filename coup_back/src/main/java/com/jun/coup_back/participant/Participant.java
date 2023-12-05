package com.jun.coup_back.participant;

import com.jun.coup_back.card.character.Assassin;
import com.jun.coup_back.card.character.Ability;
import com.jun.coup_back.card.character.Card;
import com.jun.coup_back.card.character.Contessa;
import com.jun.coup_back.io.InputRobot;
import com.jun.coup_back.io.OutputRobot;
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

    public void action(List<Participant> participantList) {
        System.out.println(id + "님 차례입니다.");
        if (Boolean.TRUE.equals(cardA.getState())) {
            System.out.println("cardA : " + cardA.toString());
        }
        if (Boolean.TRUE.equals(cardB.getState())) {
            System.out.println("cardB : " + cardB.toString());
        }
        System.out.println("보유 coin : " + coin);
        OutputRobot.printActionMenu();
        int choice = InputRobot.selectChoice();
        switch (choice) {
            case 1:
                System.out.println("1코인을 획득했습니다.");
                coin++;
                break;
            case 2:
                System.out.println("2코인을 획득했습니다.");
                coin += 2;
                break;
            case 3:
                System.out.println("3코인을 획득했습니다.");
                coin += 3;
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
                                case 1:
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
                                case 2:
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
                                case 3:
                                    participant.dead();
                                    break;
                            }
                        }
                    }
                    // 의심하거나
                    // 방어하거나
                    // 순순히 죽거나
                }
                break;
            case 5:
                System.out.println("5. 카드교환(카드 2장 가져와 교환) / 대사 케릭터 행동");
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

}
