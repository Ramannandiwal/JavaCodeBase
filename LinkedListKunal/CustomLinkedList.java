package LinkedListKunal;

import org.w3c.dom.Node;

import java.util.List;

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

        System.out.print(temp.value+"-->");
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
        int value = Head.value;
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
        int value = Tail.value;

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
        int value = temp.next.value;
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
    private  class ListNode {
        private  int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

public ListNode(){

}
        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }


    }
    //questions and answeres
    public ListNode deleteDuplicates(ListNode head) {
      ListNode temp = Head;
      while (temp!=null&&temp.next!=null){
          if(temp.value==temp.next.value){
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
            if(first.value>second.value){
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
            if(list1.value<list2.value){
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
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyhead = new ListNode(-100);
        if(head.next!=null){
            dummyhead.next=head;
            head=head.next;
        }


        while (head!=null){
            ListNode temp = dummyhead.next;
            while(temp.value<head.value){
                temp=temp.next;
            }

        }
    }
}
