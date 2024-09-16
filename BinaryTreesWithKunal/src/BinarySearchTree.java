import java.util.*;

public class BinarySearchTree {
    private  class TreeNode {
        TreeNode left;
        TreeNode right;
        TreeNode next;
        int val;
        int height;
        TreeNode(int val){
            this.val = val;
        }
        public int getVal(){
            return this.val;
        }
    }
    private TreeNode root;
    BinarySearchTree(){

    }
    public int height(TreeNode treeNode){
        if(treeNode ==null)return -1;
        return treeNode.height;
    }
    public boolean isempty(){
        return root==null;
    }
    public void display(){
        display(root,"Root Node: ");
    }
    private void display(TreeNode treeNode, String details){
        if(treeNode ==null){
            return;
        }
        System.out.println(details+ treeNode.getVal());
        display(treeNode.left,"left child of the "+ treeNode.getVal()+" :");
        display(treeNode.right,"Right child of the "+ treeNode.getVal()+" :");

    }
    public void insert(int value){
        root=insert(value,root);
    }
    public  void insert(int nums[]){
        for(int i =0;i<nums.length;i++){
            this.insert(nums[i]);
        }
    }
    private TreeNode insert(int value , TreeNode treeNode){
        if(treeNode ==null){
            treeNode =new TreeNode(value);
            return treeNode;
        }
        if(value< treeNode.val){
            treeNode.left=insert(value, treeNode.left);
        }
        if(value> treeNode.val){
            treeNode.right=insert(value, treeNode.right);
        }
        treeNode.height=Math.max(height(treeNode.left),height(treeNode.right))+1;
        return treeNode;
    }
//    public List<List<Integer>> bfs(Node root,List<List<Integer>> list){
//
//
//    }
//    public List bfs (){
//        List list = new ArrayList();
//        list.add(root.value);
//        return bfs(root,list);
//    }

//    private List bfs(Node node,List list) {
//        if(node==null)return list ;
//        if(node.left!=null){
//            list.add(node.left.value);
//        }
//        if(node.right!=null){
//            list.add(node.right.value);
//        }
//        bfs(node.left,list);
//        bfs(node.right,list);
//        return list;
//    }
//    public List<List<Integer>> bfswithlist(){
//        List<List<Integer>> list = new ArrayList<>();
//        list.get(0).add(root.value);
//        return  bfswithlist(root,list);
//    }
//
//    private List<List<Integer>> bfswithlist(Node node, List<List<Integer>> list) {
//        if(node==null){
//            return list;
//        }
//        List<Integer> temp = new ArrayList<>();
//        if(node.left!=null){
//            temp.add(node.left.value);
//        }
//        if(node.right!=null){
//            temp.add(node.right.value);
//        }
//        list.add(temp);
//        bfswithlist(node.left,list  );
//        bfswithlist(node.right,list);
//        return list;
//    }
//public List<List<Integer>> bfswithlist() {
//    List<List<Integer>> list = new ArrayList<>();
//    bfswithlist(root, list, 0); // Start the recursive traversal with root node and level 0
//    return list;
//}
//
//    private void bfswithlist(Node node, List<List<Integer>> list, int level) {
//        if (node == null) {
//            return;
//        }
//        if (level == list.size()) {
//            list.add(new ArrayList<>()); // Initialize list for the current level if it doesn't exist
//        }
//        list.get(level).add(node.value); // Add current node value to the list of current level
//
//        // Recursively traverse left and right subtrees with increased level
//        bfswithlist(node.left, list, level + 1);
//        bfswithlist(node.right, list, level + 1);
//    }
public List<List<Integer>> levelOrder(){
        return levelOrder(this.root);
}

    public TreeNode connect(TreeNode root) {
        if(root==null){
            return root;
        }
        List<List<TreeNode>> list = nextpointer(root);


        list.get(0).get(0).next=null;
        for(int i =1;i<list.size();i++){
            List<TreeNode> temp = list.get(i);
            for(int j =0;j<temp.size()-1;j++){
                TreeNode tempnode = temp.get(j);
                tempnode.next=temp.get(j+1);
            }
            temp.get(temp.size()-1).next=null;
        }
 return root;
    }

    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root==null)return list;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            int levelsize = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i =0;i<levelsize;i++){
                TreeNode currentTreeNode = queue.poll();
                temp.add(currentTreeNode.val);
                if(currentTreeNode.left!=null){
                    queue.offer(currentTreeNode.left);

                }
                if(currentTreeNode.right!=null){
                    queue.offer(currentTreeNode.right);
                }

            }
            list.add(temp);
        }
        return list;
    }

    public boolean isSymmetric(TreeNode root) {
       Queue<TreeNode> queue = new LinkedList<>();
       queue.add(root.left);
       queue.add(root.right);
       while (!queue.isEmpty()){
           TreeNode left= queue.poll();
           TreeNode right = queue.poll();
           if(left==null&&right==null)continue;
           if(left==null||right==null)return false;
           if(left.val !=right.val)return false;
           queue.add(left.left);
           queue.add(right.right);
           queue.add(left.right);
           queue.add(right.left);
       }
       return true;
    }

