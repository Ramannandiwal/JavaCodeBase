import java.util.ArrayList;

public class detectCycle {

     static class   Edge{
        int src ;
        int dest;

        Edge(int s,int d){
            this.src = s;
            this.dest = d;


        }

    }
    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        int curr = 0;
        for(int i =0;i<graph.length;i++){
            if(!vis[i]){

               if( detectCycleUtil(graph,i,vis,-1)){
                   return true;
               }
            }
        }
        return false;
    }

    private static boolean detectCycleUtil(ArrayList<Edge>[] graph, int cur, boolean[] vis, int parent) {
      vis[cur]=true;
      for(int i =0;i<graph[cur].size();i++){
          Edge e = graph[cur].get(i);
          if(!vis[e.dest]){
              if(detectCycleUtil(graph,e.dest,vis,cur)){
                  return true;
              }
          }
          else if(vis[e.dest]&&e.dest!=parent){
              return true;

          }

      }
      return  false;
    }

    public static   void createGraph(ArrayList<Edge> graph []){
        for(int i = 0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,2));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,1));

        graph[3].add(new Edge(3,0));
        graph[3].add(new Edge(3,4));

        graph[4].add(new Edge(4,3));
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        System.out.println(detectCycle(graph));

}}
