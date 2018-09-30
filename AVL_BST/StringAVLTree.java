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
    return height(root);
  }

  private int height(StringAVLNode t)
  {
    int leftTreeCounter;
    int rightTreeCounter;
    int output = 0;

    if(t != null){
      leftTreeCounter = height(t.getLeft());
      rightTreeCounter = height(t.getRight());
      if(leftTreeCounter > rightTreeCounter){
        output = leftTreeCounter + 1;
      }else{
        output = rightTreeCounter + 1;
      }
    }

    return output;
  }

  public int leafCt()
  {
    return leafCt(root);
  }

  private int leafCt(StringAVLNode t)
  {
    int output;
    if(t == null){
      //Tree is empty
      output = 0;
    }else if((t.getLeft() == null) && (t.getRight() == null)){
      //Current node is a leaf
      output = 1;
    }else{
      //recursively go into the left and right subtree
      output = leafCt(t.getLeft()) + leafCt(t.getRight());
    }
    return output;
  }

  public int balanced()
  {
    return int balanced(root);
  }

  private int balanced(StringAVLNode t)
  {
    int output;

    if(t == null){
      //Tree is empty or done traversing
      output = 0;
    }else if(t.getBalance == 0){
      //Found Balanced node and continue recursively searching
      output = 1 + balanced(t.getLeft()) + balanced(t.getRight());
    }else{
      //Recursively search
      output = balanced(t.getLeft()) + balanced(t.getLeft());
    }

    return output;
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
    int originalBalance;
    int newBalance;
    //empty tree
    if(t == null){
      t = new StringAVLNode(str);
    }else if(t.getItem() == str){
      //do nothing since item is in tree
    }else if(str.compareTo(t.getItem()) < 0){
      originalBalance = t.getLeft().getBalance();
      t.setLeft(insert(str, t.getLeft()));
      newBalance = t.getLeft().getBalance();
      if((originalBalance == 0) && (newBalance == 1)){

      }
    }else{
      originalBalance = t.getRight().getBalance();
      t.setRight(insert(str, t.getRight()));
      newBalance = t.getRight().getBalance();
    }// end if-block
  }

  public static String myName()
  {
    return "John Aquino";
  }
}