public List<List<TreeNode>> nextpointer(TreeNode treeNode){
    List<List<TreeNode>> list = new ArrayList<>();
    if(root==null)return list;
    Queue<TreeNode> queue = new LinkedList();
    queue.offer(root);
    while (!queue.isEmpty()){
        int levelsize = queue.size();
        List<TreeNode> temp = new ArrayList<>();
        for(int i =0;i<levelsize;i++){
            TreeNode currentTreeNode = queue.poll();
            temp.add(currentTreeNode);
            if(currentTreeNode.left!=null){
                queue.offer(currentTreeNode.left);

            }
            if(currentTreeNode.right!=null){
                queue.offer(currentTreeNode.right);
            }

        }
        list.add(temp);
    }
    return list;
}
public void pre(){
        pre(this.root);
}

    private void pre(TreeNode root) {
        if(root==null)return;

        pre(root.left);
        System.out.println(root.val);
        pre(root.right);

    }

    public TreeNode Levelorder(int target){
        return Levelorder(this.root,target);
}

    private TreeNode Levelorder(TreeNode treeNode, int target) {
        Queue<TreeNode> list = new LinkedList<>();
        list.add(treeNode);
        while (!list.isEmpty()){
            TreeNode currentTreeNode = list.poll();
            if(currentTreeNode.left!=null){
                list.offer(treeNode.left);
            }
            if(currentTreeNode.right!=null){
                list.offer(treeNode.right);

            }
            if(currentTreeNode.val ==target){
               break;
            }
            list.poll();

        }
      return list.peek();
    }
    public List<List<Integer>> zigzagLevelOrder(){
        return zigzagLevelOrder(this.root);
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList();
        deque.add(root);
        boolean reverse=false;
        while (!deque.isEmpty()){
            List<Integer> list = new ArrayList<>();
              int level = deque.size()  ;
              for(int i =0;i<level;i++){
                  if(!reverse){
                      TreeNode currentTreeNode = deque.pollFirst();
                      list.add(currentTreeNode.val);
                      if(currentTreeNode.left!=null){
                          deque.addLast(currentTreeNode.left);
                      }
                      if(currentTreeNode.right!=null){
                          deque.addLast(currentTreeNode.right);
                      }
                  }else{
                      TreeNode currentTreeNode = deque.pollLast();
                      list.add(currentTreeNode.val);

                      if(currentTreeNode.right!=null){
                          deque.offerFirst(currentTreeNode.right);
                      }
                      if(currentTreeNode.left!=null){
                          deque.offerFirst(currentTreeNode.left);
                      }
                  }

              }
            reverse=!reverse;
            result.add(list);

        }
        return result;
    }
    int diameter =0;
    public int diameterOfBinaryTree(TreeNode root) {
      heightleetcode(root);
      return diameter+2;
    }

    private int heightleetcode(TreeNode node) {
        if(node==null){
            return -1;
        }
        int leftheigt =heightleetcode(node.left);
        int rightheigt = heightleetcode(node.right);
        node.height=Math.max(leftheigt,rightheigt)+1;
        int dia = leftheigt+rightheigt+1;
        diameter=Math.max(dia,diameter);
        return node.height;
    }
    public void flatten(TreeNode root) {
         TreeNode current= root;
         while(current!=null){
             if(current.left!=null){
                 TreeNode temp = current.left;
                 while (temp.right!=null){
                     temp=temp.right;
                 }
                 temp.right=current.right;
                 current.right=current.left;
                 current.left=null;
             }
             current=current.right;
         }
    }
    public boolean isValidBST(TreeNode root) {
       return helper1(root,null,null);
    }

    private boolean helper1(TreeNode root, Integer low, Integer high) {
        if(root==null){
            return true;
        }
        if(low!=null&&root.val<=low)return false;
        if(high!=null&&root.val>=high)return false;
        return (helper1(root.left,low,root.val))&&(helper1(root.right,root.val,high));
    }
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> list =new ArrayList<>();
        findelement(root,list);
        return list.get(k);
    }
    private ArrayList<Integer>findelement(TreeNode node,  ArrayList<Integer> list){
        if(node==null)return list;
        findelement(node.left,list);
        list.add(node.val);
        findelement(node.right,list);
        return list;
    }
    public  String serialize(){
        return serialize(this.root);
    }
    public String serialize(TreeNode root) {
         List<String> list   = new LinkedList<>();
         helper(root,list);
         StringBuffer str = new StringBuffer();
         for(String s:list){
             str.append(s);
         }
  return str.toString();
    }

    private void helper(TreeNode root, List<String> list) {
        if(root==null){
            list.add(null+" ");
            return;
        }
        list.add(String.valueOf(root.val)+" ");
        helper(root.left,list);
        helper(root.right,list);
    }


    // Decodes your encoded data to tree.
    public boolean hasPathSum(TreeNode root, int targetSum) {
        int count = 0;
        return helper(root,targetSum,count);
    }

    private boolean helper(TreeNode root, int targetSum,int count ) {
        if(root==null)return true;
        if(count==targetSum)return true;
        count+=root.val;
        helper(root.left,targetSum,count);
        count-=root.val;
        helper(root.right,targetSum,count);
        return false;
    }
