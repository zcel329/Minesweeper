package msc;

import java.util.Arrays;
import java.util.Random;

import static msc.Utility.searchValues;

public class Board {

    enum Difficulty {
        BEGINNER(9, 9, 10),
        INTERMEDIATE(16, 16, 40),
        EXPERT(16, 30, 99);

        public final int height;
        public final int width;
        public final int mines;

        Difficulty(int height, int width, int mines) {
            this.height = height;
            this.width = width;
            this.mines = mines;
        }
    }

    private int height;
    private int width;
    private int[][] boardArray;
    private int mines;

    private Difficulty difficulty;

    public Board() {
    }

    private Board(Difficulty setting) {
        this.height = setting.height;
        this.width = setting.width;
        this.mines = setting.mines;
        this.difficulty = setting;
        boardArray = new int[height][width];

    }

    public int[][] getBoardArray() {
        return boardArray;
    }

    // Sequentially placing the mines down
    public void placeMines() {
        int minesCreated = 0;
        int i = 0;
        int j = 0;
        while (minesCreated != mines) {
            minesCreated++;
            boardArray[i][j] = -1;
            if (j == width - 1) {
                j = 0;
                i++;
            } else {
                j++;
            }
        }
    }

    public void scrambleMines() {
        Random random = new Random();
        // Shuffling mines using the Fisher Yates method
        for (int i = this.height - 1; i > 0; i--) {
            for (int j = this.width - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = boardArray[i][j];
                boardArray[i][j] = boardArray[m][n];
                boardArray[m][n] = temp;
            }
        }
    }

    public void populateBoard() {
        int mineCounter = 0;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {

                // if bomb
                if (boardArray[i][j] == -1) {
                    continue;
                }

                int[] searchValues = searchValues(i, j, this.height, this.width);

                for (int a = searchValues[0]; a < searchValues[1]; a++) {
                    for (int b = searchValues[2]; b < searchValues[3]; b++) {
                        if (boardArray[i + a][j + b] == -1) {
                            mineCounter++;
                        }
                    }
                }

                boardArray[i][j] = mineCounter;
                mineCounter = 0;
            }
        }
    }

    public Board createTrueBoard(Difficulty difficulty) {
        Board board = new Board(difficulty);
        board.placeMines();
        board.scrambleMines();
        board.populateBoard();
        return board;
    }

    public char[][] createPlayerBoard() {
        char[][] arr = new char[difficulty.height][difficulty.width];
        for (int i = 0; i < this.height; i++) {
            Arrays.fill(arr[i], '*');
        }
        return arr;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}