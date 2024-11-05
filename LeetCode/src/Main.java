import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int ans = 0;
        int left = 0;
        int arr[] = new int[256];
        for (int right = 0; right < s.length(); right++) {
            char currentchar = s.charAt(right);
            while (arr[currentchar - 'a'] > 0) {
                arr[s.charAt(left) - 'a']--;
                left++;
            }
            arr[currentchar - 'a']++;
            ans = Math.max(right - left + 1, ans);
        }
        return ans - 1;
    }

    public int getCommon(int[] nums1, int[] nums2) {
        int first = 0;
        int second = 0;
        while (first < nums1.length && second < nums2.length) {
            if (nums1[first] == nums2[second]) {
                return nums1[first];
            }
            if (nums1[first] > nums2[second]) {
                second++;
            } else {
                first++;
            }
        }
        return -1;
    }

    public static String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                String substrings = s.substring(i, j);
                if (helper(substrings)) {
                    result = (result.length() > substrings.length()) ? result : substrings;
                }
            }
        }
        return result;
    }

    public static boolean helper(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int first = 0;
        int k = 0;
        int second = 0;
        while (first < nums1.length && second < nums2.length) {
            if (nums1[first] <= nums2[second]) {
                result[k] = nums1[first];
                first++;
            } else {
                result[k] = nums2[second];
                second++;
            }
            k++;
        }
        while (first < nums1.length) {
            result[k] = nums1[first];
            first++;
            k++;
        }
        while (second < nums2.length) {
            result[k] = nums2[second];
            second++;
            k++;
        }
        int length = result.length;
        if (length % 2 == 0) {
            int half = result[length / 2 - 1];
            int half2 = result[(length / 2)];
            return (double) (half + half2) / 2;
        } else {
            return result[length / 2];
        }


    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        int newlenght = Math.min(nums1.length, nums2.length);
        int[] result = new int[newlenght];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < result.length && i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result[k] = nums1[i];
                i++;
                j++;
                k++;
            } else {
                if (nums1[i] > nums2[j]) {
                    j++;

                } else {
                    i++;
                }
            }
        }
        return result;
    }

    //    public static int numberOfSubarrays(int[] nums, int k) {
