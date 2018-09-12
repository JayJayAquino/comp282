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


class sudoku
{
  private int board [][];

  public sudoku()
  {
    //
  }

  /**
  * Construct a new board and cast the string array to int array
  */
  public sudoku(String s[])
  {
    this.board = new int[9][9];
    for(int row = 0; row < 9; row++){
      for(int col = 0; col < 9; col++){
        this.board[row][col] = ( (int) (s[row].charAt(col + col/3)) - 48);
        //cast every value in s[] from string to int.
      }//for
    }//for
  }

  /**
  * Copy and Construct a new board
  */
  public sudoku(sudoku p)
  {
    this.board = new int[9][9];
      int row;
      int col;
      for (row = 0; row < 9; row++) {
        for (col = 0; col < 9; col++) {
            this.board[row][col] = p.board[row][col];
            //Copy the board being passed in
        }
      }
  }

  /**
  * Convert the board array from int to string and print the board in a
  * read-able format
  */
  public String toString()
  {
    String result = "";
    for(int row = 0; row < this.board.length; row++){
      for(int col = 0; col < this.board[row].length; col++){
        //after every 3 spots, add a divider for read-ability
        if((col != 0) && (col % 3 == 0)){
          result = result + "|";
        }//if
        result = result + String.valueOf(this.board[row][col]);
      }//for
      result = result + "\n";
      if((row + 1) % 3 == 0){
        result = result + "------------\n";
      }//if
    }//for
    return result;
  }

  public void rotate()
  {
    int [][] temp = new int[9][9];
    int row, col;
    for(row = 0; row < 9; row++){
      for(col = 0; col < 9; col++){
        temp[col][8-row] = this.board[row][col];
      }
    }
    for(row = 0; row < 9; row++){
      for(col = 0; col < 9; col++){
        this.board[row][col] = temp[row][col];
      }
    }
  }

  public String toString2()
  {
    String result = new String();
    for(int row = 0; row < 9; row++){
      for(int col = 0; col < 9; col++){
        result = result + String.valueOf(this.board[row][col]);
      }
    }
    return result;
  }

  /**
  * Check if the board satisfies all the rules of sudoku
  */
  public boolean isValid()
  {
    boolean rowResult = true;
    boolean colResult = true;
    boolean boxResult = true;
    boolean finalResult = false;
    int Counter;
    int row;
    int col;
    int val;
    int boxRow;
    int boxCol;

    // Check every row
    for(row = 0; row < 9; row++){
      for(val = 1; val < 10; val++){
        Counter = 0;
        for(col = 0; col < 9; col++){
          if(this.board[row][col] == val){
            Counter++;
          }//if
          if(Counter > 1){
            rowResult = false;//if more than one value exists, row not valid
          }//if
        }//for
      }//for
    }//for

    //Check every Col
    for(col = 0; col < 9; col++){
      for(val = 1; val < 10; val++){
        Counter = 0;
        for(row = 0; row < 9; row++){
          if(this.board[row][col] == val){
            Counter++;
          }//if
          if(Counter > 1){
            colResult = false;//if more than one value exists, column not valid
          }
        }//for
      }//for
    }//for

    //Check every Box
    for(row = 0; row < 9; row += 3){
      for(val = 1; val < 10; val++){
        for(col = 0; col < 9; col += 3){
          Counter = 0;
          for(boxRow = row; boxRow < row+3; boxRow++){
            for(boxCol = col; boxCol < col+3; boxCol++){
              if(this.board[boxRow][boxCol] == val){
                Counter++;
              }//if
              if(Counter > 1){
                boxResult = false;//if more than one value exists, box not valid
              }//if
            }//for
          }//for
        }//for
      }//for
    }//for

    if(rowResult == true && colResult == true && boxResult == true){
      finalResult = true;
    }

    return finalResult;
  }

  /**
  * Check if the board is completely solved
  */
  public boolean isComplete()
  {
    boolean result = true;
    //If there is a zero in the board, it is not complete
    for(int row = 0; row < 9; row++){
      for(int col = 0; col < 9; col++){
        if(this.board[row][col] == 0){
          result = false;
        }//if
      }//for
    }//for

    //If the board is not valid, it is not complete
    if (!isValid()){
      result = false;
    }//if

    return result;
  }

