// Jacky Chow 113268425
import java.util.*;
public class CSE214BinaryTree<T> implements CSE214Tree<T>{
    class TreeNode {
        public T val;

        public TreeNode left;
        public TreeNode right;

        public TreeNode(T val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    TreeNode root;

    public CSE214BinaryTree() {
        root = null;
    }
    public CSE214BinaryTree(T... args) {
        for(T t: args){
            add(root, t);
            }
        }

    public void add(TreeNode temp, T arg)
    {
        if (temp == null) {
            root = new TreeNode(arg);
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(temp);

        while (!queue.isEmpty()) {
            temp = queue.peek();
            queue.remove();

            if (temp.left == null) {
                temp.left = new TreeNode(arg);
                break;
            }
            else
                queue.add(temp.left);

            if (temp.right == null) {
                temp.right = new TreeNode(arg);
                break;
            }
            else
                queue.add(temp.right);
        }
    }

    public String preorder() {
        return preorderHelper(root).substring(0, preorderHelper(root).length()-2);
    }
    private String preorderHelper(TreeNode r) {
        // root left right
        if(r == null){
            return "";
        }
        String result = "";
        result += r.val + ", ";
        if (r.left != null){
            result += preorderHelper(r.left);
        }
        if (r.right != null) {
            result += preorderHelper(r.right);
        }
        return result;
    }
    public String postorder() {
        return postorderHelper(root).substring(0, postorderHelper(root).length()-2);
    }
    private String postorderHelper(TreeNode r) {
        //left right root
        if(r == null){
            return "";
        }
        String result = "";
        if (r.left != null){
            result += postorderHelper(r.left);
        }
        if (r.right != null) {
            result += postorderHelper(r.right);
        }
        result += r.val+ ", ";
        return result;
    }
    public String inorder() {
        return inorderHelper(root).substring(0, inorderHelper(root).length()-2);
    }
    private String inorderHelper(TreeNode r) {
        //left right root
        if(r == null){
            return "";
        }
        String result = "";
        if (r.left != null){
            result += inorderHelper(r.left);
        }
        result += r.val + ", ";
        if (r.right != null) {
            result += inorderHelper(r.right);
        }
        return result;
    }

    public int numNodes() {
        return numNodesHelper(root);
    }
    private int numNodesHelper(TreeNode root) {
        if(root == null) {
            return 0; }
        return numNodesHelper(root.left) + numNodesHelper(root.right) + 1;
    }

    public int depth() {
        return depthHelper(root);
    }
    private  int depthHelper(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = depthHelper(root.left);
        int rightDepth = depthHelper(root.right);

        if (leftDepth > rightDepth)
            return (leftDepth + 1);
        else
            return (rightDepth + 1);

        }

    public static void main(String[] args) {
        CSE214BinaryTree<String> tree1 = new CSE214BinaryTree<>("1", "2", "3", "4", "5", "6", "7");
        System.out.println(tree1.inorder());
        System.out.println(tree1.preorder());
        System.out.println(tree1.postorder());
        System.out.println(tree1.numNodes());
        System.out.println(tree1.depth());


    }
}
