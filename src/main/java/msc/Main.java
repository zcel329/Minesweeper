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


        boolean run = true;
        boolean firstMove = true;

        while (run) {
            int heightPos, widthPos;

            for (char[] chars : userBoard) {
                System.out.println(Arrays.toString(chars));
            }


            char gameFlag = '0';
            boolean loop = true;
            while (loop) {
                System.out.println("Would you like to flag ('F') or pick ('P') a tile?");
                String input = myObj.nextLine();
                input = input.toLowerCase();
                switch (input) {
                    case "p" -> {
                        gameFlag = 'p';
                        loop = false;

                    }
                    case "f" -> {
                        gameFlag = 'f';
                        loop = false;
                    }
                }
            }
            // Getting a valid X position
            while (true) {
                System.out.println("Enter a X pos:");
                String xInput = myObj.nextLine();
                try {
                    widthPos = Integer.parseInt(xInput) - 1;
                    if ((widthPos >= difficulty.width) || (widthPos < 0)) {
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
                    heightPos = Integer.parseInt(xInput) - 1;
                    if ((heightPos >= difficulty.height) || (heightPos < 0)) {
                        continue;
                    }
                    break;
                } catch (NumberFormatException ignored) {
                }
            }

            if (gameFlag == 'f') {
                if ((userBoard[heightPos][widthPos] != '*') && (userBoard[heightPos][widthPos] != 'F')) {
                    System.out.println("ERROR: Cannot flag a revealed tile");
                    continue;
                } else if (userBoard[heightPos][widthPos] == 'F') {
                    userBoard[heightPos][widthPos] = '*';
                    continue;
                } else if (userBoard[heightPos][widthPos] == '*') {
                    userBoard[heightPos][widthPos] = 'F';
                    continue;
                }
            }

            if (gameFlag == 'p') {
                if (userBoard[heightPos][widthPos] == 'F') {
                    System.out.println("ERROR: Cannot reveal a flagged tile");
                    continue;
                } else if (userBoard[heightPos][widthPos] != '*') {
                    System.out.println("Are you sure you want to reveal all adjacent tiles? (Y/N)");
                    String input = myObj.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        if (Utility.searchAdjacentTiles(userBoard,trueBoard,heightPos,widthPos,-1)) {
                            Utility.gameOver(userBoard,trueBoard);
                            run = false;
                        }
                    } else {
                        continue;
                    }
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
                    Utility.gameOver(userBoard,trueBoard);
                    run = false;
                }
                case 0 -> {
                    userBoard[heightPos][widthPos] = (char) (trueBoard.getBoardArray()[heightPos][widthPos] + '0');
                    for (int k = 0; k < 20; k++) {
                        for (int i = 0; i < difficulty.height; i++) {
                            for (int j = 0; j < difficulty.width; j++) {
                                if (userBoard[i][j] == '0') {
                                    Utility.searchAdjacentTiles(userBoard, trueBoard, i, j,-1);
                                }
                            }
                        }
                    }
                }
                default ->
                        userBoard[heightPos][widthPos] = (char) (trueBoard.getBoardArray()[heightPos][widthPos] + '0');
            }


            // Checking win condition
            int mineCounter = 0;
            for (int i = 0; i < difficulty.height; i++) {
                for (int j = 0; j < difficulty.width; j++) {
                    if ((userBoard[i][j] == '*') || (userBoard[i][j] == 'F')) {
                        mineCounter++;
                    }
                }
            }
            if (mineCounter == difficulty.mines) {
                Utility.createGameOverBoard(userBoard, trueBoard);
                for (char[] chars : userBoard) {
                    System.out.println(Arrays.toString(chars));
                }
                System.out.println("***YOU WIN***");
                run = false;
            }
        }
    }
}