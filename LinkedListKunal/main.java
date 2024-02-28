package LinkedListKunal;

import java.util.*;

public class main {

    public static void main(String[] args) {

    }


    public Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
     Stack<Integer> s = new Stack<>();
     Queue<Integer> result = new LinkedList<>();
     for(int i =1;i<=k&&!q.isEmpty();i++){
         s.push(q.remove());
     }
     while (!s.isEmpty()){
         result.add(s.pop());
     }
     while (!q.isEmpty()){
         result.add(q.remove());
     }
     return result;
    }
}
