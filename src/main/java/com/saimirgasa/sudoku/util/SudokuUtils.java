package com.saimirgasa.sudoku.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;

import com.saimirgasa.sudoku.exception.InvalidSudokuFileException;

public class SudokuUtils {

    private static final String SUDOKU_ROW_EXAMPLE = "1,2,3,4,5,6,7,8,9\n";
    public static final String TAB = "      ";
    private static final String LOAD_ERROR_MESSAGE = "Could not load solution from file '%s'.\n" +
            "Please make sure that:\n" +
            "   * file content only contains numbers and commas\n" +
            "   * file content follows the following format:\n" +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE +
            TAB + SUDOKU_ROW_EXAMPLE;

    private SudokuUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int[][] readSudokuBoardFromFile(File sudokuFile) {

        int[][] board;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sudokuFile.getAbsolutePath(), StandardCharsets.UTF_8))) {
            Pattern splitPattern = Pattern.compile(",");

            board = bufferedReader
                    .lines()
                    .map(line -> Arrays.stream(splitPattern.split(line))
                            .map(String::trim)
                            .mapToInt(Integer::parseInt)
                            .toArray()
                    ).toArray(size -> new int[size][1]);
        } catch (IOException ex) {
            throw new InvalidSudokuFileException(
                    String.format("File %s could not be loaded. Please make sure that the file is in the classpath.", sudokuFile.getName()));
        } catch (NumberFormatException ex) {
            throw new InvalidSudokuFileException(String.format(LOAD_ERROR_MESSAGE, sudokuFile.getName()));
        }

        return board;
    }
}
