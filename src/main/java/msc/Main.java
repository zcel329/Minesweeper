package msc;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board.Difficulty boardtype;

        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose a difficulty below by entering the corresponding number: \n 1: [BEGINNER] \n 2: [INTERMEDIATE] \n 3: [EXPERT]");

        String selection = myObj.nextLine();  // Read user input

        try {
            int num = Integer.parseInt(selection);
            switch (num) {
                case 1 -> {
                    boardtype = Board.Difficulty.BEGINNER;
                    System.out.println("You have chosen BEGINNER \n");
                }
                case 2 -> {
                    boardtype = Board.Difficulty.INTERMEDIATE;
                    System.out.println("You have chosen INTERMEDIATE \n");
                }
                case 3 -> {
                    boardtype = Board.Difficulty.EXPERT;
                    System.out.println("You have chosen EXPERT \n");
                }
                default -> {
                    System.out.println("Error: invalid difficulty choice. Defaulted to BEGINNER difficulty.");
                    boardtype = Board.Difficulty.BEGINNER;
                }
            }
        } catch (NumberFormatException error) {
            System.out.println("Error: Invalid difficulty choice. Defaulted to BEGINNER difficulty.");
            boardtype = Board.Difficulty.BEGINNER;
        }

        Board trueBoard = new Board();
        trueBoard = trueBoard.createTrueBoard(boardtype);
        for (int i = 0; i < trueBoard.getBoardArray().length; i++) {
            System.out.println(Arrays.toString(trueBoard.getBoardArray()[i]));
        }

    }
}