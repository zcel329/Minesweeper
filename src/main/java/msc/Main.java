package msc;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board.Difficulty boardtype;

        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose a difficulty below by entering the corresponding number: \n 1: [BEGINNER] \n 2: [INTERMEDIATE] \n 3: [EXPERT]");

        String selection = myObj.nextLine();

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
        char[][] userBoard = trueBoard.createPlayerBoard();

        for (int i = 0; i < trueBoard.getBoardArray().length; i++) {
            System.out.println(Arrays.toString(trueBoard.getBoardArray()[i]));
        }

        boolean run = true;
        while (run) {
            int xPos, yPos;

            for (int i = 0; i < userBoard.length; i++) {
                System.out.println(Arrays.toString(userBoard[i]));
            }

            // Getting a valid x position
            while (true) {
                System.out.println("Enter an X pos:");
                String xInput = myObj.nextLine();
                try {
                    xPos = Integer.parseInt(xInput);
                    break;
                } catch (NumberFormatException e) {
                }
            }

            // Getting a valid y position
            while (true) {
                System.out.println("Enter a Y pos:");
                String xInput = myObj.nextLine();
                try {
                    yPos = Integer.parseInt(xInput);
                    break;
                } catch (NumberFormatException e) {
                }
            }

            int tileValue = trueBoard.getBoardArray()[xPos][yPos];

            switch (tileValue) {
                case -1 -> {
                    System.out.println("***GAME OVER***");
                    run = false;
                }
                case 0 -> {
                    userBoard[xPos][yPos] = (char)(trueBoard.getBoardArray()[xPos][yPos]+'0');
                    for (int k = 0; k < 20; k++) {
                        for (int i = 0; i < boardtype.height; i++) {
                            for (int j = 0; j < boardtype.width; j++) {
                                if (userBoard[i][j] == '0') {
                                    userBoard = Utility.searchAdjacentTiles(userBoard,trueBoard,i,j);
                                }
                            }
                        }
                    }
                }
                default -> {
                    userBoard[xPos][yPos] = (char)(trueBoard.getBoardArray()[xPos][yPos]+'0');
                }
            }
        }
    }
}