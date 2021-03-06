//// File BinarySearchTreeMain.java from the package edu.bostonuniversity.projects
//
//package edu.bostonuniversity.projects;
//
//import edu.bostonuniversity.nodes.BTNode;
//import edu.bu.met.cs342a1.TextParser;
//
///**********************************************************************************************************************
// * BinarySearchTreeMain initializes a Binary Search Tree object that is used to read in a body of text, insert the text
// * into a Binary Search Tree, and answer a series of questions about the body of text.
// *
// * @author mlewis
// * @version Dec 7, 2019
// *********************************************************************************************************************/
//
//public class BinarySearchTreeMain {
//    // Invariant of the BinarySearchTreeMain class.
//    //  1. The instance variable FILE_NAME is an absolute pathname to a text file on your local system.
//    //  2. The instance variable binarySearchTree is a binarySearchTree object used to build a Binary Search Tree
//    //     the text from the text stored in FILE_NAME.
//    //  3. The instance variable file is a TextParser object used to read in a text file stored at FILE_NAME.
//    private static final String FILE_NAME = "/Users/mlewis/Downloads/pg345 (1).txt";
//    private BinarySearchTree<String> binarySearchTree;
//    //private TextParser file;
//
//    /**
//     * public void buildTree()
//     * Helper method that builds a binary search tree from the text stored in the specified file.
//     * @postcondition
//     *  A binary search tree has been built using the text stored in the specified file.
//     */
//    private void buildTree() {
//        binarySearchTree = new BinarySearchTree<>();
//        binarySearchTree.parse(file);
//        query();
//    }
//
//    /**
//     * public static void main(String[] args)
//     * The main entry point for the BinarySearchTreeMain.java class
//     * @param args
//     *  The required signature for Java's main method.
//     */
//    public static void main(String[] args) {
//        BinarySearchTreeMain start = new BinarySearchTreeMain();
//        start.openFile();
//    }
//
//    /**
//     * public void openFile()
//     * Helper method to open a file from a specified absolute path.
//     * @postcondition
//     *  If the file exists, then it has been opened.
//     */
//    private void openFile() {
//        file = new TextParser();
//        boolean fileExists = file.openFile(FILE_NAME);
//        if (fileExists) { buildTree(); }
//    }
//
//    /**
//     * public void query()
//     * Helper method to query the Binary Search Tree and programmatically answer a series of questions.
//     * @postcondition
//     *  All questions have programmatically been answered and written to the terminal using System.out.println().
//     */
//    private void query() {
//        int answer;
//
//        System.out.println("\n----------BEGIN TEXT ANALYSIS----------\n");
//
//        System.out.println("Text contains " + binarySearchTree.getCount() + " total words.");
//
//        answer = binarySearchTree.countOccurrences("transylvania");
//        System.out.println("transylvania occurs: " + answer + " times");
//
//        answer = binarySearchTree.countOccurrences("harker");
//        System.out.println("harker occurs: " + answer + " times");
//
//        answer = binarySearchTree.countOccurrences("renfield");
//        System.out.println("renfield occurs: " + answer + " times");
//
//        answer = binarySearchTree.countOccurrences("vampire");
//        System.out.println("vampire occurs: " + answer + " times");
//
//        answer = binarySearchTree.countOccurrences("expostulate");
//        System.out.println("expostulate occurs: " + answer + " times");
//
//        answer = binarySearchTree.countOccurrences("fang");
//        System.out.println("fang occurs: " + answer + " times");
//
//        System.out.println("Tree is: " + BTNode.treeDepth(binarySearchTree.getRoot()) + " nodes deep");
//
//        System.out.println("Tree contains: " + binarySearchTree.size() + " distinct words");
//
//        System.out.println("Word at root is: " + binarySearchTree.getRoot().getData());
//
//        binarySearchTree.setDepth(0);
//        System.out.print("Deepest word(s) is/are: ");
//        binarySearchTree.getDeepestLeaves(binarySearchTree.getRoot());
//
//        System.out.println();
//        System.out.println("Total word count: " + binarySearchTree.getCount());
//
//        answer = binarySearchTree.countOccurrences(binarySearchTree.getMostFrequent());
//        System.out.println("Most frequent is: \"" + binarySearchTree.getMostFrequent() + "\" occurring " + answer +
//                " times");
//
//        binarySearchTree.setDepth(0);
//        System.out.print("First 20 words pre-order: ");
//        binarySearchTree.preorderTraversal(binarySearchTree.getRoot());
//
//        binarySearchTree.setDepth(0);
//        System.out.println();
//        System.out.print("First 20 words post-order: ");
//        binarySearchTree.postorderTraversal(binarySearchTree.getRoot());
//
//        binarySearchTree.setDepth(0);
//        System.out.println();
//        System.out.print("First 20 words in-order: ");
//        binarySearchTree.inorderTraversal(binarySearchTree.getRoot());
//
//        System.out.println("\n\n----------END TEXT ANALYSIS----------");
//    }
//}
