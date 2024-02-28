import java.util.ArrayList;

public class maze {
    public static  ArrayList PathTracker(String up, int row ,int col){
        if(row==2&&col==2){
            ArrayList list = new ArrayList();
            list.add(up);
            return list;
        }

        ArrayList Right = new ArrayList();
//        ArrayList Down = new ArrayList();
        if(row==2){
         Right.addAll(PathTracker(up+"R",row,col+1));
        }
        else if(col==2){
Right.addAll(PathTracker(up+"D",row+1,col));
        }
        else {
          Right.addAll(  PathTracker(up+"R",row,col+1));
          Right.addAll(PathTracker(up+"D",row+1,col));
          Right.addAll(PathTracker(up+"#",row+1,col+1));
        }
        ;
        return Right;
    };
    public static void main(String[] args) {
        System.out.println(PathTracker("",0,0));
    }
}
