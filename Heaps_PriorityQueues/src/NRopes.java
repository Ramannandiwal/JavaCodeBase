import java.util.PriorityQueue;

public class NRopes {
    public static void main(String[] args) {
        int arr[]={2,3,3,4,6};
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int j : arr) {
            queue.add(j);
        }
        int cost = 0;
        while (queue.size()>1){
            int min1=queue.remove();
            int min2=queue.remove();
            cost+=min2+min1;
            queue.add(min1+min2);

        }
        System.out.println(cost);
    }
}
