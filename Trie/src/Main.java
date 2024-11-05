import java.security.Key;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
 public int find(int x , int[] parent){
     if(parent[x]!=x){
         parent[x]=find(parent[x],parent);
     }
     return parent[x];
 }
 public void Union(int x ,int y  ,int[] parent , int[]rank ){

     if(parent[x]==parent[y]){
         return;
     }
     int rootx = parent[x]  ;
     int rooty = parent[y];
     if(rank[rootx]>rank[rooty]){
         parent[rooty]=rootx;
     }
     else if (rank[rooty]>rank[rootx]){
         parent[rootx]=rooty;
     }
     else{
         parent[rooty]=rootx;
         rank[rootx]++;
     }

 }
    public long countPairs(int n, int[][] edges) {

     int parent[] = new int[n];
     int rank[]  = new int[n];
     for(int i =0;i<n;i++){
         parent[i]=i;
         rank[i]=0;
     }
     for(int[] a : edges){
         int parent_x=find(a[0],parent  );
         int parent_y = find(a[1],parent);
         if(parent_x!=parent_y){
             Union(a[0],a[1],parent,rank);
         }
     }
        HashMap<Integer,Integer> map = new HashMap<>();
     for(int i =0;i<n;i++){
         map.put(parent[i],map.getOrDefault(parent[i],0)+1);
     }
     int remaining = n;
     int result = 0;
    Set<Integer> set = map.keySet();
    for(int a :set){
        int count  = map.get(a);
        result+= count*(remaining-count);
        remaining-=count;
    }
    return result;
    }
    static class Pair {
        int Node;
        int weight;

        Pair(int Node, int weight) {
            this.Node = Node;
            this.weight = weight;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            Pair22 other = (Pair22) obj;
            return this.Node == other.x;
        }


        @Override
        public int hashCode() {
            return Integer.hashCode(Node); // Use Node's hash code
        }
    }

    int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        TreeSet<Pair22> set = new TreeSet<>((a, b) -> {
            if (a.x < b.y) {
                return -1; // Prioritize lower weight
            } else if (a.x > b.y) {
                return 1;  // Higher weight comes later
            } else {
                return Integer.compare(a.x, b.y); // Break ties by Node value
            }
        });

        set.add(new Pair22(S,0));
        int[] result = new int[V];
        result[S]=0;
        while (!set.isEmpty()){
         Pair22 p =    set.pollFirst();
         int Node_value = p.x;
         int distance = p.y;
         for(ArrayList<Integer> list :adj.get(Node_value)){
             int Neighbour = list.get(0);
             int weightofNeigbhour = list.get(1);
             if(distance+weightofNeigbhour<result[Neighbour]){
                 Pair22 l = new Pair22(Neighbour,result[Neighbour]);
                 if(set.contains(l)){
                     set.remove(l);
                 }
                 result[Neighbour]=distance+weightofNeigbhour;
                 set.add(new Pair22(Neighbour,result[Neighbour]));
             }
         }


        }
             return  result;

    }
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        // Initialize each node's adjacency list
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>()); // Each node has a list of {target, weight} pairs
        }

        // Fill the adjacency list using the times array
        for (int[] time : times) {
            int u = time[0] - 1;
            int v = time[1] - 1;
            int weight = time[2];


            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(v);
            edge.add(weight);
            adj.get(u).add(edge);
        }
        int [] result = dijkstra(n,adj,k);
        int Max_element  = result[0] ;
        for(int i :result){
            Max_element=Math.max(Max_element,i);
        }
        return Max_element;
    }
