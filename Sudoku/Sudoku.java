class Spot
{
  private int row, col;

  public Spot(int row, int col)
  {
    this.row = row;
    this.col = col;
  }

  public void setRow(int row)
  {
    this.row = row;
  }

  public void setCol(int col)
  {
    this.col = col;
  }

  public int getRow()
  {
    return row;
  }

  public int getCol()
  {
    return col;
  }
}


class Sudoku
{
  private int board [][];

  // public sudoku()
  // {
  //   //
  // }

  /**
  * Construct a new board and cast the string array to int array
  */
  public Sudoku(String s[])
  {
    this.board = new int[9][9];
    for(int row = 0; row < 9; row++){
      for(int col = 0; col < 9; col++){
        this.board[row][col] = ( (int) (s[row].charAt(col + col/3)) - 48);
      }//for
    }//for
  }

  public void rotate()
  {
    int [][] temp = new int[9][9];
    int row, col;
    for(row = 0; row < 9; row++){
      for(col = 0; col < 9; col++){
        temp[col][8-row] = board[row][col];
      }
    }
    for(row = 0; row < 9; row++){
      for(col = 0; col < 9; col++){
        board[row][col] = temp[row][col];
      }
    }
  }

  // public boolean isValid()
  // {
  //
  // }

  /**
  * Checks to see if a value exists in specified row
  */
  private boolean doesRowContain(int row, int val)
  {
    boolean result = false;
    for(int col = 0; col < 9; col++){
      if(this.board[row][col] == val){
        result = true;
      }
    }
    return result;
  }

  /**
  * Checks to see if a value exists in specified col
  */
  private boolean doesColContain(int col, int val)
  {
    boolean result = false;
    for(int row = 0; row < 9; row++){
      if(this.board[row][col] == val){
        result = true;
      }
    }
    return result;
  }

  /**
  * Checks to see if a value exists in specified box
  */
  public boolean doesBoxContain(int row, int col, int val)
  {
    boolean result = false;
    for(int boxRow = (row/3)*3; boxRow < boxRow+2; boxRow++){
      for(int boxCol = (col/3)*3; boxCol < boxCol+2; boxCol++){
        if(this.board[boxRow][boxCol] == val){
          result = true;
        }
      }
    }
    return result;
  }

  public static String myName()
  {
    return "John Aquino";
  }


}
