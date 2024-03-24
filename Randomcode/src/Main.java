import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
    public static void main(String[] args) {

        System.out.println(reverseWords("a good   example"));


    }



}