package msc;

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

    public static char[][] searchAdjacentTiles(char[][] charArr, Board board, int x, int y) {

        Board.Difficulty difficulty = board.getDifficulty();

        int[] searchVals = searchValues(x,y, difficulty.height, difficulty.width);

        for (int a = searchVals[0]; a < searchVals[1]; a++) {
            for (int b = searchVals[2]; b < searchVals[3]; b++) {
                charArr[x + a][y + b] = (char) (board.getBoardArray()[x + a][y + b] + '0');
            }
        }


        return charArr;
}

}