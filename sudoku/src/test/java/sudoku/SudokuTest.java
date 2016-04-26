package sudoku;

import org.junit.Test;
import sudoku.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Simon
 * Date: 26/04/16
 * Time: 14:09
 */
public class SudokuTest {
    private BufferedReader br;
    private static final String PATH_TO_GRID_FILE = "src/test/resources/grid";
    private int nbGrid = 20;
    private List<Integer> buildListOf9Integer() {
        List<Integer> lst = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++)
            lst.add(i);
        return lst;
    }

    @Test
    public void sudokuTest() {
        for(int i = 0; i < nbGrid; i++) {
            int[][] grid = loadGrid();
            Sudoku sudoku = new Sudoku(clone(grid));
            sudoku.initSubsets();
            sudoku.solve();
            sudoku.getGrid();

            assertTrue(assertGrid(grid, sudoku.getGrid()));
        }
    }

    public boolean assertGrid(int[][] input, int[][] output) {
        for (int row = 0; row < output.length; row++) {
            List<Integer> listOfInteger = buildListOf9Integer();
            for (int col = 0; col < output[row].length; col++) {
                if (!listOfInteger.remove(new Integer(output[row][col])))
                    return false;
                if (input[row][col] != 0 && input[row][col] != output[row][col])
                    return false;
            }
        }
//        for (int col = 0; col < output.length; col++) {
//            List<Integer> listOfInteger = buildListOf9Integer();
//            for (int row = 0; row < output[row].length; row++) {
//                if (!listOfInteger.remove(new Integer(output[row][col])))
//                    return false;
//                if (input[row][col] != 0 && input[row][col] != output[row][col])
//                    return false;
//            }
//        }
        return true;
    }

    protected int[][] loadGrid() {
        int[][] grid = new int[9][9];
        try {
            if (br == null)
                br = new BufferedReader(new FileReader(PATH_TO_GRID_FILE));
            //Trash Header
            br.readLine();
            for (int row = 0; row < 9; row++) {
                String rowAsStr = br.readLine();
                for (int col = 0; col < rowAsStr.length(); col++) {
                    grid[row][col] = Integer.parseInt(rowAsStr.charAt(col) + "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grid;
    }


    protected int[][] clone(int [][] originalValue) {
        int [][] clone = new int[9][9];
        for (int row = 0 ; row <originalValue.length ; row++) {
            for (int col = 0 ; col < originalValue[row].length ; col++) {
                clone[row][col] =originalValue[row][col];
            }
        }
        return clone;
    }
}