//    class Pair{
//     int node;
//     int weight;
//     public Pair(int Node, int Weight){
//         this.weight=Weight;
//         this.node=Node;
//     }
//     @Override
//     public boolean equals(Object obj){
//         if(this == obj){
//             return true;
//         }
//         if(obj==null || this.getClass() !=obj.getClass()){
//             return false;
//         }
//         Pair other = (Pair) obj;
//         return this.node==other.node;
//     }
//     @Override
//     public int hashCode(){
//         return Integer.hashCode(this.node);
//     }
//}
//    public List<Integer> shortestPath(int n, int m, int edges[][]) {
//     List<Integer> result = new ArrayList<>();
//     HashMap<Integer, ArrayList<Pair>> adj = new HashMap<>();
//     for(int i =0;i<=n;i++){
//         adj.put(i,new ArrayList<>());
//     }
//     for(int [] a :edges){
//         int u = a[0];
//         int v = a[1];
//         int weight = a[2];
//         adj.get(u).add(new Pair(v,weight));
//         adj.get(v).add(new Pair(u,weight));
//     }
//     TreeSet<Pair> set = new TreeSet<>((a,b)->{
//         if(a.weight<b.weight)return  -1;
//         else if(b.weight<a.weight) return 1;
//       else return Integer.compare(a.node,b.node);
//     });
//     int[] parent = new int[n+1];
//     int[] distance = new int[n+1];
//     Arrays.fill(distance,Integer.MAX_VALUE);
//
//     set.add(new Pair(1,0));
//     distance[1]=0;
//     parent[1] = 1;
//     while (!set.isEmpty()){
//         Pair p = set.pollFirst();
//         int Node_value = p.node;
//         int distanceofcurrent = p.weight;
//         for(Pair pair :adj.get(Node_value)){
//             int Neighbour = pair.node;
//             int weightofNeigbhour = pair.weight;
//             if(distanceofcurrent+weightofNeigbhour<distance[Neighbour]){
//                 Pair l = new Pair(Neighbour,distance[Neighbour]);
//                 if(set.contains(l)){
//                     set.remove(l);
//                 }
//                 distance[Neighbour]=distanceofcurrent+weightofNeigbhour;
//                 set.add(new Pair(Neighbour,distance[Neighbour]));
//                 parent[Neighbour]=Node_value;
//             }
//         }
//     }
//     while (parent[n]!=1){
//           result.add(parent[n]);
//           n=parent[n];
//     }
//     return result;
//
//    }
public int minimumEffortPath(int[][] heights) {
    PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
        if(a[0]<b[0]) return -1;
        else if (a[0]>b[0]) return 1;
        else if(a[1]<b[1]) return -1;
        else return Integer.compare(a[2],b[2]);

    });
    int directions[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    queue.add(new int[]{0,0,0});
    while (!queue.isEmpty()){
        int p[] = queue.poll();
        int pathlength = p[0];
        int row = p[1];
        int col = p[2];
        if (row == heights.length - 1 && col == heights[0].length) {
            return pathlength;
        }
        for(int[] direction:directions ){

            int newRow = direction[1]+row;
            int newCol = direction[2]+col;
            if (newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length && heights[newRow][newCol] != -1) {
                queue.add(new int[]{Math.abs(heights[newRow][newCol]-heights[row][col]),newRow,newCol});
                heights[newRow][newCol] = -1;
            }
        }

    }
    return -1;
}

    class AllOne {
HashMap<String,Integer> map;
        public AllOne() {
           map = new HashMap<>();
        }

        public void inc(String key) {
          if(map.containsKey(key)){
              map.put(key,map.getOrDefault(key,0)+1);
          }else{
              map.put(key,1);
          }
        }

        public void dec(String key) {
            map.put(key,map.getOrDefault(key,0)-1);
            if(map.get(key)==0){
                map.remove(key);
            }
        }

        public String getMaxKey() {
         int max = Integer.MIN_VALUE;
         Set<String> set = map.keySet();
         String result="";
         for(String c :set){
             int count = map.get(c);
             if(count>=max){
                 max=count;
                 result=c;
             }
         }
         return result;
        }

        public String getMinKey() {
            int min = Integer.MAX_VALUE;
            Set<String> set = map.keySet();
            String result="";
            for(String c :set){
                int count = map.get(c);
                if(count<=min){
                    min=count;
                    result=c;
                }
            }
            return result;
        }
    }
  static   List<String> result = new ArrayList<>();
    public static void permutation(String p, String up){
     if(up.isEmpty()){
         result.add(p);
         return;
     }
     char c = up.charAt(0);
     for(int i =0;i<=p.length();i++){
         String a = p.substring(0,i);
         String b = p.substring(i);
         permutation(a+c+b,up.substring(1));
     }

    }
    public boolean checkInclusion(String s1, String s2) {
     permutation("",s1);
     for(int i =0;i<result.size();i++){
         String temp = result.get(i);
       if(s2.contains(temp)){
           return true;
       }
     }
     return false;
    }

    public static boolean areSentencesSimilar(String sentence1, String sentence2) {

     if(sentence1.length()<sentence2.length()){
         String temp = sentence1;
         sentence1=sentence2;
         sentence2=temp;
     }
        Deque<String> deque1 = new ArrayDeque<>();
        Deque<String> deque2 = new ArrayDeque<>();
        Collections.addAll(deque1, sentence1.split(" "));
        Collections.addAll(deque2,sentence2.split(" "));
        while (!deque2.isEmpty() && !deque1.isEmpty() && deque1.pollFirst().equals(deque2.pollFirst())){
            deque1.removeFirst();
            deque2.removeFirst();

        }
        while (!deque2.isEmpty() && !deque1.isEmpty() && deque1.pollLast().equals(deque2.pollLast())){
            deque1.removeLast();
            deque2.removeLast();

        }

return deque2.isEmpty();
    }

    public int threeSumClosest(int[] nums, int target) {
        int Minsum =Integer.MAX_VALUE;
    for(int i =0;i<nums.length-2;i++){
        for(int j =i+1;j<nums.length-1;j++){
            int sum =0;
            for(int k = j+1;k<nums.length;k++){
                sum+=nums[k];
            }
            Minsum=Math.min(Minsum,Math.abs(target-sum ));


        }
    }
    return target-Minsum;
    }



    public int minLength(String s) {
        for(int i =0;i<s.length();i++){
            for(int j =i+1;j<s.length();j++){
                if((s.charAt(i)=='A'&&s.charAt(j)=='B')||s.charAt(i)=='C'&&s.charAt(j)=='D'){
                    String temp =s.substring(0,i)+s.substring(i+1);
                    s= new String(temp);

                }
                else{
                    break;
                }
            }
        }
        return s.length();
    }
    public void swap(int[]arr, int i , int j ){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
        return;
    }
    public void reverse(int[]nums, int start , int end){
        while (start<=end){
            int temp = nums[start];
            nums[start]=nums[end];
            nums[end]=temp;
            start++;
            end--;
        }
    }
    public void nextPermutation(int[] nums) {
      int n = nums.length;
      int pivot = -1;
      for(int i =n-1;i>0;i--){
          if(nums[i-1]<nums[i]){
              pivot=i-1;
              break;
          }
      }
    if(pivot!=-1){
        int swapIndex=pivot;
        for(int i =n-1;i>=pivot+1;i--){
            if(nums[i]>nums[pivot]){
                swapIndex=i;
                break;
            }
        }
        swap(nums,swapIndex,pivot);
    }

      reverse(nums,pivot+1,nums.length-1);



    }


    public void rotate(int[][] matrix) {
      ///transpose of the matrix
        int n = matrix.length;
        for(int i =0;i<n;i++){
            for(int j =i+1;j<n;j++){
                int temp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
        for(int i=0;i<n;i++){
            for(int j =0;j<n/2;j++){
                int temp = matrix[i][j];
                matrix[i][j]=matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j]=temp;
            }
        }
    }
    public List<List<String>> groupAnagrams(String[] strs) {
       HashMap<String ,List<String>> map = new HashMap<>();
       for(String s  : strs){
          char[] arr = s.toCharArray();
          Arrays.sort(arr);
          String temp = new String(arr);
         if(!map.containsKey(temp)){
             map.put(temp,new ArrayList<>());

         }
         map.get(temp).add(s);
       }
return new ArrayList<>(map.values());
    }
//    public int[][] diagonalSort(int[][] mat) {
//      int m = mat.length;
//      int n = mat[0].length;
//      int result[][] = new int[m][n];
//
//      for(int k = 0;k<m;k++){
//          int i =k;
//          int j = 0;
//          ArrayList<Integer> list = new ArrayList<>();
//          while (i<m&&j<n){
//              list.add(mat[i][j]);
//              i++;
//              j++;
//          }
//          list.sort(Integer::compare);
//          i=k;
//          j=0;
//          int index=0;
//          while (i<m&&j<n){
//             result[i][j]=list.get(index++);
//             i++;
//             j++;
//          }
//
//
//      }
//      for(int k = 1;k<n;k++){
//          int i =0;
//          int j = k;
//          ArrayList<Integer> list = new ArrayList<>();
//          while (i<m&&j<n){
//              list.add(mat[i][j]);
//              i++;
//              j++;
//          }
//          list.sort(Integer::compare);
//          i=0;
//          j=k;
//          int index=0;
//          while (i<m&&j<n){
//              result[i][j]=list.get(index++);
//              i++;
//              j++;
//          }
//      }
//      return result;
//    }
public int[][] diagonalSort(int[][] mat) {
        HashMap<Integer,PriorityQueue<Integer>> map = new HashMap<>();

 int m = mat.length;
 int n = mat[0].length;
 int[][] result = new int[m][n];
 for(int i=0;i<m;i++){
     for(int j = 0;j<n;j++){
         int diff = i-j;
         if(!map.containsKey(diff)){
             map.put(diff,new PriorityQueue<>());
         }
         map.get(diff).add(mat[i][j]);
     }
 }
for(int i=0;i<m;i++){
    for(int j =0;j<n;j++){
        int diff= i-j;
        result[i][j]= map.get(diff).peek();
        map.get(diff).poll();
    }
}
return result;
}
    public int[] findDiagonalOrder(int[][] mat) {
     HashMap<Integer,ArrayList<Integer>> map= new HashMap<>();
     int m = mat.length;
     int n = mat[0].length;
     for(int i =0;i<m ;i++){
         for(int j =0;j<n;j++){
             map.putIfAbsent(i+j,new ArrayList<>());
             map.get(i+j).add(mat[i][j]);
         }
     }
     ArrayList<Integer> list = new ArrayList<>();
     boolean flip = false;
        for(int key :map.keySet()){
            if(flip){
               List<Integer> temp = map.get(key);
               Collections.reverse(temp);
                list.addAll(temp);
            }else{
                list.addAll(map.get(key));
            }
            flip=!flip;
        }
        int[] result = new int[list.size()];
        int index=0;
        for(int i :list){
            result[index++]=i;
        }
        return result;
    }
    public void sortColors(int[] nums) {
      HashMap<Integer,Integer> map = new HashMap<>();
      for(int i: nums){
          map.put(i,map.getOrDefault(i,0)+1);
      }
      int index=0;
      for(int key:map.keySet()){
          for(int j = 0;j<map.get(key);j++){
              nums[index++]=key;
          }
      }

    }
    public int[] findOriginalArray(int[] changed) {
       Set<Integer> set = new HashSet<>();
       for(int i: changed){
           set.add(i)   ;

       }
       List<Integer> list = new ArrayList<>();
       for(int i :set){
           if(set.contains(2*i)){
               list.add(i);

           }
       }
       if(list.size()!=changed.length/2){
           return  new int[]{};
       }
       int index=0;
       int result[] = new int[list.size()];
       for(int i : list){
           result[index++]=i;
       }
       return result;
    }

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int []result = new int[queries.length];
        int sumofEven = 0;
        for(int i :nums){
            if(i%2==0){
                sumofEven+=i;
            }
        }
        int in=0;
      for(int a[] : queries){
          int value = a[0];
          int index = a[1];
          if(nums[index]%2==0){
              sumofEven-=nums[index];
          }
         nums[index]+=value;
          if(nums[index]%2==0){
              sumofEven+=nums[index];

          }
          result[in++]=sumofEven;
      }
      return result;
    }

    public boolean increasingTriplet(int[] nums) {
          int num1= Integer.MAX_VALUE;
          int num2 = Integer.MAX_VALUE;

        for(int i =0;i<nums.length;i++){
            int num3 = nums[i];
            if(num3<=num1){
                num1=num3;
            }
            else if(num3<=num2){
                num2=num3;
            }else{
                return true;
            }
        }
return false;
    }
    public int largestPerimeter(int[] nums) {
     int result = Integer.MIN_VALUE;
     int n = nums.length;
     for(int i = 0;i<n-2;i++){
         for(int j =i;j<n-1;j++){
             for(int k = j;k<n;k++){
                 if((nums[i]+nums[j]>nums[k])||(nums[j]+nums[k]>nums[i])|| (nums[i]+nums[k]>nums[j])){
                     result=Math.max(result,nums[i]+nums[j]+nums[k]);
                 }
             }
         }
     }
     return result==Integer.MIN_VALUE ? 0 :result;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
       HashMap<Integer,Integer> map = new HashMap<>();
       for(int i =0;i<n;i++){
           if(map.containsKey(nums[i])){
               int index= map.get(nums[i]);
                if(i-index<=k){
                    return true;
                }
           }
               map.put(nums[i],i);

       }
       return false;
    }
    public boolean checkSubarraySum(int[] nums, int k) {
    int n = nums.length;
    HashMap<Integer,Integer> map = new HashMap<>();
    int sum = 0;
    for(int i =0;i<nums.length;i++){
        sum+=nums[i];
        if(map.containsKey(sum%k)){
            int index = map.get(sum%k);
            if(i-index>=2){
                return true;
            }
        }
        map.put(sum%k,i);
    }
       return false;
    }
    class Pair1{
        int x ;
        int y ;
        Pair1(int x  , int y ){
            this.x = x;
            this.y = y;

        }
    }
    public int shortestPath(int[][] grid, int k) {
        int[][] direcitons={{0,1},{1,0},{-1,0},{0,-1}};
        int m = grid.length;
        int n = grid[0].length;
        int path = 0;
        Queue<Pair1> queue = new ArrayDeque<>();
        queue.add(new Pair1(0,0));
        while (!queue.isEmpty()){
            Pair1 p = queue.poll();
            int x = p.x;
            int y = p.y;
            grid[x][y]=-1;
            for(int [] direction :direcitons){
                int new_x = x+direction[0];
                int new_y = y+direction[1];
                if(new_x == m-1 && new_y == n-1 && k>=0){
                    return path;
                }
                if(new_x<0 ||new_x>=m|| new_y<0||new_y>=n){
                   continue;
                }
                if(k>0){
                    path++;
                    queue.add(new Pair1(new_x,new_y));
                }
            }
        }
        return -1;
    }

    class Pair2{
        int arrivalTime ;
        int chairNumber;
        Pair2(int arrivalTime, int chairNumber){
            this.arrivalTime=arrivalTime;
            this.chairNumber=chairNumber;
        }
    }

    public int smallestChair(int[][] times, int targetFriend) {

        PriorityQueue<Pair2> occupied = new PriorityQueue<>((a,b)->Integer.compare(a.arrivalTime,b.arrivalTime));
        PriorityQueue<Integer> freeChair = new PriorityQueue<>();
        int chairNumbervariable = 0;
        int targetArrivalTimne = times[targetFriend][0];
        Arrays.sort(times,(a,b)->Integer.compare(a[0],b[0]));

        for(int i = 0;i<times.length;i++){
            int arri = times[i][0];
            int departure = times[i][1];
            while (!occupied.isEmpty() && occupied.peek().arrivalTime<=arri){
                int cn = occupied.peek().chairNumber;
                occupied.poll();
                freeChair.add(cn);
            }
            if(freeChair.isEmpty()){
                occupied.add(new Pair2(departure,chairNumbervariable));
                if(arri==targetArrivalTimne){
                    return chairNumbervariable;
                }
                chairNumbervariable++;
            }else{
                int least = freeChair.poll();

                if(arri==targetArrivalTimne){
                    return least;
                }

                occupied.add(new Pair2(departure,least));
            }
        }

return -1;
    }
    public static void rotate(int[] arr){
        int temp = arr[0];
        int n = arr.length;
        for(int i =0;i<n-1;i++){
            arr[i]=arr[i+1];
        }
        arr[arr.length-1]=temp;

    }
    public int kadanes(int[] arr) {
        int current = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            current += arr[i];


            maxSum = Math.max(current, maxSum);


            if (current < 0) {
                current = 0;
            }
        }
        return maxSum;
    }
    public  static int minSubarraySum(int[] arr) {
        int current = 0; // Tracks the current sum of the subarray
        int minSum = Integer.MAX_VALUE; // Tracks the minimum sum encountered

        for (int i = 0; i < arr.length; i++) {
            current += arr[i];

            // If current sum is smaller than minSum, update minSum
            minSum = Math.min(current, minSum);

            // If current sum becomes positive, reset it to 0
            if (current > 0) {
                current = 0;
            }
        }
        return minSum;
    }

    public int maxSubarraySumCircular(int[] nums) {
     int sum = Integer.MIN_VALUE;
     for(int i =0;i<nums.length-1;i++){
         sum  = Math.max(sum,kadanes(nums));
         rotate(nums);
     }
        HashMap<Integer,Integer> map = new HashMap<>();
     return sum;

    }

    public String longestDiverseString(int a, int b, int c) {
     StringBuilder string  = new StringBuilder("");
     while(a>0 || b>0 || c>0){
         int len =string.length();
         if(len>=2 && string.charAt(len-1)=='a'&& string.charAt(len-2)=='a'){
             if(b>=c && b>0){
                 string.append("b");
                 b--;
             }
             else if(c>=b && c>0){
                 string.append("c");
                 c--;
             }else{
                 break;
             }
         }
         else if (len>=2 && string.charAt(len-1)=='b'&& string.charAt(len-2)=='b'){
             if(a>=c && a>0){
                 string.append("a");
                 a--;
             }
             else if(c>=a && c>0){
                 string.append("c");
                 c--;
             }else{

                 break;
             }
         }
         else   if(len>=2 && string.charAt(len-1)=='c'&& string.charAt(len-2)=='c'){
             if(b>=a && b>0){
                 string.append("b");
                 b--;
             }
             else if(a>=b && a>0){
                 string.append("a");
                 a--;
             }else{
                 break;
             }
         }
         else{
             if(a>=b && a>=c && a>0){
                 string.append("a");
                 a--;
             }
             else if(b>=c && b>=a && b>0){
                 string.append(b);
                 b--;
             }
             else if( c>0){
                 string.append("c");
                 c--;

             }else{
                 break;
             }
         }
     }
     return string.toString();
    }

    public int minJumps(int[] arr) {

        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int i =0;i<arr.length;i++){
           if(!map.containsKey(arr[i])){
               map.put(arr[i],new ArrayList<>());
           }
            map.get(arr[i]).add(i);
        }
        boolean[] visited = new boolean[arr.length];
        queue.add(0);
        visited[0]=true;
        int step = 0;
        while (!queue.isEmpty()){
           int n = queue.size();
           while (n-- > 0){
               int index =queue.poll();

               int left = index-1;
               int right = index+1;
               if(index==n-1){
                   return step;
               }
               if(left>=0 && !visited[left]){
                   visited[left]=true;
                   queue.add(left);
               }
               if(right<=n-1 && !visited[right]){
                   visited[right]=true;
                   queue.add(right);
               }
               for(int i : map.get(arr[index])){
                   if(!visited[i]){
                       visited[i]=true;
                       queue.add(i);
                   }
               }
              map.get(arr[index]).clear();

           }
           step++;
        }
        return -1;
    }
    public long zeroFilledSubarray(int[] nums) {
       int n = nums.length;
       int result = 0;
       for(int i =0;i<n;i++){
           if(nums[i]==0){
               int j = i+1;
               while (j<n&& nums[j]==0){
                   j++;
               }
               int length = j-i+1;
               int subarray = length* (length+1)/2;
               result+=subarray;
               i=j;
           }
       }
       return result;
    }
    public void dfs(int[][] grid , int row, int col){

        if(row<0|| row>=grid.length||col<0||col>=grid[0].length ||grid[row][col]==0){
            return;
        }
       grid[row][col]=0;
        dfs(grid,row+1,col);
        dfs(grid,row-1,col);
        dfs(grid,row,col+1);
        dfs(grid,row,col-1);



    }
    public int numEnclaves(int[][] grid) {
     int result = 0;
     int m = grid.length;
     int n = grid[0].length;
 for(int i =0;i<m;i++){
     if(grid[0][i]==1){
         dfs(grid,0,i);
     }
     if(grid[m-1][i]==1){
         dfs(grid,m-1,i);
     }
 }
        for(int i =0;i<n;i++){
            if(grid[i][0]==1){
                dfs(grid,i,0);
            }
            if(grid[i][n-1]==1){
                dfs(grid,i,n-1);
            }
        }
        for(int i =0;i<m;i++){
            for(int j =0;j<n;j++){
                if(grid[i][j]==1)result++;
            }
        }
     return result;
    }
