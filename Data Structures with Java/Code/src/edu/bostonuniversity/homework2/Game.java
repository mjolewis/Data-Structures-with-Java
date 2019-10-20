// File Game.java from the package edu.bostonuniversity.homework2

package edu.bostonuniversity.homework2;
import edu.bostonuniversity.collections.LinkedStack;
import edu.bostonuniversity.nodes.NodeList;

/**********************************************************************************************************************
 * A Game is a sequence of choices attempting to solve the n Queens problem. Each choice consists of a row and column
 * and is pushed onto a LinkedStack. The n Queens problem postulates that you can place n Queens on a chess board, so
 * that no Queen could capture any other.
 *
 * @note
 *  1. Beyond Integer.MAX_VALUE elements, the size method in LinkedStack does not work.
 *  2. A Queen can move vertically, horizontally, and diagonally.
 *
 * @author mlewis
 * @version Oct 17, 2019
 *********************************************************************************************************************/

public class Game {
    // Invariant of the Game class.
    //  1. The instance variable stack is a LinkedStack.
    //  2. The instance variable success indicates whether or not a solution to the n queens problem has been found.
    //  3. The instance variable boardSize indicates how many rows and columns are on the chess board.
    private boolean success;
    private int boardSize;
    private LinkedStack<Double> stack;

    /**
     * A default constructor that initializes an empty LinkedStack with size of 8.
     * @postcondition
     *   This LinkedStack is empty and has been initialized with a size of 8.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for the new Game.
     */
    public Game() {
        success = false;
        this.boardSize = 8;
        stack = new LinkedStack<>();
    }

    /**
     * Initializes an empty LinkedStack with a size equal to the integer parameter.
     * @postcondition
     *   This LinkedStack is empty and it's size is equal to size of the given integer parameter.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for the new Game.
     */
    public Game(int boardSize) {
        success = false;
        this.boardSize = boardSize;
        stack = new LinkedStack<>();
    }

    /**
     * Helper method to determine if there is a column conflict. A column conflict indicates that placing a queen on
     * the given column would result in an invalid board setup.
     * @param column
     * Indicates the column being tested for a conflict.
     * @return boolean
     *  A return value of true indicates that placing a queen on this column does not violate a valid board set up. A
     *  return value of false indicates that a queen cannot be placed on the given column.
     */
    private boolean isColumnValid(double column) {
        NodeList cursor;

        for (cursor = stack.getHead(); cursor != null; cursor = cursor.getNext()) {
            double tmp = (double) cursor.getData();
            if (tmp == column) { return false; }
        }
        return true;
    }

    /**
     * Helper method to determine if there is a diagonal conflict. A diagonal conflict indicates that placing a queen
     * at the given row and column combination would result in an invalid board setup.
     * @param row
     *  Indicates the row being tested for a conflict.
     * @param column
     *  Indicates the column being tested for a conflict.
     * @return boolean
     *  A return value of true indicates that placing a queen in this row and column does not violate a valid board set
     *  up. A return value of false indicates that a queen cannot be placed in this row and column.
     */
    private boolean isDiagonalValid(double row, double column) {
        double slope;
        double prevRow;
        NodeList cursor;

        prevRow = stack.getSize();
        for (cursor = stack.getHead(); cursor != null; cursor = cursor.getNext()) {
            try {
                double prevColumn = (double) cursor.getData();
                slope = (row - prevRow) / (column - prevColumn);
            } catch (ArithmeticException e) {
                // This exception should only occur if the column on the stack is equal to the new column. If they are
                // equal, the queens position is invalid and we return false.
                return false;
            }

            prevRow--; // Shift the row by 1 as we move down the stack.
            if (Math.abs(slope) == 1) { return false; }
        }
        // If we reach this point, there are no diagonal conflicts.
        return true;
    }

    /**
     * Helper method to determine if there is a row conflict. A horizontal conflict indicates that placing a queen in
     * the given row would result in an invalid board setup.
     * @param row
     *  Indicates the row being tested for a conflict.
     * @return boolean
     *  A return value of true indicates that placing a queen on this row does not violate a valid board set up. A
     *  return value of false indicates that a queen cannot be placed in the given location.
     */
    private boolean isRowValid(double row) { return row != stack.getSize(); }

    /**
     * A method that activates the game play. The game is played until a solution is found.
     * @precondition
     *  A game has been initialized.
     * @postcondition
     *  A solution to the n queens problem has been found.
     */
    public void play() {
        double row = 2; // The first Queen doesn't require a comparison, so we immediately jump to row 2.
        double column = (int) (Math.random() * boardSize) + 1;

        stack.add(column); // Add first position to the stack to avoid a null pointer exception.
        while (!success) {
            column = (int) (Math.random() * boardSize) + 1; // Another position that we try to add to the stack.
            if (stack.getSize() == boardSize) {
                success = true;
                break;
            } else if (isColumnValid(column) && isDiagonalValid(row, column) && isRowValid(row)) {
                stack.add(column);
                row++;
            } else {
                // A conflict has occurred. Clear the board and try again.
                stack.removeAll();
                column = (int) (Math.random() * boardSize) + 1; // Create the first queen and...
                stack.add(column); // ...add the queen to the board.
                row = 2;
            }
        }
    }

    /**
     * An override method used to print the game results.
     * @return String
     *  A string representation of a chess board and the solution to the n queens problem.
     */
    @Override
    public String toString() {
        double count;
        NodeList current;
        StringBuilder stringBuilder = new StringBuilder();

        current = stack.getHead();
        for (int i = 1; i <= boardSize; i++) {
            stringBuilder.append("+---".repeat(Math.max(0, boardSize)));
            stringBuilder.append("+");
            stringBuilder.append("\n|");

            for (count = 1; count <= boardSize; count++) {
                double tmp = (double) current.getData();
                if (tmp == count) {
                    stringBuilder.append(" Q |");
                } else {
                    stringBuilder.append("   |");
                }
            }

            current = current.getNext();
            stringBuilder.append("\n");
        }

        stringBuilder.append("+---".repeat(Math.max(0, boardSize)));
        stringBuilder.append("+");
        return stringBuilder.toString();
    }
}
