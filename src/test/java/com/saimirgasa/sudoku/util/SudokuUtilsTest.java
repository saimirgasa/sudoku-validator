package com.saimirgasa.sudoku.util;

import java.io.File;

import com.saimirgasa.sudoku.exception.InvalidSudokuFileException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuUtilsTest {

    private static final String BASE_RESOURCE_PATH = "src/test/resources/";

    @Test
    void givenValidBoard_whenReadSudokuBoardFromFile_thenReturnBoardArray() {
        // GIVEN
        File sudokuFile = new File(BASE_RESOURCE_PATH + "valid-sudoku-board.csv");

        // WHEN
        int[][] board = SudokuUtils.readSudokuBoardFromFile(sudokuFile);

        // THEN
        assertNotNull(board);
    }

    @Test
    void givenBoardWithSpecialCharacters_whenReadSudokuBoardFromFile_thenThrowException() {
        // GIVEN
        File sudokuFile = new File(BASE_RESOURCE_PATH + "special-characters-sudoku-board.csv");

        // WHEN
        // THEN
        InvalidSudokuFileException expectedException = assertThrows(InvalidSudokuFileException.class, () -> SudokuUtils.readSudokuBoardFromFile(sudokuFile));
        assertTrue(expectedException.getMessage().contains(String.format("Could not load solution from file '%s'", sudokuFile.getName())));
    }

    @Test
    void givenNonexistentFile_whenReadSudokuBoardFromFile_thenThrowException() {
        // GIVEN
        File sudokuFile = new File(BASE_RESOURCE_PATH + "no.csv");

        // WHEN
        // THEN
        InvalidSudokuFileException expectedException = assertThrows(InvalidSudokuFileException.class, () -> SudokuUtils.readSudokuBoardFromFile(sudokuFile));
        assertTrue(expectedException.getMessage().contains(String.format("File %s could not be loaded.", sudokuFile.getName())));
    }
}
