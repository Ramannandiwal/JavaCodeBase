import java.util.ArrayList;
import java.util.Arrays;

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
    public static void main(String[] args) {


        int arr[][]={
                {1,3},
                {2,6},
                {8,10},
                {15,18}

        };
        System.out.println(merge(arr));

    }



}