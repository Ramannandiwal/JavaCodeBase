import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {
    static class Edge {
        int src;
        int dest;


        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;

        }

    }
    public static  void topSort(ArrayList<Edge>[] graph){
        boolean visited[] = new boolean[graph.length];
        Stack<Integer> stack = new Stack<>();
        for(int i =0;i<graph.length;i++){
            if(!visited[i]){
                topsort(graph,visited,stack ,i);
            }
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop() +" ");
        }
    }

    private static void topsort(ArrayList<Edge>[] graph, boolean[] visited, Stack<Integer> stack,int current) {
        visited[current]=true;
        for(int i =0;i<graph[current].size();i++){
            Edge e = graph[current].get(i);
            if(!visited[e.dest]){
                topsort(graph,visited,stack,e.dest);

            }

        }
        stack.push(current);
    }
    
    static void createGraph(ArrayList<Edge>graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }



        graph[2].add(new Edge(2, 3));


        graph[3].add(new Edge(3, 1));



        graph[4].add(new Edge(4, 0 ));
        graph[4].add(new Edge(4, 1));

        graph[4].add(new Edge(5, 0 ));
        graph[4].add(new Edge(5, 2));



    }

    public static void main(String[] args) {
        int V=6;
        ArrayList<Edge> graph[] =new ArrayList[V];
        createGraph(graph);
        topSort(graph);
    }
}
