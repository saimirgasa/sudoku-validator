package com.saimirgasa.sudoku.exception;

public class InvalidSudokuFileException extends RuntimeException {

    public InvalidSudokuFileException(String message) {
        super("-1 (INVALID)\n" + message);
    }
}
