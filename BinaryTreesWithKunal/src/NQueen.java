import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueen {
    public static List<List<String>> solveNQueens(int n) {
    List<List<String>> list = new ArrayList<>();
    boolean board[][]= new boolean[n][n];
    helper(board,0,list);
    return list;
    }
//    static  int count = 0;
////    public static int count(boolean[][]board,int row){
////
////         helper(board,row);
////         return count;
////    }

    private static void  helper(boolean[][] board, int row,List<List<String>>list) {
        if(row==board.length){
            display(board,list);
return;

        }
        for(int col=0;col<board[0].length;col++){
            if(issafe(board,row,col)){
                board[row][col]=true;
helper(board,row+1,list);
board[row][col]=false;
            }
        }
    }
    public static void  display(boolean[][] board,List<List<String >> listmain ){

//        for(boolean[] x :board){
//            for(boolean y:x){
//                if(y){
//                  list.add("Q");
//                }else {
//                 list.add(".");
//                }
//            }
//
//
//        }

      for(int i =0;i<board.length;i++){

          List<String> list = new ArrayList<>();
          StringBuilder string = new StringBuilder("");
          for(int j =0;j<board.length;j++){
              if(board[i][j]){
                 string.append("Q");
              }else {
                  string.append(".");
              }
          }
        list.add(string.toString());
           listmain.add(list);

      }
    }

    private static boolean issafe(boolean[][]board,int row, int col) {
        for (int i =0;i<board.length;i++ ){
            if(board[row][i])return false;
        }
        for (int i =0;i<board.length;i++){
            if(board[i][col])return false;
        }
        int maxleft = Math.min(row,col);
        for(int i = 1;i<=maxleft;i++){
            if(board[row-i][col-i])return false;

        }
        int maxRight = Math.min(row,board.length-1-col);
        for(int i =1;i<=maxRight;i++){
            if(board[row-i][col+i])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean[][] board = new boolean[5][5];
//        System.out.println(solveNQueens(4));
        List<List<String>> list = new LinkedList<>();
        for(int i =0;i<3;i++){
            list.add(new LinkedList<>());
        }
        list.get(0).add("Raman");
        list.get(1).add("is");

    }

}
