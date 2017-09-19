import java.util.Stack;

/**
 * Tree Node for AVL tree
 *
 * @author Huiping Cao
 *
 */
class AVLNode{
    private int data; 	//the element value for this node
    private AVL left;	//the left child of this node
    private AVL right;	//the right child of this node
    private int height; //height of the tree rooted at this node

    public AVLNode()				{	data = 0; left = new AVL(); right = new AVL(); height = 0;}
    public AVLNode(int initData)	{	data = initData; left = new AVL();	right = new AVL();	height = 0;}

    /**
     * Constructor with the initial element, initial left and right children
     * @param initData: the initial element
     * @param initLeft: left child
     * @param initRight: right child
     */
    public AVLNode(int initData, AVL initLeft, AVL initRight){
        data = initData;
        left = initLeft;
        right = initRight;
        height = 1;
    }



    public int getData()	{ return data; }
    public AVL getLeft() 	{ return left; }
    public AVL getRight() 	{ return right; }
    public int getHeight()	{ return height;}
    public void setData(int data)	{this.data = data;}
    public void setLeft(AVL left)	{ this.left = left;}
    public void setRight(AVL right)	{ this.right = right;}

    /**
     * Set the height of the tree rooted at this node
     */
    public void setHeight()
    {
        this.height = 1+Math.max(getLeftHeight(), getRightHeight());
    }


    /**
     * Convert this BTNode to a string
     */
    public String toString()
    {
        String str="";
        if(left==null) str +="(null)";
        else str +="("+left.getRoot().getData()+")";

        str += data;

        if(right==null) str +="(null)";
        else str +="("+right.getRoot().getData()+")";

        return str;
    }


    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    //Rebalancing related methods

    /**
     * Get the height of the left subtree
     */
    public int getLeftHeight(){
        if(left==null||left.getRoot()==null) 	return 0;
        else return left.getRoot().getHeight();
    }

    /**
     * Get the height of the right subtree
     * @return: the height of the right sub-tree
     */
    public int getRightHeight(){
        if(right==null||right.getRoot()==null) 	return 0;
        else return right.getRoot().getHeight();
    }

}


public class AVL {
    private AVLNode	root; //instance variable to denote the root of the AVL tree

    //Constructors for the AVL tree
    public AVL()		{root = null;}
    public AVL(int e)	{root = new AVLNode(e,new AVL(),new AVL());}

    //Basic set and get methods
    public AVLNode getRoot() 			{return root;}
    public void setRoot(AVLNode _root) 	{this.root = _root;}
    public boolean isEmpty()			{return (root==null);}

    private AVL getLeftSubtree()	{ return root.getLeft();	}
    private AVL getRightSubtree()	{ return root.getRight();	}
    private void setHeight()		{root.setHeight(); }

    /**
     * Check whether the tree (rooted at this node) is right heavy or not
     * @return
     */
    private boolean rightHeavy(){
        return (root.getLeftHeight() < root.getRightHeight());
    }

    /**
     * Check whether the tree (rooted at this node) is left heavy or not
     * @param node
     * @return
     */
    private boolean leftHeavy(){
        return (root.getLeftHeight() > root.getRightHeight());
    }

    /**
     * Check whether the tree (rooted at this node) is right too heavy or not
     * @return
     */
    private boolean rightTooHeavy(){
        return ((root.getLeftHeight()+1)< root.getRightHeight());
    }

    /**
     * Check whether the tree (rooted at this node) is left too heavy or not
     * @param node
     * @return
     */
    private boolean leftTooHeavy(){
        return (root.getLeftHeight() > (root.getRightHeight()+1));
    }


    /**
     * Traversal the tree in-order and print it
     */
    public void inOrderTraversal(){
        inOrderTraversal(0);
    }

    /**
     * Private function to print the tree with in-order traversal
     * @param indentation: the number of space before the data, to make the printing more beautiful
     */
    private void inOrderTraversal(int indentation){
        if(root!=null){
            if(root.getRight()!=null)root.getRight().inOrderTraversal(indentation+1);
            for(int i=0;i<indentation*2;i++)
                System.out.print(" ");
            System.out.println(root.getData());
            if(root.getLeft()!=null)root.getLeft().inOrderTraversal(indentation+1);
        }
    }

    public String inOrderStr()
    {
        if(root!=null)
            return  (root.getLeft().inOrderStr() + " " + root.getData() + " " + root.getRight().inOrderStr());
        else
            return "";
    }

