import java.util.*;

public class Question {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int top  =0;
        int left =0;
        int bottom = matrix.length-1;
        int right = matrix[0].length-1;
        while(top<=bottom&&left<=right){
            for(int i = left;i<=right;i++){
                ans.add(matrix[top][i]);
            }
            top++;
            for(int i = top;i<=bottom;i++){
                ans.add(matrix[i][right]);
            }
            right--;
            if(top<=bottom){
                for(int i =right;i>=left;i--){
                    ans.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if(left<=right){
                for(int  i =bottom;i>=top;i--){
                    ans.add(matrix[i][left]);

                }
                left--;
            }
        }
        return ans;
    }
    static int fib(int n) {
        if(n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    int[] series(int n) {
        int[] ans = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            ans[i] = fib(i);
        }
        return ans;
    }
    public static void transpose(int n, int a[][]) {
        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }
    }
    static void rotateby90(int matrix[][], int n) {
        transpose(n, matrix);
      for(int i =0;i<n/2;i++){
          for(int j = 0;j<n;j++){
              int temp = matrix[i][j];
              matrix[i][j]=matrix[n-1-i][j];
              matrix[n-1-i][j]=temp;
          }
      }
    }


    public Stack<Integer> insertAtBottom(Stack<Integer> st, int x) {
      Stack<Integer> a1 = new Stack<>();
      Stack<Integer> ans = new Stack<>();
      ans.add(x);
      while (!st.isEmpty()) {
          a1.add(st.pop());
      }
      while (!a1.isEmpty()) {
          ans.add(a1.pop());
      }
      return ans;
    }

    public static int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        int left = 0;
        int right = 0;
        int ans = 0;
        while (left<=right&& right<nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            if(map.get(nums[right])<=k){
                ans = Math.max(ans, right - left + 1);
                right++;
            }
            else{
                while (map.get(nums[left]) > k) {
                    map.put(nums[left], map.get(nums[left]) - 1);
                    left++;
                }
            }
        }
        return ans;
    }
    public static int calculateDesktopProductIDs(char[] productID) {
        int desktopCount = 0;

        // Iterate through the product IDs
        for (char id : productID) {
            // Check if the product ID is a desktop product ID
            if (!isLaptopProductID(id)) {
                desktopCount++;
            }
        }

        return desktopCount;
    }

    // Function to check if the given character is a laptop product ID
//
    private static boolean isLaptopProductID(char id) {
        String laptopProductIDs = "aioueAIOUE";
        return laptopProductIDs.contains(String.valueOf(id));
    }



    public static int FindSecretCode(int secretCode, int firstKey, int secondKey) {
        // Calculate (S^N % 10) using modular exponentiation
        long base1 = secretCode;
        long exponent1 = firstKey;
        long modulus1 = 10;
        long result1 = modularExponentiation(base1, exponent1, modulus1);

        // Calculate ((S^N % 10)^M) using modular exponentiation
        long base2 = result1;
        long exponent2 = secondKey;
        long modulus2 = 1000000007;
        long result2 = modularExponentiation(base2, exponent2, modulus2);

        return (int) result2; // Convert the result to int and return
    }

    // Function to perform modular exponentiation
    private static long modularExponentiation(long base, long exponent, long modulus) {
        if (modulus == 1) return 0; // If modulus is 1, return 0

        long result = 1;
        base = base % modulus; // Take modulo of base

        while (exponent > 0) {
            // If exponent is odd, multiply base with result
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }

            // exponent must be even now
            exponent = exponent >> 1; // Divide exponent by 2
            base = (base * base) % modulus; // Change base to base^2 % modulus
        }

