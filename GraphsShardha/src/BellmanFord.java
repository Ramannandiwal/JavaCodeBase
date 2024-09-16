import java.util.ArrayList;

public class BellmanFord {
    public static  void createGraph(ArrayList<Edge> graph[]){
        for(int i =0;i<graph.length;i++)
        {
            graph[i]=new ArrayList<>();

        }
        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2 ,4));

        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));

        graph[3].add(new Edge(3,4,4));

        graph[4].add(new Edge(4,1,-1));



    }
    public  static  void bellmanForm(ArrayList<Edge>graph[],int src) {
        int[] distance = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (i != src) {
                distance[i] = Integer.MAX_VALUE;
            }
        }
        int V = graph.length;
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Edge> edges : graph) {
                for (Edge e : edges) {
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.weight;
                    if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {
                        distance[v] = distance[u] + wt;
                    }
                }
            }
        }
        for (int j : distance) {
            System.out.print(j + " ");
        }
    }
    public  static  void bellmanFord(ArrayList<Edge> grap,int src,int V){
        int distance[]=new int[V];
        for (int i = 0; i < distance.length; i++) {
            if (i != src) {
                distance[i] = Integer.MAX_VALUE;
            }
        }
        for(int i =0;i<V-1;i++){

         for(int j =0;j<grap.size();j++){
             Edge e = grap.get(j);
             int u =e.src;
             int v =e.dest;
             int wt = e.weight;
             if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {
                 distance[v] = distance[u] + wt;
             }
         }
            for (int j : distance) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

    }
    private static void createGraph2(ArrayList<Edge> grap) {
        grap.add(new Edge(0,1,2));
        grap.add(new Edge(0,2 ,4));

        grap.add(new Edge(1,2,-4));

        grap.add(new Edge(2,3,2));

        grap.add(new Edge(3,4,4));

        grap.add(new Edge(4,1,-1));
    }
    public static void main(String[] args) {
        int v = 5;
        ArrayList<Edge> graph[]=new ArrayList[v];
        ArrayList<Edge> grap = new ArrayList<>();

        createGraph(graph);
        createGraph2(grap);

        bellmanForm(graph,0);
        System.out.println();
        bellmanFord(grap,0,v);

    }


}
