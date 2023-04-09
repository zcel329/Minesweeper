package msc;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
            Board board = new Board();
            board = board.createBoard(Board.BoardType.BEGINNER);
            for (int i = 0; i < board.getBoardArray().length; i++) {
                System.out.println(Arrays.toString(board.getBoardArray()[i]));
            }
        }
    }