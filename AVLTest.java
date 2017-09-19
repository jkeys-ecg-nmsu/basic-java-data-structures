

public class AVLTest {

    public static void main(String[] args) {
        testInsertRemoveCase1();
        testInsertRemoveCase2();
        testOther();
    }

    private static void testInsertRemoveCase1(){
        System.out.println("******** testInsertRemoveCase1 *************");

        AVL avltree = new AVL();
        avltree.inOrderTraversal();System.out.println();

        //normal insertion
        avltree.insert(44); System.out.println("insert 44 "); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        avltree.insert(17); System.out.println("insert 17 "); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        avltree.insert(78); System.out.println("insert 78 "); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        avltree.insert(50); System.out.println("insert 50 "); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        avltree.insert(88); System.out.println("insert 88 "); avltree.inOrderTraversal();System.out.println("\n-------------\n");

        //insertion, case 1, single rotation
        avltree.insert(80); System.out.println("insert 80, Balance the right sub-tree, case 1");avltree.inOrderTraversal();System.out.println();

        boolean rc = avltree.remove(44); System.out.println("remove 44, rc="+rc);avltree.inOrderTraversal();System.out.println("\n-------------\n");
        rc = avltree.remove(17); System.out.println("remove 17, rc="+rc);avltree.inOrderTraversal();System.out.println("\n-------------\n");

        //Remove case 3,double rotation
        rc = avltree.remove(50); System.out.println("remove 50, rc="+rc+", Balance the right sub-tree, case 3"); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        rc = avltree.remove(50); System.out.println("remove 50, rc="+rc+", not exist"); avltree.inOrderTraversal();System.out.println("\n-------------\n");

        System.out.println("In-order traversal results.");
        System.out.println(avltree.inOrderStr());
    }

    private static void testInsertRemoveCase2(){
        System.out.println("******** testInsertRemoveCase2 *************");
        AVL avltree = new AVL();
        avltree.inOrderTraversal();System.out.println();

        //normal insertion
        avltree.insert(44); System.out.println("insert 44 ");  avltree.inOrderTraversal();System.out.println();
        avltree.insert(17); System.out.println("insert 17 ");  avltree.inOrderTraversal();System.out.println();
        avltree.insert(78); System.out.println("insert 78 ");  avltree.inOrderTraversal();System.out.println();
        avltree.insert(15); System.out.println("insert 15 ");  avltree.inOrderTraversal();System.out.println();
        avltree.insert(32); System.out.println("insert 32 ");  avltree.inOrderTraversal();System.out.println();

        //insertion, balance case 2
        avltree.insert(14); System.out.println("insert 14, Balance the left sub-tree, case 2");  avltree.inOrderTraversal();System.out.println();
        boolean rc= avltree.remove(14); System.out.println("remove 14, rc="+rc);avltree.inOrderTraversal();System.out.println("\n-------------\n");
        rc= avltree.remove(32); System.out.println("remove 32, rc="+rc);avltree.inOrderTraversal();System.out.println("\n-------------\n");

        //remove, balance case 2
        rc= avltree.remove(15); System.out.println("remove 15, Case 2 (balance the right sub-tree), rc="+rc );avltree.inOrderTraversal();System.out.println("\n-------------\n");

        System.out.println("In-order traversal results.");
        System.out.println(avltree.inOrderStr());
    }



    private static void testOther(){
        AVL avltree = new AVL();
        avltree.inOrderTraversal();System.out.println();

        avltree.insert(44); System.out.println("insert 44 "); avltree.inOrderTraversal(); System.out.println();
        avltree.insert(17); System.out.println("insert 17 "); avltree.inOrderTraversal(); System.out.println();
        avltree.insert(78); System.out.println("insert 78 "); avltree.inOrderTraversal(); System.out.println();
        avltree.insert(15); System.out.println("insert 15 "); avltree.inOrderTraversal(); System.out.println();
        avltree.insert(32); System.out.println("insert 32 "); avltree.inOrderTraversal(); System.out.println();
        avltree.insert(18); System.out.println("insert 18 "); avltree.inOrderTraversal(); System.out.println();
        avltree.insert(33); System.out.println("insert 33 "); avltree.inOrderTraversal(); System.out.println();
        avltree.insert(48); System.out.println("insert 48 "); avltree.inOrderTraversal();System.out.println();
        avltree.insert(62); System.out.println("insert 62 "); avltree.inOrderTraversal();System.out.println();
        avltree.insert(92); System.out.println("insert 92 "); avltree.inOrderTraversal();System.out.println();
        avltree.insert(45); System.out.println("insert 45 "); avltree.inOrderTraversal();System.out.println();
        avltree.insert(49); System.out.println("insert 49 "); avltree.inOrderTraversal();System.out.println();
        avltree.insert(54); System.out.println("insert 54 "); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        avltree.insert(70); System.out.println("insert 70 "); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        //invoke complicated case 3
        System.out.println("insert 53...");
        avltree.insert(53); avltree.inOrderTraversal();System.out.println("\n-------------\n");
        System.out.println("insert 53...");
        avltree.insert(53); avltree.inOrderTraversal();System.out.println("\n-------------\n");

        System.out.println("avltree.countOccurrences(53) = "+ avltree.countOccurrences(53));
        System.out.println("avltree.countOccurrences(15) = "+ avltree.countOccurrences(15));

        System.out.println("In-order traversal results.");
        System.out.println(avltree.inOrderStr());

        System.out.println("Pre-order traversal recursively.");
        avltree.preOrderPrtRecursive(); System.out.println("\n");

        System.out.println("Pre-order traversal non-recursively.");
        avltree.preOrderPrtNonRecursive(); System.out.println("\n");
    }
}