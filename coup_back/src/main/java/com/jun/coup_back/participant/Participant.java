package com.jun.coup_back.participant;

import com.jun.coup_back.card.Deck;
import com.jun.coup_back.card.character.Assassin;
import com.jun.coup_back.card.character.Captain;
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

    private boolean isChallenged;

    private Action action;

    public void addCoin(int coin) {
        this.coin += coin;
    }

    public void minusCoin(int coin) {
        this.coin -= coin;
    }

    public void action(ParticipantList participantList2, List<Participant> participantList, Deck deck) {
        System.out.println(id + "님 차례입니다.");
        showAliveCard();
        System.out.println("보유 coin : " + coin);
        OutputRobot.printActionMenu();
        int choice = InputRobot.selectChoice();

        isChallenged = false;
        switch (choice) {
            case 1:
                action.addOneCoin(this);
                break;
            case 2:
                action.addTwoCoin(this, participantList2);
                break;
            case 3:
                action.addThreeCoin(this, participantList2);
                break;
            case 4:
                action.assassinate(this, participantList2);
                break;
            case 5:
                action.exchangeCard(this, participantList2, deck);
                break;
            case 6:
                action.extortCoin(this, participantList2);
                break;
            case 7:
                action.coup(this, participantList2);
                break;
            default:
                System.out.println("앙");
        }
        System.out.println("\n\n");
    }

    public void showAliveCard() {
        if (Boolean.TRUE.equals(cardA.getState())) {
            System.out.println("cardA : " + cardA.toString());
        }
        if (Boolean.TRUE.equals(cardB.getState())) {
            System.out.println("cardB : " + cardB.toString());
        }
    }

    public void dead() {
        if (cardA.getState() && cardB.getState()) {
            System.out.println("cardA : " + cardA.toString());
            System.out.println("cardB : " + cardB.toString());
            System.out.println("1. cardA를 버린다.");
            System.out.println("2. cardB를 버린다.");
            int cardChoice = InputRobot.selectChoice(2);
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

    public int minusCoin() {
        if (coin >= 2) {
            coin -= 2;
            return 2;
        } else if (coin == 1) {
            coin -= 1;
            return 1;
        }
        return 0;
    }
}
