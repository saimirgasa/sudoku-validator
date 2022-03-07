package com.saimirgasa.sudoku.exception;

public class InvalidSudokuSolutionException extends RuntimeException {

    public InvalidSudokuSolutionException(String message) {
        super("-1 (INVALID)\n" + message);
    }
}