//
//        int result=0;
//        int[] prefixOdd=new int[nums.length];
//        if(nums.length==0)return 0;
//        prefixOdd[0] = nums[0] % 2 == 0 ? 0 : 1;
//        for(int i=1;i<nums.length;i++){
//            prefixOdd[i] = prefixOdd[i - 1] + (nums[i] % 2 == 0 ? 0 : 1);
//        }
//        for(int i =0;i<nums.length;i++){
//            for(int j =i;j<nums.length;j++){
//                int count =prefixOdd[j]-(i==0?0:prefixOdd[i-1]);
//                if(count==k)result++;
//              }
//        }
//
//        return result;
//    }

    public int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int oddcount = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            oddcount += nums[i] % 2;
            if (map.containsKey(oddcount - k)) {
                result += map.get(oddcount - k);
            }
            map.put(oddcount, map.getOrDefault(oddcount, 0) + 1);


        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int result = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static int longestSubarray(int[] nums, int limit) {
        class Pair {
            int element;
            int index;

            Pair(int element, int index) {
                this.element = element;
                this.index = index;
            }

        }
        int result = 0;
        PriorityQueue<Pair> maxheap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                // Compare based on element in descending order
                return Integer.compare(p2.element, p1.element);
            }
        });

        PriorityQueue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                // Compare based on element in ascending order
                return Integer.compare(p1.element, p2.element);
            }
        });
        int left = 0;
        int right = 0;
        while (left < nums.length) {
            maxheap.add(new Pair(nums[left], left));
            minheap.add(new Pair(nums[left], left));
            while (Math.abs(maxheap.peek().element - minheap.peek().element) > limit) {
                right = Math.min(maxheap.peek().index, minheap.peek().index);
                while (maxheap.peek().index < right) {
                    maxheap.poll();
                }
                while (minheap.peek().index < right) {
                    minheap.poll();
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;

    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new LinkedList<>();
        List<Integer> values = new ArrayList<>();
        values.add(12);
        values.add(23);
        values.add(34);
        values.add(45);
        values.add(56);
        values.add(67);
        values.add(78);
        values.add(89);

        // Length 3
        values.add(123);
        values.add(234);
        values.add(345);
        values.add(456);
        values.add(567);
        values.add(678);
        values.add(789);

        // Length 4
        values.add(1234);
        values.add(2345);
        values.add(3456);
        values.add(4567);
        values.add(5678);
        values.add(6789);

        // Length 5
        values.add(12345);
        values.add(23456);
        values.add(34567);
        values.add(45678);
        values.add(56789);

        // Length 6
        values.add(123456);
        values.add(234567);
        values.add(345678);
        values.add(456789);

        // Length 7
        values.add(1234567);
        values.add(2345678);
        values.add(3456789);

        // Length 8
        values.add(12345678);
        values.add(23456789);

        // Length 9
        values.add(123456789);
        for (int i = 0; i < values.size(); i++) {
            int number = values.get(i);
            if (number < low) {
                continue;
            }
            if (number > high) break;
            result.add(number);
        }


        return result;
    }

    public int findCenter(int[][] edges) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];
            map.put(src, map.getOrDefault(src, 0) + 1);


        }
        return 0;
    }

    public static boolean window(int a, int b, int c) {
        return (a % 2 != 0 && b % 2 != 0 && c % 2 != 0);
    }

    public boolean threeConsecutiveOdds(int[] arr) {
        if (arr.length <= 2) return false;
        boolean result = false;
        for (int i = 0; i < arr.length - 2; i++) {
            int a = arr[i];
            int b = arr[i + 1];
            int c = arr[i + 2];
            if (window(a, b, c)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static int minMutation(String startGene, String endGene, String[] bank) {
        HashSet<String> set = new HashSet<>(List.of(bank));
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        int result = -1;
        queue.add(startGene);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String remo = queue.poll();
                for (int j = 0; j < 8; j++) {
                    String a = remo.substring(0, j);
                    String b = remo.substring(j);
                    for (char c : new char[]{'A', 'C', 'G', 'T'}) {
                        String finalstring = a + c + b;

                        if (finalstring.equals(endGene)) {
                            return result;
                        }
                        if (!visited.contains(finalstring) && set.contains(finalstring)) {
                            visited.add(finalstring);
                            queue.add(finalstring);
                            result += 1;
                        }
                    }
                }
            }
        }
        return result;

    }

    class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    //    public int nearestExit(char[][] maze, int[] entrance) {
//    int step =-1;
//    Queue<Pair>queue = new LinkedList<>();
//    HashSet<Pair> set = new HashSet<>();
//    queue.add(new Pair(entrance[0],entrance[1]));
//    while (!queue.isEmpty()){
//        int n =queue.size();
//        while (n-->0){
//            Pair p = queue.poll();
//            if(p.row<0||p.col<0||p.row>maze.length-1||p.col>maze[0].length-1)break;
//            if((p.row!=entrance[0]&&p.col!=entrance[1])&&(p.row==0||p.col==0||p.row==maze.length-1||p.col==maze[0].length-1)){
//                return step;
//            }
//            if(!set.contains(p)){
//                set.add(p);
//               queue.add(new Pair(p.row-1,p.col));
//                queue.add(new Pair(p.row+1,p.col));
//                queue.add(new Pair(p.row,p.col+1));
//                queue.add(new Pair(p.row,p.col-1));
//
//            }
//        }
//        step++;
//    }
//return step;
//    }
    public int removeStones(int[][] stones) {
        if (stones.length <= 1) {
            return 0;
        }
        int result = 0;
        boolean visited[] = new boolean[stones.length];
        for (int i = 0; i < stones.length; i++) {
            if (!visited[i]) {
                dfs(stones, visited, i);
                result++;
            }
        }
        return stones.length - result;
    }

    private void dfs(int[][] stones, boolean[] visited, int index) {
        visited[index] = true;
        for (int i = 0; i < stones.length; i++) {
            int row = stones[i][0];
            int col = stones[i][1];
            if (!visited[i] && (row == stones[index][0] || col == stones[index][1])) {
                dfs(stones, visited, i);
            }
        }
    }
//    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
//        boolean[] visited = new boolean[rooms.size()];
//        for(int i =0;i<rooms.size();i++){
//            if(!visited[i]){
//                dfss(rooms,visited,i);
//            }
//        }
//        for(boolean a :visited){
//            if(a)return false;
//        }
//        return true;
//    }

    private void dfss(List<List<Integer>> rooms, boolean[] visited, int src) {
        visited[src] = true;

        for (int i : rooms.get(src)) {
            if (!visited[i]) {
                dfss(rooms, visited, i);
            }

        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (detectCycle(adj, visited, recStack, i)) {
                    return new int[]{0};
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                toposort(adj, visited, stack, i);
            }
        }
        int i = 0;
        int resutl[] = new int[stack.size()];
        while (!stack.isEmpty()) {
            resutl[i++] = stack.pop();
        }
        return resutl;
    }

    private void toposort(ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack, int index) {
        visited[index] = true;
        for (int i : adj.get(index)) {
            if (!visited[i]) {
                toposort(adj, visited, stack, i);
                stack.add(i);
            }
        }
    }

    private static boolean detectCycle(ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recStack, int index) {
        if (recStack[index]) {
            return true;
        }
        if (visited[index]) {
            return false;
        }

        visited[index] = true;
        recStack[index] = true;

        for (int neighbor : adj.get(index)) {
            if (detectCycle(adj, visited, recStack, neighbor)) {
                return true;
            }
        }

        recStack[index] = false;
        return false;
    }

    boolean togle = false;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        int[] color = new int[n + 1];
        //0-->unvisited
        //1-->blue
        //2-->green
        boolean result = false;

        for (int i = 1; i < adj.size(); i++) {
            if (color[i] == 0) {
                result = colorgraph(adj, color, i, togle);
            }
        }
        return result;
    }

    private boolean colorgraph(ArrayList<ArrayList<Integer>> adj, int[] color, int index, boolean togle) {

        color[index] = togle ? 1 : 2;
        int colorvalue = color[index];
        for (int i : adj.get(index)) {
            if (color[index] == color[i]) return false;

            color[i] = (colorvalue == 1) ? 2 : 1;
            colorgraph(adj, color, i, !togle);
        }
        return false;
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        dfsforallPath(graph, result, 0, graph.length - 1, list, visited);
        return result;
    }

    private void dfsforallPath(int[][] graph, List<List<Integer>> result, int src, int dest, List<Integer> list, boolean[] visited) {
        if (src == dest) {
            list.add(dest);
            result.add(new ArrayList<>(list));
            return;
        }
        visited[src] = true;
        list.add(Integer.valueOf(src));
        for (int i = 0; i < graph[src].length; i++) {
            if (!visited[graph[src][i]]) {
                dfsforallPath(graph, result, graph[src][i], dest, list, visited);
            }
        }
        list.removeLast();
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int k = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums2[j] > nums1[i]) {
                i++;
            } else {
                list.add(Integer.valueOf(nums1[i]));
                i++;
                j++;
            }
        }
        int[] result = new int[list.size()];
        for (int m : list) {
            result[k++] = m;

        }
        return result;
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        return dfs(edges, hasApple, 0, -1);
    }

    private int dfs(int[][] edges, List<Boolean> hasApple, int current, int parent) {
        int time = 0;

        for (int i = 0; i < edges[current].length; i++) {
            if (edges[current][i] == parent) {
                continue;
            }
            int timechild = dfs(edges, hasApple, i, current);
            if (timechild > 0 || hasApple.get(edges[current][i])) {
                time += timechild + 2;

            }
        }
        return time;
    }

    List<Integer> r = new ArrayList<>();
    List<Integer> count = new ArrayList<>();

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        dfs(list, labels, 0, -1);
        int ans[] = new int[n];
        for (int i = 0; i < r.size(); i++) {
            ans[i] = r.get(i);
        }
        return ans;
    }

    private void dfs(List<List<Integer>> list, String labels, int current, int parent) {
        Character c = labels.charAt(current);
        int beforecount = count.get(c - 'a');
        count.add(current, count.get(c - 'a') + 1);

        for (int i = 0; i < list.get(current).size(); i++) {
            if (list.get(current).get(i) == parent) continue;
            dfs(list, labels, list.get(current).get(i), current);
        }
        int aftercount = count.get(c - 'a');
        r.add(current, aftercount - beforecount);

    }

    //    int result1=Integer.MIN_VALUE;
