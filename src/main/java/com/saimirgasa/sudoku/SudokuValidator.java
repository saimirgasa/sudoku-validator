package com.saimirgasa.sudoku;

import java.util.HashSet;
import java.util.Set;

import com.saimirgasa.sudoku.exception.InvalidSudokuSolutionException;

public class SudokuValidator {

    private static final int BOARD_SIZE = 9;
    private static final int SUB_SQUARE_SIZE = 3;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    /**
     * This is a method that validates the whole Sudoku board
     * Initially checks the validity of all the rows then,
     * checks the validity of the columns, and lastly
     * checks the validity of the sub-squares
     *
     * @param board the 2-dimensional array representing the Sudoku board
     */
    public void validateSudoku(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            validateRow(i, board);
            validateColumn(i, board);
            validateSubSquares(board);
        }
    }

    /**
     * This method is used to validate the row at the given index
     *
     * @param rowIndex the index of the row to be validated
     * @param board    the 2-dimensional array representing the Sudoku board
     */
    private void validateRow(int rowIndex, int[][] board) {
        int[] row = board[rowIndex];
        if (row.length > BOARD_SIZE) {
            throw new InvalidSudokuSolutionException(String.format("Row %d has more numbers than allowed, (%d)", rowIndex + 1, BOARD_SIZE));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < row.length; i++) {
            int value = row[i];
            if (value < MIN_VALUE || value > MAX_VALUE) {
                throw new InvalidSudokuSolutionException(String.format("Row %d Column %d has a value outside the range of 1-9, (%d)", rowIndex + 1, i + 1, value));
            } else if (!set.add(value)) {
                throw new InvalidSudokuSolutionException(String.format("Number %d appears more than one time in Row %d", value, rowIndex + 1));
            }
        }
    }

    /**
     * This method is used to validate the column at the given index
     *
     * @param column the index of the column to be validated
     * @param board  the 2-dimensional array representing the Sudoku board
     */
    private void validateColumn(int column, int[][] board) {
        if (board.length > BOARD_SIZE) {
            throw new InvalidSudokuSolutionException(String.format("The board has more rows than allowed, (%d)", BOARD_SIZE));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            int value = board[i][column];
            if (value < MIN_VALUE || value > MAX_VALUE) {
                throw new InvalidSudokuSolutionException(
                        String.format("Row %d Column %d has a value outside the range of 1-9, (%d)", i + 1, column + 1, value));
            } else if (!set.add(value)) {
                throw new InvalidSudokuSolutionException(String.format("Number %d appears more than one time in Column %d", value, column + 1));
            }
        }
    }

    /**
     * This method is used to validate the sub-squares of the board
     *
     * @param board the 2-dimensional array representing the Sudoku board
     */
    private void validateSubSquares(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row = row + SUB_SQUARE_SIZE) {
            for (int col = 0; col < BOARD_SIZE; col = col + SUB_SQUARE_SIZE) {
                if (validateSubSquare(board, row, col) != 1) {
                    throw new InvalidSudokuSolutionException("One of the sub-squares has duplicate values");
                }
            }
        }
    }

    /**
     * This method is used to validate one sub-square of the size SUB_SQUARE_SIZE x SUB_SQUARE_SIZE
     *
     * @param board the 2-dimensional array representing the Sudoku board
     * @param row   the rowIndex where the sub-square starts
     * @param col   the columnIndex where the sub-square starts
     * @return 1 if the subs-square is valid, -1 if the sub-square has duplicates or
     * values outside the range of 1-9
     */
    private int validateSubSquare(int[][] board, int row, int col) {
        Set<Integer> set = new HashSet<>();
        for (int r = row; r < row + SUB_SQUARE_SIZE; r++) {
            for (int c = col; c < col + SUB_SQUARE_SIZE; c++) {
                if (!set.add(board[r][c])) {
                    return -1;
                }
            }
        }
        return 1;
    }
}