  /**
  * Checks to see if a value exists in specified row
  */
  private boolean doesRowContain(int row, int val)
  {
    boolean result = false;
    for(int col = 0; col < 9; col++){
      if(this.board[row][col] == val){
        result = true;
      }//if
    }//for
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
      }//if
    }//for
    return result;
  }

  /**
  * Checks to see if a value exists in specified box
  */
  private boolean doesBoxContain(int row, int col, int val)
  {
    boolean result = false;
    row = (row/3)*3;
    col = (col/3)*3;
    for(int boxRow = row; boxRow < row+3; boxRow++){
      for(int boxCol = col; boxCol < col+3; boxCol++){
        if(this.board[boxRow][boxCol] == val){
          result = true;
        }//if
      }//for
    }//for
    return result;
  }

  /**
  * Return a value to be inputed at a given spot
  */
  private int fillSpot(Spot sq)
  {
    int result = 0;
    int finalResult = 0;
    int Counter = 0;
    int row = sq.getRow();
    int col = sq.getCol();
    for(int value = 1; value < 10; value++){
      //if the value passes all these conditions, increment the counter
      if(
      (this.board[row][col] == 0) &&
      (!doesRowContain(row, value)) &&
      (!doesColContain(col, value)) &&
      (!doesBoxContain(row, col, value))
      ){
        Counter++;
        result = value;
      }//if
    }//for

    // if only one value passed the conditions, it must be returned
    if(Counter == 1){
      finalResult = result;
    }//if
    return finalResult;
  }

  /**
  * Check if a value can only be inserted at one spot in a specified row,
  * if so return that spot
  */
  private Spot rowFill(int row, int val)
  {
    Spot sq = new Spot(0,0);
    Spot temp = null;
    int Counter = 0;
    if(!doesRowContain(row, val)){
      for(int col = 0; col < 9; col++){
        //if the value passes all of these conditions increment the counter
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

  /**
  * Check if a value can only be inserted at one spot in a specified column,
  * if so return that spot
  */
  private Spot colFill(int col, int val)
  {
    Spot sq = new Spot(0,0);
    Spot temp = null;
    int Counter = 0;
    if(!doesColContain(col, val)){
      for(int row = 0; row < 9; row++){
        //if the value passes all of these conditions increment the counter
        if(
        (this.board[row][col] == 0) &&
        (!doesRowContain(row, val)) &&
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

  /**
  * Check if a value can only be inserted at one spot in a specified box,
  * if so return that spot
  */
  private Spot boxFill(int rowbox, int colbox, int val)
  {
    rowbox = (rowbox/3) * 3;
    colbox = (colbox/3) * 3;
    Spot sq = new Spot(0,0);
    Spot temp = null;
    int Counter = 0;
    if(!doesBoxContain(rowbox, colbox, val)){
      for(int row = rowbox; row < rowbox+3; row++){
        for(int col = colbox; col < colbox+3; col++){
          //if the value passes all of these conditions increment the counter
          if(
          (this.board[row][col] == 0) &&
          (!doesRowContain(row, val)) &&
          (!doesColContain(col, val))
          ){
            Counter++;
            sq.setRow(row);
            sq.setCol(col);
          }//if
        }//for
      }//for
      if(Counter == 1){
        temp = new Spot(sq.getRow(), sq.getCol());
      }//if
    }//if
    return temp;
  }

  public void solve()
  {
    boolean changes = true;
    Spot s = new Spot(0,0);
    int row;
    int col;
    int val;

    while(changes == true){
      changes = false;

      //fill spots based on rowFill
      for(row = 0; row < 9; row++){
        for(val = 1; val < 10; val++){
          s = rowFill(row, val);
          if(s != null){
            this.board[s.getRow()][s.getCol()] = val;
            changes = true;
          }//if
        }//for
      }//for

      //fill spots based on colFill
      for(col = 0; col < 9; col++){
        for(val = 1; val < 10; val++){
          s = colFill(col, val);
          if(s != null){
            this.board[s.getRow()][s.getCol()] = val;
            changes = true;
          }//if
        }//for
      }//for

      //fill spots based on boxFill
      for(row = 0; row < 9; row++){
        for(col = 0; col < 9; col++){
          for(val = 1; val < 10; val++){
            s = boxFill(row, col, val);
            if(s != null){
              this.board[s.getRow()][s.getCol()] = val;
              changes = true;
            }//if
          }//for
        }//for
      }//for

      //fill spots based on fillSpot
      for(row = 0; row < 9; row++){
        for(col = 0; col < 9; col++){
          if(this.board[row][col] == 0){
            s = new Spot(row, col);
            this.board[s.getRow()][s.getCol()] = fillSpot(s);
          }//if
        }//for
      }//for

    }//while
  }

  public static String myName()
  {
    return "John Aquino";
  }
}
