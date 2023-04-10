package msc;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board.Difficulty difficulty;

        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose a difficulty below by entering the corresponding number: \n 1: [BEGINNER] \n 2: [INTERMEDIATE] \n 3: [EXPERT]");

        String selection = myObj.nextLine();

        try {
            int num = Integer.parseInt(selection);
            switch (num) {
                case 1 -> {
                    difficulty = Board.Difficulty.BEGINNER;
                    System.out.println("You have chosen BEGINNER \n");
                }
                case 2 -> {
                    difficulty = Board.Difficulty.INTERMEDIATE;
                    System.out.println("You have chosen INTERMEDIATE \n");
                }
                case 3 -> {
                    difficulty = Board.Difficulty.EXPERT;
                    System.out.println("You have chosen EXPERT \n");
                }
                default -> {
                    System.out.println("Error: invalid difficulty choice. Defaulted to BEGINNER difficulty.");
                    difficulty = Board.Difficulty.BEGINNER;
                }
            }
        } catch (NumberFormatException error) {
            System.out.println("Error: Invalid difficulty choice. Defaulted to BEGINNER difficulty.");
            difficulty = Board.Difficulty.BEGINNER;
        }

        Board trueBoard = new Board();
        trueBoard = trueBoard.createTrueBoard(difficulty);
        char[][] userBoard = trueBoard.createPlayerBoard();


//        For testing
//        for (int i = 0; i < trueBoard.getBoardArray().length; i++) {
//            System.out.println(Arrays.toString(trueBoard.getBoardArray()[i]));
//        }

        boolean run = true;
        boolean firstMove = true;

        char[][] displayBoard = Utility.createDisplayBoard(difficulty);

        while (run) {
            int heightPos, widthPos;

            Utility.updateDisplayBoard(displayBoard,userBoard,difficulty);

            for (char[] chars : displayBoard) {
                System.out.println(Arrays.toString(chars));
            }

            // Getting a valid X position
            while (true) {
                System.out.println("Enter a X pos:");
                String xInput = myObj.nextLine();
                try {
                    widthPos = Integer.parseInt(xInput)-1;
                    if (widthPos >= difficulty.width) {
                        continue;
                    }
                    break;
                } catch (NumberFormatException ignored) {
                }
            }

            // Getting a valid Y position
            while (true) {
                System.out.println("Enter an Y pos:");
                String xInput = myObj.nextLine();
                try {
                    heightPos = Integer.parseInt(xInput)-1;
                    if (heightPos >= difficulty.height) {
                        continue;
                    }
                    break;
                } catch (NumberFormatException ignored) {
                }
            }

            int tileValue = trueBoard.getBoardArray()[heightPos][widthPos];


            // Making sure that the game can't be over in first move
            if (firstMove) {
                firstMove = false;
                if (tileValue == -1) {
                    trueBoard.moveMine(heightPos, widthPos);
                    tileValue = trueBoard.getBoardArray()[heightPos][widthPos];
                }
            }

            switch (tileValue) {
                case -1 -> {
                    Utility.createGameOverBoard(userBoard, trueBoard);
                    Utility.updateDisplayBoard(displayBoard,userBoard,difficulty);
                    for (char[] chars : displayBoard) {
                        System.out.println(Arrays.toString(chars));
                    }
                    System.out.println("***GAME OVER***");
                    run = false;
                }
                case 0 -> {
                    userBoard[heightPos][widthPos] = (char) (trueBoard.getBoardArray()[heightPos][widthPos] + '0');
                    for (int k = 0; k < 20; k++) {
                        for (int i = 0; i < difficulty.height; i++) {
                            for (int j = 0; j < difficulty.width; j++) {
                                if (userBoard[i][j] == '0') {
                                    Utility.searchAdjacentTiles(userBoard, trueBoard, i, j);
                                }
                            }
                        }
                    }
                }
                default -> userBoard[heightPos][widthPos] = (char) (trueBoard.getBoardArray()[heightPos][widthPos] + '0');
            }
        }
    }
}