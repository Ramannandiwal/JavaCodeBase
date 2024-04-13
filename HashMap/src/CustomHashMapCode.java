import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class CustomHashMapCode {

    static class CHashMap<K,V>{
        public  class  Node{
             K key ;
             V value;

             public Node(K key , V value ){
                 this.key=key;
                 this.value=value;
             }

        }
        private  int n ;
        private  int N ;

        private LinkedList<Node> buckets[];
          public  CHashMap(){
              this.N=4;
              this.buckets=new LinkedList[4];

              for(int i =0;i<4;i++){
                  this.buckets[i]= new LinkedList<>();
              }

          }
          private int hashfunction(K key ){
              int hc = key.hashCode();
              hc  = Math.abs(hc);
              return hc%N;
          }
          private int SeachinLL(K key,int bi){
              LinkedList<Node> ll = buckets[bi];
              int di =0;
              for(int i =0;i<ll.size();i++){
                  Node node = ll.get(i);
                  if(node.key.equals(key)){
                      return di;
                  }
                  di++;
              }
              return -1;
          }
          private  void rehash(){
              LinkedList<Node> oldBucket[] = buckets;
              buckets = new LinkedList[N*2];
              N=2*N;
              for(int i =0;i<buckets.length;i++){
                  buckets[i]= new LinkedList<>();
              }

              for(int i =0;i<oldBucket.length;i++){
                  LinkedList<Node> ll = oldBucket[i];
                  for(int j =0;j<ll.size();j++){
                      Node node = ll.remove();
                      put(node.key,node.value);
                  }
              }
          }
          public void put (K key , V value ){
              int bi = hashfunction(key);
              int di = SeachinLL(key,bi);
                 if(di != -1){
                     Node node = buckets[bi].get(di);
                     node.value=value;
                 }else{
                     buckets[bi].add(new Node(key,value));
                     n++;
                 }


                 double lamda = n/N;
                 if(lamda>2.0){
                     rehash();
                 }

          }

          public boolean containsKeys (K key ){

              int bi = hashfunction(key);
              int di = SeachinLL(key,bi);
              if(di!=-1){
                  return true;
              }
              else{
                  return false;
              }


          }
          public V get(K key){
              int bi = hashfunction(key);
              int di = SeachinLL(key,bi);
              if(di!=-1){
                  Node node = buckets[bi].get(di);
                  return node.value;
              }
              else{
                  return null;
              }
          }
         public V remove (K key){
             int bi = hashfunction(key);
             int di = SeachinLL(key,bi);
             if(di!=-1){
                 Node node = buckets[bi].remove(di);
                 n--;
                 return node.value;
             }
             else{
                 return null;
             }
         }
         public ArrayList<K> keySet(){
             ArrayList<K> keys = new ArrayList<>();
             for(int i =0;i<buckets.length;i++){
                 LinkedList<Node> ll = buckets[i];
                 for(int j =0;j<ll.size();j++){
                     Node node = ll.get(j);
                     keys.add(node.key);
                 }
             }
             return keys;
         }
         public boolean isEmpty(){
             return n==0;
         }

        public static void main(String[] args) {
            CHashMap<String,Integer> map = new CHashMap<>();
            map.put("ram",1);
            map.put("shyam",2);
            map .put("kal",3);
            map.put("Raman",4);
            map.put("shyams",5);

            System.out.println(map.keySet());
            map.remove("shyams");
            System.out.println(map.keySet());
        }
    }
}