    /**
     * Print the tree using pre-order traversal strategy recursively.
     */
    public void preOrderPrtRecursive(){
        if(root==null) return;

        System.out.print(root.getData()+" ");
        this.getLeftSubtree().preOrderPrtRecursive();
        this.getRightSubtree().preOrderPrtRecursive();
    }

    private void rotateLeft() {
        AVL treeB = new AVL();
        treeB.root = getRightSubtree().root;

        AVL T1 = getRightSubtree().getLeftSubtree();

        getRightSubtree().root = T1.root;

        treeB.getLeftSubtree().root = this.root;

        this.root = treeB.root;

        getLeftSubtree().setHeight();
        treeB.setHeight();
        treeB.getLeftSubtree().setHeight();
        this.setHeight();
    }

    private void rotateRight() {
        AVL b = new AVL();
        b.root = getLeftSubtree().root;
        getLeftSubtree().root = getLeftSubtree().getRightSubtree().root;

        b.getRightSubtree().root = this.root;

        this.root = b.root;

        getRightSubtree().setHeight();
        b.setHeight();
        b.getRightSubtree().setHeight();
        this.setHeight();
    }

    private void doubleRotateRight() {
        getRightSubtree().rotateRight();
        this.rotateLeft();
    }

    private void doubleRotateLeft() {
        getLeftSubtree().rotateLeft();
        this.rotateRight();
    }


    public void insert(int e) {
        if (root == null) {
            root = new AVLNode(e);
            root.setHeight();
            //this.rebalance();
            return;
        }
        if (e <= root.getData()) {
            //rebalance();
            getLeftSubtree().insert(e);
            setHeight();
            rebalance();
        }
        else {
            //rebalance();
            getRightSubtree().insert(e);
            setHeight();
            rebalance();
        }

    }

    private void rebalance() {
        if(root == null)
            return;

        //System.out.println("in rebalance, this.data: " + this.root.getData() + "\ngetLeftHeight: " + root.getLeftHeight() + " and getRightHeight: " + root.getRightHeight());

        if(leftTooHeavy()) {
            System.out.println("in rebalance, leftTooHeavy()");
            if(getLeftSubtree().rightHeavy())
                getLeftSubtree().rotateLeft();

            rotateRight();

        } else if(rightTooHeavy()) {
            System.out.println("in rebalance, rightTooHeavy()");
            if(getRightSubtree().leftHeavy())
                getRightSubtree().rotateRight();

            rotateLeft();
        }

        //getLeftSubtree().rebalance();
        //getRightSubtree().rebalance();

    }

    private void removeCurrentNode() {
        AVL left = getLeftSubtree();
        AVL right = getRightSubtree();

        if(left.root == null && right.root == null)
            this.root = null;
        else if (left.root == null && right.root != null)
            this.root = right.root;
        else if (left.root != null && right.root == null)
            this.root = left.root;
        else {
            AVL rover = left;

            while(rover.getRightSubtree().root != null) {
                rover = rover.getRightSubtree();
            }

            this.root = rover.root;
            this.root.setRight(right);
            this.setHeight();
            this.rebalance();
        }
    }

    public boolean remove(int e) {
        if(root == null) {
            return false;
        }

        if(e == root.getData()) {
            this.removeCurrentNode();
            return true;
        } else if (e < root.getData()) {
            boolean result = getLeftSubtree().remove(e);
            setHeight();
            rebalance();
            return result;
        } else {
            boolean result = getRightSubtree().remove(e);
            setHeight();
            rebalance();
            return result;
        }
    }

    public int countOccurrences(int e) {
        if(root == null) return 0;

        if(root.getData() == e) return 1 + getLeftSubtree().countOccurrences(e) + getRightSubtree().countOccurrences(e);
        else return 0 + getLeftSubtree().countOccurrences(e) + getRightSubtree().countOccurrences(e);
    }

    public void preOrderPrtNonRecursive() {
        if(root == null)
            return;

/*
        AVL roverPrev = this;
        AVL rover = roverPrev.getLeftSubtree();*/

        Stack<AVL> s = new Stack<>();

        s.push(this);

        while(!s.empty()) {
            AVL curr = s.pop();

            System.out.print(curr.getRoot().getData() + " ");
            //System.out.println("\ncurr.getRoot().getRightSub(): " + curr.getRoot().getRight());
            //System.out.println("\ncurr.getRoot().getLeftSub(): " + curr.getRoot().getLeft());

            if(curr.getRightSubtree().getRoot() != null)
                s.push(curr.getRightSubtree());

            if(curr.getLeftSubtree().getRoot() != null)
                s.push(curr.getLeftSubtree());


        }
    }
}
