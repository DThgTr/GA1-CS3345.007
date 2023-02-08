//Thang Tran 
//tdt200004

import java.util.*;

public class BinTree <T extends Comparable<T>> {
    private Node<T> root;
    //constructor
    BinTree(Node<T> root) {
        this.root = root;
    }
    //auxiliary functions
    //return an in order list of binary tree's content, largest to smallest
    public ArrayList<T> GetContent() {
        ArrayList<T> content = new ArrayList<T>();
        getContent(root, content);
        return content; 
    }
    //get content body
    public void getContent(Node<T> curr, ArrayList<T> content) {
        if (curr == null)
            return;
        getContent(curr.getRight(), content);
        content.add(curr.getData());
        getContent(curr.getLeft(), content);
    }
    //==========================================================================
    //insert a generic item into the tree
    public void Insert(T key) {
        if (root != null)
            insert(key, root);
        else
            root = new Node<T>(key);
    }
    //insert function body
    public void insert(T key, Node<T> curr) {
        if (curr != null) {
            if (key.compareTo(curr.getData()) < 0) {
                if (curr.getLeft() == null)
                    curr.setLeft(new Node<T>(key));
                else
                    insert(key, curr.getLeft());
            }
            else if (key.compareTo(curr.getData()) > 0){
                if (curr.getRight() == null)
                    curr.setRight(new Node<T>(key));
                else
                    insert(key, curr.getRight());
            }
        }
    }
    //==========================================================================
    //search and return confirmation of a particular item's existence in the tree
    public T Search(T key) {
        return search(key, root);
    }
    //seach function body
    public T search(T key, Node<T> curr) {
        if (curr != null) {
           if (key.compareTo(curr.getData()) < 0)
                return search(key, curr.getLeft());
    	    else if (key.compareTo(curr.getData()) > 0)
    	        return search(key, curr.getRight());
    	    else
    	        return curr.getData();
        }
        return null;
    }
    //==========================================================================
    //remove an element from the binary tree
    public void Remove(T key) {
        root = remove(key, root);
    }
    //find the max value of the right branch from a specific node
    public Node<T> maxVal(Node<T> curr) {
        if (curr.getRight() != null)
            maxVal(curr.getRight());
        return curr;
    }
    //Remove function body
    public Node<T> remove(T key, Node<T> curr) {
        if (curr == null)
            return null;
            
        if (key.compareTo(curr.getData()) < 0) {
            curr.setLeft(remove(key, curr.getLeft()));
        }
        else if (key.compareTo(curr.getData()) > 0) {
            curr.setRight(remove(key, curr.getRight()));
        }
        //if found node to be deleted
        else {
            //if node to be deleted only has 1 or no childs
            if (curr.getLeft() == null)
                return curr.getRight();
            else if (curr.getRight() == null)
                return curr.getLeft();
                
            //if node to be deleted has both child
            //find maximum node of left tree
            Node<T> maxLeft = maxVal(curr.getLeft());
            //replace node to be deleted with maximum node on left branch
            curr.setData(maxLeft.getData());
            //remove maximum node on the left branch
            curr.setLeft(remove(maxLeft.getData(), curr.getLeft()));
        }
        return curr;
    }
}