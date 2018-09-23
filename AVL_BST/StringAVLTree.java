// John Aquino, Comp282 Mon/Wed, Assignment 2
// Date:
//
// Description:

class StringAVLNode
{
  private String item;
  private int balance;
  private StringAVLNode left, right;

  public StringAVLNode(String str)
  {
    this.item = str;
    this.left = null;
    this.right = null;
  }

  public int getBalance()
  {
    return balance;
  }

  public void setBalance(int bal)
  {
    this.balance = bal;
  }

  public String getItem()
  {
    return item;
  }

  public StringAVLNode getLeft()
  {
    return left;
  }

  public void setLeft(StringAVLNode pt)
  {
    this.left = pt;
  }

  public StringAVLNode getRight()
  {
    return right;
  }

  public void setRight(StringAVLNode pt)
  {
    this.right = pt;
  }
}

class StringAVLTree
{
  StringAVLNode root;

  public StringAVLTree()
  {
    //
  }

  private static StringAVLNode rotateRight(StringAVLNode t)
  {
    //
  }

  private static StringAVLNode rotateLeft(StringAVLNode t)
  {
    //
  }

  public int height()
  {
    //
  }

  public int leafCt()
  {
    //
  }

  public int balanced()
  {
    //
  }

  public String successor(String str)
  {
    //
  }

  public void insert(String str)
  {
    //
  }

  private StringAVLNode insert(String str, StringAVLNode t)
  {
    //
  }

  public static String myName()
  {
    return "John Aquino";
  }
}
