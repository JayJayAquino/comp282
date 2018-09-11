public class test2a{
  public static void main(String[] args)
  {
    String s[][] = {
      {"800 000 042",
        "007 059 063",
        "000 000 900",

        "000 900 400",
        "650 080 071",
        "004 003 000",

        "002 000 000",
        "910 620 800",
        "780 000 004" },
        {
          "800 000 042",
  					"007 059 063",
  					"000 000 900",

  					"000 300 400",
  					"650 080 071",
  					"004 003 000",

  					"002 000 000",
  					"910 620 800",
  					"780 000 004" },
        {
          "002 600 700",
  					"800 004 005",
  					"007 005 320",

  					"109 000 007",
  					"000 507 000",
  					"600 000 904",

  					"058 700 600",
  					"200 300 009",
  					"003 006 800"
        }
    };

    sudoku p;
    Spot sp = null;
    System.out.println("Author: " + sudoku.myName());
    p = new sudoku(s[0]);
    //
  //   for(int i = 0; i<9; i++){
  //   sp = p.colFill(i,4);
  //   System.out.println(sp);
  //   if(sp != null){
  //     System.out.println(sp.getRow());
  //     System.out.println(sp.getCol());
  //
  //     System.out.println(p.fillSpot(sp));
  //   }
  // }


    // System.out.println(p.rowFill(4,9));
    System.out.println(p.isValid());
    p.solve();
    System.out.print(p.toString());
    System.out.println(p.isComplete());
    System.out.println(p.isValid());
    // System.out.println(p.doesBoxContain(3, 4, 3));
    // System.out.println(p.rowFill(7,4));
    // sp = p.rowFill(7,4);
    // System.out.println(p.fillSpot(sp));
  }
}
