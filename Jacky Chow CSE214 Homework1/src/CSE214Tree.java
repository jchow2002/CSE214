public interface CSE214Tree<T> {
    String preorder();
    String postorder();
    String inorder();
    int numNodes();
    int depth(); // a tree with only the root node returns 0; empty tree returns -1
}