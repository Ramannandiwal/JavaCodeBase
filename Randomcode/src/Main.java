import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;







public class Main {
    public static int totalNQueens(int n) {
       boolean board[][] = new boolean[n][n];
       return helper(board,0);
    }


    private static int helper(boolean[][] board, int row) {
        if(row==board.length){
            return 1;
        }
        int count =0;
        for(int col = 0;col<board.length;col++){
            if(issafe(board,row,col)){
                board[row][col]=true;
             count+=helper(board,row+1);
                board[row][col]=false;
            }
        }
        return count;
    }
    private static boolean issafe(boolean[][] board, int row, int col) {
        for(int i =0;i<row;i++){
            if(board[i][col])return false;
        }
        for(int i =0;i<col;i++){
            if(board[row][i])return false;
        }
        int maxleft = Math.min(row,col);
        int maxRight =Math.min(row,board.length-1-col);

        for(int i =1;i<=maxleft;i++){
            if(board[row-i][col-i])return false;
        }
        for(int i =1;i<=maxRight;i++){
            if(board[row-i][col+i])return false;
        }
        return true;
    }
    public static ArrayList<ArrayList<Integer>>  merge(int[][] intervals) {
          ArrayList<ArrayList<Integer>> list = new ArrayList<>();
          for(int i =0;i<intervals.length;i++){
              for(int j =i+1;j<intervals.length-1;j++){
                  if(intervals[i][1]>=intervals[j][0]&&intervals[i][0]<=intervals[j][1]){
                      ArrayList<Integer> temp = new ArrayList<>();
                      temp.add(intervals[i][0]);
                      temp.add(intervals[j][1]);
                      list.add(temp);
                      i+=2;
                  }
                  else{
                      ArrayList<Integer> temp = new ArrayList<>();
                      temp.add(intervals[i][0]);
                      temp.add(intervals[i][1]);
                      list.add(temp);
                  }
              }
          }

          return list;

    }
    public String customSortString(String order, String s) {
           String result = "";
        Queue<Character> queue = new LinkedList<>();

        for(int i =0;i<order.length();i++){
            queue.add(order.charAt(i));
        }
       while (!queue.isEmpty()){
           char c = queue.poll();
           if(s.contains(c+"")){
               result= result+c;
           }

       }
       for(int i =0;i<s.length();i++){
           if(!result.contains(s.charAt(i)+"")){
               result=s.charAt(i)+result;
           }
       }
        return result;
    }
    public static int pivotInteger(int n) {

int totalsum = (n*(n+1))/2;
for(int i =1;i<=n;i++){
    int sum = helpersum(i);
    int t_s=totalsum-sum+i;
    if(t_s==sum){
        return i;

    }
}
     return -1;
    }
    private static int helpersum(int x){
        int sum  =0;
        for(int i =1;i<=x;i++){
            sum+=i;
        }
        return sum;
    }
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int zeors=0;
        for(int i :nums){
            if(i==0){
                zeors++;
                if(zeors>1){
                    return new int[nums.length];
                }else{
                    product*=i;
                }
            }

        }
        for(int i =0;i<nums.length;i++){
                if(zeors==1){
                    nums[i]=nums[i]==0 ? product:0;
                }
                else{
                    nums[i]=product/nums[i];
                }
        }
        return nums;
    }
    public static String reverseWords(String s) {
s = s.trim();
ArrayList<String> list = new ArrayList<>(Arrays.asList(s.split(" ")));
 StringBuilder result = new StringBuilder();
 for(int i =list.size()-1;i>=0;i--){
     if(!list.get(i).equals(" ") && !(list.get(i).isEmpty())){
         result.append(list.get(i));
         result.append(" ");
     }

 }
     return result.toString().trim();

    }
    public int distributeCookies(int[] cookies, int k) {
    int result = Integer.MAX_VALUE;
    int arr[]= new int[k];
     solve(cookies,arr,0,result);
     return result;
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for(String st :wordDict){
            set.add(st);
        }
        List<String> result = new ArrayList<>();
        wordbreak(s,set,result,"",0);
        return result;
    }
