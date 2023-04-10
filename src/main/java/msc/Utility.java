package msc;

import java.util.Arrays;

public class Utility {

    public static int[] searchValues(int heightPos, int widthPos, int height, int width) {
        int aInit, aMax, bInit, bMax;
        // top left corner
        if (heightPos == 0 && widthPos == 0) {
            aInit = 0;
            aMax = 2;
            bInit = 0;
            bMax = 2;
        }
        // if top right
        else if ((heightPos == 0) && (widthPos == width - 1)) {
            aInit = 0;
            aMax = 2;
            bInit = -1;
            bMax = 1;
        }
        // if bottom left
        else if ((heightPos == height - 1) && (widthPos == 0)) {
            aInit = -1;
            aMax = 1;
            bInit = 0;
            bMax = 2;
        }
        // if bottom right
        else if ((heightPos == height - 1) && (widthPos == width - 1)) {
            aInit = -1;
            aMax = 1;
            bInit = -1;
            bMax = 1;
        }
        // if non-corner left
        else if (widthPos == 0) {
            aInit = -1;
            aMax = 2;
            bInit = 0;
            bMax = 2;
        }
        // if non-corner right
        else if (widthPos == width - 1) {
            aInit = -1;
            aMax = 2;
            bInit = -1;
            bMax = 1;
        }
        // if non-corner top
        else if (heightPos == 0) {
            aInit = 0;
            aMax = 2;
            bInit = -1;
            bMax = 2;
        }
        // if non-corner bot
        else if (heightPos == height - 1) {
            aInit = -1;
            aMax = 1;
            bInit = -1;
            bMax = 2;
        }
        // else internal
        else {
            aInit = -1;
            aMax = 2;
            bInit = -1;
            bMax = 2;
        }
        return new int[]{aInit, aMax, bInit, bMax};
    }

    public static Boolean searchAdjacentTiles(char[][] charArr, Board board, int heightPos, int widthPos, int target) {

        boolean bool = false;

        Board.Difficulty difficulty = board.getDifficulty();

        int[] searchValues = searchValues(heightPos, widthPos, difficulty.height, difficulty.width);

        for (int a = searchValues[0]; a < searchValues[1]; a++) {
            for (int b = searchValues[2]; b < searchValues[3]; b++) {
                if (charArr[heightPos + a][widthPos + b] != 'F') {
                    charArr[heightPos + a][widthPos + b] = (char) (board.getBoardArray()[heightPos + a][widthPos + b] + '0');
                    if (board.getBoardArray()[heightPos + a][widthPos + b] == target) {
                        bool = true;
                    }
                }
            }
        }
        return bool;
    }

    public static void createGameOverBoard(char[][] charArr, Board board) {
        Board.Difficulty difficulty = board.getDifficulty();

        for (int i = 0; i < difficulty.height; i++) {
            for (int j = 0; j < difficulty.width; j++) {
                if (board.getBoardArray()[i][j] == -1) {
                    charArr[i][j] = 'X';
                }
            }
        }
    }

    public static String[][] createDisplayBoard(Board.Difficulty difficulty) {
        String[][] displayBoard = new String[difficulty.height + 2][difficulty.width + 2];
        for (int i = 0; i < difficulty.height + 2; i++) {
            Arrays.fill(displayBoard[i], " ");
        }
        for (int i = 0; i < difficulty.height + 1; i++) {
            displayBoard[i + 1][0] = String.valueOf(i);
        }
        for (int j = 0; j < difficulty.width + 1; j++) {
            displayBoard[0][j + 1] = String.valueOf(j);
        }
        displayBoard[0][0] = "/";
        displayBoard[0][1] = "/";
        displayBoard[1][0] = "/";
        return displayBoard;
    }

    public static void updateDisplayBoard(String[][] arr1, char[][] arr2, Board.Difficulty difficulty) {
        for (int i = 0; i < difficulty.height; i++) {
            for (int j = 0; j < difficulty.width; j++) {
                arr1[i + 2][j + 2] = String.valueOf(arr2[i][j]);
            }
        }
    }

    public static void gameOver(char[][] userBoard, Board trueBoard, String[][] displayBoard) {
        Utility.createGameOverBoard(userBoard, trueBoard);
        Utility.updateDisplayBoard(displayBoard, userBoard, trueBoard.getDifficulty());
        for (String[] chars : displayBoard) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println("***GAME OVER***");
    }

}