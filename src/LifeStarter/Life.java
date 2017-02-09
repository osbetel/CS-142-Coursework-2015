package LifeStarter;

import java.security.AlgorithmConstraints;
import java.util.Arrays;

/**
 * Class Life is the model (data structure) that holds and updates the
 * status of our cellular automaton game.  It provides methods to initialize
 * the game (done automatically when the game is first created), update one generation
 * and access the game status, which can be used by client code to display
 * the game board and the state of the game
 * 
 * @author  Barbara Goldner, based on work by Hal Perkins. Modified by Andrew Nguyen 

 */
public class Life {
    // public constants

    /** Number of rows/columns in Life Grid  */
    public static final int NROWSCOLS = 19;


    // private instance variables
    private State[][] board;      // game board
    private int generations;   // how many generations have happened
    private State [][] newStates;   // next game board states


    /** Construct and initialize new game board
     *  Displays the LifeGUI display object for this game */
    public Life() {
        board = new State[NROWSCOLS][NROWSCOLS];
        newStates = new State[NROWSCOLS][NROWSCOLS];
        newGame();
    }

    /** initialize new game
     * */
    public void newGame() {
        generations = 0;

        //Set everything to dead
        for (int i = 0; i < NROWSCOLS; i++) {
            for (int k = 0; k < NROWSCOLS; k++) {
                board[i][k] = State.DEAD;
            }
        }

        //***START STATE.ALIVE SETTINGS***

        //Blinker in the top left.
        board[1][2] = State.ALIVE;
        board[2][2] = State.ALIVE;
        board[3][2] = State.ALIVE;

        //Block in the top right.
        board[1][17] = State.ALIVE;
        board[1][16] = State.ALIVE;
        board[2][17] = State.ALIVE;
        board[2][16] = State.ALIVE;

        //Glider in bottom left.
        board[17][3] = State.ALIVE;
        board[16][3] = State.ALIVE;
        board[15][3] = State.ALIVE;
        board[15][2] = State.ALIVE;
        board[16][1] = State.ALIVE;

        //Center piece splits into traffic light.
        board[8][10] = State.ALIVE;
        board[9][10] = State.ALIVE;
        board[9][11] = State.ALIVE;
        board[10][10] = State.ALIVE;

        //CONFIGURATION TERMINATES AT GENERATION 38
        //***END STATE.ALIVE SETTINGS***

        //Assign secondary newStates array to match the board.
        //Used for calculating and reassigning the new states after each life cycle.
        for (int i = 0; i < NROWSCOLS; i++) {
            for (int k = 0; k < NROWSCOLS; k++) {
                newStates[i][k] = board[i][k];
            }
        }

    }

    /** Returns the number of generations
     * @return The number of generations that have been run so far.
     */
    public int getGenerationCount() {
        return generations;
    }

    /** Return the current state of game board cell at given row/column
     *  (Squares numbered from 0 to NROWSCOLS-1).
     *  @throws ArrayIndexOutOfBoundsException for bad row/col      //Not IllegalArgEx. No need for precondition, Exc. is thrown automatically.
     *  @param row The row of the desired cell.
     *  @param col The column of the desired cell.
     */
    public State getCell(int row, int col) {
        return board[row][col];
    }


    /** Process one life cycle of the cellular automaton
     *
     */
    public void lifeCycle() {
        //Calculate next state of the board
        for (int i = 0; i < NROWSCOLS; i++) {
            for (int k = 0; k < NROWSCOLS; k++) {
                //newStates[][] is a copy of the board, but the new states are calculated based on it, and changed AFTERWARDS
                newStates[i][k] = cellChecker(i,k);
            }
        }

        //Assign the next state of the board
        for (int i = 0; i < NROWSCOLS; i++) {
            for (int k = 0; k < NROWSCOLS; k++) {
                this.board[i][k] = newStates[i][k];
//                System.out.println("actual board value @ (" + i + "," + k + ") is " + this.board[i][k]);
//                System.out.println("new board value @ (" + i + "," + k + ") is " + newStates[i][k]);
            }
        }
        generations += 1;   //Generations + 1 every time the life cycle button is clicked or timer goes off in LifeGUI
    }

