import java.io.*;
import java.util.*;

class Edge_Node
{
  private Vertex_Node target;
  private Edge_Node next;

  public Edge_Node(Vertex_Node t, Edge_Node e)
  {
    target = t;
    next = e;
  }

  public Vertex_Node getTarget()
  {
    return target;
  }

  public Edge_Node getNext()
  {
    return next;
  }
}

class Vertex_Node
{
  private String name;
  private Edge_Node edge_head;
  private int distance;
  private Vertex_Node parent;
  private Vertex_Node next;
  private boolean visited;

  public Vertex_Node(String s, Vertex_Node v)
  {
    name = s;
    next = v;
    distance = -1;
    visited = false;
  }

  public String getName()
  {
    return name;
  }

  public int getDistance()
  {
    return distance;
  }

  public void setDistance(int d)
  {
    distance = d;
  }

  public Edge_Node getNbrList()
  {
    return edge_head;
  }

  public void setNbrList(Edge_Node e)
  {
    edge_head = e;
  }

  public Vertex_Node getNext()
  {
    return next;
  }

  public Vertex_Node getParent()
  {
    return parent;
  }

  public void setParent(Vertex_Node n)
  {
    parent = n;
  }

  public void setVisited()
  {
    visited = true;
  }

  public boolean getVisited()
  {
    return visited;
  }

}

class Graph
{
  private Vertex_Node head;
  private int size;

  public Graph()
  {
    head = null;
    size = 0;
  }

  public void clearDist()
  {
    //finish this
  }

  public Vertex_Node findVertex(String s)
  {
    Vertex_Node pt = head;
    while(pt != null && s.compareTo(pt.getName()) != 0){
      pt = pt.getNext();
    }

    return pt;
  }

  public Vertex_Node input(String fileName) throws IOException
  {
    String inputLine, sourceName, targetName;
    Vertex_Node source = null, target;
    Edge_Node e;
    StringTokenizer input;
    BufferedReader inFile = new BufferedReader(new FileReader(fileName));
    inputLine = inFile.readLine();
    while(inputLine != null){
      input = new StringTokenizer(inputLine);
      sourceName = input.nextToken();
      source = findVertex(sourceName);
      if(source == null){
        head = new Vertex_Node(sourceName, head);
        source = head;
        size++;
      }
      if(input.hasMoreTokens()){
        targetName = input.nextToken();
        target = findVertex(targetName);
        if(target == null){
          head = new Vertex_Node(targetName, head);
          target = head;
          size++;
        }

        e = source.getNbrList();
        while(e != null){
          if(e.getTarget() == target){
            System.out.print("Multiple edges from " + source.getName() +
            " to ");
            System.out.println(target.getName() + ".");
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

  public void output()
  {
    Vertex_Node v = head;
    Edge_Node e;
    while(v != null){
      System.out.print(v.getName() + ": ");
      e = v.getNbrList();
      while(e != null){
        System.out.print(e.getTarget().getName() + " ");
        e = e.getNext();
      }
      System.out.println();
      v = v.getNext();
    }
  }

  public void output_bfs(Vertex_Node s)
  {
    Queue<String> bfsQueue = new LinkedList<>();
  }

  public void output_dfs(Vertex_Node s)
  {
    //
  }

  public static boolean implementedDFS()
  {
    return false;
  }

  public String myName()
  {
    return "John Aquino";
  }

  public static void main(String args[]){
    String fileName = "Graph1.txt";
    Graph g = new Graph();
    output_bfs(g.input(fileName));
  }
}
