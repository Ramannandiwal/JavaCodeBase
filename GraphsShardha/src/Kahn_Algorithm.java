import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Kahn_Algorithm {

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[2].add(new Edge(2,3));


        graph[3].add(new Edge(3,1));


        graph[4].add(new Edge(4,0));
        graph[4].add(new Edge(4,1));


        graph[5].add(new Edge(5,0));
        graph[5].add(new Edge(5,2));

    }
    public static ArrayList<Integer> indexreturnZeroIndree(int[] indegree){
        ArrayList<Integer> list = new ArrayList<>();
      for(int i=0;i<indegree.length;i++){
          if(indegree[i]==0){
              list.add(i);
          }
      }
      return list;
    }
    public static void topologicalSort(ArrayList<Edge>[] graph){
        int[] indegree=new int[graph.length];
        for(int i=0;i<graph.length;i++){
           for(int j =0;j<graph[i].size();j++){
               Edge e = graph[i].get(j) ;
               indegree[e.dest]++;
           }
        }
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> list = indexreturnZeroIndree(indegree);
        for(int i=0;i<list.size();i++){
            queue.add(list.get(i))  ;
        }
        while (!queue.isEmpty()){
            int a = queue.poll();
            System.out.print(a+" ");
          for(int i=0;i<graph[a].size();i++){
              Edge e = graph[a].get(i);
              indegree[e.dest]--;
              if(indegree[e.dest]==0){
                  queue.add(e.dest);
              }
          }
        }

    }

    public static void main(String[] args) {
        int v =6;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        topologicalSort(graph);
    }
}
