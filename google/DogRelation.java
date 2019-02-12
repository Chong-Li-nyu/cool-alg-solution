package google;
import java.util.*;

public class DogRelation {
/**
 * There are dogs. Each dog have two parents and some number of children, they have their own cubs, etc.
 *  Design a function which checks if two dogs are related in some way (marriage is not counted). 
 * Effectievly this means finding a common ancestor in a tree which is not arranged in any way.
 */
  /*
   * IDEA: bfs from both dog, maintain two queue and do bfs alternately (or choose the shorter queue to do a round of bfs)
   */
  
  public boolean isRelated(Node d1, Node d2) {
    Queue<Node>[] q = new Queue[2];
    for(int i = 0; i<2; i++) {
      q[i] = new LinkedList<Node>();
    }
    
    Set<Node> visited = new HashSet<>();
    q[0].offer(d1);
    q[1].offer(d2);
    while(!q[0].isEmpty() || !q[1].isEmpty()) {
      int index =0;
      if( q[0].isEmpty()) index = 1;
      else if( q[1].isEmpty()) index = 0;
      else {
        index = q[0].size() < q[1].size()?0: 1;
      }
      //one round bfs
      for(int i = q[index].size(); i>0; i--) {
        Node pop = q[index].poll();
        if( visited.contains(pop) ) return true;
        visited.add(pop);
        q[index].offer(pop.p1);
        q[index].offer(pop.p2);
      }
      //they have some common ancester means they are related
      
    }
    return false;
    
  }
  
 
}
class Node{
  Node p1;
  Node p2;
  public Node() {}
}



