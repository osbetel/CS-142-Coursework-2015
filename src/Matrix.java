import java.util.Arrays;

/** class to model a 3 x 4 matrix of doubles <br>
 * Supports Gaussian row operations
 * <br> In this version, rows and columns start at 0
 * @author csc 142 class, modified by Andrew Nguyen
 */

public class Matrix {

    public static int ROW = 3;
    public static int COL = 4;

  // declare the instance variable that will hold the 2-dim array
    double[][] matrix;

  
  /**Instantiate a ROW x COL matrix, empty
  */
  public Matrix() {
      matrix = new double[ROW][COL];
      /**
       * Shorthand for
       * matrix[0] = new double[COL];       {0,0,0,0}
       * matrix[1] = new double[COL];       {0,0,0,0}
       * matrix[2] = new double[COL];       {0,0,0,0}
       * Values default to 0.
       */

  }
  
  
  /** set the value of a particular element in the matrix
   * @param row the row of the element. 0 <= row < Matrix.ROW
   * @param col the column of the element.   0 <= col < Matrix.COL
   * @param value the value to be stored
   */
  public void setValue(int row, int col, double value) {
	  // Why don't we have to test row/col for validity?

      //It will automatically throw an out of bounds exception if the row,col
      //values aren't correct; no need to do it ourselves.
      this.matrix[row][col] = value;
  }
  
  /** returns the value of a particular element in the matrix
   * @param row the row of the element. 0 <= row < Matrix.ROW
   * @param col the column of the element.   0 <= col < Matrix.COL
   */
  public double getValue(int row, int col) {
      return this.matrix[row][col];
  }
  
  
  /** swap 2 rows of this matrix
   * @param r1 one of the rows to swap.  0 <= r1 < Matrix.ROW
   * @param r2 the other row to swap.   0 <= r2 < Matrix.COL
   */
  public void swapRows(int r1, int r2) {
      double[] tempArray = this.matrix[r1];
      this.matrix[r1] = this.matrix[r2];
      this.matrix[r2] = tempArray;
  }
  
  /** multiply one row by a non-zero constant
   * @param multiple the non-zero constant
   * @param row the row to change
   * @throws IllegalArgumentException if multiple is 0
   */
  public void multiplyRow(double multiple, int row) throws IllegalArgumentException {
      if (multiple == 0) {
          throw new IllegalArgumentException("You cannot multiply by 0");
      }
      for (int i = 0; i < this.matrix[row].length; i++) {
          this.matrix[row][i] *= multiple;
      }
  }
  
  /** add row r1 into row r2. Equivalent to r2 += r1 
   * @param r1 the row to add  0 <= r1 < Matrix.ROW
   * @param r2 the row to add into.  0 <= r2 < Matrix.ROW.  This row will change.
   */
  public void addRows (int r1, int r2) {
      for (int i = 0; i < COL; i++) {
          this.matrix[r2][i] += this.matrix[r1][i];
      }
  }
  /**
   * set new row in the matrix
   * @param row the new row to insert
   * @param rIdx the index of this new row  0 <= rIdx < Matrix.ROW
   * @return the old row
   * @throws IllegalArgumentException if row is not the correct length of Matrix.COL
   */
	public double[] replace(double[] row, int rIdx) throws IllegalArgumentException {
        if (row.length != COL) {
            throw new IllegalArgumentException("The length of your replacement array must be equal to the length of a row in the matrix.");
        }
        double[] oldRow = this.matrix[rIdx];
        this.matrix[rIdx] = row;
        return oldRow;
    }
	
  /**
   * Add 2 Matrices together and return a new Matrix
   * @param m the 2nd Matrix
   * @return the matrix sum of this + m
   */
	
	public Matrix sum(Matrix m){
        Matrix sumArray = new Matrix();
        for (int i = 0; i < ROW; i++) {
            for (int k = 0; k < COL; k++) {
                sumArray.matrix[i][k] = (this.matrix[i][k] + m.matrix[i][k]);
            }
        }
        return sumArray;
    }
	
  
  /** Return this matrix as a String of 3 rows of numbers in 4 columns
   */
  public String toString() {
      String s = "";
      for (double[] x:matrix) {
          s += Arrays.toString(x) + "\n";
      }
      s = s.trim();
      return s;
  }
}  
  