//    public long maxAlternatingSum(int[] nums) {
//        return maxAlternatingSum(nums,new ArrayList<Integer>(),0);
//    }
  private int  findsum(ArrayList<Integer> list ){
        int sum =0;
        for(int i =0;i<list.size();i++){
            if(i%2==0){
                sum+=list.get(i);
            }else {
                sum-=list.get(i);
            }
        }
        return sum;
  }
    private  long maxAlternatingSum(int[] nums, ArrayList<Integer> es, int i) {
        if(i>=nums.length){
            return findsum(es);
        }
        es.add(nums[i]);
        long  include =maxAlternatingSum(nums,es,i+1);
        es.removeLast();
        long exclude = maxAlternatingSum(nums,es,i+1);
        return  Math.max(include,exclude);

    }

    private void wordbreak(String s, HashSet<String> set, List<String> result, String strings, int index) {
        if(index>=s.length())   {
            result.add(strings);
            return;
        }
        for(int j =index+1;j<s.length();j++ ){
            if(set.contains(s.substring(index,j+1))){
                if(!strings.isEmpty()){
                    String original = strings;
                    strings+=s.substring(index,j+1)+" ";
                  wordbreak(s,set,result,strings,index+1);
                  strings=original;
                }else{
                    String original = strings;
                    strings+=s.substring(index,j+1);
                    wordbreak(s,set,result,strings,index+1);
                    strings=original;
                }
            }
        }
    }

    private void solve(int[] cookies, int[] arr, int index,int result) {
        if(index>=cookies.length){
            int[] temparr = arr;
            Arrays.sort(temparr);
            int maxelement = temparr[temparr.length-1];
            result= Math.min(result,maxelement);
            return;

        }
        for(int i =0;i<arr.length;i++) {
            arr[index] += cookies[index];
            solve(cookies, arr, index + 1, result);
            arr[index] -= cookies[index];
        }

    }

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[]freq = new int[26];
        for(char a :letters){
            freq[a-'a']++;
        }

        return solve(words,score,freq,0,0,Integer.MIN_VALUE);

    }

    private int solve(String[] words, int[] score, int[] freq, int index,int tempscore,int result) {
        result=Math.max(tempscore,result);
        if(index==words.length){
            return  result;
        }
        String word = words[index];
        int tempfreq[]=freq.clone();
        int j =0;
        int level_sum=0;
        boolean include = true;
        while(j<word.length()){
            int i = word.charAt(j)-'a';
            tempfreq[i]--;
            level_sum+=score[i];
            if(tempfreq[i]<0){
                include=false;
            }
        }
        if(include){
            return solve(words,score,tempfreq,index+1,level_sum+tempscore,result);
        }
        return solve(words,score,freq,index+1,tempscore,result);

    }
    public long maxAlternatingSum(int[] nums) {
     long dp[][] = new long[100001][2];
     for(long[] a :dp){
         Arrays.fill(a,-1);
     }
     return help(nums,dp,0,true);

    }
    private  long help(int[]nums,long[][]dp,int index,boolean a ){
        if(index>=nums.length){
            return 0;
        }
        int aindex=a?1:0;
        if(dp[index][aindex]!=-1){
            return dp[index][aindex];
        }
        long exclude = help(nums,dp,index+1,a);
        int value = nums[index];
        if(!a){
            value=-value;
        }
        long include = help(nums,dp,index+1,!a)+value;
        dp[index][aindex]=Math.max(include,exclude);
        return dp[index][aindex];

    }
    public int maximumRequests(int n, int[][] requests) {
       int[] arr = new int[n];
       return solve(0,arr,0,requests);
    }
    private int solve(int index,int[] arr , int count, int[][]request){
        if(index>=request.length){
            for(int i :arr){
                if(i!=0){
                    return 0;
                }
            }
            return 1;

        }
        int from = request[index][0];
        int to = request[index][1];
         arr[from]++;
         arr[to]++;
        int take = solve(index+1,arr,count+1,request);
       arr[from]--;
       arr[from]++;
        int exclude = solve(index+1,arr,count,request);
        return Math.max(take,exclude);
    }
    public boolean checkRecord(String s) {
        if(s.contains("LLL")){
            return false;
        }
        boolean a =false;
        for(char c :s.toCharArray()){
            if(c=='A'){
                if(a){
                    return false;

                }
                a=true;
            }
        }
        return true;
    }
    public List<List<Integer>> combine(int n, int k) {
      List<List<Integer>> result = new ArrayList<>();
      List<Integer> temp = new ArrayList<>();
      comhine(1,k,result,temp,n);
      return result;
    }

    private void comhine(int start, int k, List<List<Integer>> result, List<Integer> temp,int n ) {
        if(k==0){
            result.add(temp);
            return;
        }
        if(start>n){
            return;
        }
        temp.add(start);
        comhine(start+1,k-1,result,temp,n);
        temp.removeLast();
        comhine(start+1,k,result,temp,n);
    }
    public int lengthOfLIS(int[] nums) {
     return helperlengthofLts(0,nums,0);
    }

    private int helperlengthofLts(int index, int[] nums, int count) {
        if (index >= nums.length) {
            return count;
        }
        int skip = helperlengthofLts(index+1,nums,count);
        int take=0;
        if(nums[index]>nums[index+1]){
             take = helperlengthofLts(index+1,nums,count+1);

        }
        return Math.max(skip,take);
    }
    int dp[][];
    public int findLongestChain(int[][] pairs) {

        dp = new int[pairs.length+1][pairs.length];
        for(int i[] :dp){
            Arrays.fill(i,-1);
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        return solvehelperLeng(-1,0,pairs);

    }

    private int solvehelperLeng(int prevIndex, int CurrentIndex, int[][] pairs) {
        if(CurrentIndex>=pairs.length){
            return 0;
        }
        int dpPrevIndex=prevIndex+1;
        if(dp[CurrentIndex][dpPrevIndex]!=-1){
            return dp[CurrentIndex][dpPrevIndex];
        }
        int skip = solvehelperLeng(prevIndex,CurrentIndex+1,pairs);
        int take = Integer.MIN_VALUE;
        if(prevIndex==-1||pairs[prevIndex][1]<pairs[CurrentIndex][0]){
             take = 1+solvehelperLeng(CurrentIndex, CurrentIndex+1,pairs);
        }
        dp[CurrentIndex][dpPrevIndex]= Math.max(skip,take);
        return Math.max(skip,take);
    }
    public int findLongestChains(int[][] nums) {

       int t[] = new int[nums.length+1];
      int result=1;
        for(int i =0;i<nums.length;i++){
           for(int j =0;j<i;j++){
               if(nums[j][0]<nums[i][1]){
                   t[i]=Math.max(t[i],1+t[j]);
                   result =Math.max(result,t[i]);
               }
           }
        }
       return result;

    }
    public int longestStrChain(String[] words) {
     return longestStrChain(words,0);
    }
    public static boolean containsAllCharacters(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        String sortedS1 = new String(arr1);
        String sortedS2 = new String(arr2);
        return sortedS2.contains(sortedS1);
    }
    private int longestStrChain(String[] words, int index) {
        if (index >= words.length) {
            return 0;
        }
        int skip = longestStrChain(words,index+1);
        int take=0;
        for(int  j = index+1;j<words.length;j++){
            if(Math.abs(words[index].length()-words[j].length())==1&&containsAllCharacters(words[index],words[j])){
               take = 1+longestStrChain(words,index+1);

            }
        }
        return Math.max(skip,take);
    }
    public static int numSteps(String s) {
      Long a  = binaryToDecimal(s);

    return helpernumSteps(a,0);
    }

    private static int helpernumSteps(Long number, int count) {
        if (number == 1) {
            return count;
        }
        int ans =0;
        if(number%2==0){
            ans+=helpernumSteps(number/2,count+1);
        }else{
            ans+=helpernumSteps(number+1,count+1);
        }
        return ans;
    }

    public static Long binaryToDecimal(String binaryString) {
        return Long.parseLong(binaryString,2);
    }
  private static   int calculatexor(int arr[],int start, int end){
        int ans = 0;
        for(int i=start;i<=end;i++){
            ans^=arr[i];
        }
        return ans;
  }
    public static int countTriplets(int[] arr) {
        int count= 0;
     for(int i=0;i<arr.length-1;i++){
         for(int j =i+1;j<arr.length;j++){
             for(int k =j;k<arr.length;k++){
                 int a = calculatexor(arr,i,j-1);
                 int b = calculatexor(arr,j,k);
                 if(a==b)count++;

             }
         }
     }
     return count;
    }
    public int numOfArrays(int n, int m, int k) {
       return solvenumofArrayas(n,m,0,k,0,-1);
    }

    private int solvenumofArrayas(int n, int m, int index,int k, int searchCost, int max) {
        if(n==index){
            if (searchCost == k) {
                return 1;
            }else {
                return 0;
            }
        }

        int result =0;
        for(int  i = 1;i<=m;i++){
            if(i>max){
                result+=solvenumofArrayas(n,m,index+1,k,searchCost+1,i);
            }else{
                result+=solvenumofArrayas(n,m,index+1,k,searchCost,max);
            }
        }

        return result;
    }
    public static int[] singleNumber(int[] nums) {
        if (nums.length == 2) {
            return nums;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                list.add(key);
            }
        }

        // Assuming there are exactly two elements with a count of 1
        int[] arr = new int[2];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
    public static boolean isNStraightHand(int[] hand, int groupSize) {
           if(hand.length%groupSize!=0){
               return false;
           }
        TreeMap<Integer,Integer> map = new TreeMap<>();
       for(int i:hand){
           map.put(i,map.getOrDefault(i,0)+1);
       }
       while (!map.isEmpty()){
           int currentelement = map.firstKey();
           int currentFrequeyncy = map.get(currentelement);
           for(int i =0;i<groupSize;i++){
            if(map.get(currentelement+i)==0){
                return false;
            }
            map.put(currentelement+i,map.get(currentelement+i)-1);
            if(map.get(currentelement+i)<1){
                map.remove(currentelement+i);
            }

           }
       }
       

        return  true;
    }
    public static List<String> commonChars(String[] words) {
        int minFrequency[]= new int[26];
        Arrays.fill(minFrequency,Integer.MAX_VALUE);
        for (String str : words) {
            int[] charCount = new int[26];
            for (char c : str.toCharArray()) {
                charCount[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                minFrequency[i] = Math.min(minFrequency[i], charCount[i]);
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (minFrequency[i] > 0) {
                result.add(String.valueOf((char) (i + 'a')));
                minFrequency[i]--;
            }
        }

        return result;

    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        String[] arr = sentence.split(" ");
        System.out.println(Arrays.toString(arr) );
        return " ";
    }


    public static int subarraysDivByK(int[] nums, int key) {
        int count =0;
        for(int i =0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                int sum =0;
                for(int k =i;k<=j;k++){
                    sum+=nums[k];
                }
                if(sum%key==0)count++;
            }
        }
        return count;
    }
    public int heightChecker(int[] heights) {
      int temp[]=heights.clone();
      Arrays.sort(heights);
      int count =0;
      for(int i=0;i<heights.length;i++){
          if(heights[i]!=temp[i]){
              count++;
          }
      }
      return count;
    }
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
      TreeMap<Integer,Integer> map = new TreeMap<>();
    for(int i :arr1){
        map.put(i,map.getOrDefault(i,0)+1);

    }
    int index=0;
    for(int i :arr2){
        while (map.get(i)>0){
            arr1[index++]=i;
            map.put(i,map.getOrDefault(i,0)-1);
        }
        map.remove(i);
    }
    for(int i:map.keySet()){
        int freq = map.get(i);
        while (freq>0){
            arr1[index++]=i;
            freq--;
        }

    }
    return arr1;

    }
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    int project[][]=new int[profits.length][capital.length];
    for(int i =0;i<profits.length;i++){
        project[i][0]=capital[i];
        project[i][1]=profits[i];
    }
    Arrays.sort(project,Comparator.comparing(a ->a[0]));
    PriorityQueue<Integer>maxheap = new PriorityQueue<>(Collections.reverseOrder());
    int i =0;
    while (k-- >0){
        while (i<profits.length&&project[i][0]<=w){
            maxheap.offer(project[i][1]);
            i++;
        }
        if(maxheap.isEmpty()){
            break;
        }
        w+=maxheap.poll();


    }
    return w;
    }

