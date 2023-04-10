package msc;

import java.util.Arrays;

public class Utility {

    public static int[] searchValues(int x, int y, int height, int width) {
        int aInit, aMax, bInit, bMax;
        // top left corner
        if (x == 0 && y == 0) {
            aInit = 0;
            aMax = 2;
            bInit = 0;
            bMax = 2;
        }
        // if top right
        else if ((x == 0) && (y == width - 1)) {
            aInit = 0;
            aMax = 2;
            bInit = -1;
            bMax = 1;
        }
        // if bottom left
        else if ((x == height - 1) && (y == 0)) {
            aInit = -1;
            aMax = 1;
            bInit = 0;
            bMax = 2;
        }
        // if bottom right
        else if ((x == height - 1) && (y == width - 1)) {
            aInit = -1;
            aMax = 1;
            bInit = -1;
            bMax = 1;
        }
        // if non-corner left
        else if (y == 0) {
            aInit = -1;
            aMax = 2;
            bInit = 0;
            bMax = 2;
        }
        // if non-corner right
        else if (y == width - 1) {
            aInit = -1;
            aMax = 2;
            bInit = -1;
            bMax = 1;
        }
        // if non-corner top
        else if (x == 0) {
            aInit = 0;
            aMax = 2;
            bInit = -1;
            bMax = 2;
        }
        // if non-corner bot
        else if (x == height - 1) {
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

    public static Boolean searchAdjacentTiles(char[][] charArr, Board board, int x, int y, int target) {

        Boolean bool = false;

        Board.Difficulty difficulty = board.getDifficulty();

        int[] searchValues = searchValues(x, y, difficulty.height, difficulty.width);

        for (int a = searchValues[0]; a < searchValues[1]; a++) {
            for (int b = searchValues[2]; b < searchValues[3]; b++) {
                if (charArr[x + a][y + b] != 'F') {
                    charArr[x + a][y + b] = (char) (board.getBoardArray()[x + a][y + b] + '0');
                    if (board.getBoardArray()[x + a][y + b] == target) {
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

    public static char[][] createDisplayBoard(Board.Difficulty boardtype) {
        char[][] displayBoard = new char[boardtype.height + 2][boardtype.width + 2];
        for (int i = 0; i < boardtype.height + 2; i++) {
            Arrays.fill(displayBoard[i], ' ');
        }
        for (int i = 0; i < boardtype.height + 1; i++) {
            displayBoard[i + 1][0] = (char) (i + '0');
        }
        for (int j = 0; j < boardtype.width + 1; j++) {
            displayBoard[0][j + 1] = (char) (j + '0');
        }
        displayBoard[0][0] = '/';
        displayBoard[0][1] = '/';
        displayBoard[1][0] = '/';
        return displayBoard;
    }

    public static void updateDisplayBoard(char[][] arr1, char[][] arr2, Board.Difficulty boardtype) {
        for (int i = 0; i < boardtype.height; i++) {
            for (int j = 0; j < boardtype.width; j++) {
                arr1[i + 2][j + 2] = arr2[i][j];
            }
        }
    }

    public static void gameOver() {

    }

}