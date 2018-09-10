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
  //   //
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
    row = (row/3)*3;
    col = (col/3)*3;
    for(int boxRow = row; boxRow < row+3; boxRow++){
      for(int boxCol = col; boxCol < col+3; boxCol++){
        if(this.board[boxRow][boxCol] == val){
          result = true;
        }
      }
    }
    return result;
  }

  public Spot rowFill(int row, int val)
  {
    Spot sq = new Spot(0,0);
    Spot temp = null;
    int Counter = 0;
    if(!doesRowContain(row, val)){
      for(int col = 0; col < 9; col++){
        if(
        (this.board[row][col] == 0) &&
        (!doesColContain(col, val)) &&
        (!doesBoxContain(row, col, val))
        ){
          Counter++;
          sq.setRow(row);
          sq.setCol(col);
        }//if
      }//for
      if(Counter == 1){
        temp = new Spot(sq.getRow(), sq.getCol());
      }//if
    }//if
    return temp;
  }

  public Spot colFill(int col, int val)
  {
    Spot sq = new Spot(0,0);
    Spot temp = null;
    int Counter = 0;
    if(!doesColContain(col, val)){
      for(int row = 0; row < 9; row++){
        if(
        (this.board[row][col] == 0) &&
        (!doesRowContain(row, val)) &&
        (!doesBoxContain(row, col, val))
        ){
          Counter++;
          sq.setRow(row);
          sq.setCol(col);
        }
      }
      if(Counter == 1){
        temp = new Spot(sq.getRow(), sq.getCol());
      }
    }
    return temp;
  }

  public Spot boxFill(int rowbox, int colbox, int val)
  {
    rowbox = (rowbox/3) * 3;
    colbox = (colbox/3) * 3;
    Spot sq = new Spot(0,0);
    Spot temp = null;
    int Counter = 0;
    if(!doesBoxContain(rowbox, colbox, val)){
      for(int row = rowbox; row < rowbox+3; row++){
        for(int col = colbox; col < colbox+3; col++){
          if(
          (this.board[row][col] == 0) &&
          (!doesRowContain(row, val)) &&
          (!doesColContain(col, val))
          ){
            Counter++;
            sq.setRow(row);
            sq.setCol(col);
          }
        }
      }
      if(Counter == 1){
        temp = new Spot(sq.getRow(), sq.getCol());
      }
    }
    return temp;
  }

  public static String myName()
  {
    return "John Aquino";
  }


}