public  ArrayList<StringBuilder> sumNumbers(){
       ArrayList<StringBuilder> list =  sumNumbers(this.root);
       return list;
}
    public ArrayList<StringBuilder> sumNumbers(TreeNode root) {
        ArrayList<StringBuilder> list = new ArrayList<>();
        StringBuilder s = new StringBuilder("");
        helpersum(root,list,s);
       return list;
    }

    private ArrayList<StringBuilder> helpersum(TreeNode root, ArrayList<StringBuilder> list,StringBuilder s) {
        if(root==null)return list;
        s.append(root.val);
        if(root.left==null&&root.right==null){
            list.add(s);
           return list;
        }
        helpersum(root.left,list,s);

        helpersum(root.right,list,s);
      s=new StringBuilder(s.substring(0,s.length()-2));
      return list;
    }

    int ans=Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

      helper(root);
      return  ans;
    }

    private int helper(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left= helper(root.left);


        int right = helper(root.right);
        left=Math.max(0,left);
        right=Math.max(0,right);
        int pathsum = left+right+root.val;
        ans = Math.max(ans,pathsum);
        return Math.max(left,right)+root.val;
    }
    public TreeNode bstToGst(TreeNode root) {
     return HelperBstToGst(root,0);
    }

    private TreeNode HelperBstToGst(TreeNode root,int value) {
        if(root==null){
            return null;
        }
        TreeNode right =HelperBstToGst(root.right,value);
        root.val+=value;

        TreeNode left=HelperBstToGst(root.left,root.val);
        root.left=left;
        root.right=right;
        return root;

    }
    int index=0;
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        helper(root,list);
        list.sort((a,b)->a-b);
return null;
    }
    public TreeNode createBinaryTree(int[][] descriptions) {
     HashMap<Integer,TreeNode> map = new HashMap<>();
     Set<Integer> childs = new HashSet<>();
     for(int[] i:descriptions){
         int parent =i[0];
         int child=i[1];
         int left=i[2];
         if(!map.containsKey(parent)){
             TreeNode p = new TreeNode(parent);
             map.put(parent,p);
         }
         if(!map.containsKey(child)){
             TreeNode ch = new TreeNode(child);
             map.put(child,ch);
         }
         if(left==1){
             map.get(parent).left=map.get(child);
         }else {
             map.get(parent).right=map.get(child);
         }
         childs.add(child);
     }
     for(int[]i:descriptions){
         int parent = i[0];
         if(!childs.contains(parent)){
             return map.get(parent);
         }
     }
     return null;

    }
    public boolean findPath(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return false;
        }

        if (root.val == value) {
            return true;
        }

        // Check left subtree
        if (findPath(root.left, value, path)) {
            path.append("L");
            return true;
        }

        // Check right subtree
        if (findPath(root.right, value, path)) {
            path.append("R");
            return true;
        }

        return false;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
       
    }
    private void helper(TreeNode root, ArrayList<Integer> list) {
        if(root==null)return;
        list.add(root.val);
        helper(root.left,list);
        helper(root.right,list  );
    }

}


