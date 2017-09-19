


/**
 *  Node for Binary Tree
 *
 * @author Huiping Cao
 */
class BSTNode{
    private int data;   //the element value for this node
    private BST left;	//the left child of this node
    private BST right;	//the right child of this node

    /**
     * No-argument constructor
     */
    public BSTNode(){}

    /**
     * Constructor with the initial element
     * @param initData: the initial element
     */
    public BSTNode(int initData){
        data = initData;
        left = new BST();
        right = new BST();
    }

    /**
     * Constructor with the initial element, initial left and right children
     * @param initData: the initial element
     * @param initParent: the initial parent tree
     * @param initLeft: left child
     * @param initRight: right child
     */
    public BSTNode(int initData, BST initLeft, BST initRight){
        data = initData;
        left = initLeft;
        right = initRight;
    }

    /**
     * Evaluate whether this node is a leaf node or not
     * @return true if it is a leaf node; otherwise, return false.
     */
    public boolean isLeaf()
    {
        return (((left==null)||(left!=null && left.root==null))
                &&((right==null)||(right!=null && right.root==null)));
    }


    /**
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * @param set the element in this node
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * @return the left child
     */
    public BST getLeft() {
        return left;
    }

    /**
     * @param the left child to be set
     */
    public void setLeft(BST left) {
        this.left = left;
    }

    /**
     * @return the right child
     */
    public BST getRight() {
        return right;
    }

    /**
     * @param the right child to be set
     */
    public void setRight(BST right) {
        this.right = right;
    }


    /**
     * Convert this BTNode to a string
     */
    public String toString()
    {
        String str="";

        if((left==null)||(left!=null && left.root==null)) str +="(null)";
        else str +="("+left.root.getData()+")";

        str += data;

        if((right==null)||(right!=null&&right.root==null)) str +="(null)";
        else str +="("+right.root.getData()+")";

        return str;
    }
}


/**
 * The class for Binary Search Tree
 * @author Huiping Cao
 *
 */
public class BST {
    protected BSTNode root; //the tree root


    /**
     * Get the left subtree of this tree
     *
     * @return the left subtree of this tree
     */
    private BST getLeftSubtree() {
        return root.getLeft();
    }

    /**
     * Get the right subtree of this tree
     *
     * @return the right subtree of this tree
     */
    private BST getRightSubtree() {
        return root.getRight();
    }

    /**
     * Print the tree using in-order traversal strategy
     */
    public void inOrderPrt() {
        inOrderPrt(0);
    }

    /**
     * private, recursive function
     * I slightly changed the method to print right subtree first
     * It is to make the display more easier to read
     *
     * @param node
     * @param indentation
     * @param branch
     */
    private void inOrderPrt(int indentation) {
        if (root != null) {
            if (getRightSubtree() != null) getRightSubtree().inOrderPrt(indentation + 1);

            for (int i = 0; i < indentation * 4; i++) System.out.print(" ");

            System.out.println(root.getData());

            if (getLeftSubtree() != null) getLeftSubtree().inOrderPrt(indentation + 1);
        }
    }


    /**
     * Debug function, print the tree for debugging purpose
     */
    public String toString() {
        if (root != null) return root.toString();
        else return null;
    }

    ///////////////////////////////////////
    ///////////////////////////////////////
    // Please add the functions required for your lab homework here.

    public BSTNode searchNonRecursion(int e) {
        if (root == null)
            return null;

        BST rover = this;

        while (rover.root != null) {
            if (e == rover.root.getData())
                return rover.root;
            else if (e < rover.root.getData())
                rover = rover.root.getLeft();
            else
                rover = rover.root.getRight();
        }

        return null;
    }

    public BSTNode searchRecursion(int e) {
        if (root == null) return null;

        if (e == root.getData())
            return root;
        else if (e < root.getData())
            return getLeftSubtree().searchRecursion(e);
        else
            return getRightSubtree().searchRecursion(e);
    }

    public boolean insert(int e) {
        if (searchRecursion(e) != null)
            return false;

        if (root == null) {
            root = new BSTNode(e);
            return true;
        }

        if (e == root.getData())
            return false;
        if (e < root.getData())
            return getLeftSubtree().insert(e);
        else
            return getRightSubtree().insert(e);
    }

    private int getMaxDataLeft() {
        BST roverPrev = this;
        BST rover = this.getLeftSubtree();
        int max = root.getData();

        while (rover.root != null) {
            max = rover.root.getData();
            roverPrev = rover;
            rover = rover.getRightSubtree();
        }

        roverPrev.root = null;
        return max;
    }

    public boolean remove(int e) {
        if (searchRecursion(e) == null)
            return false;

        BST rover = this;

        while (rover.root != null) {
            if (e == rover.root.getData())
                break;
            else if (e < rover.root.getData())
                rover = rover.getLeftSubtree();
            else
                rover = rover.getRightSubtree();
        }

        if (rover.getLeftSubtree().root == null && rover.getRightSubtree().root == null) {
            rover.root = null;
        } else if (rover.getLeftSubtree().root == null && rover.getRightSubtree().root != null) {
            rover.root = rover.getRightSubtree().root;
        } else if (rover.getLeftSubtree().root != null && rover.getRightSubtree().root == null) {
            rover.root = rover.getLeftSubtree().root;
        } else if (rover.getLeftSubtree().root != null && rover.getRightSubtree().root != null) {
            int maxDataLeft = rover.getMaxDataLeft();
            rover.root.setData(maxDataLeft);
        }

        return true;
    }

    public int sum() {
        if (root == null)
            return 0;

        return root.getData() + getLeftSubtree().sum() + getRightSubtree().sum();
    }
}