//   static class Pair{
//        int difficulty;
//        int profit;
//        Pair(int difficulty,int profit){
//            this.profit=profit;
//            this.difficulty=difficulty;
//        }
//    }

//    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
//       int result =0;
//
//    Arrays.sort(worker);
//    Pair[] pair = new Pair[difficulty.length];
//    for(int i=0;i<difficulty.length;i++){
//        pair[i]=new Pair(difficulty[i],profit[i]);
//    }
//    Arrays.sort(pair, Comparator.comparingInt(a -> a.difficulty));
//      int j =0;
//      int maxProfit =0;
//      for(int i =0;i<worker.length;i++){
//          if(j<pair.length&& worker[i]>=pair[j].difficulty){
//              maxProfit=Math.max(maxProfit,pair[j].profit);
//              j++;
//          }
//          result+=maxProfit;
//      }
//
//
//        return result;
//
//    }

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
      int unsatisfied =0;
      for(int i =0;i<minutes;i++){
          unsatisfied+=customers[i]*grumpy[i];
      }

      int max =unsatisfied;
      int i =0;
      int j =minutes;
      while(j<customers.length){
         unsatisfied+=customers[j]*grumpy[j];
         unsatisfied-=customers[i]*grumpy[i];
         max=Math.max(unsatisfied,max);
         i++;
         j++;


      }
    int total = max;
      for(int k =0;k<customers.length;k++){
          total+=customers[k]*(1-grumpy[k]);
      }
      return total;
    }
