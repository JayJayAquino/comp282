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

  private static StringAVLNode rotateRight(StringAVLNode t)
  {
    StringAVLNode output = t.getLeft();

    t.setLeft(t.getLeft().getRight());
    output.setRight(t);
    return output;
  }

  private static StringAVLNode rotateLeft(StringAVLNode t)
  {
    StringAVLNode output = t.getRight();

    t.setRight(t.getRight().getLeft());
    output.setLeft(t);
    return output;
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
      //recursively go down the left subtree
      leftTreeCounter = height(t.getLeft());
      //recursively go down the right subtree
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
      output = balanced(t.getLeft()) + balanced(t.getRight());
    }

    return output;
  }

  public String successor(String str)
  {
    StringAVLNode output;
    StringAVLNode lastLeft = null;
    String finalResult = null;

    output = successor(str, root, lastLeft);
    if(output != null){
      finalResult = output.getItem();
    }

    return finalResult;
  }

  private StringAVLNode successor(String str, StringAVLNode t, StringAVLNode lastLeft)
  {
    StringAVLNode output = null;
    StringAVLNode temp;
    StringAVLNode successor;

    if(t == null){
      //value not found
      output = null;
    }else if(str.compareTo(t.getItem()) < 0){
      lastLeft = t;
      t = successor(str, t.getLeft(), lastLeft);
    }else if(str.compareTo(t.getItem()) > 0){
      t = successor(str, t.getRight(), lastLeft);
    }else if(str.compareTo(t.getItem()) == 0){
      if(t.getRight() == null){
        successor = lastLeft;
      }else{
        temp = t.getRight();
        while(temp.getLeft() != null){
          temp = temp.getLeft();
        }
        successor = temp;
      }
      output = successor;
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
    int tempBalance;

    //empty tree
    if(t == null){
      t = new StringAVLNode(str);
    }else if(str.compareTo(t.getItem()) == 0){
      //do nothing since item is in tree
    }else if(str.compareTo(t.getItem()) < 0){
      //Node we want to insert is less than current node
      if(t.getLeft() != null){
        originalBalance = t.getLeft().getBalance();
        t.setLeft(insert(str, t.getLeft()));
        newBalance = t.getLeft().getBalance();
        if((originalBalance == 0) && (newBalance != 0)){
          t.setBalance(t.getBalance() - 1);
        }
      }else{
        //can just insert and change balance
        t.setLeft(insert(str, t.getLeft()));
        t.setBalance(t.getBalance() - 1);
      }

      if(t.getBalance() == -2){
        //rotations must happen
        if(str.compareTo(t.getLeft().getItem()) < 0){
          //Single Right rotate
          t = rotateRight(t);
          t.setBalance(0);
          t.getRight().setBalance(0);
        }else if(str.compareTo(t.getLeft().getItem()) > 0){
          tempBalance = t.getLeft().getRight().getBalance();
          if(tempBalance == 0){
            //Simple Left Right rotate
            t.setLeft(rotateLeft(t.getLeft()));
            t = rotateRight(t);
            t.setBalance(0);
            t.getLeft().setBalance(0);
            t.getRight().setBalance(0);
          }else if(tempBalance == -1){
            //Special Left Right case 1
            t.setLeft(rotateLeft(t.getLeft()));
            t = rotateRight(t);
            t.setBalance(0);
            t.getLeft().setBalance(0);
            t.getRight().setBalance(1);
          }else if(tempBalance == 1){
            //Special Left Right case 2
            t.setLeft(rotateLeft(t.getLeft()));
            t = rotateRight(t);
            t.setBalance(0);
            t.getLeft().setBalance(-1);
            t.getRight().setBalance(0);
          }
        }
      }
    }else{
      //Node we want to insert is greater than current node
      if(t.getRight() != null){
        originalBalance = t.getRight().getBalance();
        t.setRight(insert(str, t.getRight()));
        newBalance = t.getRight().getBalance();
        if((originalBalance == 0) && (newBalance != 0)){
          t.setBalance(t.getBalance() + 1);
        }
      }else{
        //can just insert and change balance
        t.setRight(insert(str, t.getRight()));
        t.setBalance(t.getBalance() + 1);
      }

      if(t.getBalance() == 2){
        if(str.compareTo(t.getRight().getItem()) > 0){
          //Single Left rotate
          t = rotateLeft(t);
          t.getLeft().setBalance(0);
          t.setBalance(0);
        }else if(str.compareTo(t.getRight().getItem()) < 0){
          //Right Left Rotate
          tempBalance = t.getRight().getLeft().getBalance();
          if(tempBalance == 0){
            //simple case
            t.setRight(rotateRight(t.getRight()));
            t = rotateLeft(t);
            t.setBalance(0);
            t.getLeft().setBalance(0);
            t.getRight().setBalance(0);
          }else if(tempBalance == -1){
            //special case 1
            t.setRight(rotateRight(t.getRight()));
            t = rotateLeft(t);
            t.setBalance(0);
            t.getLeft().setBalance(0);
            t.getRight().setBalance(1);
          }else if(tempBalance == 1){
            //special case 2
            t.setRight(rotateRight(t.getRight()));
            t = rotateLeft(t);
            t.setBalance(0);
            t.getLeft().setBalance(-1);
            t.getRight().setBalance(0);
          }
        }
      }
    }// end if-block

    return t;
  }

  public static String myName()
  {
    return "John Aquino";
  }
}
