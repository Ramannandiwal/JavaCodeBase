import java.util.ArrayList;

public class InsertinHeap {
    ArrayList<Integer> arr = new ArrayList<>();
    public void  insert(int data ){
        arr.add(data);

        int x = arr.size()-1;

        while (x>0 && arr.get(x)<arr.get((x-1)/2)){
            int temp = arr.get(x);
            arr.set(x,arr.get((x-1)/2));
            arr.set((x-1)/2,temp);
            x = (x-1)/2;

        }
    }
    public void display(){
        System.out.println(arr.toString());
    }
    public int peek (){
        return arr.get(0);
    }
    private  void heapify(int idx){
        int left = 2*idx+1;
        int right = 2*idx+2;
        int min = idx;
        if(left<arr.size() && arr.get(left)<arr.get(min)){
            min = left;
        }
        if(right<arr.size() && arr.get(right)<arr.get(min)){
            min = right;
        }
        if(min!=idx){
            int temp = arr.get(idx);
            arr.set(idx,arr.get(min));
            arr.set(min,temp);
            heapify(min);
        }

    }
    public  int remove (){
        int x = arr.get(0);
        int temp = arr.get(0);
        arr.set(0,arr.get(arr.size()-1));
        arr.set(arr.size()-1,temp);
        arr.remove(arr.size()-1);
        heapify(0);
        return x;
    }
    public boolean isEmpty(){
        return arr.isEmpty();
    }

    public static void main(String[] args) {

        InsertinHeap obj = new InsertinHeap();
        obj.insert(13);
        obj.insert(10);

        obj.insert(14);
        obj.insert(15);
        obj.insert(6);
        obj.insert(7);
 while (!obj.isEmpty()){
     System.out.println(obj.remove());
 }

    }
}