//    class  Pair{
//        int destination;
//        int price;
//        Pair(int x, int y ){
//            this.destination=x;
//            this.price=y;
//        }
//
//    }
//    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//     int result=0;
//     Vector<Integer> distance = new Vector<>(n);
//     for(int i =0;i<n;i++){
//         distance.add(Integer.MAX_VALUE);
//     }
//        HashMap<Integer, ArrayList<Pair>> adj = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            adj.put(i, new ArrayList<>());
//        }
//        for (int[] i : flights) {
//            int source = i[0];
//            int destination = i[1];
//            int costprice = i[2];
//            adj.get(source).add(new Pair(destination, costprice));
//        }
//         Queue<Pair> queue = new LinkedList<>();
//        queue.add(new Pair(src,0));
//        distance.set(src,0) ;
//        int step = 0;
//
//        while (!queue.isEmpty()&&step<=k){
//            int N = queue.size()    ;
//            while (N-->0){
//                Pair p = queue.poll();
//                int u =p.destination;
//                int d = p.price;
//              for(Pair P :adj.get(u)){
//                  int v = P.destination;
//
//                  int cost = P.price;
//                  if(distance.get(v)>d+cost){
//                      distance.set(v,d+cost);
//                      queue.add(new Pair(v,d+cost));
//                  }
//
//              }
//            }
//            step++;
//
//        }
//        return  distance.get(dst)==Integer.MAX_VALUE?-1:distance.get(dst);
//    }
    class Pair{
        int destination;
        int distance;
        Pair(int x, int y){
            this.destination=x;
            this.distance=y;
        }
}
//    public int minScore(int n, int[][] roads) {
//       HashMap<Integer,ArrayList<Pair>> adj = new HashMap<>();
//                for (int i = 0; i < n; i++) {
//            adj.put(i, new ArrayList<>());
//        }
//        for (int[] i : roads) {
//            int source = i[0];
//            int destination = i[1];
//            int distance = i[2];
//            adj.get(source).add(new Pair(destination, distance));
//        }
//        for(int i =0;i<adj.size();i++){
//
//        }
//
//    }
public int[] sortJumbled(int[] mapping, int[] nums) {
 int[]temp = new int[nums.length];
 for(int i =0;i<nums.length;i++){
     int sizeofthenumber = (int) (Math. log10(nums[i]) + 1);
     int originalnumber=nums[i];
     int result=0;
     while (sizeofthenumber-->0){
         int currentnumberdigit=originalnumber%10;
         int newnubmer=mapping[currentnumberdigit];
         result+=newnubmer*10;
     }

 }
 return new int[]{33};
}

        public int countSeniors(String[] details) {
        int result =0;
        for(String s :details){
            if(Integer.parseInt(s.substring(12,14))>=60){
                result++;
            }
        }
        return result;
        }

    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
     return Arrays.equals(target,arr);
    }
    public  static int rangeSum(int[] nums, int n, int left, int right) {
        ArrayList<Integer> list = new ArrayList<>();
      int prefexSum[]=new int[nums.length];
      prefexSum[0]=nums[0];
      for(int i =1;i<nums.length;i++){
          prefexSum[i]=prefexSum[i-1]+nums[i];
      }
      for(int i =0;i<nums.length;i++){
          for(int j=i;j<nums.length;j++){
              list.add(i==0?prefexSum[j]:prefexSum[j]-prefexSum[i-1]) ;
          }
      }
      list.sort((a,b)->a-b);
      long sum =0;

        for(int i =left-1;i<right;i++){
            sum+=list.get(i);
        }

       int  result = (int) (sum % 1000000007);
        return result;

    }
