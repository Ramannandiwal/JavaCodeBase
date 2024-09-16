import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class biPartitieGraph {
    static class Edge {
        int src;
        int dest;


        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;

        }
    }
    static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));


        graph[4].add(new Edge(4, 2 ));
        graph[4].add(new Edge(4, 3));



    }
    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1) {
                queue.add(i);
                color[i] = 0;
                while (!queue.isEmpty()) {
                    int current = queue.remove();
                    for (int j = 0; j < graph[current].size(); j++) {
                        Edge e = graph[current].get(j);
                        if (color[e.dest] == -1) {
                            int nextcolor = color[current] == 0 ? 1 : 0;
                            color[e.dest] = nextcolor;
                            queue.add(e.dest);
                        } else if (color[e.dest] == color[current]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean[] visited = new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!visited[i]){
                if(detectCycleUtil(graph,i,visited,-1)){
                    return true;
                }
            }
        }
        return false;
    }
private static boolean isbiPartitiusingcycle(ArrayList<Edge>[] graph){
        if(!detectCycle(graph)){
            return true;
        }else{

            int length=0;
            return length%2==0;
        }

}

//    private static int findlengthofTheCycle(ArrayList<Edge>[] graph) {
//        boolean [] visited = new boolean[graph.length];
//
//    }


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
        return false;



    }

//    private static boolean isBipartiteUtil(ArrayList<Edge>[] graph,int[] color) {
//        Queue<Integer> queue =new LinkedList<>();
//        queue.add(0);
//        //1--->yellow
//        //0--->empty
//        //2--->blue
//        color[0]=1;
//        while (!queue.isEmpty()){
//            int current=queue.remove();
//            int currentColor=color[current];
//            for(int  i =0;i<graph[current].size();i++){
//                Edge e = graph[current].get(i);
//
//                if(color[e.dest]==0){
//                    color[e.dest]=currentColor==1?2:1;
//                    queue.add(e.dest);
//                }
//               else if(color[e.dest]==color[current])return false;
//                else{
//                    continue;
//                }
//
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        int V=7;
        ArrayList<Edge>[] grap = new ArrayList[V];
        createGraph(grap);
        System.out.println(isBipartite(grap));
    }
}
