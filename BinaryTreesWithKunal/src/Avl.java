public class Avl {
    public static void main(String[] args) {
   Avl tree = new Avl();

for (int i =0;i<1000;i++){
    tree.insert(i);
}
        System.out.println(tree.height());
}
    private  class  Node{
        Node left;
        Node right;
        int value;
        int height;
        Node (int value){
            this.value=value;
        }
        public int getValue(){
            return this.value;
        }
    }
    private Node root;
    Avl(){

    }
    public  int height(){
        return height(root);
    }

    private int height(Node node){
        if(node==null)return -1;
        return node.height;
    }
    public boolean isempty(){
        return root==null;
    }
    public void display(){
        display(root,"Root Node: ");
    }
    private void display(Node node ,String details){
        if(node==null){
            return;
        }
        System.out.println(details+node.getValue());
        display(node.left,"left child of the "+node.getValue()+" :");
        display(node.right,"Right child of the "+node.getValue()+" :");

    }
    public void insert(int value){
        root=insert(value,root);
    }
    public  void insert(int nums[]){
        for(int i =0;i<nums.length;i++){
            this.insert(nums[i]);
        }
    }
    private Node insert(int value , Node node){
        if(node==null){
            node=new Node(value);
            return node;
        }
        if(value<node.value){
            node.left=insert(value,node.left);
        }
        if(value>node.value){
            node.right=insert(value,node.right);
        }
        node.height=Math.max(height(node.left),height(node.right))+1;
        return rotate(node);


    }
    private  Node rotate(Node node){
        if(height(node.left)-height(node.right)>1){
            //that means it is left heavy and left heavy has two cases
            if(height(node.left.left)-height(node.left.right)>0){
                //than it is left left case
                return rightrotate(node);
            }
            if(height(node.left.left)-height(node.left.right)<0){
                //than it is left left case
                node.left=leftrotate(node.left);
                return rightrotate(node);
            }
        }

        if(height(node.left)-height(node.right)<-1){
            //that means it is left heavy and left heavy has two cases
            if(height(node.right.left)-height(node.right.right)<0){
                //than it is right  rigth case
                return leftrotate(node);
            }
            if(height(node.right.left)-height(node.right.right)>0){
                //than it is left left case
                node.right=rightrotate(node.right);
                return leftrotate(node);
            }
        }
        return  node;
    }
    public Node rightrotate(Node p){
        Node c = p.left;
        Node  t = c.right;
        c.right=p;

        p.left=t;
        p.height=Math.max(height(p.left),height(p.right))+1;
        c.height=Math.max(height(c.left),height(c.right))+1;
        return c;
    }
    public Node leftrotate(Node c){
     Node p = c.right;
     Node t = p.left;
     p.left=c;
     c.right=t;
        p.height=Math.max(height(p.left),height(p.right))+1;
        c.height=Math.max(height(c.left),height(c.right))+1;
        return p;
    }
    public boolean balanced(){
        return balanced(root);
    }
    private boolean balanced(Node node){
        if(node==null){
            return  true;
        }
        return Math.abs(height(node.left)-height(node.right))<=1 &&balanced(node.left) &&balanced(node.right);
    }
}
