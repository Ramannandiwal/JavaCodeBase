import java.util.*;

public class BinaryTree {

 private static  class TreeNode {
     private int val;
     TreeNode left;
     TreeNode right;
  public TreeNode(int val){
      this.val = val;

     }

 }
 private TreeNode root;
 public  BinaryTree(){

 }
 public void  insert(int value){

 }
// public  void populate(){
//     Scanner sc = new Scanner(System.in);
//     System.out.println("Enter the root node value");
//     int value = sc.nextInt();
//     root=new Node(value);
//     populate(sc,root);
// }
//
//    private void populate(Scanner sc,Node root) {
//        System.out.println("Do you want to insert to the left of the  "+root.value);
//        boolean left = sc.nextBoolean();
//        if(left){
//            System.out.println("enter the value of the left of "+root.value);
//            int value = sc.nextInt();
//            root.left=new Node(value);
//            populate(sc,root.left);
//        }
//        System.out.println("Do you want to insert to the right of the  "+root.value);
//
//       boolean right = sc.nextBoolean();
//       if(right){
//           System.out.println("enter the value of the right of "+root.value);
//           int value=sc.nextInt();
//           root.right=new Node(value);
//           populate(sc,root.right);
//       }
//
//    }
Scanner sc = new Scanner(System.in);
    public void populate(){

        System.out.println("Enter your Root Node");
      root = new TreeNode(sc.nextInt());
     populate(root);
    }
    private void populate(TreeNode treeNode){

        System.out.println("Do you want to insert to the left of " + treeNode.val);
        boolean left = sc.nextBoolean();
        if(left){
            System.out.println("Enter the value of the left Node");
            treeNode.left= new TreeNode(sc.nextInt());
            populate(treeNode.left);
        }
        System.out.println("Do you want to insert to the right of the "+ treeNode.val);
        boolean right =sc.nextBoolean();
        if(right){
            System.out.println("Enter the value of the right node ");
            treeNode.right=new TreeNode(sc.nextInt());
            populate(treeNode.right);
        }

    }
    public  void display( ){
     display(root,"");
    }

    private void display(TreeNode root, String s) {
     if(root==null){
         return;
     }
        System.out.println(s+root.val);
        display(root.left,s+"\t");
        display(root.right,s+"\t");
    }
    public List<List<Integer>> pathSum(int targetSum){
        return pathSum(this.root,targetSum);

    }
    private List<List<Integer>> pathSum(TreeNode root, int targetSum) {
           List<List<Integer>> result = new ArrayList<>();
           List<Integer> list = new ArrayList<>();
           int count = 0;
        helper(root, targetSum, count, result, list);

           return result;
    }


    private void helper(TreeNode root, int targetSum, int count, List<List<Integer>> result, List<Integer> list) {
        if (root == null) return;

        count += root.val; // Update the count with the current node's value

        list.add(root.val);

        if (root.left == null && root.right == null && count == targetSum) {
            // If the current node is a leaf and count equals targetSum, add the current list to result
            result.add(new ArrayList<>(list)); // Add a new instance of list
        }

        // Recursively explore left and right subtrees
        helper(root.left, targetSum, count, result, list);
        helper(root.right, targetSum, count, result, list);

        // Backtrack: Remove the last element from the list before returning from recursion
        list.remove(list.size() - 1);
    }

    public void insert() {
        this.root = new TreeNode(5);
        this.root.left = new TreeNode(4);
        this.root.right = new TreeNode(8);
        this.root.left.left = new TreeNode(11);
        this.root.left.left.left = new TreeNode(7);
        this.root.left.left.right = new TreeNode(2);
        this.root.right.left = new TreeNode(13);
        this.root.right.right = new TreeNode(4);
        this.root.right.right.left = new TreeNode(5);
        this.root.right.right.right = new TreeNode(1);
    }
    public int findBottomLeftValue(TreeNode root) {
       List<List<TreeNode>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int levelsize=queue.size();

            List<TreeNode> list = new ArrayList<>();

            for (int i =0;i<levelsize;i++){
                TreeNode currentNode= queue.poll();
                if(currentNode.left!=null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right!=null){
                    queue.offer(currentNode.right);
                }
                list.add(currentNode);
            }
            result.add(list);

        }
        List<TreeNode> resutlt= new ArrayList<>();
        resutlt=result.get(result.size()-1);
        return resutlt.get(0).val;
    }
    public boolean isEvenODDTREE(){
        return isEvenOddTree(this.root);
    }
    public boolean isEvenOddTree(TreeNode root) {
       List<List<Integer>> result = new ArrayList<>();
       List<Integer> list= new ArrayList<>();
       helper(root, result,list);
       for(int i =0;i<result.size();i++){
           List<Integer> temp = result.get(i);
           for(int j = 0;j<temp.size()-1;j++){
               if(i%2==0){
                 if(!(  temp.get(j)%2!=0&&temp.get(j+1)%2!=0&&(temp.get(j)<temp.get(j+1)))){
                     return false;
                 }
               }
               else{
                   if(!(    temp.get(j)%2==0&&temp.get(j+1)%2==0&&(temp.get(j)>temp.get(j+1)))){
                       return false;
                   }
               }
           }
       }
        System.out.println(result);
    return true;
    }

    private void helper(TreeNode root, List<List<Integer>> result, List<Integer> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int levelsize=queue.size();

            List<Integer> temp = new ArrayList<>();

            for (int i =0;i<levelsize;i++){

                TreeNode currentNode= queue.poll();
                if(currentNode.left!=null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right!=null){
                    queue.offer(currentNode.right);
                }
                temp.add(currentNode.val);
            }
            result.add(temp);

        }

    }
public  void insertiseven(){
        this.root = new TreeNode(2);
    root.left=new TreeNode(12);
    root.right = new TreeNode(8);
    root.left.left=new TreeNode(5);
    root.left.right=new TreeNode(9);
    root.left.left.left=new TreeNode(18);
    root.left.left.right=new TreeNode(16);

}
    public static void main(String[] args) {






    }
}

