import java.util.ArrayList;
import java.util.PriorityQueue;

class Pair implements Comparable<Pair>{
    int n;
    int distance;
    Pair(int e ,int distance){
        this.n=e;
        this.distance=distance;
    }

    @Override
    public int compareTo(Pair o) {
        return this.distance-o.distance;
    }
}

public class Dijkastra {
    public static void creeateGraph(ArrayList<Edge>[] graph){
        for(int i =0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));
        graph[1].add(new Edge(1,3,7));
        graph[1].add(new Edge(1,2,1));
        graph[2].add(new Edge(1,4,3));
        graph[3].add(new Edge(3,5,1));
        graph[4].add(new Edge(0,3,2));
        graph[4].add(new Edge(0,5,5));
    }
    public static void dijkastra(ArrayList<Edge>[] graph,int src){
        int dist[]=new int[graph.length];
        for(int i=0;i<graph.length;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        boolean[] visited=new boolean[graph.length];
        PriorityQueue<Pair> queue=new PriorityQueue<>();
        queue.add(new Pair(src,0));
        while(!queue.isEmpty()){
            Pair cur=queue.remove();
            if(!visited[cur.n]){
                visited[cur.n]=true;
                for(int i =0;i<graph[cur.n].size();i++){
                    Edge e = graph[cur.n].get(i);
                    int u =e.src;
                    int v = e.dest;
                    int wt = e.weight;
                    if(dist[u]+wt<dist[v])
                    {
                    dist[v]=dist[u]+wt;
                    queue.add(new Pair(v,dist[v]));
                    }
                }
            }
        }
        for(int i =0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }

    public static void main(String[] args) {
        int v =6;
        ArrayList<Edge>[] graph=new ArrayList[v];
creeateGraph(graph);
 dijkastra(graph,0);
    }
}
