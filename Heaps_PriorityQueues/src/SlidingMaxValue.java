import java.util.Arrays;
import java.util.PriorityQueue;

public class SlidingMaxValue {
    static  class  Pair implements Comparable<Pair>{
        int idx;
        int value;
        Pair(int idx,int value){
            this.idx=idx;
            this.value=value;
        }

        @Override
        public int compareTo(Pair o) {
            return o.value-this.value;
        }
    }

    public static void main(String[] args) {
        int arr[]={1,3,-1,-3,5,3,6,7};
        int k =3;
        int result[]= new int[arr.length-k+1];
        PriorityQueue<Pair> queue = new PriorityQueue<>();
    for(int i =0;i<k;i++){
        queue.add(new Pair(i,arr[i]));
    }
    result[0]=queue.peek().value;
    for(int i =k;i<arr.length;i++){
        while (!queue.isEmpty() &&queue.peek().idx<=(i-k)){
            queue.remove();
        }
        queue.add(new Pair(i,arr[i]));
        result[i-k+1]=queue.peek().value;


    }
//        System.out.println(Arrays.toString(result));
        System.out.println("raman".hashCode());
    }
}