//    public int longestPath(int[] parent, String s) {
//        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//        for(int i =0;i<parent.length;i++){
//            adj.add(new ArrayList<>());
//        }
//        for(int i =1;i<parent.length;i++){
//            int u =i;
//            int v = parent[i];
//            adj.get(u).add(v);
//            adj.get(v).add(u);
//
//        }
//        Dfs(adj,s,0,-1);
//        return result1;
//    }
//
//    private int Dfs(ArrayList<ArrayList<Integer>> adj, String s, int current, int parent) {
//       int longest =0;
//       int secondlongest=0;
//
//       for(int child :adj.get(current)){
//           if(child==parent)continue;
//           int childLongestLenght = Dfs(adj,s,child,current);
//           if(s.charAt(child)==s.charAt(parent))continue;
//
//           secondlongest=Math.max(secondlongest,childLongestLenght);
//           if(secondlongest>longest){
//               int temp =secondlongest;
//               secondlongest=longest;
//               longest=temp;
//           }
//
//
//
//       }
//      int onlyone = Math.max(longest,secondlongest)+1;
//       int onlyroot = 1;
//       int onlydownans = 1 + secondlongest+longest;
//       result1=Math.max(result1,Math.max(onlyone,Math.max(onlyroot,onlydownans)));
//       return Math.max(onlyroot,onlyone);
//    }
//public int findCircleNum(int[][] isConnected) {
//  ArrayList<ArrayList<Integer>> list = new ArrayList<>();
//  for(int i =0;i<isConnected.length;i++){
//      list.add(new ArrayList<>());
//  }
//  for(int i=0;i<isConnected.length;i++){
//      for(int j =0;j<isConnected[0].length;j++){
//          if(isConnected[i][j]!=0){
//              list.get(i).add(j);
//              list.get(j).add(i);
//          }
//
//      }
//  }
//  boolean[]visited =new boolean[isConnected.length];
//  int count =0;
//  Queue<Integer> queue = new LinkedList<>();
//
//  for(int i =0;i<isConnected.length;i++){
//    if(!visited[i]){
//        queue.add(i);
//       while (!queue.isEmpty()){
//           int current= queue.remove();
//          for(int child:list.get(current)){
//              if(!visited[child]){
//                  queue.add(child);
//              }
//          }
//       }
//        count++;
//    }
//  }
//  return count;
//}
//public int findCircleNum(int[][] isConnected) {
// Hashtable<Integer,ArrayList<Integer>> adj = new Hashtable<>();
// for(int i =0;i<isConnected.length;i++){
//     adj.put(i,new ArrayList<>());
// }
//      for(int i=0;i<isConnected.length;i++){
//      for(int j =0;j<isConnected[0].length;j++){
//          if(isConnected[i][j]!=0){
//           adj.get(i).add(j);
//           adj.get(j).add(i);
//          }
//
//      }
//      }
//      boolean[]visited =new boolean[isConnected.length];
//  int count =0;
//  Queue<Integer> queue = new LinkedList<>();
//
//  for(int i =0;i<isConnected.length;i++){
//    if(!visited[i]){
//        queue.add(i);
//       while (!queue.isEmpty()){
//           int current= queue.remove();
//          for(int child:adj.get(current)){
//              if(!visited[child]){
//                  queue.add(child);
//              }
//          }
//       }
//        count++;
//    }
//  }
//  return count;
//}
    public int findCircleNum(int[][] isConnected) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int i = 0; i < isConnected.length; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[0].length; j++) {
                if (isConnected[i][j] != 0) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                helperwithdfs(adj, visited, i);
            }
            count++;
        }

        return count;
    }

    private void helperwithdfs(HashMap<Integer, ArrayList<Integer>> adj, boolean[] visited, int current) {

        if (visited[current]) return;
        visited[current] = true;
        for (int i : adj.get(current)) {
            helperwithdfs(adj, visited, i);
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());

        }
        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            list.get(u).add(v);
        }
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][1]]++;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int v : list.get(current)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.add(v);
                    count++;
                }
            }

        }
        return !(count == numCourses);
    }

    public int maximumGain(String s, int x, int y) {
        String a = "ab";
        String b = "ba";
        int score = 0;


        while (s.contains(a)) {
            score += x;
            s = s.replace(a, "");
        }
        while (s.contains(b)) {
            score += y;
            s = s.replace(b, "");
        }
        return score;
    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int maxmininrow = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            int mininrow = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                mininrow = Math.min(mininrow, matrix[i][j]);
            }
            maxmininrow = Math.max(mininrow, maxmininrow);
        }
        int minmaxincol = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            int maxincol = Integer.MIN_VALUE;
            for (int j = 0; j < matrix.length; j++) {
                maxincol = Math.max(maxincol, matrix[j][i]);
            }
            minmaxincol = Math.min(minmaxincol, maxincol);
        }
        if (maxmininrow == minmaxincol) {
            list.add(maxmininrow);
        }
        return list;


    }

    class Pairs {
        int row;
        int col;

        Pairs(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<Pairs> queue = new LinkedList<>();
        queue.add(new Pairs(entrance[0], entrance[1]));
        HashSet<Pairs> set = new HashSet<>();
        int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {0, -1}};
        int result = -1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            Pairs p = queue.poll();
            result++;
            while (n-- > 0) {
                for (int[] i : direction) {
                    int newrow = p.row + i[0];
                    int newcol = p.col + i[1];
                    if (newrow < 0 || newrow > maze.length - 1 || newcol < 0 || newcol > maze[0].length - 1) {
                        continue;
                    }
                    if ((newrow == 0 || newcol == 0 || newrow == maze.length - 1 || newcol == maze[0].length - 1) && (newrow != entrance[0] || newcol != entrance[1])) {
                        return result;
                    }
                    Pairs newpair = new Pairs(newrow, newcol);
                    if (!set.contains(newpair)) {
                        queue.add(newpair);
                        set.add(newpair);
                    }
                }
            }

        }

        return result;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] i : edges) {
            int u = i[0];
            int v = i[1];
            adj.put(u, new ArrayList<>(v));
            adj.put(v, new ArrayList<>(u));
        }
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int a = queue.poll();
            if (source == destination) return true;
            if (!visited[a]) {
                visited[a] = true;
                for (int b : adj.get(a)) {
                    if (!visited[b]) {
                        queue.add(b);
                        source = b;
                    }
                }
            }
        }
        return false;


    }

    private boolean dfsofPath(HashMap<Integer, List<Integer>> adj, boolean[] visited, int source, int destination) {
        if (source == destination) return true;
        if (!visited[source]) {
            visited[source] = true;
            for (Integer i : adj.get(source)) {
                if (!visited[i]) {
                    if (dfsofPath(adj, visited, i, destination)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            if (!visited[i]) {
                dfsofkeys(rooms, visited, i);
            }
        }

        for (boolean a : visited) {
            if (!a) {
                return false;
            }
        }
        return true;
    }

    private void dfsofkeys(List<List<Integer>> rooms, boolean[] visited, int i) {
        visited[i] = true;
        for (int j : rooms.get(i)) {
            if (!visited[j]) {
                dfsofkeys(rooms, visited, j);
            }
        }
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        String result = new String("");
        HashMap<Character, ArrayList<Character>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(s1.charAt(i), new ArrayList<>(s2.charAt(i)));
            adj.put(s2.charAt(i), new ArrayList<>(s1.charAt(i)));
        }

        for (int i = 0; i < baseStr.length(); i++) {
            boolean[] visited = new boolean[26];
            Character minchar = baseStr.charAt(i);
            if (!visited[i - 'a']) {
                Character res = DFS(adj, visited, minchar);
                result += res;
            }
        }
        return result;

    }

    private Character DFS(HashMap<Character, ArrayList<Character>> adj, boolean[] visited, Character minchar) {
        visited[minchar - 'a'] = true;
        Character mincharacter = minchar;
        for (Character c : adj.get(mincharacter)) {
            if (!visited[c - 'a']) {
                mincharacter = (char) Math.max(mincharacter, DFS(adj, visited, c));
            }
        }
        return mincharacter;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }
    public boolean isSubPath(ListNode head, TreeNode root) {
     return helper(head,root);
    }

    private boolean helper(ListNode head, TreeNode root) {
        if(root==null){
            return false;
        }
        if(head==null){
            return true;
        }
        if(root.val==head.val){
            if(head.next==null){
                return false;
            }
            boolean left = helper(head.next,root.left);
            boolean right = helper(head.next,root.right);
            return left||right;

        }
        boolean left = helper(head,root.left);
        boolean right = helper(head,root.right);
        return left||right;
    }
    public int[][] spiralMatrix(int m, int n, ListNode head) {
         int top = 0;
         int left = 0;
         int right = n-1;
         int  down = m -1;
         int[][] result = new int[m][n];
         for(int[] a :result){
             Arrays.fill(a,-1);
         }
         while (top<=down||left<=right||head!=null){
             for (int i = left; i <= right; i++) {
                 result[top][i]=head.val;
             }
             top++; // Move top boundary down

             for (int i = top; i <= down; i++) {
                 result[i][right]=head.val;
             }
             right--; // Move right boundary left

             if (top <= down) { // Ensure there are rows remaining
                 for (int i = right; i >= left; i--) {
                     result[down][i] =head.val;
                 }
                 down--;
             }

             if (left <= right) { // Ensure there are columns remaining
                 for (int i = down; i >= top; i--) {
                     result[i][left] =head.val;
                 }
                 left++; // Move left boundary right
             }
         }
         return result;
    }
    public static void main(String[] args) {
////        System.out.println(numberOfSubarrays(new int[]{1,2,3,4},2));
//        System.out.println(minMutation("","",new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
        int[][] matrix = {
                {1, 2, 3, 4},
                {14, 15, 16, 5},
                {13, 20, 17, 6},
                {12, 19, 18, 7},
                {11, 10, 9, 8}
        };
        int left = 0, top = 0;
        int right = 3;
        int down = 4;
        while (left <= right && top <= down) {
            for (int i = left; i <= right; i++) {
                System.out.print(matrix[top][i] + " ");
            }
            top++; // Move top boundary down

            for (int i = top; i <= down; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--; // Move right boundary left

            if (top <= down) { // Ensure there are rows remaining
                for (int i = right; i >= left; i--) {
                    System.out.print(matrix[down][i] + " ");
                }
                down--; // Move bottom boundary up
            }

            if (left <= right) { // Ensure there are columns remaining
                for (int i = down; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left++; // Move left boundary right
            }
        }

    }
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
     int result = 0;
     for(int i =0;i<arr1.length;i++){
         for(int j =0;j<arr2.length;j++){
             String a = Integer.toString(arr1[i]);
             String b = Integer.toString(arr2[j]);
             int templength = 0;
             for(int index = 0;index<Math.min(a.length(),b.length());index++){
                 if(a.charAt(index)==b.charAt(index)) {
                     templength++;
                 }
                 else {
                     break;
                 }
                 result=Math.max(result,templength);
             }

         }
     }
     return result;
    }
    public static int find(int x, Vector<Integer> parent) {
        if (parent.get(x) != x) {
            parent.set(x, find(parent.get(x), parent)); // Path compression
        }
        return parent.get(x);
    }

    public static void Union(int x, int y, Vector<Integer> parent, Vector<Integer> rank) {
        int x_parent = find(x, parent);
        int y_parent = find(y, parent);

        if (x_parent == y_parent) {
            return;
        }

        if (rank.get(x_parent) > rank.get(y_parent)) {
            parent.set(y_parent, x_parent);
        } else if (rank.get(x_parent) < rank.get(y_parent)) {
            parent.set(x_parent, y_parent);
        } else {
            parent.set(y_parent, x_parent);
            rank.set(x_parent, rank.get(x_parent) + 1);
        }
    }

    public int makeConnected(int n, int[][] connections) {
        Vector<Integer> parent = new Vector<>();
        Vector<Integer> rank = new Vector<>();
        for(int i=0;i<n;i++){
            parent.add(i);
            rank.add(0);
        }
        int components = n;
        for(int a[]:connections){
            int u = a[0];
            int v =a[1];
            if(find(u,parent)==find(v,parent)){
                continue;
            }
            else{
                Union(u,v,parent,rank);
            }
        }
        return components-1;
    }
    public boolean equationsPossible(String[] equations) {
        Vector<Integer> parent = new Vector<>();
        for(int i=0;i<26;i++){
            parent.set(i,i);
        }
        Vector<Integer> rank = new Vector<>();
        for(int i =0;i<26;i++){
            rank.set(i,0)   ;
        }
        for(String s :equations){
            if(s.charAt(1)=='='){
                Union(s.charAt(0)-'0',s.charAt(3)-'0',parent,rank);
            }
        }
        for(String s :equations){
            if(s.charAt(1)=='!'){
                int x_parent = find(s.charAt(0)-'0',parent);
                int y_parent = find(s.charAt(3)-'0',parent);
                if(x_parent==y_parent){
                    return false;
                }
            }
        }
        return true;
    }
}