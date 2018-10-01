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
    this.balance = 0;
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

  private static StringAVLNode rotateRight(StringAVLNode t) {
    StringAVLNode node = t.getLeft();

    node.setRight(t);

    return node;
  }

  private static StringAVLNode rotateLeft(StringAVLNode t)
  {
    StringAVLNode node = t.getRight();

    node.setLeft(t);

    return node;
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
    return balanced(root);
  }

  private int balanced(StringAVLNode t)
  {
    int output;

    if(t == null){
      //Tree is empty or done traversing
      output = 0;
    }else if(t.getBalance() == 0){
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
    String output = null;

    if(root == null){
      //dont do the recursive call
    }else{
      output = successor(str, root);
    }

    return output;
  }

  private String successor(String str, StringAVLNode t)
  {
    StringAVLNode parent = null;
    String output;

    if(str.compareTo(t.getItem()) == 0){
      if(t.getRight() == null){
        if(parent.getLeft() == t){
          output = parent.getItem();
        }else{
          //come back to this later
        }
      }else if(t.getRight().getLeft() == null){
        output = t.getRight().getItem();
      }else{
        t = t.getRight();
        while(t.getLeft() != null){
          t = t.getLeft();
        }
        output = t.getItem();
      }
    }else if(str.compareTo(t.getItem()) < 0){
      parent = t;
      output = successor(str, t.getLeft());
    }else{
      parent = t;
      output = successor(str, t.getRight());
    }

    return output;
  }

  public void insert(String str)
  {
    root = insert(str, root);
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
      //Node we want to insert is less than current node
      originalBalance = t.getLeft().getBalance();
      t.setLeft(insert(str, t.getLeft()));
      newBalance = t.getLeft().getBalance();
      if((originalBalance == 0) && ((newBalance == 1) || (newBalance == -1))){
        t.setBalance(t.getBalance() - 1);
      }
      if(t.getBalance() == 2){
        //left of here
      }
    }else{
      //Node we want to insert is greater than current node
      originalBalance = t.getRight().getBalance();
      t.setRight(insert(str, t.getRight()));
      newBalance = t.getRight().getBalance();
      if((originalBalance == 0) && ((newBalance == 1) || (newBalance == -1))){
        t.setBalance(t.getBalance() + 1);
      }
      if(t.getBalance() == 2){
        //left off here
      }
    }// end if-block

    return t;
  }

  public static String myName()
  {
    return "John Aquino";
  }
}
