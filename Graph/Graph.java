// John Aquino, Comp282 Mon/Wed, Assignment 4
// Date: Decemeber 11th 2018
//
// Description: This program contains all the logic that deals with
// finding the breadthFirstSearch and depthFirstSearch of a graph

import java.io.*; // for BufferedReader
import java.util.*; // for StringTokenizer

class Edge_Node {

    private Vertex_Node target;
    private Edge_Node next;

    public Edge_Node(Vertex_Node t, Edge_Node e) {
      target = t;
      next = e;
    }

    public Vertex_Node getTarget() {
      return target;
    }

    public Edge_Node getNext() {
      return next;
    }
}

class Vertex_Node {

    private String name;
    private boolean mark;
    private Edge_Node edge_head;
    private int distance;
    private Vertex_Node parent;
    private Vertex_Node next;

    public Vertex_Node(String s, Vertex_Node v) {
      name = s;
      next = v;
      distance = -1;
      mark = false;
    }

    public void setMark(boolean newMark) {
      mark = newMark;
    }

    public boolean getMark() {
      return mark;
    }

    public String getName() {
      return name;
    }

    public int getDistance() {
      return distance;
    }

    public void setDistance(int d) {
      distance = d;
    }

    public Edge_Node getNbrList() {
      return edge_head;
    }

    public void setNbrList(Edge_Node e) {
      edge_head = e;
    }

    public Vertex_Node getNext() {
      return next;
    }

    public Vertex_Node getParent() {
      return parent;
    }

    public void setParent(Vertex_Node n) {
      parent = n;
    }

    //without this, getName prints the mem address of the string object
    public String toString(){
      return name;
    }
}

class Graph {

    private Vertex_Node head;
    private int size;

    public Graph() {
        head = null;
        size = 0;
    }

    // reset all distance values to -1
    public void clearDist(){
      Vertex_Node temp = head;
      while (temp != null){
          temp.setDistance(-1);
          temp = temp.getNext();
      }
    }

    // reset all marks to false
    public void clearMark(){
      Vertex_Node temp = head;
      while (temp != null) {
          temp.setMark(false);
          temp = temp.getNext();
      }
    }

    public Vertex_Node findVertex(String s) {
        Vertex_Node pt = head;
        while (pt != null && s.compareTo(pt.getName()) != 0) {
            pt = pt.getNext();
        }
        return pt;
    }

    public Vertex_Node input(String fileName) throws IOException {
        String inputLine, sourceName, targetName;
        Vertex_Node source = null, target;
        Edge_Node e;
        StringTokenizer input;
        BufferedReader inFile = new BufferedReader(new FileReader(fileName));
        inputLine = inFile.readLine();
        while (inputLine != null) {
            input = new StringTokenizer(inputLine);
            sourceName = input.nextToken();
            source = findVertex(sourceName);
            if (source == null) {
                head = new Vertex_Node(sourceName, head);
                source = head;
                size++;
            }
            if (input.hasMoreTokens()) {
                targetName = input.nextToken();
                target = findVertex(targetName);
                if (target == null) {
                    head = new Vertex_Node(targetName, head);
                    target = head;
                    size++;
                }
                e = source.getNbrList();
                while (e != null) {
                    if (e.getTarget() == target) {
                        System.out.print("Multiple edges from "
                                + source.getName() + " to ");
                        System.out.println(target.getName() + ".");
                        break;
                    }
                    e = e.getNext();
                }
                source.setNbrList(new Edge_Node(target, source.getNbrList()));
                e = target.getNbrList();
                while (e != null) {
                    if (e.getTarget() == source) {
                        System.out.print("Multiple edges from "
                                + target.getName() + " to ");
                        System.out.println(source.getName() + ".");
                        break;
                    }
                    e = e.getNext();
                }
                target.setNbrList(new Edge_Node(source, target.getNbrList()));
            }
            inputLine = inFile.readLine();
        }
        inFile.close();
        return source;
    }

