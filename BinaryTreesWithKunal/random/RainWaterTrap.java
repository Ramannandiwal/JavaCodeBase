public class RainWaterTrap {
    public static int trap(int[] height) {
        if(height.length<=2){
            return 0;
        }

        boolean increasing =true;
        boolean decreasing =true;
        for (int i =0;i<height.length-1;i++){
            if(height[i]>height[i+1]){
                increasing=false;
                break;
            }
        }
        for (int i =height.length-1;i>=1;i--){
            if(height[i]<height[i-1]){
                decreasing=false;
                break;
            }
        }
        if(increasing ==true||decreasing==true){
            return 0;
        }


        int left[]=new int[height.length];
        left[0]=height[0];
        for(int i =1;i<height.length;i++){
            left[i]=Math.max(left[i-1],height[i]);
        }

        int right[]=new int[height.length];

        right[right.length-1]=height[height.length-1];
        for(int i=height.length-2;i>=0;i--){
            right[i]=Math.max(right[i+1],height[i]);
        }

        int volume =0;
        for(int i =0;i<height.length;i++){
            volume+= Math.abs((Math.min(left[i],right[i]))-height[i]);
        }
        return volume;
    }
    public static void main(String[] args) {
       int[] height ={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }
}
