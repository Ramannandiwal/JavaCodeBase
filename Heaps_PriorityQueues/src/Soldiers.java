import java.util.PriorityQueue;
import java.util.Scanner;

public class Soldiers {
    static  class  week implements Comparable<week>{
        int index;
        int soldiers;
        week(int index, int soldiers){
            this.index=index;
            this.soldiers=soldiers;

        }



        @Override
        public int compareTo(week o) {
            if(this.soldiers==o.soldiers){
                return  this.index-o.index;
            }else {
                return this.soldiers-o.soldiers;
            }
        }
    }
    public static void main(String[] args) {
        PriorityQueue<week> pq = new PriorityQueue<>();

     int matrix[][]={
             {1,0,0,0},
             {1,1,1,1},
             {1,0,0,0},
             {1,0,0,0}


     };
     for(int i=0;i<matrix.length;i++){
         int count = 0;
         for(int j =0;j<matrix[i].length;j++){
             count+=matrix[i][j]==1?1:0;
         }
        pq.add(new week(i,count));
     }
     int k = new Scanner(System.in).nextInt();
   for (int i =0;i<k;i++){
       System.out.println("week rows are "+"R"+pq.remove().index);
   }
    }
}
