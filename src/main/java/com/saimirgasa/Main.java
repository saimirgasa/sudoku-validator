package com.saimirgasa;

import java.io.File;

import com.saimirgasa.sudoku.SudokuValidator;
import com.saimirgasa.sudoku.exception.InvalidSudokuFileException;
import com.saimirgasa.sudoku.exception.InvalidSudokuSolutionException;

import static com.saimirgasa.sudoku.util.SudokuUtils.readSudokuBoardFromFile;

public class Main {

    public static void main(String[] args) {

        try {
            File file = new File(args[0]);
            SudokuValidator validator = new SudokuValidator();
            int[][] board = readSudokuBoardFromFile(file);
            validator.validateSudoku(board);
        } catch (InvalidSudokuFileException | InvalidSudokuSolutionException exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }

        System.out.println("0 (VALID)");
    }
}
