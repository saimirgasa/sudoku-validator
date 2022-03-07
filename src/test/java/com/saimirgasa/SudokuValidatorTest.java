package com.saimirgasa;

import com.saimirgasa.sudoku.SudokuValidator;
import com.saimirgasa.sudoku.exception.InvalidSudokuSolutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

class SudokuValidatorTest {

    private SudokuValidator sudokuValidator;

    @BeforeEach
    public void setUp() {
        sudokuValidator = new SudokuValidator();
    }

    @Test
    void givenValidSudokuSolution_whenValidateSudoku_thenValidateSubSquareIsCalled() throws Exception {
        // GIVEN
        int[][] board = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // WHEN
        SudokuValidator mockValidator = spy(new SudokuValidator());
        mockValidator.validateSudoku(board);

        // THEN
        verifyPrivate(mockValidator, times(1)).invoke("validateSubSquare", board, ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
    }

    @Test
    void givenDuplicateValuesInRow_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {5, 5, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("Number 5 appears more than one time in Row 1"));
    }

    @Test
    void givenNumberInRowGreaterThanMaxValue_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {5, 10, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("Row 1 Column 2 has a value outside the range of 1-9, (10)"));
    }

    @Test
    void givenNumberInRowLowerThanMinValue_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {5, 0, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("Row 1 Column 2 has a value outside the range of 1-9, (0)"));
    }

    @Test
    void givenDuplicateValuesInColumn_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {5, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("Number 5 appears more than one time in Column 1"));
    }

    @Test
    void givenNumberInColumnGreaterThanMaxValue_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {10, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("Row 2 Column 1 has a value outside the range of 1-9, (10)"));
    }

    @Test
    void givenNumberInColumnLowerThanMinValue_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {0, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("Row 2 Column 1 has a value outside the range of 1-9, (0)"));
    }

    @Test
    void givenDuplicateValuesInSubSquare_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("One of the sub-squares has duplicate values"));
    }

    @Test
    void givenMoreThan9Columns_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("Row 1 has more numbers than allowed"));
    }

    @Test
    void givenMoreThan9Rows_whenValidateSudoku_thenReturnInvalid() {
        // GIVEN
        int[][] board = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {9, 1, 2, 3, 4, 5, 6, 7, 8},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };

        // WHEN
        // THEN
        InvalidSudokuSolutionException expectedException = assertThrows(InvalidSudokuSolutionException.class, () -> sudokuValidator.validateSudoku(board));
        assertTrue(expectedException.getMessage().contains("The board has more rows than allowed"));
    }
}
