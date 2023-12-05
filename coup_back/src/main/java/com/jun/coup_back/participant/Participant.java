package com.jun.coup_back.participant;

import com.jun.coup_back.card.character.Card;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participant {

    private Card cardA;
    private Card cardB;

    public void action() {
        System.out.println("###7가지 액션 중 하나를 선택해주세요.###");
        System.out.println("1. 소득(1원 얻기)");
        System.out.println("2. 외부원조(2원 얻기) / 공작에게 방해받을 수 있음");
        System.out.println("3. 세금징수(3원 얻기) / 공작 캐릭터 행동");
        System.out.println("4. 암살(3원으로 카드 1장 제거) / 암살자 케릭터 행동");
        System.out.println("5. 카드교환(카드 2장 가져와 교환) / 대사 케릭터 행동");
        System.out.println("6. 갈취(다른 플레이어 코인 2원 갈취) / 사령관 케릭터 행동");
        System.out.println("7. 쿠!!!(다른 플레이어 카드 1장 제거)");
        Scanner scanner = new Scanner(System.in);
        int actionNumber = scanner.nextInt();
        System.out.println(actionNumber);
    }

}
