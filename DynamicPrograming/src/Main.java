import java.security.Key;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static int knapscan(int[] weight, int[] value, int capacity, int n) {
        if (n == weight.length || capacity == 0) {
            return 0;
        }
        if (weight[n] <= capacity) {
            int ans1 = value[n] + knapscan(weight, value, capacity - weight[n], n + 1);
            int ans2 = knapscan(weight, value, capacity, n + 1);
            return Math.max(ans2, ans1);
        } else {
            return knapscan(weight, value, capacity, n + 1);
        }
    }

    public static int knapscakmemo(int[] weight, int[] value, int capacity, int n, int[][] dp) {
        if (n == weight.length || capacity == 0) {
            return 0;
        }
        if (dp[n][capacity] != -1) {
            return dp[n][capacity];
        }
        if (weight[n] <= capacity) {
            int ans1 = value[n] + knapscakmemo(weight, value, capacity - weight[n], n + 1, dp);
            int ans2 = knapscakmemo(weight, value, capacity, n + 1, dp);
            dp[n][capacity] = Math.max(ans2, ans1);
            return dp[n][capacity];
        } else {
            dp[n][capacity] = knapscakmemo(weight, value, capacity, n + 1, dp);
            return dp[n][capacity];
        }
    }
    public int findMaxK(int[] nums) {
    int largest = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1 ; j <nums.length ; j++) {
                if(nums[i]==Math.abs(nums[j])){
                    largest=Math.max(nums[i],largest);
                }

            }

        }
        return largest;
    }
    public long maximumHappinessSum(int[] happiness, int k) {
       PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compare);
       for(int i :happiness){
           queue.add(i);
       }
       long result =0;
       int count =0;
        for (int i = 0; i < k; i++) {
            int top =queue.poll();
            result+=(top-count);
            count++;

        }
        return result;
    }


    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingDouble(a->(double) a[0]/a[1]));
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(queue.size()>k){
                    queue.poll();
                }
                queue.add(new int[]{arr[i],arr[j]});
            }

        }
        return queue.poll();

    }
    public static void main(String[] args) {
        int value[] = {15, 14, 10, 45, 30};
        int weight[] = {2, 5, 1, 3, 4};
        int capacity = 7;
        int dp[][] = new int[value.length + 1][capacity + 1]; // Corrected size
        for (int i[] : dp) {
            Arrays.fill(i, -1);
        }
        System.out.println(knapscan(weight, value, capacity, 0));
        System.out.println(knapscakmemo(weight, value, capacity, 0, dp));
    }
}
