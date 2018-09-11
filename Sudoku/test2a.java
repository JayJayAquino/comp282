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
          "800 000 042",
        "007 059 063",
        "000 000 900",

        "000 900 400",
        "650 080 071",
        "004 003 000",

        "002 000 000",
        "910 620 800",
        "780 000 004"
        }
    };

    sudoku p;
    Spot sp = new Spot(0,0);
    System.out.println("Author: " + sudoku.myName());
    p = new sudoku(s[1]);
    System.out.println(p.isValid());
    // System.out.println(p.doesBoxContain(3, 4, 3));
    // System.out.println(p.rowFill(7,4));
    // sp = p.rowFill(7,4);
    // System.out.println(p.fillSpot(sp));
  }
}
