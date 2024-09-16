import java.util.*;

public class DSU {


    public int find(int i ,ArrayList<Integer> parent ){
        if(i ==parent.get(i)){
            return i;
        }
        return parent.set(i,find(parent.get(i),parent));
    }
    public void  Union(int x ,int y ,ArrayList<Integer> parent , ArrayList<Integer> rank ){

        int x_Parent = find(x,parent);
        int y_Parent = find(y,parent);
        if(x_Parent==y_Parent){
            return;
        }
        if(rank.get(x_Parent)>rank.get(y_Parent)){
            parent.set(y_Parent,x_Parent);
        }  else if(rank.get(x_Parent)<rank.get(y_Parent)){
            parent.set(x_Parent,y_Parent);
        }
        else{
            parent.set(x_Parent,y_Parent);
            rank.set(y_Parent,rank.get(y_Parent)+1);
        }
    }
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> parent = new ArrayList<>(V);
        ArrayList<Integer> rank = new ArrayList<>(V);
    for(int i =0;i<V;i++){
        parent.set(i,i);
    }

     for(int i=0;i<V;i++){
         for(int j:adj.get(i)){
             if(i<j){
                 int parent_i=find(i,parent);
                 int parent_j=find(j,parent);
                 if(parent_i==parent_j){
                     return true;
                 }
                 Union(i,j,parent,rank);
             }
         }
     }
     return false;
    }
    public int closestMeetingNode(int[] edges, int node1, int node2) {
    ArrayList<Integer> distance1 = new ArrayList<>(edges.length);
        for (int i = 0; i < edges.length; i++) {
            distance1.add(Integer.MAX_VALUE);
        }
    ArrayList<Integer> distance2 = new ArrayList<>(edges.length);
        for (int i = 0; i < edges.length; i++) {
            distance2.add(Integer.MAX_VALUE);
        }
        distance1.set(node1,0);
        distance2.set(node2,0);
    boolean[] visited1 = new boolean[edges.length];
        boolean[] visited2 = new boolean[edges.length];
        BFS(edges,visited1,distance1,node1);
        BFS(edges,visited2,distance2,node2);
    int minvalue = Integer.MAX_VALUE;
    int nodeindex=-1;
    for(int i =0;i<edges.length;i++){
        int maxD=Math.max(distance1.get(i),distance2.get(i));
        if (minvalue > maxD) {
            minvalue=maxD;
            nodeindex=i;
        }
    }
return nodeindex;
    }

    private void BFS(int[] edges, boolean[] visited, ArrayList<Integer> distance, int node1) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node1);
        visited[node1]=true;
        while (!queue.isEmpty()){
            int u = queue.poll();
            int v = edges[u];
            if(v!=-1&& !visited[v]){
                visited[v]=true;
                distance.set(v,distance.get(u)+1);
                queue.add(v);
            }

        }
    }

    private void DFSs(int[] edges, boolean[] visited, ArrayList<Integer> distance, int node) {
        visited[node]=true;
        int v = edges[node];
        if(v!=-1&&!visited[v]){
            visited[v]=true;
            distance.set(v,distance.get(node)+1);
            DFSs(edges,visited,distance,v);
        }
    }
     class DestinationWIthPrice{
        int destination ;
        int cost ;
        DestinationWIthPrice(int x , int y ){
            this.destination=x;
            this.cost=y;
        }
    }
