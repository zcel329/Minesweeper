package msc;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose difficulty: \n 1: [BEGINNER] \n 2: [INTERMEDIATE] \n 3: [EXPERT]");

        String selection = myObj.nextLine();  // Read user input
        int diff = Integer.parseInt(selection);
        Board.BoardType boardtype;
        switch (diff) {
            case 1 -> {
                boardtype = Board.BoardType.BEGINNER;
                System.out.println("You have chosen BEGINNER \n");
            }
            case 2 -> {
                boardtype = Board.BoardType.INTERMEDIATE;
                System.out.println("You have chosen INTERMEDIATE \n");
            }
            case 3 -> {
                boardtype = Board.BoardType.EXPERT;
                System.out.println("You have chosen EXPERT \n");
            }
            default -> {
                System.out.println("Error: invalid difficulty choice. Defaulted to BEGINNER difficulty.");
                boardtype = Board.BoardType.BEGINNER;
            }
        }
        Board board = new Board();
        board = board.createBoard(boardtype);
        for (int i = 0; i < board.getBoardArray().length; i++) {
            System.out.println(Arrays.toString(board.getBoardArray()[i]));
        }
    }
}