//    public String kthDistinct(String[] arr, int k) {
//     HashMap<Character,Integer> map = new HashMap<>();
//     for(int i =0;i<arr.length;i++){
//
//     }
//    }
    void dfs(int[][]grid ,int row, int col){
        if(row<0||col<0||row>=grid.length||col>=grid[0].length||grid[row][col]==0){
            return;
        }
        if(grid[row][col]==-1){
            return;
        }
        if(grid[row][col]==1){
            grid[row][col]=-1;
            dfs(grid,row,col+1);
            dfs(grid, row, col-1);
            dfs(grid,row+1,col);
            dfs(grid,row-1,col);
        }
    }
    public  int numberofIsland(int[][]grid){
        int row = grid.length;
        int col = grid[0].length;
        int result=0;
        for (int i =0;i<row;i++){
            for(int j =0;j<col;j++){
                dfs(grid,i,j);
                result++;
            }
        }
        return result;
    }
public int minDays(int[][] grid) {
  int row = grid.length;
  int col = grid[0].length;
  int countofIsland=numberofIsland(grid);
  if(countofIsland==0 ||countofIsland>1){
      return 0;
  }else{
      for(int i =0;i<row;i++){
          for(int j =0;j<col;j++){
              if(grid[i][j]==1){
                  grid[i][j]=0;
                  countofIsland = numberofIsland(grid);
                  if(countofIsland==0 ||countofIsland>1){
                      return 0;
                  }
                   grid[i][j]=1;
              }
          }
      }
  }
  return 2;
}
List<List<Integer> >resutl = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates,target,0,list);
        return resutl;
    }

    private void combinationSum(int[] candidates, int target, int i, List<Integer> list) {
        if(target==0){
            resutl.add(new ArrayList<>(list));
        }
            for(int index=i;index<candidates.length;index++){
                combinationSum(candidates,target-candidates[i],index,list);
            }
    }
    public int strangePrinter(String s) {
        if(s.length()<=1){
            return 1;
        }
        return solve(0,s.length()-1,s);
    }

    private int solve(int left, int right, String s) {
        if(left==right){
            return 1;
        }
        if(left>right){
            return 0;
    }
        int i =left+1;
        while (i<=right&&s.charAt(i)==s.charAt(left)){
            i++;
        }
        if(i==right+1){
            return 1;
        }
        int simple = 1+solve(i,right,s);
        int approach=Integer.MAX_VALUE;
        for(int j =i;j<right;j++){
            if(s.charAt(j)==s.charAt(left)) {
                int answer= solve(i,j-i,s)+solve(j,right,s);
                approach=Math.max(approach,answer);
            }
        }
        return Math.max(approach,simple);
    }

    public String nearestPalindromic(String n) {
        int number = Integer.parseInt(n);
        int front=number;
        int back=number;
        while(true){
            if(Palindrome(front)){
                break;
            }
            front++;
        }
        while(true){
            if(Palindrome(back)){
                break;
            }
            back--;
        }
        int diff1=Math.abs(number-front);
        int diff2=Math.abs(number-back);
                if(diff1<diff2){
                    return String.valueOf(front);
                }else if(diff1>diff2){
                    return String.valueOf(back);
                }else{
                    return String.valueOf(back);
                }
    }

    private boolean Palindrome(int n) {
        int originalNumber = n;
        int reversedNumber = 0;

        while (n != 0) {
            int digit = n % 10;
            reversedNumber = reversedNumber * 10 + digit;
            n /= 10;
        }

        return originalNumber == reversedNumber;
    }
    public int[] missingRolls(int[] rolls, int mean, int n) {
    int m = rolls.length;
    if(m==n){
        return new int[0];
    }
    int sumofnumber = 0;
    for(int i :rolls){
        sumofnumber+=i;

    }
    int total = m+n;
    int requriedsum = (mean*total)-sumofnumber;
    return findSumofN(requriedsum,n);
    }
public static  int[]findSumofN(int Sum,int n ){
        int result[]=new int[n];

        return result;
}
    public static void main(String[] args) {

        System.out.println(rangeSum(new int[]{1,2,3,4},5,1,5));


    }

}

