import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()){
            return 0;
        }
        int ans = 0;
        int left = 0;
        int arr[]=new int[256];
        for(int right =0;right<s.length();right++){
            char currentchar = s.charAt(right);
            while (arr[currentchar-'a']>0){
                arr[s.charAt(left)-'a']--;
                left++;
            }
            arr[currentchar-'a']++;
            ans=Math.max(right-left+1,ans);
        }
        return ans-1;
    }
    public int getCommon(int[] nums1, int[] nums2) {
        int first =0;
        int second = 0;
        while (first<nums1.length&&second<nums2.length){
            if(nums1[first]==nums2[second]){
                return nums1[first];
            }
            if(nums1[first]>nums2[second]){
                second++;
            }else {
                first++;
            }
        }
        return -1;
    }

    public static String longestPalindrome(String s) {
        String result = "";
        for(int i =0;i<s.length();i++){
            for(int j =i+1;j<s.length();j++){
                String substrings= s.substring(i,j);
                if(helper(substrings)){
                    result= (result.length()>substrings.length())?result:substrings;
                }
            }
        }
return result;
    }
    public static boolean helper(String s ){
        int start = 0;
        int end = s.length()-1;
        while (start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int[] result = new int[nums1.length+nums2.length];
        int first =0;
        int k =0;
        int second = 0;
        while (first<nums1.length&&second<nums2.length){
            if(nums1[first]<=nums2[second]){
                result[k]=nums1[first];
                first++;
            }else {
                result[k]=nums2[second];
                second++;
            }
            k++;
        }
        while (first<nums1.length){
            result[k]=nums1[first];
            first++;
            k++;
        }
        while (second<nums2.length){
            result[k]=nums2[second];
            second++;
            k++;
        }
        int length  = result.length;
        if(length%2==0){
            int half = result[length/2-1];
            int half2=result[(length/2)];
            return (double) (half + half2) /2;
        }else {
            return result[length/2];
        }


    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        int newlenght = Math.min(nums1.length, nums2.length);
        int[] result = new int[newlenght];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i =0;
        int j =0;
        int k =0;
        while(k<result.length&&i<nums1.length&&j<nums2.length){
            if(nums1[i]==nums2[j]){
                result[k]=nums1[i];
                i++;
                j++;
                k++;
            }else{
                if(nums1[i]>nums2[j]){
                    j++;

                }else{
                    i++;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersection(new int[]{4,9,5},new int[]{9,4,9,8,4})));
    }
}