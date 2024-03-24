import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bfs {

    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 6, 1));
        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));

    }
    public static void bfsnonconnected(ArrayList<Edge>[] graph){
        int curr =0;
        boolean vi[]= new boolean[graph.length];
        for(int i =0;i<graph.length;i++){
            if(!vi[i]){
                bfs(graph,curr,vi);
            }
        }
    }
    public static void bfs(ArrayList<Edge> graph[],int curr,boolean vis[]){


        Queue<Integer> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()){
            int a = queue.poll();


          if(!vis[a]){
              System.out.print(a+" ");
              vis[a]=true;
              for(int i =0;i<graph[a].size();i++){
                  Edge e  = graph[a].get(i);
                  queue.add(e.dest);
              }
          }

        }









        }



    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
     bfsnonconnected(graph);
    }
}
