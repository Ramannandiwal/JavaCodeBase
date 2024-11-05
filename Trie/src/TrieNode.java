import java.lang.reflect.Array;
import java.util.*;

public class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean hasfinisehd ;
    class Pair{
        int x;
        int y ;
        public Pair(int x ,int y ){
            this.x = x;
            this.y = y;
        }

    }
    void dfs(int i ,HashMap<Integer, ArrayList<Integer>> adj,boolean[] visited){
        if(visited[i]){
            return;
        }
        visited[i]=true;
        for(int v:adj.get(i)){
            if(!visited[v]){
                dfs(v,adj,visited);
                visited[v]=true;
            }
        }
    }
    public long countPairs(int n, int[][] edges) {
        int count = 0;
        int total = n;
        int result = 0;
        HashMap<Integer,ArrayList<Integer>> adj = new HashMap<>();
        for(int i =0;i<n;i++){
            adj.put(i,new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];


            adj.get(u).add(v);


            adj.get(v).add(u);
        }
        boolean[] visited = new boolean[n];
        for(int i =0;i<n;i++){
            if(!visited[i]){
                dfs(i,adj,visited);
                count++;
            }
            result+= (count*(total-count));
            total-=count;


        }
        return result;
    }
}
