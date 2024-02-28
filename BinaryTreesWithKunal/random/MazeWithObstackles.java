public class MazeWithObstackles {

    public static void maze(int row, int col, String s , boolean[][]maze){
        if(row==maze.length-1&&col==maze[0].length-1){
            System.out.println(s);
            return;
        }
        if(maze[row][col]==false){
            return;
        }
        if(row<maze.length-1){
            maze(row+1,col,s+"D",maze);
        }
        if(col<maze[0].length-1) {
            maze(row,col+1,s+"R",maze);
        }
    }
    public static void main(String[] args) {
        boolean arr[][]={
                {true,true,true},
                {true,true,false},
                {true,true,true}
        };
        maze(0,0,"",arr);
    }
}
