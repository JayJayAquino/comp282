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
    this.root = null;
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
    int height;
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
    root = insert(str, this.root);
  }

  private StringAVLNode insert(String str, StringAVLNode t)
  {
    //empty tree
    if(tree == null){
      tree = new StringAVLNode(str);
    }else if(t.getItem() == str){
      //do nothing since item is in tree
    }else if(t.getItem().compareTo(str) < 0){
      t.setLeft(insert(str, t.getLeft()));
    }else{
      t.setRight(insert(str, t.getRight()));
    }
  }

  public static String myName()
  {
    return "John Aquino";
  }
}