    /**
     * Helper method for lifeCycle() above. Does the calculation per cell to determine it's next state.
     * @param x The "x-coordinate" of the cell to calculate. x is vertical, 0 at the top down to NROWSCOLS-1 on the bottom in the GUI
     * @param y The "y-coordinate" of the cell to calculate. y is horizontal, 0 at the left up to NROWSCOLS-1 on the right in the GUI
     * @return Returns a single state of the cell at (x,y) after calculating to see if it would grow or divide.
     */
    private State cellChecker(int x, int y) {
        //board[i][k] = center. Need to check all surrounding cells.
        int nearbyAlive = 0;
        State[] cellCheckerArray = {};  //Placeholder to initialize.

        //MIDDLE CELLS ***ONLY STRUCTURE PART OF THE ASSIGNMENT***
        if (x > 0 && x < NROWSCOLS - 1 && y > 0 && y < NROWSCOLS-1) {
            State cell1 = board[x - 1][y - 1];
            State cell2 = board[x - 1][y];
            State cell3 = board[x - 1][y + 1];
            State cell4 = board[x][y - 1];
            //Cell5 in the middle of a 3x3 square of cells.
            State cell6 = board[x][y + 1];
            State cell7 = board[x + 1][y - 1];
            State cell8 = board[x + 1][y];
            State cell9 = board[x + 1][y + 1];
            cellCheckerArray = new State[] {cell1, cell2, cell3, cell4, cell6, cell7, cell8, cell9};
        }

        //Below is the decision structure for calculating corner cells and edge cells if you want to utilize it.

        //CORNERS
        /*if (x == 0 && y == 0) { //Upper left corner
            State cell6 = board[x][y + 1];
            State cell8 = board[x + 1][y];
            State cell9 = board[x + 1][y + 1];
            cellCheckerArray = new State[] {cell6, cell8, cell9};

        } else if (x == 0 && y == NROWSCOLS - 1) { //Upper right corner
            State cell4 = board[x][y-1];
            State cell7 = board[x + 1][y - 1];
            State cell8 = board[x + 1][y];
            cellCheckerArray = new State[] {cell4, cell7, cell8};

        } else if (x == NROWSCOLS - 1 && y == 0) { //Lower left corner
            State cell2 = board[x - 1][y];
            State cell3 = board[x - 1][y + 1];
            State cell6 = board[x][y + 1];
            cellCheckerArray = new State[] {cell2, cell3, cell6};

        } else if (x == NROWSCOLS - 1 && y == NROWSCOLS - 1) { //Lower right corner
            State cell1 = board[x - 1][y - 1];
            State cell2 = board[x - 1][y];
            State cell4 = board[x][y - 1];
            cellCheckerArray = new State[] {cell1, cell2, cell4};

        }
        //EDGES
        else if ((y == 0) && (x != 0 || x != NROWSCOLS -1)) { //Left edges
            State cell2 = board[x - 1][y];
            State cell3 = board[x - 1][y + 1];
            State cell6 = board[x][y + 1];
            State cell8 = board[x + 1][y];
            State cell9 = board[x + 1][y + 1];
            cellCheckerArray = new State[] {cell2, cell3, cell6, cell8, cell9};

        } else if ((y == NROWSCOLS - 1) && (x != 0 || x != NROWSCOLS -1)) { //Right edges
            State cell1 = board[x - 1][y - 1];
            State cell2 = board[x - 1][y];
            State cell4 = board[x][y - 1];
            State cell7 = board[x + 1][y - 1];
            State cell8 = board[x + 1][y];
            cellCheckerArray = new State[] {cell1, cell2, cell4, cell7, cell8};

        } else if ((x == 0) && (y != 0 || y != NROWSCOLS -1)) { //Top edges
            State cell4 = board[x][y - 1];
            State cell6 = board[x][y + 1];
            State cell7 = board[x + 1][y - 1];
            State cell8 = board[x + 1][y];
            State cell9 = board[x + 1][y + 1];
            cellCheckerArray = new State[] {cell4, cell6, cell7, cell8, cell9};

        } else if ((x == NROWSCOLS - 1) && (y != 0 || y != NROWSCOLS -1)) { //Bottom edgesState cell1 = board[x - 1][y - 1];
            State cell1 = board[x - 1][y - 1];
            State cell2 = board[x - 1][y];
            State cell3 = board[x - 1][y + 1];
            State cell4 = board[x][y - 1];
            State cell6 = board[x][y + 1];
            cellCheckerArray = new State[] {cell1, cell2, cell3, cell4, cell6};
        }*/

        //Checks the cellCheckerArray to see if any of the adjacent cells are alive.
        for (int j = 0; j < cellCheckerArray.length; j++) {
            if (cellCheckerArray[j] == State.ALIVE) {
                nearbyAlive += 1;
            }
        }

        //Determines the next state of the cell, assigns it to newStates[x][y] (its own position).
        if (nearbyAlive == 3) {     //Birth
            newStates[x][y] = State.ALIVE;
//            System.out.println("Cell (" + x + "," + y + ") is born.");
        } else if ((nearbyAlive <= 3 && nearbyAlive >= 2) && board[x][y] == State.ALIVE) {      //Survival
//            System.out.println("Cell (" + x + "," + y + ") is surviving.");
            //do nothing
        } else if (nearbyAlive <= 1 || nearbyAlive >= 4) {      //Loneliness & Overcrowding
            newStates[x][y] = State.DEAD;
//            System.out.println("Cell (" + x + "," + y + ") has died.");
        }
        return newStates[x][y];
    }
}
