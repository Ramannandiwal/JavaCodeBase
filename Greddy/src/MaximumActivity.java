import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MaximumActivity {
    public static void main(String[] args) {
        int[] start = {1,0,3,5,8,5};
        int[] end =   {2,6,4,9,9,7};
        int activities[][]= new int[start.length][3];
        for(int i =0;i<start.length;i++){
            activities[i][0]=i;
            activities[i][1]=start[i];
            activities[i][2]=end[i];
        }
        Arrays.sort(activities, Comparator.comparingDouble(o ->o[2]));
        ArrayList<Integer> list = new ArrayList<>();
        int maxactiviy = 1;
        list.add(activities[0][0]);
        int endtime=activities[0][2];
        for(int i =1;i<activities.length;i++){
            if(activities[i][1]>=endtime){
                maxactiviy++;
                list.add(activities[i][0]);
                endtime=activities[i][2];
            }
        }

        System.out.println(maxactiviy);

        for(int item:list){
            System.out.print("A"+item+" ");
        }

    }
}