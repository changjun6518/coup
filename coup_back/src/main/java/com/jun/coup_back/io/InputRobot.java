package com.jun.coup_back.io;

import java.util.List;
import java.util.Scanner;

public class InputRobot {

    public static final Scanner scanner = new Scanner(System.in);

    public static int selectChoice() {
        int choice = selectChoice(7);
        switch (choice) {
            case 1:
                System.out.println("1. 소득(1원 얻기)");
                break;
            case 2:
                System.out.println("2. 외부원조(2원 얻기) / 공작에게 방해받을 수 있음");
                break;
            case 3:
                System.out.println("3. 세금징수(3원 얻기) / 공작 캐릭터 행동");
                break;
            case 4:
                System.out.println("4. 암살(3원으로 카드 1장 제거) / 암살자 케릭터 행동");
                break;
            case 5:
                System.out.println("5. 카드교환(카드 2장 가져와 교환) / 대사 케릭터 행동");
                break;
            case 6:
                System.out.println("6. 갈취(다른 플레이어 코인 2원 갈취) / 사령관 케릭터 행동");
                break;
            case 7:
                System.out.println("7. 쿠!!!(다른 플레이어 카드 1장 제거)");
                break;
        }
        return choice;
    }

    public static int selectChoice(int limit) {
        String userInput = scanner.next();
        try {
            int userInputNumber = Integer.parseInt(userInput);
            if (userInputNumber <= 0 || userInputNumber > limit) {
                throw new IllegalArgumentException(String.format("음 1부터 %d까지 가능합니다.", limit));
            }
            return userInputNumber;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return selectChoice(limit);
        }
    }

    public static int selectChoice(List<Integer> idList) {
        String userInput = scanner.next();
        try {
            int userInputNumber = Integer.parseInt(userInput);
            if (!idList.contains(userInputNumber)) {
                StringBuilder errorMessage = new StringBuilder();
                for (Integer id : idList) {
                    errorMessage.append(id).append("번 \t");
                }
                errorMessage.append("만 입력 가능합니다.");
                throw new IllegalArgumentException(errorMessage.toString());
            }
            return userInputNumber;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return selectChoice(idList);
        }
    }

    public static int selectChoice(int limit, List<Integer> idList) {
        String userInput = scanner.next();
        try {
            int userInputNumber = Integer.parseInt(userInput);
            if (userInputNumber <= 0 || userInputNumber > limit) {
                throw new IllegalArgumentException(String.format("음 1부터 %d까지 가능합니다.", limit));
            }
            if (!idList.contains(userInputNumber)) {
                StringBuilder errorMessage = new StringBuilder();
                for (Integer id : idList) {
                    errorMessage.append(id).append("번 \t");
                }
                errorMessage.append("만 입력 가능합니다.");
                throw new IllegalArgumentException(errorMessage.toString());
            }
            return userInputNumber;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return selectChoice(idList);
        }
    }
}