//    public int closedIsland(int[][] grid) {
//        boolean[][] visited = new boolean[grid.length][grid[0].length];
//        int result = 0;
//
//        for(int i =0;i<grid.length;i++){
//            for(int j =0;j<grid.length;j++){
//                if(grid[i][j]==0){
//                    if(dfs(grid, i, j)){
//                        result++;
//                    }
//
//                }
//            }
//        }
//        return  result;
//    }

    public char findKthBit(int n, int k) {

        if(n==1)return 0;
        int length = (1<<n) -1 ;
        if(k<Math.ceil(length/2.0)){
           return findKthBit(n-1,k);
        }
        else if(k==Math.ceil(length/2.0)){
            return '1';
        }
        else{
            char c = findKthBit(n-1,length-k+1);
            return c=='0'? '1':'0';
        }

    }
    public class TreeNode { int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
        public long kthLargestLevelSum(TreeNode root, int k) {
          Queue<TreeNode> queue = new LinkedList<>();
          PriorityQueue<Integer> priorityQueue  = new PriorityQueue<>((a,b)->Integer.compare(b,a));
          if(root==null){
              return -1;
          }
          queue.add(root);
          while (!queue.isEmpty()){
              int n = queue.size();
              int sum = 0;
              while (n-- > 0 ){
                  if(!queue.isEmpty()){
                     TreeNode temp = queue.poll();
                       if(temp!=null){
                           sum+=temp.val;
                           if(temp.left!=null){
                               queue.add(temp.left);
                           }
                           if(temp.right!=null){
                               queue.add(temp.right);
                           }
                       }
                  }

                  }
              priorityQueue.add(sum);
              }
            while (k -- >0){
             if(!priorityQueue.isEmpty()){
                 priorityQueue.poll();
             }
            }
            return  priorityQueue.isEmpty() ? -1 : priorityQueue.poll();
          }


        }
    public TreeNode replaceValueInTree(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int n = queue.size();
            int sum = 0;
            while (n-- > 0 ){
                if(!queue.isEmpty()){
                    TreeNode temp = queue.poll();
                    if(temp!=null){
                        sum+=temp.val;
                        if(temp.left!=null){
                            queue.add(temp.left);
                        }
                        if(temp.right!=null){
                            queue.add(temp.right);
                        }
                    }
                }

            }
          list.add(sum);
        }
         solve(root,list,0);
        return root;


    }
    public void  solve(TreeNode root , ArrayList<Integer> list ,int i ){
        if(root==null){
            return;
        }
        int leftVal = root.left==null ? 0 :root.left.val;
        int rightVal = root.right==null ? 0 :root.right.val;

        if(root.left!=null){
            root.left.val=list.get(i)-(leftVal+rightVal);
        }
        if(root.right!=null){
            root.right.val=list.get(i)-(leftVal+rightVal);
        }
        solve(root.left,list,i);
        solve(root.right,list,i);


    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        ArrayList<ArrayList<Integer>> list1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
        queue1.add(root1);
        queue2.add(root2);
        while(!queue1.isEmpty()) {
            int n = queue1.size();
            ArrayList<Integer> templist1 = new ArrayList<>();
            ArrayList<Integer> templist2 = new ArrayList<>();
            while (n-- > 0) {
                TreeNode temp1 = queue1.poll();
                TreeNode temp2 = queue2.poll();
                if (temp1 != null) {
                    templist1.add(temp1.val);
                    if (temp1.left != null) {
                        queue1.add(temp1.left);
                    }

                    if (temp1.right != null) {
                        queue1.add(temp1.right);
                    }

                }
                if (temp2 != null) {
                    templist2.add(temp2.val);

                    if (temp2.left != null) {
                        queue2.add(temp2.left);
                    }

                    if (temp2.right != null) {
                        queue2.add(temp2.right);

                    }


                }


            }
            list1.add(templist1);
            list2.add(templist2);

        }
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list1.get(i).size(); j++) {

            }
        }
        return true;
    }
    public List<String> removeSubfolders(String[] folder) {
         List<String> result = new ArrayList<>();
         Arrays.sort(folder);
         result.add(folder[0]);
         for(int i =1;i<folder.length;i++){
             String Current = folder[i];
             String last = result.getLast();
             last+="/";
             if(!Current.contains(last)){
                 result.add(Current);
             }
         }
         return result;
    }
    public int subarray(int[] arr){
        int length = 0;
        for(int i =1;i<arr.length-1;i++){
            if((int)Math.pow(arr[i],2)!=arr[i+1]){
                return 0;
            }
        }
        return arr.length;
    }
    int maxLenght = 0;
    public int longestSquareStreak(int[] nums) {

        solveSubsequeunce(nums,0);
        return maxLenght;


    }

    private int[] solveSubsequeunce(int[] nums, int index) {
        if(index==nums.length){
            return  new int[]{};
        }
       return  new int[]{};
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->Integer.compare(b,a));
        for(int i : nums){
            queue.add(i);
            while(queue.size()>k){
                queue.remove();
            }
        }
        return queue.remove();
    }
    class Pair22 {
        int x ;
        int y ;
        Pair22(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj){
            if(this ==obj)return true;
            if(obj == null || this.getClass() != obj.getClass()){
                return false;
            }
            Pair22 p = (Pair22)obj;
            return (p.x==this.x && p.y == this.y);


        }

        @Override
        public int hashCode() {
            return Integer.hashCode(this.x+this.y); // Use Node's hash code
        }

    }
    public int bfs(int[][] mat,int i , int j ){
        int[][] directions={{0,1},{1,0},{0,-1},{-1,0}};
        Queue<Pair22> queue = new LinkedList<>();
        queue.add(new Pair22(i,j));
        Set<Pair22> visited = new HashSet<>();
        visited.add(new Pair22(i,j));
        int level = 0;
        while (!queue.isEmpty()){
            int n = queue.size();
            while (n -- >0){
                Pair22 p = queue.remove();
                if(mat[p.x][p.y]==0){
                    return level;
                }
                for(int[] direction:directions){
                    int new_x = direction[0]+p.x;
                    int new_y = direction[1]+p.y;
                    if(new_x >=mat.length || new_x<0 || new_y>=mat[0].length||new_y<0){
                        continue;
                    }
                    if(visited.contains(new Pair22(new_x,new_y))){
                        continue;
                    }
                    visited.add(new Pair22(new_x,new_y));
                    queue.add(new Pair22(new_x,new_y));
                }
            }
            level++;
        }
        return -1;
    }
    public int[][] updateMatrix(int[][] mat) {
      int[][] result = new int[mat.length][mat[0].length];
      for(int i =0;i<mat.length;i++){
          for(int j = 0;j<mat[0].length;j++){
              result[i][j]=bfs(mat,i,j);
          }
      }
      return result;
    }

    public int bestClosingTime(String customers) {
     int minHour = 0;
     int penalty =(int) customers.chars().filter(ch -> ch == 'Y').count();
     int minPenalty = penalty;
     for(int i =0;i<customers.length();i++){
         if(customers.charAt(i)=='Y'){
             penalty--;
         }else{
             penalty++;
         }
         if(penalty<minPenalty){
             minPenalty=penalty;
             minHour=i+1;
         }
     }
     return minHour;

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
         result.add(new ArrayList<>());
         result.getFirst().add(1);
        for(int i = 1;i<numRows;i++){
            result.add(new ArrayList<>(i+1));
            result.get(i).add(1);
            for(int j =1;j<result.get(i).size()-1;j++){
                result.get(i).add(result.get(i-1).get(j)+result.get(i-1).get(j-1));
            }


        }
        return result;
    }
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
      int n = groupSizes.length;
      List<Integer>[] map = new List[n+1];
      List<List<Integer>> result = new ArrayList<>();
      for (int i = 0;i<groupSizes.length;i++){
          if(map[groupSizes[i]]==null){
            map[groupSizes[i]]=new ArrayList<>();
          }
          map[groupSizes[i]].add(i);
          if(map[groupSizes[i]].size()==groupSizes[i]){
              result.add(map[groupSizes[i]]);
              map[groupSizes[i]]=null;
          }
      }
      return result;
    }

    public int binarySearch(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int result = arr.length;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == 1) {
                start = mid + 1;
            } else {
                result = mid;
                end = mid - 1;
            }
        }
        return result;
    }

    public int[] kWeakestRows(int[][] mat, int k) {
      PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->Integer.compare(b[0],a[0]));
        for (int i = 0; i < mat.length; i++) {
            int numberofOnes = binarySearch(mat[i]);
             queue.add(new int[]{numberofOnes,i});
            if (queue.size() > k) {
                queue.remove();
            }
        }


        int[] result = new int[k];
       int j = k-1;
       while (!queue.isEmpty()){
           result[j--]=queue.remove()[2];
       }
        return result;
    }
    public int findDuplicate(int[] nums) {
      int slow = 0;
      int fast = 0;
      while (true){
          slow = nums[slow];
          fast = nums[nums[fast]];
          if(slow==fast){
              return nums[slow];
          }

      }

    }



    public int minOperations(int[] nums, int x) {
       HashMap<Integer,Integer> map = new HashMap<>();
       map.put(0,-1);
       int sum = 0;
       for(int i =0;i<nums.length;i++){
          sum+=nums[i];
          map.put(sum,i);
       }
       int maxLength = Integer.MIN_VALUE;
       int target = sum-x;
       sum  =0;
       for(int i =0;i<nums.length;i++){
           sum+=nums[i];
            if(map.containsKey(sum-target)){
                int index = map.get(sum-target);
                maxLength=Math.max(maxLength,i-index);
            }
       }
       return maxLength == Integer.MIN_VALUE ? -1 :nums.length-maxLength;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
      if(nums1.length>nums2.length){
       return    findMedianSortedArrays(nums2,nums1);
      }
      int m = nums1.length;
      int n = nums2.length;
      int l = 0;
      int r = m;
      while (l<=r){
          int px = l+(r-l)/2;
          int py = (m+n+1)/2-px;

          int x1= px==0 ? Integer.MIN_VALUE : nums1[px-1] ;

          int x2 =py==0 ? Integer.MIN_VALUE : nums2[py-1];

          int x3 = px==m ?Integer.MAX_VALUE: nums1[px];

          int x4 = py==m ? Integer.MAX_VALUE:nums2[py];

          if(x1<=x4 && x2<=x3){
              if((m+n)%2==1){
                  return Math.max(x1,x2);
              }
              return (Math.max(x1,x2)+Math.min(x3,x4))/2.0;
          }
          if(x1>x4){
              r=px-1;
          }
          else{
              l = px+1;
          }
      }
return -1;
    }




