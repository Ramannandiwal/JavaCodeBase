import java.util.ArrayList;
import java.util.HashMap;

public class CustomLinkedList {
    private ListNode Head;
    private ListNode Tail;
    private int size;

    public CustomLinkedList() {
        this.size=0;

    }
    public  void insertFirst(int value){
        ListNode listNode = new ListNode(value);
        listNode.next=Head;
        Head= listNode;
        if(Tail==null){
            Tail=Head;
        }
        size+=1;
    }
public  void  display(){
    ListNode temp = Head;
    System.out.print("Head-->");
    while (temp!=null){

        System.out.print(temp.val +"-->");
        temp=temp.next;

    }
    System.out.println("End");

    }
    public void insert(int value, int index){
        if(index==0){
            insertFirst(value);
            return;
        }
        if(index==size){
            addLast(value);
            return;
        }
        ListNode temp =Head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        ListNode listNode =new ListNode(value,temp.next);
        temp.next= listNode;
        size++;
    }
    public  int deleteFirst(){
        int value = Head.val;
        Head=Head.next;
        if(Head==null){
            Tail=null;
        }
        size--;
        return value;  

    }
    public int deleteLast()
    {
        if(size<=1){
            return deleteFirst();
        }
        ListNode secondLast = get(size-2);
        int value = Tail.val;

        Tail=secondLast;
        Tail.next=null;
        return value;
    }
    public  int delete(int index){
        if(index==0){
            return deleteFirst();
        }
        if(index==size-1){
            return deleteLast();
        }
        ListNode temp = get(index-1);
        int value = temp.next.val;
        temp.next=temp.next.next;
        return value;

    }
public ListNode get(int index){
        ListNode listNode = Head;
        for(int i =0;i<index;i++){
            listNode = listNode.next;
        }
        return listNode;
}
    public  void  insertRec(int value,int index){
        Head=insertRec(value,index,Head);
    }

    private ListNode insertRec(int value, int index, ListNode listNode){
        if(index==0){
            ListNode temp = new ListNode(value, listNode);
            size++;
            return temp;
        }

        listNode.next=   insertRec(value,index-1, listNode.next);
        return listNode;
    }
    public  void addLast(int value){
        if(Tail==null){
            insertFirst(value);
            return;
        }
        ListNode listNode = new ListNode(value);
        Tail.next= listNode;
        Tail= listNode;
        size++;
    }
    public  class ListNode {
        private  int val;
        private ListNode next;

