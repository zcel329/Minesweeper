package msc;

import java.util.Random;

public class Board {

    enum BoardType {BEGINNER, INTERMEDIATE, EXPERT}

    private int height;
    private int width;
    private int[][] boardArray;

    private int mines;

    public Board() {}


    private Board(BoardType setting) {
        switch (setting) {
            case BEGINNER -> {
                height = 9;
                width = 9;
                mines = 10;
            }
            case INTERMEDIATE -> {
                height = 16;
                width = 16;
                mines = 40;
            }
            case EXPERT -> {
                height = 16;
                width = 30;
                mines = 99;
            }
        }
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
        for (int i = boardArray.length - 1; i > 0; i--) {
            for (int j = boardArray[i].length - 1; j > 0; j--) {
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
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray.length; j++) {

                // if bomb
                if (boardArray[i][j] == -1) {
                    continue;
                }
                // if top left
                else if ((i == 0) && (j == 0)) {
                    for (int a = 0; a < 2; a++) {
                        for (int b = 0; b < 2; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // if top right
                else if ((i == 0) && (j == boardArray.length-1)) {
                    for (int a = 0; a < 2; a++) {
                        for (int b = -1; b < 1; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // if bottom left
                else if ((i == boardArray.length-1) && (j == 0)) {
                    for (int a = -1; a < 1; a++) {
                        for (int b = 0; b < 2; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // if bottom right
                else if ((i == boardArray.length-1) && (j == boardArray.length-1)){
                    for (int a = -1; a < 1; a++) {
                        for (int b = -1; b < 1; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // if non-corner left
                else if (j == 0) {
                    for (int a = -1; a < 2; a++) {
                        for (int b = 0; b < 2; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // if non-corner right
                else if (j == boardArray.length-1) {
                    for (int a = -1; a < 2; a++) {
                        for (int b = -1; b < 1; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // if non-corner top
                else if (i == 0) {
                    for (int a = 0; a < 2; a++) {
                        for (int b = -1; b < 2; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // if non-corner bot
                else if (i == boardArray.length-1) {
                    for (int a = -1; a < 1; a++) {
                        for (int b = -1; b < 2; b++) {
                            if (boardArray[i+a][j+b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                // else internal
                else {
                    for (int a = -1; a < 2; a++) {
                        for (int b = -1; b < 2; b++) {
                            if (boardArray[i + a][j + b] == -1) {
                                mineCounter++;
                            }
                        }
                    }
                }
                boardArray[i][j] = mineCounter;
                mineCounter=0;
            }
        }
    }
    public Board createBoard(Board.BoardType boardType) {
        Board board = new Board(boardType);
        board.placeMines();
        board.scrambleMines();
        board.populateBoard();
        return board;
    }
}