        return result;
    }
    public int longestPalindrome(String s) {
        int arr[]= new int[26];
        for(char c :s.toCharArray()){
            arr[c-'a']++;

        }
      int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i)-'a';
            if(arr[c]%2==0){
                result+=2;
                arr[c]-=2;
            }

        }
        return result;
    }

    public boolean exist(char[][] board, String word) {
        int index = 0;
        for(int i =0;i<board.length;i++){
            for(int j =0;j<board[i].length;j++){
                if(board[i][j]==word.charAt(index)&&find(board,i,j,index+1,word)){
                    return true;
                }
            }
        }
        return false;

    }

    private boolean find(char[][] board, int row, int col, int index,String word) {

        if(index==word.length()){
            return true;
        }
        if(row<0||col<0||row>=board.length||col>=board.length){
            return false;
        }
        char temp = board[row][col];
        board[row][col]='$';
        boolean result = (find(board,row,col+1,index+1,word)||
                find(board,row,col-1,index+1,word)||
                        find(board,row+1,col,index+1,word)||
                find(board,row-1,col,index+1,word));
        board[row][col]=temp;
        return result;
    }


    public boolean checkIfPangram(String sentence) {
        sentence = sentence.toLowerCase();
        int arr[] = new int[26];
        for (int i = 0; i < sentence.length(); i++) {
            arr[sentence.charAt(i)-'a']++;

        }
        for (int a :arr){
            if(a==0)return false;
        }
        return true;
    }

    public String defangIPaddr(String address) {
        return  address.replace(".","[.]");
    }


    public int lengthOfLastWord(String s) {
        s=s.trim();
        int ans = 0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' '){
                ans++;
            }else {
                break;
            }

        }return ans;
    }


    public int subarraysWithKDistinct(int[] nums, int k) {
        int i=0;
        int j = 0;
        int n = nums.length-1;
        int i_max = 0;
        int result = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        while(j<n){
            map.put(nums[j],map.getOrDefault(nums[j],0)+1);

            while(map.size()>k){
                map.put(nums[i],map.get(nums[i])-1);
                if(map.get(nums[i])==0){
                    map.remove(nums[i]);
                }
                i++;
                i_max=i;
            }
            while (map.get(nums[i])>1){
                map.put(nums[i],map.get(nums[i])-1);
                i++;
            }

            if(k==map.size()){
                result+=(1+i-i_max);
            }

         j++;
        }
        return result ;
    }

    public String makeGood(String s) {
       Stack<Character> st = new Stack<>();
       for(int i =0;i<s.length();i++){
           if(!st.isEmpty() && Math.abs(s.charAt(i) - st.peek()) == 32){
              st.pop();
           }else {
               st.push(s.charAt(i));
           }
       }
       return st.toString();
    }
    int[][] mem = new int[101][101];
    public boolean checkValidString(String s) {


        for (int i = 0; i < mem.length; i++) {
            Arrays.fill(mem[i], -1);
        }


        return solve(0,0,s);
    }
    boolean solve(int index,int open ,String s ){
      if(index==s.length()){
          return open==0;
      }
      if(mem[index][open]!=-1){
          int a = mem[index][open];
          return a==1;
      }
      boolean ans = false;
      if(s.charAt(index)=='('){
               ans|=solve(index+1,open+1,s);
      } else if (s.charAt(index)==')') {
         if(open>0){
             ans|=solve(index+1,open-1,s);
         }

      }else{
          ans|= solve(index+1,open+1,s);
          ans|=solve(index+1,open,s);

          if(open>0){
              ans|=solve(index+1,open-1,s);
          }


      }
      if(ans){
          mem[index][open]=1;
      }else {
          mem[index][open]=0;
      }
       return ans;
    }
    public static int countStudents(int[] students, int[] sandwiches) {
         Stack<Integer> s = new Stack<>();
         for(int i =sandwiches.length-1;i>=0;i--){
             s.add(sandwiches[i]);
         }
         Queue<Integer> sand = new LinkedList<>();
        for (int i = 0; i < students.length; i++) {
            sand.add(students[i]);

        }
        for(int i =0;i<sandwiches.length;i++){
            if(Objects.equals(s.peek(), sand.peek())){
                s.pop();
                sand.remove();
            }
            else {
                int j =0;
                while (j<sand.size()&&s.peek()!=sand.peek()){
                    int temp = sand.remove();
                    sand.add(temp);
                    j++;
                }
            }
        }
        return sand.size();
    }
    public int[] deckRevealedIncreasing(int[] deck) {
          Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < deck.length; i++) {
            queue.add(i);
            
        }
        int result[] = new int[deck.length];
        Arrays.sort(deck);
        for (int i = 0; i < deck.length; i++) {
            int idx=queue.remove();
       result[idx]=deck[i];
            if(!queue.isEmpty()){
                queue.add(queue.remove());
            }
        }
        return result;
    }

//    public static String removeKdigits(String num, int k) {
//        String ans  ="";
//      int result = Integer.MAX_VALUE ;
//      int i =0;
//      int j = k;
//      StringBuilder c = new StringBuilder(num);
//        for (int l = 0; l < num.length()-k; l++) {
//
//                c.delete(1,1+k);
//                int temp  =0;
//            for (int m = 0; m <c.length() ; m++) {
//                Character chr = c.charAt(m);
//                temp =temp*10+Integer.valueOf(chr);
//            }
//          ans = String.valueOf(Math.min(result,temp));
//            c = new StringBuilder(num);
//            i++;
//            j++;
//
//
//        }
//        return ans;
//    }
public int kthSmallest(int[][] matrix, int k) {
  PriorityQueue<Integer> pq =  new PriorityQueue<>();
    int n = matrix.length;
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++){
            pq.add(matrix[i][j]);
            if(pq.size() > k) pq.remove();
        }
    return pq.remove();
}
    public static String removeKdigits(String num, int k) {
        String ans = "";
        int result = Integer.MAX_VALUE; // Initialize result to maximum possible value
        for (int l = 0; l <= num.length() - k; l++) { // Modify loop condition to include equality
            StringBuilder c = new StringBuilder(num);
            c.delete(l, l + k); // Delete k digits from l to l+k
            int temp = 0;
            for (int m = 0; m < c.length(); m++) {
                Character chr = c.charAt(m);
                temp = temp * 10 + Integer.valueOf(chr);
            }
            result = Math.min(result, temp); // Update result with minimum value found so far
        }
        ans = String.valueOf(result);
        return ans;
    }
    public static void main(String[] args) {
//        int nums[] = {3,1,1};
//             HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
//        }
//        System.out.println(maxSubarrayLength(nums, 1)); // Output should be 6
//        System.out.println(map);
//        System.out.println(map);
//        System.out.println(map.get(4));

//         HashMap<Integer,Integer> map = new HashMap<>();
//         int arr[] = {1,2,3,4,5,1,2,3,3,3,4};
//        for (int i = 0; i < arr.length  ; i++) {
//            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
//
//        }
//        System.out.println(new Question().longestPalindrome("abccccdd"));
//        System.out.println(new Question().makeGood("leEeetcode"));
        System.out.println( removeKdigits("1432219",3));
    }
}
