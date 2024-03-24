import java.util.*;

public class hasPath {
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

    public static boolean hasPath(ArrayList<Edge> graph[], int src, int dest, boolean vis[]){
        if(src ==dest){
            return true;
        }

        if(vis[src] == true){
            return false;
        }
        vis[src] = true;
        for(Edge e : graph[src]){
            if(hasPath(graph, e.dest, dest, vis)){
                return true;
            }
        }
        return false;

    }
    static ArrayList<ArrayList<String>> paths = new ArrayList<>();
    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest) {
        boolean[] visited = new boolean[graph.length];
        ArrayList<String> currentPath = new ArrayList<>();
        printAllPathsUtil(graph, src, dest, visited, currentPath);
    }

    private static void printAllPathsUtil(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, ArrayList<String> currentPath) {
        visited[src] = true;
        currentPath.add(String.valueOf(src));

        if (src == dest) {
            paths.add(new ArrayList<>(currentPath));
            // Print or process the current path here if needed
        } else {
            for (Edge e : graph[src]) {
                if (!visited[e.dest]) {
                    printAllPathsUtil(graph, e.dest, dest, visited, currentPath);
                }
            }
        }

        // Backtrack
        visited[src] = false;
        currentPath.remove(currentPath.size() - 1);
    }

    // TC = O(v+e)
    public static void main(String args[]){
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        printAllPaths(graph,0,6);
        System.out.println(paths);

    }

}