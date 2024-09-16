import java.util.ArrayList;
import java.util.PriorityQueue;
  class Pair implements Comparable<Pair>{
    int v;
    int cost ;
    public Pair (int v , int cost) {
        this.v=v;
        this.cost=cost;
    }

      @Override
      public int compareTo(Pair o) {
          return this.cost-o.cost;
      }
  }
public class Prims {
    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i =0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0,1,10));
        graph[0].add(new Edge(0,2,15));
        graph[0].add(new Edge(0,3,30));


        graph[1].add(new Edge(1,0,10));
        graph[1].add(new Edge(1,3,40));



        graph[2].add(new Edge(2,0,15));
        graph[2].add(new Edge(2,3,50));


        graph[3].add(new Edge(3,1,40));
        graph[3].add(new Edge(3,2,50));
    }
    public static void PrimsMst(ArrayList<Edge>[] graph){
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.add(new Pair(0,0))   ;
        int minimumWeidhgt=0;
        while (!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!visited[curr.v]){
                visited[curr.v]=true;
                minimumWeidhgt+=curr.cost;
                for(int i=0;i<graph[curr.v].size();i++){
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Pair(e.dest,e.weight));
                }

            }
        }
        System.out.println(minimumWeidhgt);

    }

    public static void main(String[] args) {
        int V=4;
        ArrayList<Edge> graph[]=new ArrayList[V];
        createGraph(graph);
        PrimsMst(graph);
    }

}