    public void output() {
        Vertex_Node v = head;
        Edge_Node e;
        while (v != null) {
            System.out.print(v.getName() + ": ");
            e = v.getNbrList();
            while (e != null) {
                System.out.print(e.getTarget().getName() + " ");
                e = e.getNext();
            }
            System.out.println();
            v = v.getNext();
        }
    }

    /**
     * driver for breadthFirstSearch, some logic is here in-case of
     * unconnected graph
     * @param s [starting node]
     */
    public void output_bfs(Vertex_Node s) {
      breadthFirstSearch(s);
      Vertex_Node vertex = head;

      //in case graph is unconnected
      while (vertex.getNext() != null){
        breadthFirstSearch(vertex);
        vertex = vertex.getNext();
      }//while
    }

    /*
    main breadthFirstSearch method
     */
    private void breadthFirstSearch(Vertex_Node s)
    {
      Queue<Vertex_Node> q = new LinkedList<>();

      if (!s.getMark()){
        //no parent at begining
        Vertex_Node parent = null;
        q.add(s);
        s.setDistance(0);
        s.setMark(true);

        System.out.println(q.peek().getName() + ", "
        + q.peek().getDistance() + ", "
        + parent);

        while (!q.isEmpty()){
          //if not marked, visit and print
          if(!q.peek().getMark()){
            //print
            System.out.println(q.peek().getName() + ", "
            + q.peek().getDistance() + ", "
            + parent);
          }//if

          //grab parent of node in queue
          parent = q.poll();
          Edge_Node nbr = parent.getNbrList();
          //continue breadthFirstSearch
          while (nbr != null) {
            if (!nbr.getTarget().getMark()) {
              //node has been vistited
              nbr.getTarget().setMark(true);
              nbr.getTarget().setDistance(parent.getDistance()+1);
              //print
              System.out.println(nbr.getTarget().getName() + ", "
              + nbr.getTarget().getDistance() + ", "
              + parent);

              q.add(nbr.getTarget());
            }//if
            //grab next edge node
            nbr = nbr.getNext();
          }//while
        }//while
      }//if
    }

    /**
     * driver for depthFirstSearch, some logic is here in-case of
     * unconnected graph
     * @param s [starting node]
     */
    public void output_dfs(Vertex_Node s)
    {
      clearDist();
      clearMark();

      depthFirstSearch(s);
      Vertex_Node vertex = head;
      //in case graph is unconnected
      while(vertex.getNext() != null){
          depthFirstSearch(vertex);
          vertex = vertex.getNext();
      }//while
    }

    /*
    main depthFirstSearch method
     */
    private void depthFirstSearch(Vertex_Node s)
    {
      Stack<Vertex_Node> stack = new Stack<>();
      s.setDistance(0);
      stack.push(s);
      while (!stack.isEmpty()){
        if (!stack.peek().getMark()){
          //print
          System.out.println(stack.peek() + ", "
          + stack.peek().getDistance() + ", "
          + stack.peek().getParent());

          stack.peek().setMark(true);
        }//while

        Edge_Node temp = stack.peek().getNbrList();
        boolean progress = true;

        //continue breadthFirstSearch
        while (temp != null && temp.getTarget() != null && progress){
          if (!temp.getTarget().getMark()){
            //set target's parent
            temp.getTarget().setParent(stack.peek());
            //calculate distance
            temp.getTarget().setDistance(stack.peek().getDistance()+1);
            stack.push(temp.getTarget());
            progress = false;
          }else{
            //continue bfs
            temp = temp.getNext();
            progress = true;
          }//if
        }//while

        //back track once
        if (temp == null && stack.peek().getMark()){
          stack.pop();
        }//if
      }//while
    }


    public static boolean implementedDFS()
    {
      return true;
    }

    public static String myName() {
        return "John Aquino";
    }
}
