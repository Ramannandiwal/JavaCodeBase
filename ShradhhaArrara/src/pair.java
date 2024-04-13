public class pair {
    public static void pairprint(int arr[]){
        for(int i =0;i<arr.length;i++){
            for(int j =i+1;j<arr.length;j++){
                System.out.print("("+arr[i]+","+arr[j]+"),");
            }
            System.out.println();

        }
    }
    public  static  void printSubarray(int arr[]){
        for(int i =0;i<arr.length;i++){
            for(int j =i;j<arr.length;j++){
                System.out.print("{");
                for(int k = i;k<=j;k++){
                    if(k==j){
                        System.out.print(arr[k]);
                    }else{System.out.print(arr[k]+",");};

                }
             if(j==arr.length-1){
                 System.out.print("}");
             }
             else{
                 System.out.print("},");
             }
            }
            System.out.println();
        }
    }
    public  static  void printSubarraySum(int arr[]){
        int start_index=0;
        int end_index=0;
        int general_Sum = 0;
        for(int i =0;i<arr.length;i++){

            for(int j =i;j<arr.length;j++){
                int sum = 0;
                System.out.print("{");
                for(int k = i;k<=j;k++){
                    if(k==j){
                        System.out.print(arr[k]);
                    }else{System.out.print(arr[k]+",");};
                     sum+=arr[k];
                }
                if(j==arr.length-1){
                    System.out.print("}");
                }
                else{
                    System.out.print("},");
                }
               if(general_Sum<=sum){
                   start_index=i;
                   end_index=j;
                   general_Sum=sum;
               }
            }
            System.out.println();
        }
        System.out.println(general_Sum);
        System.out.println(start_index+"--------"+end_index);
        for(int i =start_index;i<=end_index;i++){
            System.out.print(arr[i]+" ");
        }
    }
    public  static  void  printsubarraysumwithprefix(int[] arr){
        int globalsum=0;
        int sum=0;
        int prefixsum[]=new int[arr.length];
        prefixsum[0]=arr[0];
        for(int i =1;i<arr.length;i++){
            prefixsum[i]=prefixsum[i-1]+arr[i];
        }
        for(int i=0;i<arr.length;i++){
            for(int j =i;j<arr.length;j++){
                sum=(i==0)?prefixsum[j]:prefixsum[j]-prefixsum[i-1];
                globalsum=Math.max(globalsum,sum);
            }
        }
        System.out.println(globalsum);
    }
    public  static  int maxsumkadane(int[]arr){
        int maxsum=0;
        int currentsum=0;
        for(int i =0;i<arr.length;i++){
            if(currentsum<0){
                currentsum=0;
            }else {
                currentsum+=arr[i];
            }
            maxsum=Math.max(currentsum,maxsum);
        }
        return maxsum;


    }
    public static void main(String[] args) {
   int arr[]={2,8,10};

   printsubarraysumwithprefix(arr);
        System.out.println(maxsumkadane(arr));
    }
}