        public ListNode(int value) {
            this.val = value;
        }

public ListNode(){

}
        public ListNode(int value, ListNode next) {
            this.val = value;
            this.next = next;
        }


    }
    //questions and answeres
    public ListNode deleteDuplicates(ListNode head) {
      ListNode temp = Head;
      while (temp!=null&&temp.next!=null){
          if(temp.val ==temp.next.val){
              temp.next = temp.next.next;
          }
          else{
             temp = temp.next;
          }
      }
      return Head;
    }
    //quesiton 2 merge tow sorted list
//    public CustomLinkedList mergeTwoLists(CustomLinkedList list1, CustomLinkedList list2) {
//        CustomLinkedList ans = new CustomLinkedList();
//       Node f= list1.Head;
//       Node s= list2.Head;
//
//        while (f!=null&&s!=null){
//            if(f.value<=s.value){
//                ans.addLast(f.value);
//               f=f.next;
//            }
//            else{
//            ans.addLast(s.value);
//            s=s.next;
//            }
//        }
//        while(f!=null){
//            ans.addLast(f.value);
//            f=f.next;
//        }
//        while(s!=null){
//            ans.addLast(s.value);
//            s=s.next;
//        }
//        return ans;
//    }
    public boolean hasCycle(ListNode head) {
       ListNode fast = head;
       ListNode slow = head;
       while(fast!=null&&fast.next!=null){
           if(fast==slow){
               return true;
           }
           fast = fast.next.next;
           slow= slow.next;
       }
       return false;
    }
    //find the length of the cyclye
    public  int lengthCyclye(ListNode head){
        ListNode fast =head;
        ListNode slow =head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow =slow.next;
            if(fast==slow){
                int length=0;
                ListNode temp = slow;
                do{
                    length++;
                    temp=temp.next;
                }while (temp!=slow);
            }
        }
        return 0;
    }
    public ListNode detectCycle(ListNode head) {
    int lenght = 0;
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow= slow.next;
            if(fast==slow){
              lenght=lengthCyclye(slow);
              break;
            }

        }
        if(lenght==0){
            return null;
        }
     //find the start Node
        ListNode first=head;
        ListNode second = head;
        while (lenght>0){
            second=second.next;
            lenght--;
        }
        //keep moving both forward and meet cyclekt start
        while (first!=second){
            first=first.next;
            second=second.next;

        }
        return first;
    }
    public ListNode middleNode(ListNode head) {
     int length =0;
     ListNode temp= head;
     while (temp!=null){
         temp=temp.next;
         length++;
     }
     ListNode middleprevious=null;
     ListNode middle = null;
     for(int i =0;i<length/2;i++){
         if(middleprevious==null){
             middleprevious=head;

         }else {
             middleprevious=middleprevious.next;
         }
     }
     middle=middleprevious.next;
     middleprevious.next=null;
     return middle;
    }
    public void BubbleSort(){
        bubbleSort(size-1,0);
    }

    private void bubbleSort(int row, int col) {
        if(row==0){
            return ;
        }
        if(col<row){
            ListNode first = get(col);
            ListNode second = get(col+1);
            if(first.val >second.val){
                //swap
                if(first==Head){
                    Head=second;
                    first.next=second.next;
                    second.next=first;
                }else if(second==Tail){
                    ListNode previous=get(col-1);
                    previous.next=second;
                    Tail=first;
                    first.next=null;
                    second.next=Tail;
                }else{
                    ListNode previous = get(col-1);
                    previous.next=second;
                    first.next=second.next;
                    second.next=first;

                }
            }
            bubbleSort(row,col+1);
        }else {
            bubbleSort(row-1,0);
        }
    }
    public ListNode sortList(ListNode head) {
        if(head==null&&head.next==null){
            return head;
        }
        ListNode middle = middleNode(head);
        ListNode middleplusone = middle.next;
        middle.next=null;
        ListNode left = sortList(head);
        ListNode right = sortList(middleplusone);
        return mergeList(left,right);

    }
    public void reverse(ListNode node){
        if(node==Tail){
            Head=Tail;
return ;        }
        reverse(node.next);
        Tail.next=node;
        Tail=node;
        Tail.next=null;
    }
    //inplace revesal of the linkedlist
    public  void inplacereversal(){
     if(size<2){
         return;
     }
     ListNode prev= null;
     ListNode present = Head;
     ListNode next = present.next;
     while (present!=null){
    present.next=prev;
    prev=present;
    present=next;
    if(next!=null){
        next=next.next;
    }

     }
     Head=prev;

    }
    public ListNode reverseshardha(){
     Head=reverseShardhaKharpra(Head);
     return Head;
    }
    public ListNode reverseShardhaKharpra(ListNode head){
        ListNode previous = null;
        ListNode current = head;
        ListNode next;
        while(current!=null){
            next=current.next;
            current.next=previous;
            previous=current;
            current=next;

        }

        return previous;

    }
    private ListNode mergeList(ListNode list1, ListNode list2){
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while(list1!=null&&list2!=null){
            if(list1.val <list2.val){
                tail.next=list1;
                list1= list1.next;
                tail = tail.next;

            }else {
                tail.next=list2;
                list2=list2.next;
                tail=tail.next;
            }
        }
        tail.next=list1!=null?list1:list2;
        return dummyHead.next;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
         int length= 0;
         ListNode temp = head;
         while(temp!=null){
             temp = temp.next;
             length++;
         }
         temp=head;
         for(int i=1;i<=length;i++){
             temp=temp.next;
         }
         temp.next=temp.next.next;
         return head;
    }
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
     ListNode first = list1;
     for(int i =0;i<a-1;i++){
         first=first.next;
     }
     ListNode second =first;
     for(int i =a;i<=b;i++){
         second=second.next;
     }
     ListNode end = second.next;
     second.next=null;
     ListNode temp = list2;
     while (temp.next!=null){
         temp=temp.next;
     }
 first.next=list2;
     temp.next=end;
     return list1;
    }
    public ListNode mergeNodes(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode temp= head ;
        ListNode tm=head.next;

        while(temp!=null){
            int sum =0;
          while(temp.next!=null&&tm.next.val !=0){
              sum+=tm.val;
              tm =tm.next;
          }
          sum+=tm==null?0:tm.val;
          list.add(sum);
          temp =tm;
        }
        ListNode result = new ListNode();
        ListNode tem = result;
        for(int i =0;i<list.size();i++){
            ListNode r = new ListNode(list.get(i));
            tem.next=r;
            tem=tem.next;
        }
return result.next;
    }
    public ListNode modifiedList(int[] nums, ListNode head) {
        HashMap<Integer,Integer> map = new HashMap<>();

    for(int i=0;i<nums.length;i++){
        map.putIfAbsent(nums[i],map.getOrDefault(nums[i],0)+1);
    }
    ListNode dummyHead=new ListNode(-1);
    ListNode r = dummyHead;
    while (head!=null){
        int freq= map.get(head.val);
        if(freq>0){
            map.put(head.val,map.get(head.val)-1);
        }else{
            ListNode tempnode = new ListNode(head.val);
            r.next=tempnode;
            r=r.next;
        }
        head=head.next;
    }
    return dummyHead.next;
    }
}

public static void main(String[] args) {

}