package msc;

public class Utility {



    public static char[][] searchAdjacentTiles(char[][] charArr, Board board, int x, int y, int target) {

        Board.Difficulty difficulty = board.getDifficulty();
        int[][] intArr = board.getBoardArray();

        // top left corner
        if (x == 0 && y == 0) {
            for (int a = 0; a < 2; a++) {
                for (int b = 0; b < 2; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // if top right
        else if ((x == 0) && (y == difficulty.width - 1)) {
            for (int a = 0; a < 2; a++) {
                for (int b = -1; b < 1; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // if bottom left
        else if ((x == difficulty.height - 1) && (y == 0)) {
            for (int a = -1; a < 1; a++) {
                for (int b = 0; b < 2; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // if bottom right
        else if ((x == difficulty.height - 1) && (y == difficulty.width - 1)) {
            for (int a = -1; a < 1; a++) {
                for (int b = -1; b < 1; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // if non-corner left
        else if (y == 0) {
            for (int a = -1; a < 2; a++) {
                for (int b = 0; b < 2; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // if non-corner right
        else if (y == difficulty.width - 1) {
            for (int a = -1; a < 2; a++) {
                for (int b = -1; b < 1; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // if non-corner top
        else if (x == 0) {
            for (int a = 0; a < 2; a++) {
                for (int b = -1; b < 2; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // if non-corner bot
        else if (x == difficulty.height - 1) {
            for (int a = -1; a < 1; a++) {
                for (int b = -1; b < 2; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }
        // else internal
        else {
            for (int a = -1; a < 2; a++) {
                for (int b = -1; b < 2; b++) {
                    if (intArr[x + a][y + b] != target) {
                        charArr[x + a][y + b] = (char)(board.getBoardArray()[x + a][y + b]+'0');
                    }
                }
            }
        }


        return charArr;
    }

}
