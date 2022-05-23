public class CSE214TreeSet<E extends Comparable<E>> extends BinaryTree<E> implements CSE214Set<E> {

    public String color;

    BinaryTree<E> root = new BinaryTree<>();

    public CSE214TreeSet() {
        super();
    }

    public CSE214TreeSet(E e) {
        super(e);
        root.setValue(e);
        this.color = "red";
    }


    @Override
    //check for size
    public int size() {
        BinaryTree<E> current = root;
        System.out.println("root left = " + root.getLeft().getValue());
        return numNodesHelper(current);
    }

    private int numNodesHelper(BinaryTree<E> root) {
        BinaryTree<E> current = root;

        if (current == null) {
            return 0;
        }
        return numNodesHelper(current.getLeft()) + numNodesHelper(current.getRight()) + 1;
    }

    @Override
    // check for contains element
    public boolean contains(E o) {
        BinaryTree<E> current = root;

        return containsHelper(o, current);
    }

    public boolean containsHelper(E o, BinaryTree<E> root) {
        if (root == null) return false;
        else if (root.getValue() == o) return true;
        else {
            return containsHelper(o, root.getLeft()) || containsHelper(o, root.getRight());
        }
    }

    @Override
    public boolean add(E e) {
        if (root == null)
            root = new BinaryTree<>(e);
        else {
            // prevent duplicate elements
            if (contains(e))
                return false;
            // Locate the parent node
            BinaryTree<E> parent = null;
            BinaryTree<E> current = root;
            while (current != null)
                if (e.compareTo(current.getValue()) < 0) {
                    parent = current;
                    current = current.getLeft();
                } else if (e.compareTo(current.getValue()) > 0) {
                    parent = current;
                    current = current.getRight();
                } else
                    return false; // Duplicate node not inserted
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.getValue()) < 0) {
                parent.setLeft(new BinaryTree<E>(e));
            } else {
                parent.setRight(new BinaryTree<E>(e));
            }
        }

        int bf = balanceFactor();
        if (bf < -1) {
            rightRotate();
        } else if (bf > 1) {
            leftRotate();
        }
        return true; // inserted successfully
    }

    public void leftRotate() {
        // BinaryTree<E> x = root;
        BinaryTree<E> y = root.getRight();
        root.setRight(y.getLeft());

        y.setLeft(root);
        root = y;
    }

    public void rightRotate() {
        BinaryTree<E> y = root.getLeft();
        root.setLeft(y.getRight());

        y.setRight(root);
        root = y;
    }

    public int balanceFactor() {
        BinaryTree<E> current = root;
        return depthHelper(root.getRight()) - depthHelper(root.getLeft());
    }

    private int depthHelper(BinaryTree<E> root) {
        if (root == null)
            return 0;
        int leftDepth = depthHelper(root.getLeft());
        int rightDepth = depthHelper(root.getRight());

        if (leftDepth > rightDepth)
            return (leftDepth + 1);
        else
            return (rightDepth + 1);

    }


    public void fixTree(BinaryTree<E> x) {
    }


    public String preorder() {
        BinaryTree<E> current = root;
        return preorderHelper(current).substring(0, preorderHelper(current).length() - 2);
    }

    private String preorderHelper(BinaryTree<E> root) {
        // root left right
        if (root == null) {
            return "";
        }
        String result = "";
        result += root.getValue() + ", ";
        if (root.getLeft() != null) {
            result += preorderHelper(root.getLeft());
        }
        if (root.getRight() != null) {
            result += preorderHelper(root.getRight());
        }
        return result;
    }

    public static void main(String[] args) {
        CSE214TreeSet<Integer> empty = new CSE214TreeSet<>();
        CSE214TreeSet<Integer> singleton = new CSE214TreeSet<>(5);
        // System.out.println(singleton.getColor());
        System.out.println(singleton.add(2));
        System.out.println(singleton.add(7));
        System.out.println(singleton.add(6));
        System.out.println(singleton.add(8));
        System.out.println(singleton.size());
        System.out.println(singleton.contains(7));
        System.out.println(singleton.contains(1));
        System.out.println(singleton.toString());


    }
}