public int upperBound(ArrayList<Integer> list , int start, int end, int target){
       int result = -1;
        while (start<=end){
            int mid = start+(end-start)/2;
            if(list.get(mid)==target){
                result=mid;
            }
            if(list.get(mid)>target){
                result=mid;
                end=mid-1;
            }else{
             start=mid+1;
            }

        }
        return result;
}
    public int minOperations(int[] nums) {


int result  = Integer.MAX_VALUE;
int n = nums.length;
HashSet<Integer> set  = new HashSet<>();
for(int i :nums){
    set.add(i);
}
ArrayList<Integer> list = new ArrayList<>(set);
list.sort(Integer::compare);
for(int i =0;i<list.size();i++){
    int L = list.get(i);
    int R = L+n-1;
    int index = upperBound(list,i,list.size()-1,R);
    result=Math.min(result,n-(index-i));
}


    return result;

    }
    public void swap1(int[]arr ){
        int temp = arr[1];
       for(int i=1;i<arr.length-1;i++){
           arr[i]=arr[i+1];
       }
       arr[arr.length-1]=temp;
    }
    public int reductionOperations(int[] nums) {
      Arrays.sort(nums);
      int op = 0;
      int smallest = nums[0];
      while (nums[nums.length-1]!=smallest){
          int i = nums.length-1;
          while (nums[i]==nums[i-1]){
              i--;
          }

          nums[i]=nums[i-1];
          op++;

      }
      return op;
    }
    public boolean checkMountain(int[] arr) {
        int n = arr.length;

        if (n < 3) {
            return false;
        }

        int i = 0;


        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }


        if (i == 0 || i == n - 1) {
            return false;
        }


        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }


        return i == n - 1;
    }
    int length = 0;
    public int minimumMountainRemovals(int[] nums) {
      if(nums.length<=3){
          return 0;
      }

      return length;
    }
    public int garbageCollection(String[] garbage, int[] travel) {
     int time = 0;
     int M_Index=0;
     int P_Index=0;
     int G_Index=0;
     for(int i =0;i<garbage.length;i++){
         for(int j = 0;j<garbage[i].length();j++){
             if(garbage[i].charAt(j)=='M'){
                 M_Index=i;
             }
            else if(garbage[i].charAt(j)=='P'){
                 P_Index=i;
             }
             else{
                 G_Index=i;
             }

             time++;
         }

     }

     for(int i =1;i<travel.length;i++){
         travel[i]+=travel[i-1];
     }
     time+= M_Index > 0 ? travel[M_Index-1]:0;
        time+= P_Index > 0 ? travel[P_Index-1]:0;
        time+= G_Index > 0 ? travel[G_Index-1]:0;
     return time;
    }
    class PairOFRow{
        int row;
        int col;
        int value;

        public PairOFRow(int col, int row, int value) {
            this.col = col;
            this.row = row;
            this.value = value;
        }
    }
    public int[] findDiagonalOrder(List<List<Integer>> nums) {

     HashMap<Integer,List<PairOFRow>> map = new HashMap<>();
     for(int i =0;i<nums.size();i++){
         for(int j = 0;j<nums.get(i).size();j++){
             int sum = i+j;
             if(!map.containsKey(sum)){
                 map.put(sum,new ArrayList<>());
             }
             map.get(sum).add(new PairOFRow(i,j,nums.get(i).get(j)));

         }
     }
     Set<Integer> set = map.keySet();
     for(int i :set){
         map.get(i).sort((a,b)->Integer.compare(b.value,a.value));
     }
     ArrayList<Integer> list = new ArrayList<>();
     for(int i :set){
         List<PairOFRow> temp = map.get(i);
         for (PairOFRow pairOFRow : temp) {
             list.add(pairOFRow.value);
         }

     }
     int[] result = new int[list.size()];
     int index=0;
     for(int i :list){
         result[index++]=i;
     }
     return result;
    }
    public boolean checkAp(List<Integer> list){
        int n = list.size();
        int maxElement = list.get(0);
        int minElement = list.get(0);
        HashSet<Integer> set  = new HashSet<>();
        for(int i :list){
            maxElement=Math.max(maxElement,i);
            minElement=Math.min(minElement,i);
            set.add(i);
        }
        if( (maxElement-minElement) % (n-1)!=0){
            return false;
        }
        int commonDiffernce = (maxElement-minElement)/n-1;

        int current = minElement;
        while (current<maxElement){
            if(!set.contains(current)){
                return false;
            }
            current+=commonDiffernce;
        }
return true;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
    int m = l.length;
    List<Boolean> result = new ArrayList<>();
    for(int i = 0;i<m;i++){
        int leftRage = l[i];
        int righRange = r[i];
        List<Integer> temp = new ArrayList<>();
        for(int j = leftRage;j<=righRange && j<nums.length;j++){
          temp.add(nums[j]);
        }
        boolean b = checkAp(temp);

        result.add(b);
    }
    return result;
    }
    public int minSubArrayLen(int target, int[] nums) {
        int minLenght = Integer.MAX_VALUE;
        int[] prefix = new int[nums.length];
        prefix[0]=nums[0];
        for(int i =1;i<nums.length;i++){
            prefix[i]=prefix[i-1]+nums[i];
        }

     for(int i =0;i<nums.length;i++){
         for(int j =0;j<nums.length;j++){
           int sum = prefix[j] - ((i==0) ? prefix[i] : prefix[i-1] );
           if(sum>=target){
               minLenght =Math.min(minLenght,j-i+1);
           }
         }
     }
     Queue<Integer> queue =new LinkedList<>();
     ArrayDeque<Integer> deque = new ArrayDeque<>();

     return minLenght;
    }
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int result = 0;
        int i =0;
        int j =0;
        int n = nums.length;
        while(j<n){
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }
                i++;
            }

          result=Math.max(result,j-i+1);
          j++;
        }
        return result;
    }
    public boolean checkFreq(int[] frew){
        for(int i :frew){
            if(i!=0){
                return false;
            }
        }
        return true;
    }
    public boolean checkInclusion(String s1, String s2) {
        int[] freq = new int[26];
        for(char c :s1.toCharArray()){
            freq[c-'a']++;
        }
        for(int i=0;i<s1.length();i++){
            freq[s2.charAt(i)]--;
        }
        if(checkFreq(freq)){
            return true;
        }
        int i =0;
        int j = s1.length();
        while (j<s2.length()){
            freq[s2.charAt(j)-'a']--;
            freq[s2.charAt(i)-'a']++;
            if(checkFreq(freq)){
                return true;
            }
            i++;
            j++;
        }
        return false;

    }


    public static void main(String[] args) {
        String s = new String("R");

//        permutation("","abc");
//        System.out.println(result);
//        System.out.println("Hello world!");
//        areSentencesSimilar("my friend is good","he is my friend ");
//     int[] arr = {1,2,3,4,50,2000,3};
//     PriorityQueue<Integer> queue = new PriorityQueue<>();
//  int a =     Arrays.stream(arr).max().getAsInt();
//        System.out.println(minSubarraySum(arr));
//        System.out.println(Arrays.stream(arr).max().getAsInt());
//        System.out.println('b'-'B');
//        Set<Character> visited = new HashSet<>();
//       String[] s = {"a","sldjls"};
//        IntStream i = s[0].chars();
        System.out.println(findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
    }
}