//    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//        HashMap<Integer,ArrayList<DestinationWIthPrice>> adj = new HashMap<>();
//      for(int i=0;i<n;i++){
//          adj.put(i,new ArrayList<>());
//
//      }
//
//        for (int[] i : flights) {
//            int u = i[0];
//            int v = i[1];
//            int cost = i[2];
//
//            ArrayList<DestinationWIthPrice> list = adj.getOrDefault(u, new ArrayList<>());
//            list.add(new DestinationWIthPrice(v, cost));
//
//            adj.put(u, list);
//        }
//        Queue<Integer> queue = new LinkedList<>();
//        boolean[]visited = new boolean[n];
//        queue.add(src);
//        visited[src]=true;
//        int cost =0;
//        while (!queue.isEmpty()){
//            int nextstation = queue.poll();
//            visited[nextstation]=true;
//            if(nextstation==dst){
//                break;
//            }
//            int nextMin = Integer.MAX_VALUE;
//            boolean nextstationisdestiantinon=false;
//            for(DestinationWIthPrice p :adj.get(nextstation)){
//                int v = p.destination;
//                int flightcost= p.cost;
//                if(k>0){
//                    nextMin=Math.max(nextMin,v);
//
//                }else{
//                    if(v==dst){
//                        nextstationisdestiantinon=true;
//                    }
//                }
//
//            }
//            queue.add(nextMin);
//            visited[nextMin]=true;
//            cost+=
//        }
//return cost;
//    }
class Pair {
    int a;
    int b;
    int difference;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
        this.difference = Math.abs(a - b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return a == pair.a && b == pair.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}


public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((p, q) -> p.difference - q.difference);
    HashSet<Pair> set = new HashSet<>();

    for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            Pair pair = new Pair(nums[i], nums[j]);
            if (!set.contains(pair)) {
                set.add(pair);
                priorityQueue.add(pair);
            }
        }
    }

 for(int  i=0;i<k-1;i++){
     if(!priorityQueue.isEmpty()){
         priorityQueue.remove();
     }
 }
 return priorityQueue.poll().difference;
}

    public boolean lemonadeChange(int[] bills) {
          int count[]=new int[3];
          for(int i:bills){
              if(i==5){
                  count[0]++;
              }else if(i==10){
                  count[1]++;
              }else{
                  count[2]++;
              }
          }
         int frequnceyoffive=count[0];
          int frequnceyoften=count[1];
          int frequencyoftwenty=count[2];
          if(frequnceyoffive==0){
              return false;
          }
          if(frequencyoftwenty==0){
              if(frequnceyoffive>=frequnceyoften){
                  return true;
              }
          }
          if(frequnceyoffive*3>=frequencyoftwenty||(frequnceyoften>=))

    }
    public int maxDistance(List<List<Integer>> arrays) {
     int minnumber=Integer.MAX_VALUE;
     int skipindex=0;
    for(int i =0;i<arrays.size();i++){
        for(int j:arrays.get(i)){
           if(j<=minnumber){
               minnumber=j;
               skipindex=i;
           }
        }
    }
     int maxnumber=Integer.MIN_VALUE;
       for(int i =0;i<arrays.size();i++){
           if(i==skipindex)continue;
           for(int j:arrays.get(i)){
               maxnumber=Math.max(j,maxnumber);
           }
       }
        return Math.abs(maxnumber-minnumber);
    }
    public int nthUglyNumber(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int i2=1;
        int i3=1;
        int i5=1;

      list.set(1,1);
        for(int i =2;i<=n;i++){
            int i2ugly= list.get(i2);
            int i3ugly=list.get(i3)*3;
            int i5ugly=list.get(i5)*5;
            int minUgly=Math.min(Math.min(i2ugly,i3ugly),i5ugly);
            list.set(i,minUgly);

            if(minUgly==i2ugly){
                i2++;
            }
            if(minUgly==i3ugly){
                i3++;
            }
            if(minUgly==i5ugly){
                i5++;
            }
        }
        return list.get(n);
    }

    public int minSteps(int n) {
        if(n==1){
            return 0;
        }
        int dp[][]=new int[1001][1001];

       for(int i[]:dp){
           Arrays.fill(i,-1);
       }
        return 1+solve(1,1,n,dp);
    }

    private int solve(int currA, int clipBoardA, int n,int[][]dp) {
        if(currA==n ){
            return 0;
        }
        if(dp[currA][clipBoardA]!=-1){
            return dp[currA][clipBoardA];
        }
        if(currA>n){return 100000;}

        int copyandPaste=1+1+solve(currA*2,currA,n,dp);
        int paste=1+solve(currA+clipBoardA,clipBoardA,n,dp);
        return dp[currA][clipBoardA]= Math.min(copyandPaste,paste);
    }
