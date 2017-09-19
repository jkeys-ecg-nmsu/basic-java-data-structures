/**
 * Created by jkeys on 11/3/2015.
 */
public class BSTest {


    /**
     * Test cases provided by the instructor
     * @throws Exception
     */
    private static void test1() throws Exception{
        BST tree = new BST();
        System.out.println("Initial tree:");
        tree.inOrderPrt(); //test inOrder traversal

        boolean rc = true;

        //test 1: insert method, and test 2 in-order traversal
        rc = tree.insert(12); System.out.println("\nInsert 12, inserted="+rc+", after adding 12:"); tree.inOrderPrt();
        rc = tree.insert(6);  System.out.println("\nInsert 6, inserted="+rc+", after adding 6:"); tree.inOrderPrt();
        rc = tree.insert(19); System.out.println("\nInsert 19, inserted="+rc+", after adding 19:"); tree.inOrderPrt();
        rc = tree.insert(4);  System.out.println("\nInsert 4, inserted="+rc+", after adding 4:");
        tree.inOrderPrt();
        rc = tree.insert(8);  System.out.println("\nInsert 8, inserted="+rc+", after adding 8:"); tree.inOrderPrt();
        rc = tree.insert(16); System.out.println("\nInsert 16, inserted="+rc + ", after adding 16:");
        tree.inOrderPrt();
        rc = tree.insert(8);
        System.out.println("\nInsert 8 (second), inserted="+rc+", after adding 8:"); tree.inOrderPrt();
        rc = tree.insert(5);
        System.out.println("\nInsert 5, inserted=" + rc + ", after adding 5:"); tree.inOrderPrt();
        rc = tree.insert(9);  System.out.println("\nInsert 9, inserted="+rc+", after adding 9:"); tree.inOrderPrt();
        rc = tree.insert(20);  System.out.println("\nInsert 20, inserted="+rc+", after adding 20:"); tree.inOrderPrt();

        System.out.println("Inorder traversal results:");
        tree.inOrderPrt();
        System.out.print("\n\n");

        //test 3: search method
        BSTNode node = tree.searchRecursion(6);
        System.out.println("searchRecursion 6, node="+node);
        node = tree.searchRecursion(22);
        System.out.println("searchRecursion 22, node="+node);
        node = tree.searchRecursion(8);
        System.out.println("searchRecursion 8, node="+node+"\n");

        node = tree.searchNonRecursion(6);
        System.out.println("searchNonRecursion 6, node="+node);
        node = tree.searchNonRecursion(22);
        System.out.println("searchNonRecursion 22, node="+node);
        node = tree.searchNonRecursion(8);
        System.out.println("searchNonRecursion 8, node="+node);

        //test 4: remove method
        rc = tree.remove(30); //test case 0: e does not exist
        System.out.println("\nRemove 30, rc="+rc);
        tree.inOrderPrt();

        rc = tree.remove(20); //test case 1: leaf
        System.out.println("\nRemove 20, rc="+rc);
        tree.inOrderPrt();

        rc = tree.remove(4); //test case 2: left is empty
        System.out.println("\nRemove 4, rc="+rc);
        tree.inOrderPrt();


        rc = tree.remove(19); //test case 3: right is empty
        System.out.println("\nRemove 19, rc="+rc);
        tree.inOrderPrt();

        rc = tree.remove(6); //test case 4: no subtree is empty
        System.out.println("\nRemove 6, rc="+rc);
        tree.inOrderPrt();

        rc = tree.remove(12); //more tests: remove root
        System.out.println("\nRemove 12, rc="+rc);
        tree.inOrderPrt();

        rc = tree.remove(8); //more tests
        System.out.println("\nRemove 8, rc="+rc);
        tree.inOrderPrt();

        rc = tree.remove(5); //more tests
        System.out.println("\nRemove 5, rc="+rc);
        tree.inOrderPrt();

        rc = tree.remove(8); //more tests
        System.out.println("\nRemove 8, rc="+rc);
        tree.inOrderPrt();
        rc = tree.remove(16); //more tests
        System.out.println("\nRemove 16, rc="+rc);
        tree.inOrderPrt();
        System.out.print("sum="+tree.sum()+"\n");

        System.out.println("\nAdding a series of numbers:");
        tree.insert(30);
        tree.insert(20);tree.insert(10);tree.insert(25);tree.insert(28);tree.insert(24);
        tree.insert(11);tree.insert(5);tree.insert(11);tree.insert(12);
        tree.insert(50);tree.insert(40);tree.insert(35);
        tree.inOrderPrt();
        System.out.print("sum="+tree.sum()+"\n");

        System.out.print("sum="+tree.sum()+"\n");
        System.out.print("\n\n");

        System.out.println("\nRemove 20 (removeNode case 4):");
        tree.remove(20);
        tree.inOrderPrt();
        System.out.print("sum="+tree.sum()+"\n");

        System.out.println("Inorder traversal results: ");
        tree.inOrderPrt();
        System.out.print("\n");
        System.out.print("sum="+tree.sum()+"\n");
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //You need to let your program pass the  test cases in this function
        test1();

        //You can add your own test cases here.

        System.out.println("\n\n====my tests====\n");


        BST bst1 = new BST();

        bst1.insert(5);
        System.out.println("after first insert");
        bst1.insert(4);
        bst1.insert(6);
        bst1.insert(3);
        bst1.insert(7);
        bst1.insert(2);
        bst1.insert(8);

        bst1.inOrderPrt();

        BST bst2 = new BST();

        bst2.insert(50);
        bst2.insert(25);
        bst2.insert(37);
        bst2.insert(27);
        bst2.insert(100);
        bst2.insert(100); //no effect

        bst2.inOrderPrt();

        System.out.println(bst2.searchRecursion(37)); //found 37
        System.out.println(bst2.searchRecursion(36)); //not found, so just null
        System.out.println(bst2.searchRecursion(100)); //found 100


        System.out.println(bst2.searchNonRecursion(37)); //found 37
        System.out.println(bst2.searchNonRecursion(36)); //not found, so just null
        System.out.println(bst2.searchNonRecursion(100)); //found 100

        bst1.remove(2);
        bst1.remove(6);
        bst1.remove(-5); //no effect
        bst1.inOrderPrt();

        System.out.println(bst1.sum());
        System.out.println(bst2.sum());

        BST bst3 = new BST();
        bst3.insert(500);
        bst3.insert(250);
        bst3.insert(125);
        bst3.insert(375);
        bst3.insert(750);
        bst3.insert(875);
        bst3.insert(625);

        bst3.inOrderPrt();

        System.out.println("\n\n===Remove 500 from BST===\n");

        bst3.remove(500);
        //bst3.remove(250);

        bst3.inOrderPrt();


        System.out.println("\n\n===Insert 300 from BST===\n");

        bst3.insert(300);

        bst3.inOrderPrt();

    }
}
