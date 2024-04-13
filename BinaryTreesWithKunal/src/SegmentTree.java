public class SegmentTree {
    private static class Node{
        int data;
        int startinterval;
        int endinterval;
        Node left;
        Node right;
        public Node(int startinterval,int endinterval){
            this.startinterval=startinterval;
            this.endinterval=endinterval;
        }

    }
    Node root;
    public SegmentTree(int[] arr){
        this.root=constructTree(arr,0,arr.length-1);

    }

    private Node constructTree(int[] arr, int start, int end) {
        int mid = start+(end-start)/2;
        if(start==end){
            Node leaf = new Node(start,end);
            leaf.data=arr[start];
            return leaf;
        }
        Node node = new Node(start,end);
        node.left=constructTree(arr,start,mid);
        node.right=constructTree(arr,mid+1,end);
        node.data=node.left.data+node.right.data;
        return node;  
    }
    public void display(){
        display(root);
    }

    private void display(Node node) {
        String str = "";
        if(node.left!=null){
            str = str+"Interval=["+node.left.startinterval +" - "+node.left.endinterval+"]"+"and data:"+node.left.data+"-->";
        }
        else{
            str+=str+"No left Child";
        }
        str = str+"Interval=["+node.startinterval +" - "+node.endinterval+"]"+"and data:"+node.data+"-->";
        if(node.right!=null){
            str = str+"Interval=["+node.right.startinterval +" - "+node.right.endinterval+"]"+"and data:"+node.right.data;
        }
        else{
            str+=str+"No right Child";

        }
        System.out.println(str+"\n");
        if(node.left!=null){
            display(node.left);
        }
        if(node.right!=null){
            display(node.right);
        }
    }
    public int query( int q1,int q2){
        return this.query(this.root,q1,q2);
    }

    private int query(Node node, int q1, int q2) {
        // If the node's interval lies completely within the query interval
        if (node.startinterval >= q1 && node.endinterval <= q2) {
            return node.data;
        }
        // If the node's interval lies outside the query interval
        else if (node.endinterval < q1 || node.startinterval > q2) {
            return 0;
        }
        // If the node's interval overlaps partially with the query interval
        else {
            return query(node.left, q1, q2) + query(node.right, q1, q2);
        }
    }

    public int update(int index,int value){
        this.root.data=update(root,index,value);
        return root.data;
    }
    public  int  update(Node node,int index,int value){
        if(index>=node.startinterval&&index<=node.endinterval){
            if(index==node.startinterval&&index==node.endinterval){
                node.data=value;
                return node.data;
            }else{
                int ansleft =update(node.left,index,value);
                int ansright =update(node.right,index,value);
                node.data=ansleft+ansright;
                return node.data;
            }
        }
        return node.data;
    }
}
