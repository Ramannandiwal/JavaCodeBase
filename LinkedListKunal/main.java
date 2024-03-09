package LinkedListKunal;

import java.util.*;

public class main {

    public static void main(String[] args) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int arr[]={1,2,3,4,5};
        for(int i:arr){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        int val =Integer.MIN_VALUE;
        for(int i:map.values()){
           val=Math.max(val,i);
        }
        int count  =0;
        for(int i :map.values()){
            if(i==val){
                count++;
            }
        }
        System.out.println(count);

    }



}
