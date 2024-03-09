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
    public static void main(String[] args) {
        System.out.println(helper("sabas"));
        System.out.println(longestPalindrome("s"));
    }
}