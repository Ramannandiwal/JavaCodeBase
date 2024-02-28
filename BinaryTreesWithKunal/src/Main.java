import com.sun.jdi.IntegerValue;
import com.sun.jdi.Type;
import com.sun.jdi.VirtualMachine;

import java.util.*;

public class Main {




    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0;i<nums.length;i++){
            for(int j =i+1;j<nums.length;j++){
                for(int k = j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> list1 = new ArrayList<>();
                        list1.add(nums[i]);
                        list1.add(nums[j]);
                        list1.add(nums[k]);
                        list.add(list1);
                    }
                }
            }
        }
return list;
    }
    public static void main(String[] args)  {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));


    }

}
